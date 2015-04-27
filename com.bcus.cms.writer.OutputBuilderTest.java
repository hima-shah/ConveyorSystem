package com.bcus.cms.writer;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.bcus.cms.model.Bag;
import com.bcus.cms.model.ConveyorGraph;
import com.bcus.cms.model.Edge;
import com.bcus.cms.model.Flight;
import com.bcus.cms.model.Output;
import com.bcus.cms.processor.FloydWarshallGraphrProcessor;
import com.bcus.cms.processor.GraphProcessor;

public class OutputBuilderTest {
	
	private static List<Bag>  bagList;
	private static Map<String, Flight> flightMap ;
	private static ConveyorGraph c ;
	private static GraphProcessor processor = new FloydWarshallGraphrProcessor();
	private static List<Output> expectedOutput ;
	private static List<Output> outlist ;
	public static void init(){
		processor = new FloydWarshallGraphrProcessor();
		c = new ConveyorGraph();
		List<String> nodes = c.getNodeNameList();
		nodes.add("A1");
		nodes.add("A2");
		nodes.add("A3");
		nodes.add("A4");
		nodes.add("A5");
		Set<Edge> edgeset = c.getEdgeSet();
		edgeset.add(new Edge("A1", "A2", 8));
		edgeset.add(new Edge("A1", "A3", 4));
		edgeset.add(new Edge("A4", "A3", 9));
		edgeset.add(new Edge("A4", "A2", 2));
		edgeset.add(new Edge("A2", "A3", 1));
		processor.buildGraph(c);

		Bag b1 = new Bag("B1","A1","Fl4");
		Bag b2 = new Bag("B2","A2","Fl1");
		Bag b3 = new Bag("B3","A4","Fl3");
		
		bagList = new ArrayList<Bag>();
		bagList.add(b1);
		bagList.add(b2);
		bagList.add(b3);
		
		flightMap = new HashMap<String,Flight>();
		Flight f1 = new Flight("Fl1","A1","JFK","9.00");
		Flight f2 = new Flight("Fl3","A3","JFK","9.00");
		Flight f3 = new Flight("Fl4","A4","JFK","9.00");
		flightMap.put("Fl1",f1);
		flightMap.put("Fl3",f2);
		flightMap.put("Fl4",f3);
		
		
		//expected output
		List<String>  nodeList1 = new ArrayList<String>();
		nodeList1.add("A1");
		nodeList1.add("A3");
		nodeList1.add("A2");
		nodeList1.add("A4");
		
		List<String>  nodeList2 = new ArrayList<String>();
		nodeList2.add("A2");
		nodeList2.add("A3");
		nodeList2.add("A1");
		List<String>  nodeList3 = new ArrayList<String>();
		nodeList3.add("A4");
		nodeList3.add("A2");
		nodeList3.add("A3");
		expectedOutput = new ArrayList<Output>();
		Output o1 = new Output("B1", nodeList1, "7");
		Output o2 = new Output("B2", nodeList2, "5");
		Output o3 = new Output("B3", nodeList3, "3");
		
		expectedOutput.add(o1);
		expectedOutput.add(o2);
		expectedOutput.add(o3);
		outlist = OutputBuilder.buildOutput(c, bagList, flightMap);
		
	}
	
	@Test
	public void testOutputBuilder(){
		
		
		
		assertEquals(expectedOutput, outlist);		
	}
	
}
