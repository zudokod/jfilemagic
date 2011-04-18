package com.jfm.util.ooxml.impl;

import com.jfm.util.HexUtil;

/***
 * Validates the file signature for ooxml files
 * 
 * @author harisgx
 * 
 */
public class OOXMLFileMagicValidator {

	public static final String FILE_SIGN = "504b";
	public static final String FILE_SIGN_END = "000000";

	/****
	 * validate the file signature for the ooxml file
	 * 
	 * @param buffer
	 *            - array of buffer
	 * @return <code>true</code> if valid
	 */
	public static boolean isValidFileSignatureForOOXML(byte[] buffer) {

		boolean isValid = false;
		StringBuilder hexFromBytes = HexUtil.toHexFromBytes(buffer);
		int indexOf = 0;
		int posCount = 1;
		int lastFoundIndex = -1;

		while (posCount < hexFromBytes.length()) {

			indexOf = hexFromBytes.indexOf(FILE_SIGN, posCount);
			if (indexOf != -1) {
				posCount = indexOf + 4 + 34 + 6;
				if (posCount <= hexFromBytes.length()) {
					String trail = hexFromBytes.substring(indexOf + 4 + 34, indexOf + 4 + 34 + 6);
					if (FILE_SIGN_END.equalsIgnoreCase(trail)) {
						lastFoundIndex = indexOf;
					}
				}
			} else {
				break;
			}

		}

		if (lastFoundIndex != -1) {
			// check if the lastfound index is the last index
			if ((lastFoundIndex + 4 + 34 + 6) / 2 == (buffer.length)) {
				isValid = true;
			}
		}

		return isValid;
	}

}
