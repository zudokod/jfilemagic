package com.jfm.test;

import org.junit.Test;

import com.jfm.util.FileMagicUtil;
import com.jfm.util.FileType;
import static org.junit.Assert.*;

public class FileMagicTest {
	
	@Test public void DocxTest(){
		String path = "C:\\temp\\test.docx"; //give the path
		FileType fileType = FileMagicUtil.getFileType(path);
		assertTrue("The file has to be docx",fileType == FileType.DOCX);
	}
	
	@Test public void PdfTest(){
		String path = "C:\\temp\\test.pdf"; //give the path
		FileType fileType = FileMagicUtil.getFileType(path);
		assertTrue("The file has to be pdf",fileType == FileType.PDF);
	}
	
	@Test public void XLSXTest(){
		String path = "C:\\temp\\test.xlsx"; //give the path
		FileType fileType = FileMagicUtil.getFileType(path);
		assertTrue("The file has to be xlsx",fileType == FileType.XLSX);
	}
	
	@Test public void PPTXTest(){
		String path = "C:\\temp\\test.pptx"; //give the path
		FileType fileType = FileMagicUtil.getFileType(path);
		assertTrue("The file has to be xlsx",fileType == FileType.PPTX);
	}

}
