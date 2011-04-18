package com.jfm.util;

/***
 * Strategy context for FileChecker
 * 
 * @author harisgx
 * 
 */
public class FileMagicContext {
	
	private FileMagicInf fileChecker;
	
	public FileMagicContext(){
		
	}

	public FileMagicInf getFileChecker() {
		return fileChecker;
	}

	public void setFileChecker(FileMagicInf fileChecker) {
		this.fileChecker = fileChecker;
	}
	
	public FileType getFileType(String filePath){
		FileType fileType = FileType.UNDEFINED; 
		if(fileChecker != null){
			fileType = fileChecker.getFileType(filePath);
		}
		return fileType;
	}
	
}
