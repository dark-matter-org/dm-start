package com.connectifex.dmstart;

/**
 * We can't call a mainline from the that dark-matter jar directly in a bundled
 * run configuration, so this lets us call it from the local project scope.
 */
public class DSLBootstrapMain {

	public static void main(String[] args) {
		try {
			DSLBootstrap interactive = new DSLBootstrap(args);
			interactive.run();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		
	}

}
