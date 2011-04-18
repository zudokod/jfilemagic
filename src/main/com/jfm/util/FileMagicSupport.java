package com.jfm.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/***
 * Supported file identifier implementations configured in
 * <path>/conf/support.properties</path>
 * 
 * @author harisgx
 * 
 */
public class FileMagicSupport {
	private static final Log log = LogFactory.getLog(FileMagicSupport.class);
	
	private static FileMagicSupport fms = null;
	private static List<String> supportedImpls;
	
	
	private FileMagicSupport(){		 
	}
	
	/***
	 * 
	 * @return singleton instance of FileMagicSupport
	 */
	public static FileMagicSupport getInstance(){
		if(fms == null){
			fms = new FileMagicSupport();
			Properties props =	new Properties();		
			
			try {
				props.load(FileMagicSupport.class.getResourceAsStream("/conf/support.properties"));
			} catch (FileNotFoundException e) {
				log.error("FileNotFoundException for properties file - /conf/support.properties");
			} catch (IOException e) {
				log.error("IOException for properties file - /conf/support.properties");
			}
			Collection<Object> values = props.values();
			if(values != null && values.size() > 0){
				supportedImpls = new ArrayList<String>();
				for(Object value : values){
					supportedImpls.add((String) value);
				}
			}
			
		}
		return fms;
	}
	
	/***
	 * 
	 * @return list of absolute class names for which type identifier implementation exist
	 */
	public  List<String> getSupportedImplementations(){
		return supportedImpls;
	}
}
