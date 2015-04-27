package com.bcus.cms.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.bcus.cms.RunConveyorSystem;
import com.bcus.cms.model.Output;
import com.bcus.cms.util.ConveyorSystemConstants;

/**
 * Write the output to output file
 * @author hima_shah
 * 
 */

public class FileOutputWriter implements OutputWriter {
	private final static Logger logger = Logger
			.getLogger(RunConveyorSystem.class.getName());
	private final static String classname = FileOutputWriter.class.getName();
	
	private String outputFileName ;
	public FileOutputWriter(){
		this.outputFileName =  ConveyorSystemConstants.OUTPUT_FILE_PATH;
	}
	
	public FileOutputWriter(String fileName){
		this.outputFileName=fileName;
	}

	private File getFile() throws IOException {
		File file = new File(outputFileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}

	/**
	 * Writes output to file
	 * @param outlist : List of output objects
	 */
	@Override
	public void write(List<Output> outlist) {
		String sourceMethod = "write";
		logger.entering(classname, sourceMethod);
		File outputfile;
		try {
			outputfile = getFile();
			FileWriter fw = new FileWriter(outputfile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(formatOutput(outlist));
			bw.flush();
			bw.close();
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
		logger.exiting(classname, sourceMethod);
	}

	private String formatOutput(List<Output> outlist) {
		StringBuilder result = new StringBuilder();
		for (Output o : outlist) {
			result.append(o).append(ConveyorSystemConstants.LINE_SEPERATOR);
		}
		return result.toString();
	}
}
