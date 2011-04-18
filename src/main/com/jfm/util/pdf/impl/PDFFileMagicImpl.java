package com.jfm.util.pdf.impl;

import com.jfm.util.FileType;
import com.jfm.util.HexUtil;
import com.jfm.util.impl.AbstactFileMagic;

/***
 * Strategy implementation for pdf documents
 * 
 * @author harisgx
 *
 */
public class PDFFileMagicImpl extends AbstactFileMagic {

	public static final String PDF_SIGN_1 = "0a2525454f460a";
	public static final String PDF_SIGN_2 = "0d0a2525454f460d0a";
	public static final String PDF_SIGN_3 = "0d2525454f460d";

	@Override
	public FileType getFileType(String filePath) {
		// TODO Auto-generated method stub
		FileType type = FileType.UNDEFINED;
		super.filePath = filePath;
		if(isvalidFileSignatureForPDF(super.getFileStreamByteArray())){
			type = FileType.PDF;
		}
		return type;
	}

	private boolean isvalidFileSignatureForPDF(byte[] buffer) {
		boolean valid = false;
		int index = -1;
		StringBuilder hexFromBytes = HexUtil.toHexFromBytes(buffer);
		String matchedSign = "";
		index = hexFromBytes.indexOf(PDF_SIGN_1, 1);		
		if (index == -1) {
			index = hexFromBytes.indexOf(PDF_SIGN_2, 1);
			if (index == -1) {
				index = hexFromBytes.indexOf(PDF_SIGN_3, 1);
				if (index != -1) matchedSign = PDF_SIGN_3;
			}else{
				matchedSign = PDF_SIGN_2;
			}
		}else{
			matchedSign = PDF_SIGN_1;
		}
		
		if(index != -1){
			int finalIndex = ((index + matchedSign.length()) / 2);
			if(finalIndex == buffer.length){
				valid = true;
			}
		}
		return valid;
	}

}
