package com.bcus.cms.writer;

import java.util.List;

import com.bcus.cms.model.Output;
/**
 * 
 * @author hima_shah
 *
 */
public interface OutputWriter {
	/**
	 * Write output objects
	 * @param outlist
	 */
	public void write(List<Output> outlist);
}
