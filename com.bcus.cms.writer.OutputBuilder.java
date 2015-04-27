package com.bcus.cms.writer;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.bcus.cms.model.Bag;
import com.bcus.cms.model.ConveyorGraph;
import com.bcus.cms.model.Flight;
import com.bcus.cms.model.Output;
import com.bcus.cms.util.ConveyorLogger;
import com.bcus.cms.util.ConveyorSystemConstants;

public class OutputBuilder {
	private static final Logger logger = ConveyorLogger.getLogger();
	private static final String classname = OutputBuilder.class.getName();
	
	public static List<Output> buildOutput(final ConveyorGraph conveyorGraph, final List<Bag> bagList, final Map<String,Flight> flightMap){
		String sourceMethod = "buildOutput";
		logger.entering(classname, sourceMethod);
		List<String> nodeNameList = conveyorGraph.getNodeNameList();
		ConveyorGraph.MatrixCell[][] adjMatrix = conveyorGraph.getAdjacencyMatrix();
		List<Output> outlist = new ArrayList<Output>();
		Output output = new Output();
		int startIndex, endIndex;
		for(Bag bag : bagList){
			output = new Output();
			output.setBagNum(bag.getBagNum());
			String startNode = bag.getEntryGate();
			String endNode="";
			if(bag.getFlight().equals(ConveyorSystemConstants.ARRIVAL_NODE)){
				endNode = ConveyorSystemConstants.BAGGAGE_CLAIM_NODE;
			}else{
				endNode = (flightMap.get(bag.getFlight())).getFlightGate();
			}
			
			startIndex = nodeNameList.indexOf(startNode);
			endIndex = nodeNameList.indexOf(endNode);
			output.setNodeNameList(adjMatrix[startIndex][endIndex].getPath());
			output.setTraveltime(new DecimalFormat("#").format(adjMatrix[startIndex][endIndex].getTravelTime()));
			outlist.add(output);
		}
		if(Level.INFO.equals(logger.getLevel())){
			logger.info(outlist.toString());
		}
		logger.exiting(classname, sourceMethod);
		return outlist;
	}
}
