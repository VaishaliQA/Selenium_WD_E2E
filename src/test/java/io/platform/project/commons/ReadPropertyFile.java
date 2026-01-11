package io.platform.project.commons;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ReadPropertyFile {
	private static Logger logger = Logger.getLogger(ReadPropertyFile.class);
	private static String configFileLocation = "./src/main/resources/config.properties";
	
	private static String readFile(String File, String key){
		String value = null;
		try {
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(File);
			prop.load(in);
			
			//Get values from the property file
			value = prop.getProperty(key);
			logger.info("Value in Property file for key "+ key + " is " + value);
			return value;
		}catch (IOException ioe){
			logger.info("Couldn't locate Property File");
			ioe.printStackTrace();
		}
		return value;
	}
	
	public static String getConfigPropertyVal(final String key){
		String configPropertyVal = readFile(configFileLocation, key);
		return configPropertyVal!=null ? configPropertyVal.trim() : configPropertyVal;
	}
}

