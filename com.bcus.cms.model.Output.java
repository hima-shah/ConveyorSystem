package com.bcus.cms.model;

import java.io.Serializable;
import java.util.List;

import com.bcus.cms.util.ConveyorSystemConstants;


/**
 * 
 * @author hima_shah
 *
 */
public class Output implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1507051610974412530L;
	private String bagNum;
	private List<String> nodeNameList;
	private String traveltime;
	
	public Output(){}
	
	public Output(String bagNum, List<String> nodeList, String traveltime){
		this.bagNum = bagNum;
		this.nodeNameList = nodeList;
		this.traveltime = traveltime;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bagNum == null) ? 0 : bagNum.hashCode());
		result = prime * result
				+ ((nodeNameList == null) ? 0 : nodeNameList.hashCode());
		result = prime * result
				+ ((traveltime == null) ? 0 : traveltime.hashCode());
		return result;
	}

	
	public void setBagNum(String bagNum) {
		this.bagNum = bagNum;
	}

	public void setNodeNameList(List<String> nodeNameList) {
		
		this.nodeNameList = nodeNameList;
	}

	public void setTraveltime(String traveltime) {
		this.traveltime = traveltime;
	}
	
	private String getPathString(List<String> nodeNameList){
		StringBuilder strBuilder = new StringBuilder();
		for(String node:nodeNameList){
			strBuilder.append(node).append(ConveyorSystemConstants.WHITESPACE_SEPERATOR);
		}
		return strBuilder.toString();
	}
	
	@Override
	public String toString(){
		return new StringBuilder().append(bagNum).append(ConveyorSystemConstants.WHITESPACE_SEPERATOR).append(getPathString(nodeNameList))
							.append(ConveyorSystemConstants.COLON_SEPERATOR).append(ConveyorSystemConstants.WHITESPACE_SEPERATOR).append(traveltime).toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Output other = (Output) obj;
		if (bagNum == null) {
			if (other.bagNum != null)
				return false;
		} else if (!bagNum.equals(other.bagNum))
			return false;
		if (nodeNameList == null) {
			if (other.nodeNameList != null)
				return false;
		} else if (!nodeNameList.equals(other.nodeNameList))
			return false;
		if (traveltime == null) {
			if (other.traveltime != null)
				return false;
		} else if (!traveltime.equals(other.traveltime))
			return false;
		return true;
	}

	
}
