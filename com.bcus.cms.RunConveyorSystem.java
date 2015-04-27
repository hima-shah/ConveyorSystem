package com.bcus.cms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.bcus.cms.model.Bag;
import com.bcus.cms.model.ConveyorGraph;
import com.bcus.cms.model.Flight;
import com.bcus.cms.model.Output;
import com.bcus.cms.processor.FloydWarshallGraphrProcessor;
import com.bcus.cms.processor.GraphProcessor;
import com.bcus.cms.reader.FileInputReader;
import com.bcus.cms.reader.InputReader;
import com.bcus.cms.util.ConveyorSystemConstants;
import com.bcus.cms.writer.FileOutputWriter;
import com.bcus.cms.writer.OutputBuilder;
import com.bcus.cms.writer.OutputWriter;


/**
 * The main class
 * @author hima_shah
 *
 */
public class RunConveyorSystem {
	
	private final static Logger logger = Logger.getLogger(RunConveyorSystem.class.getName());
	/**
	 * Can pass input file and output file as command line arguments.
	 * args[0] = Input file
	 * args[1] = Output file
	 * @param args
	 */
	public static void main(String[] args){ 

		String inputFile = "", outputFile="";
		if(args.length>0){
			inputFile = args[0];
		}else{
			inputFile = ConveyorSystemConstants.FILE_PATH;
		}
		if(args.length>1){
			outputFile = args[1];
		}else{
			outputFile = ConveyorSystemConstants.OUTPUT_FILE_PATH;
		}
			
		logger.info("Input File : " + inputFile);
		logger.info("Output File : " + outputFile);
		//Initialize local Collections
		List<Output> outlist = new ArrayList<Output>();
		List<Bag> bagList = new ArrayList<Bag>();
		Map<String,Flight> flightMap = new HashMap<String, Flight>();
		ConveyorGraph conveyorGraph = new ConveyorGraph();
		
		//Read file to build bagList, FlightMap and read nodes and edges of Conveyor Graph
		InputReader reader=new FileInputReader(inputFile);
		reader.readInput(bagList,flightMap,conveyorGraph);
		
		logger.fine("Read File Complete ...");
		
		
		//Build adjacency matrix for optimal path 
		GraphProcessor graphProcessor = new FloydWarshallGraphrProcessor();
		graphProcessor.buildGraph(conveyorGraph);
			
		logger.fine("Build graph complete ...");
		//Form outputList
		outlist= OutputBuilder.buildOutput(conveyorGraph, bagList, flightMap);
		
		logger.fine("Output builder is complete ...");
		//Format and write output to file
		OutputWriter outputWriter = new FileOutputWriter(outputFile);
		outputWriter.write(outlist);
		
		logger.fine("Output File write is complete ...");
	}
	
}
