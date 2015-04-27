package com.bcus.cms.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;
/**
 * 
 * @author hima_shah
 *
 */
public class ConveyorLogger {
	
		private static Logger logger = Logger.getLogger(ConveyorLogger.class.getName());
		
		static{
			try{
				if(logger.getHandlers().length ==0){
					LogManager.getLogManager().readConfiguration(new FileInputStream(ConveyorSystemConstants.LOGGING_PROPERTIES));
				}
			}catch(IOException ioe){
				ioe.printStackTrace();
					Logger.getAnonymousLogger().severe("Could not load default logging.properties file");
					Logger.getAnonymousLogger().severe(ioe.getMessage());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		public ConveyorLogger(){
			super();
		}
		
		public static Logger getLogger(){
			return logger;
		}
}
