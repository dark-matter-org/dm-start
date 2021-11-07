package com.connectifex.dmstart;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

import org.dmd.dmc.DmcNameClashException;
import org.dmd.dmc.DmcValueException;
import org.dmd.dmc.DmcValueExceptionSet;
import org.dmd.dmc.rules.DmcRuleExceptionSet;
import org.dmd.util.codegen.Manipulator;
import org.dmd.util.exceptions.ResultException;

/**
 * The DSLBootstrap class will prompt the user for some initial input such as a 
 * package name and abbreviation for a new domain specific language and then
 * generate the dark-matter infrastructure required to get them started.
 */
public class DSLBootstrap {
	
	private final static String SRC_MAIN_JAVA = "src/main/java";
	private final static String POM = "pom.xml";

	String runningDir;
	String workspace;
	String project;
	
	TreeMap<String,ProjectInfo>	projectsByName = new TreeMap<String, DSLBootstrap.ProjectInfo>();
	TreeMap<Integer,ProjectInfo>	projectsByNumber = new TreeMap<Integer, DSLBootstrap.ProjectInfo>();
	
    BufferedReader  in = new BufferedReader(new InputStreamReader(System.in));
    
    // Where we're creating the DSL
    ProjectInfo	targetProject;
    
    // The folder where the java will be generated, generally src or src/main/java
    String		targetSourceFolder;
    
    // The package for the DSL
    String		targetPackage;
    
    // The folder location for the project
    String		targetFolder;
    
    // The file extension and/or abbreviation of the DSL name - this is all lowercase
    String		targetDslAbbrev;
    
    // This is the same as the abbrev except that the first letter is capitalized
    String		targetModuleName;
    
    

	public DSLBootstrap(String[] args) throws IOException {
		initialize(args);
	}
	
	public void run() throws IOException, ResultException, DmcValueException, DmcValueExceptionSet, DmcNameClashException, DmcRuleExceptionSet{
		if (findProjectAndSrc(runningDir)){
			findOtherProjects();
			gatherInput();
			
			DSLArtifactGenerator generator = new DSLArtifactGenerator();
			generator.generateDSD(workspace, targetProject.name, targetSourceFolder, targetPackage, targetFolder, targetDslAbbrev, targetModuleName);
			
			String pomFN = workspace + "/" + targetProject.name + "/" + POM;
			POMAdjuster.adjust(pomFN, targetPackage, targetDslAbbrev, generator.utilMainClass());
			
			System.out.println();
			System.out.println("Please right click the dm-start project and select 'Refresh'");
			System.out.println();
			System.out.println("See the project's README for further instructions...\n\n");
		}
		else{
			System.err.println("Not running in an Eclipse project");
		}
	}

	void initialize(String[] args) throws IOException {
        File curr = new File(".");
        runningDir = curr.getCanonicalPath();
		
		int lastSlash = runningDir.lastIndexOf(File.separator);
		workspace = runningDir.substring(0,lastSlash);
		
		project = runningDir.substring(lastSlash + 1);
	}
	
	boolean findProjectAndSrc(String dir){
		boolean	haveProject = false;
		boolean haveSrc = false;
		
		File rd = new File(dir);
		
		File[] files = rd.listFiles();
		for(int i=0; i<files.length; i++){
			if (files[i].getName().equals(".project")){
				haveProject = true;
			}
			if (files[i].getName().equals("src")){
				if (files[i].isDirectory())
				 haveSrc = true;
			}
		}
		
		return(haveProject && haveSrc);
	}
	
	enum State {
		getWorkspaceConfirmation,
		getProject,
		getJavaSrcFolder,
		getPackage,
		getDslAbbrev
	}
	
