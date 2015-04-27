package com.bcus.cms.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.bcus.cms.model.Bag;
import com.bcus.cms.model.ConveyorGraph;
import com.bcus.cms.model.Edge;
import com.bcus.cms.model.Flight;
import com.bcus.cms.util.ConveyorLogger;
import com.bcus.cms.util.ConveyorSystemConstants;

/**
 * It reads given input file and initializes objects like bag list, flight list and nodes and paths(edges) of coneyor system
 * @author hima_shah
 *
 */
public class FileInputReader implements InputReader {

	private static final Logger logger = ConveyorLogger.getLogger();
	private static final String classname = FileInputReader.class.getName();

	private String fileName ;
	public FileInputReader() {
		fileName = ConveyorSystemConstants.FILE_PATH;
	}
	public FileInputReader(String fileName){
		this.fileName = fileName;
	}

	/**
	 * 
	 * @param bagList : List of bags; Initialized by input file Bag section
	 * @param flights : Map of flight name to flight object, initialized by input file Departures section
	 * @param conveyorGraph : Weighted bi-directional graph is assigned to node list and edges in the conveyer system graph
	 */
	@Override
	public void readInput(List<Bag> bagList,
			Map<String, Flight> flights, ConveyorGraph conveyorGraph) {
		String sourceMethod = "readFile";
		logger.entering(classname, sourceMethod);
		BufferedReader br = null;
		try {

			br = new BufferedReader(new FileReader(fileName));

			String currLine;
			Boolean isConveyorSection = false, isBagSection = false, isFlightSection = false;
			while ((currLine = br.readLine()) != null
					&& currLine.trim().length() > 0) {
				if (currLine.startsWith(ConveyorSystemConstants.SECTION)) {
					if (currLine
							.contains(ConveyorSystemConstants.CONVEYOR_SECTION)) {
						isConveyorSection = true;
						isFlightSection = false;
						isBagSection = false;
					} else if (currLine
							.contains(ConveyorSystemConstants.FLIGHT_SECTION)) {
						isConveyorSection = false;
						isFlightSection = true;
						isBagSection = false;
					} else if (currLine
							.contains(ConveyorSystemConstants.BAGS_SECTION)) {
						isConveyorSection = false;
						isFlightSection = false;
						isBagSection = true;
					}
				} else {
					if (isConveyorSection) {
						processConvSystem(currLine, conveyorGraph);
					} else if (isFlightSection) {
						processDepartures(currLine, flights);
					} else if (isBagSection) {
						processBags(currLine, bagList);
					} else {
						logger.log(Level.SEVERE, "Invalid row :'"+currLine +" in input file ");
					}
				}
			}
		} catch (IOException e) {
			logger.log(Level.SEVERE,e.getMessage(),e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.log(Level.SEVERE,e.getMessage(),e);
				}
				logger.exiting(classname, sourceMethod);
			}
		}
	}

	/**
	 * Reads Conveyor Section line by line.
	 * Reading the input file and initializes nodes (flight gates) and edges (path connecting nodes along with traveltime)
	 * @param line
	 * @param conveyorGraph
	 */
	private void processConvSystem(String line, ConveyorGraph conveyorGraph) {
		List<String> nodeNameList = conveyorGraph.getNodeNameList();
		Set<Edge> edgeSet = conveyorGraph.getEdgeSet();
		
		String arr[] = line.split(ConveyorSystemConstants.WHITESPACE_SEPERATOR);

		if (arr.length >= 3) {
			if (!nodeNameList.contains(arr[0])) {
				nodeNameList.add(arr[0]);
			}
			if (!nodeNameList.contains(arr[1])) {
				nodeNameList.add(arr[1]);
			}
			Edge e = new Edge(arr[0], arr[1], Integer.parseInt(arr[2]));
			edgeSet.add(e);
		} else {
			logger.log(Level.SEVERE, "Invalid row :'"+line +"' for Conveyor System input ");
		}

	}

	/**
	 * Reads Departure Section line by line.
	 * Reading the input file and initializes flight map (flight gates) and edges (path connecting nodes along with traveltime)
	 * @param line
	 * @param conveyorGraph
	 */
	private void processDepartures(String line, Map<String, Flight> flightMap) {
		String arr[] = line.split(ConveyorSystemConstants.WHITESPACE_SEPERATOR);
		if (arr.length < 4) {
			 logger.log(Level.SEVERE, "Invalid row :'"+line +"' for Departure input ");
			System.out.println("\n Invalid row :'"+line +"' for Departure input");
		} else {
			flightMap.put(arr[0], new Flight(arr[0], arr[1], arr[2], arr[3]));
		}
	}

	/**
	 * Reads Bags section line by line
	 * Initialize Bag object and adds to bag list as it appears in the input.
	 * Output is  generated in the same sequence as bag input sequence.
	 * @param line
	 * @param bagList
	 */
	private void processBags(String line, List<Bag> bagList) {
		String arr[] = line.split(ConveyorSystemConstants.WHITESPACE_SEPERATOR);
		if (arr.length < 3) {
			logger.log(Level.SEVERE, "Invalid row :'"+line +"' for Bags input ");
		} else {
			bagList.add(new Bag(arr[0], arr[1], arr[2]));
		}
	}
}
