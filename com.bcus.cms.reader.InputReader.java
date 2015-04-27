package com.bcus.cms.reader;

import java.util.List;
import java.util.Map;

import com.bcus.cms.model.Bag;
import com.bcus.cms.model.ConveyorGraph;
import com.bcus.cms.model.Flight;

/**
 * 
 * @author hima_shah
 *
 */
public interface InputReader {
	/**
	 * 
	 * @param bagList : List of bags; Initialized by input file Bag section
	 * @param flights : Map of flight name to flight object, initialized by input file Departures section
	 * @param conveyorGraph : Weighted bi-directional graph is assigned to node list and edges in the conveyer system graph
	 */
	public void readInput(List<Bag> bagList,
			Map<String, Flight> flights, ConveyorGraph conveyorGraph) ;
}
