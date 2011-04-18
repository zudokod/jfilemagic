package com.jfm.util;

import java.io.File;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/***
 * utility for identifying file signatures
 * 
 * @author harisgx
 *
 */
public class FileMagicUtil {
	
	private static final Log log = LogFactory.getLog(FileMagicUtil.class);
	
	private FileMagicUtil(){
		
	}
	
	/***
	 * Get file type for file . The file size should be > 0 bytes
	 * @param filePath - absolute path of the file, should not be a directory
	 * @return {link FileType}
	 */
	public static FileType getFileType(String filePath){
		FileType fileType = FileType.UNDEFINED;
		File file = new File(filePath);
		if(file.exists() && file.canRead() && !file.isDirectory() && file.length() > 0){
			FileMagicContext context = new FileMagicContext();			
			List<String> supportedImpls = FileMagicSupport.getInstance().getSupportedImplementations();
			if(supportedImpls != null){
				for(String className : supportedImpls){
					context.setFileChecker(getInstance(className));
					fileType = context.getFileType(filePath);
					if(fileType != FileType.UNDEFINED) break;
				}
			}
			
		}else{
			log.error("Invalid file - " + filePath);
		}
		return fileType;
	}
	
	private static FileMagicInf getInstance(String className){
		try {
			return (FileMagicInf)Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			log.error("Class Instantiation failed for " + className);
		} catch (IllegalAccessException e) {
			log.error("IllegalAccessException for " + className);
		} catch (ClassNotFoundException e) {
			log.error("ClassNotFoundException for " + className);
		}
		return null;
	}

}