	void gatherInput() throws IOException{
		String input = null;
        State state = State.getWorkspaceConfirmation;
        
        if (projectsByName.size() == 1) {
        	// No need to prompt for project
        	targetProject = projectsByNumber.get(1);
        	state = State.getJavaSrcFolder;
        }
        else {
        	state = State.getProject;
        }
        
        boolean done = false;

		System.out.println("You are running in the " + workspace + " workspace, that's where we'll create your DSL...\n");

		while(!done){
        	switch(state){
        	case getProject:
        		System.out.println("Please specify the number of the project where you want to create the DSL: \n");
        		showProjects();
        		input = getResponse();
        		int pnum;
        		try{
        			pnum = Integer.decode(input);
        		}
        		catch(NumberFormatException ex){
        			System.err.println("Not a valid number: " + input);
        			continue;
        		}
        		if ( (pnum < 1) || (pnum > projectsByName.size()) ){
        			System.err.println("Not a valid number: " + input);
        			continue;
        		}
        		
        		targetProject = projectsByNumber.get(pnum);
        		
        		System.out.println("We'll create the DSL in: " + targetProject.name + "\n");
        		state = State.getJavaSrcFolder;
        		break;
        	case getJavaSrcFolder:        		
        		input = SRC_MAIN_JAVA;

        		if (input.matches("[a-z][/a-z]*")){
    				targetSourceFolder = input;
    				
    				String testFolderName = workspace + File.separator + targetProject.name + File.separator + targetSourceFolder;
    				
    				File tf = new File(testFolderName);
    				if (!tf.exists()){
    					System.err.println("Sorry, the target java source folder DOESN'T EXIST: " + testFolderName);
    					continue;
    				}
    				System.out.println("Target java source folder: " + targetSourceFolder + "\n");
    				state = State.getPackage;
    			}
    			else{
    				System.err.println("Bad java source folder name: " + input + "\n");
    				continue;
    			}
        		
        		break;
        	case getPackage:
    			System.out.println("Specify a new java package in which to create the DSL. This MUST be a new package.");
    			System.out.println("We won't try to generate the DSL into an existing package, due to the risk of overwriting existing files.\n");
    			input = getResponse();
    			if (input.matches("[a-z][a-z0-9.]*")){
    				targetPackage = input;
    				
    				String folder = input.replaceAll("\\.", File.separator);
    				targetFolder = workspace + File.separator + targetProject.name + File.separator + targetSourceFolder + File.separator + folder;
    				
    				File tf = new File(targetFolder);
    				if (tf.exists()){
    					System.err.println("Sorry, the target folder already exists: " + targetFolder);
    					continue;
    				}
    				System.out.println("Target source folder: " + targetFolder + "\n");
    				state = State.getDslAbbrev;
    			}
    			else{
    				System.err.println("Bad package name: " + input + "\n");
    				continue;
    			}
        		break;
        	case getDslAbbrev:
    			System.out.println("Specify the abbreviation of your DSL. By convention, this will be the file extension");
    			System.out.println("of files that contain definitions associated with your DSL.\n");
    			System.out.println("It should 3-6 characters (or there abouts).\n");
    			System.out.println("It's useful to have a unique prefix for DSLs associated with your company or division,");
    			System.out.println("for example all dark-matter DSLs start with 'dm'.\n");
    			input = getResponse();
    			
    			if (input.matches("[a-zA-Z][a-zA-Z]*")){
    				targetDslAbbrev = input.toLowerCase();
    				targetModuleName = Manipulator.capFirstChar(input);
    				
    				System.out.println("DSL file extension: " + targetDslAbbrev);
    				System.out.println("DSL module name:    " + targetModuleName + "Module");
    				done = true;
    			}
    			else{
    				System.err.println("The abbreviation should be letters only");
    				continue;
    			}
 		
        		break;
        	}
        }
	}
	
	String getResponse() throws IOException {
		String input = in.readLine();
		if (input == null){
			System.out.println("Exiting...");
			System.exit(0);
		}
		return(input.trim());
	}
	
	void showProjects(){
		for(ProjectInfo pi: projectsByName.values()){
			System.out.println(pi.number + "  " + pi.name);
		}
		System.out.println();
	}
	
	void findOtherProjects() throws IOException{
		File wsdir = new File(workspace);
		File[] files = wsdir.listFiles();
		for(int i=0; i<files.length; i++){
			if (files[i].isDirectory()){
				if (findProjectAndSrc(files[i].getCanonicalPath())){
					ProjectInfo pi = new ProjectInfo(files[i].getName());
					projectsByName.put(pi.name, pi);
				}
			}
		}
		
		int number = 1;
		for(ProjectInfo pi: projectsByName.values()){
			pi.number = number++;
			projectsByNumber.put(pi.number, pi);
		}
	}

	class ProjectInfo {
		String name;
		int number;
		
		ProjectInfo(String n){
			name = n;
		}
	}
}
