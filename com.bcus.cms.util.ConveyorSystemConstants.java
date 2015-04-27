package com.bcus.cms.util;

/**
 * Application constants
 * 
 * @author hima_shah
 *
 */

public class ConveyorSystemConstants {
	public static final String WHITESPACE_SEPERATOR = " ";
	public static final String COLON_SEPERATOR = ":";
	public static final String LINE_SEPERATOR = System
			.getProperty("line.separator");

	public static final String ARRIVAL_NODE = "ARRIVAL";
	public static final String BAGGAGE_CLAIM_NODE = "BaggageClaim";
	public static final String TICKETING_NODE = "Concourse_A_Ticketing";

	public final static String SECTION = "# Section:";
	public final static String CONVEYOR_SECTION = " Conveyor System";
	public final static String FLIGHT_SECTION = " Departures";
	public final static String BAGS_SECTION = " Bags";

	// File paths
	public static final String FILE_PATH = "c:\\BCUS\\Input.txt";
	public static final String OUTPUT_FILE_PATH = "c:\\BCUS\\Output.txt";
	// Logging
	public final static String LOGGING_PROPERTIES = "c:/BCUS/logging.properties";
	public final static String LOG_PATH = "c:\\BCUS\\logs\\";
}
