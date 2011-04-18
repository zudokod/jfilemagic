package com.jfm.util.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jfm.util.FileMagicInf;

/***
 * Abstract class for file magic util
 * 
 * @author harisgx
 * 
 */
public abstract class AbstactFileMagic implements FileMagicInf {
	
	private static final Log log = LogFactory.getLog(AbstactFileMagic.class);
	
	protected String filePath;
	protected InputStream fileInStream = null;
	
	protected byte[]  getFileStreamByteArray(){
		
		InputStream fileInStream = null;
		byte[] byteArray = null;
		try {
			fileInStream = new FileInputStream(filePath);
			byteArray = IOUtils.toByteArray(fileInStream);
		} catch (FileNotFoundException e) {
			log.error("FileNotFoundException for " + filePath);
		} catch (IOException e) {
			log.error("IOException for " + filePath);
		}finally{
			closeStream();
		}
		
		return byteArray;
		
	}
	
	protected void closeStream(){
		IOUtils.closeQuietly(fileInStream);
	}

}
