package com.jfm.util;

/**
 * Strategy interface for file checker
 * 
 * @author harisgx
 * 
 */
public interface FileMagicInf {
	
	FileType getFileType(String filePath);

}
