package com.jfm.util.ooxml.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jfm.util.FileType;
import com.jfm.util.impl.AbstactFileMagic;

/***
 * Strategy implementation for OOXML MSOffice documents
 * 
 * @author harisgx
 * 
 */
public class OOXMLFileMagicImpl extends AbstactFileMagic {

	private static final Log log = LogFactory.getLog(OOXMLFileMagicImpl.class);
	
	public OOXMLFileMagicImpl(){
		
	}

	private boolean isValidOOXML(String filePath) {
		boolean isValid = false;
		byte[] fileStreamByteArray = super.getFileStreamByteArray();
		if(fileStreamByteArray != null)
		isValid = OOXMLFileMagicValidator.isValidFileSignatureForOOXML(fileStreamByteArray);			
		return isValid;

	}

	@Override
	public FileType getFileType(String filePath) {

		super.filePath = filePath;
		FileType type = FileType.UNDEFINED;
		try {
			if(isValidOOXML(filePath)){
				type = OOXMLContentTypeIdentifier.getOOXMLContentType(filePath);
			}
		} catch (Exception e) {
			log.error("Exception occured while reading "+ filePath);
		}		
		
		return type;
	}

}
