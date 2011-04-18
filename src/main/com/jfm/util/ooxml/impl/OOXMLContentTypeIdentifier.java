package com.jfm.util.ooxml.impl;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jfm.util.FileType;

/****
 * Identifies the content type of the documents in ooxml standard
 * 
 * @author harisgx
 * 
 */
public class OOXMLContentTypeIdentifier {
	
	private static final Log log = LogFactory.getLog(OOXMLContentTypeIdentifier.class);
	
	public static final String DOCX_TYPE = "application/vnd.openxmlformats-officedocument.wordprocessingml";
	public static final String PPTX_TYPE = "application/vnd.openxmlformats-officedocument.presentationml";
	public static final String XLSX_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml";
	
	
	public static final String CONTENT_TYPE_XML = "[Content_Types].xml";
	
	/***
	 * Gets the content type for open xml format. To be used only after
	 * the file signature match is valid
	 * 
	 * @param filePath - path of the file
	 * @return {@link FileType} matching to content type.
	 * @throws Exception
	 */
	public static FileType getOOXMLContentType(String filePath) throws Exception{
		ZipEntry ze = null;
		ZipFile zippy = null;
		FileType contentType = FileType.UNDEFINED;
		try{
			zippy = new ZipFile(filePath);
			Enumeration<? extends ZipEntry> zipEntries = zippy.entries();
			
			while (zipEntries.hasMoreElements()) {
				ze = zipEntries.nextElement();
				String fileName = ze.getName();
				if (CONTENT_TYPE_XML.equals(fileName)) {
					InputStream inputStream = zippy.getInputStream(ze);
					String contentXML = new String(IOUtils.toByteArray(inputStream));
					contentType = getContentType(contentXML);
					inputStream.close();
				}
			}
		}catch(Exception e){
			log.error("Exception while reading " + filePath);
		}finally{
			zippy.close();
		}
		
		return contentType;
	}
	
	
	private static FileType getContentType(String contentXML){
		FileType type = FileType.UNDEFINED;
		
		if (contentXML.contains(DOCX_TYPE)){
			type = FileType.DOCX;
		}else if(contentXML.contains(PPTX_TYPE)){
			type = FileType.PPTX;
		}else if(contentXML.contains(XLSX_TYPE)){
			type = FileType.XLSX;
		}
		
		return type;	
	}

}
