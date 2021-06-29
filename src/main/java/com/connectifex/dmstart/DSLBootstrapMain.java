package com.connectifex.dmstart;

import org.dmd.dsd.tools.dmstart.DSDInteractive;

/**
 * We can't call a mainline from the that dark-matter jar directly in a bundled
 * run configuration, so this lets us call it from the local project scope.
 */
public class DSLBootstrapMain {

	public static void main(String[] args) {
		try {
			DSDInteractive interactive = new DSDInteractive(args);
			interactive.run();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		
	}

}
