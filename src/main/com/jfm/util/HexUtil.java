package com.jfm.util;

/****
 * Utility for generating hex strings
 * 
 * @author harisgx
 * 
 */
public class HexUtil {

	private static final String[] hexSymbols = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	/***
	 * Converts byte to hex string.
	 * 
	 * @param b
	 *            byte to be converted to hex string
	 * @return hex string
	 */
	public static final StringBuilder toHexFromByte(byte b) {
		byte leftSymbol = (byte) (b >>> 4 & 0xF);
		byte rightSymbol = (byte) (b & 0xF);
		return new StringBuilder(hexSymbols[leftSymbol] + hexSymbols[rightSymbol]);
	}
	
	/***
	 * Converts array of bytes
	 * 
	 * @param bytes
	 *            byte array to be converted to hex string
	 * @return hex string
	 */
	public static final StringBuilder toHexFromBytes(byte[] bytes) {
		
		StringBuilder hexBuilder = null;
		
		if ((bytes != null) && (bytes.length > 0)){
			hexBuilder = new StringBuilder(bytes.length * 2);
			for (int i = 0; i < bytes.length; i++) {
				hexBuilder.append(toHexFromByte(bytes[i]));
			}
		}else{
			hexBuilder = new StringBuilder("");
		}
		
		return hexBuilder;
	}
	
	
	

}
