package com.gonghanxun.cms.utils;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	/**
	 *  闅忔満瀛楃涓叉簮
	 */
	static char charArray[] = new char[36];
	static {
		// 鏋勫缓闅忔満瀛楃涓茬殑鍘熷鏁扮粍
		for (int i = 0; i < 10; i++) {
			charArray[i] = (char)('0' + i);
		}
		for (int i = 0; i < 26; i++) {
			charArray[i+10] = (char)('A' + i);
		}		
	}
	
	
	

	/**
	 * 鍒ゆ柇涓�涓瓧绗︿覆鏄惁涓虹┖锛岀┖瀛楃涓蹭篃璁や负鏄殑绌�
	 * @param str
	 * @return 涓虹┖杩斿洖true  鍚﹀垯杩斿洖false
	 * 
	 */
	public static boolean isBlank(String str) {
		return null==str||"".equals(str.trim());
	}
	
	/**
	 * 鍒ゆ柇涓�涓瓧绗︿覆鏃堕棿鍚︽湁鍊� 
	 * @param str   
	 * @return 闈炵┖杩斿洖true  绌哄瓧绗︿覆鎴栫┖杩斿洖false
	 */
	public static boolean haveValue(String str) {
		return null!=str && !"".equals(str.trim());
	}
	
	/**
	 * 鍒ゆ柇鏄惁涓烘暟瀛�
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		String regex = "^\\d{1,}$";
		Pattern compile = Pattern.compile(regex);
		Matcher matcher = compile.matcher(str);
		boolean find = matcher.find();
		return find;
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isMobile(String str) {
		String regex = "^(135|136|138)\\d{8}$";
		Pattern compile = Pattern.compile(regex);
		Matcher matcher = compile.matcher(str);
		boolean find = matcher.find();
		return find;
		
		
	}
	
	public static String getRandomStr(int n) {
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		//StringBuffer sb = new StringBuffer();
		for (int i = 0; i < n; i++) {
			char randomChar = (char)('a' +  random.nextInt(26));// 0 ~  25;
			sb.append(randomChar);
		}
		return sb.toString();
	}
	
	/**
	 * 鑾峰彇鑻辨枃鍜屾暟瀛楃粍鍚堢殑瀛楃涓�
	 * @param n
	 * @return
	 */
	public static String getRandomStrNum(int n) {
		//char charArray[] = {'0','1' ..}
		Random random = new Random();
		
		//鑾峰彇闅忔満瀛楃涓�
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			// 鑾峰彇鏁扮粍鐨勪笅鏍�
			int index =  random.nextInt(36);// 0 ~  25;
			char randomChar = charArray[index];
			sb.append(randomChar);
		}
		
		return sb.toString();
		
	}
	
	/**
	 * 鑾峰彇闅忔満瀛楃涓� 闀垮害2涓簄
	 * @param n
	 * @return 
	 * @throws UnsupportedEncodingException 
	 */
	public static String getGb2312(int n) throws UnsupportedEncodingException {
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(getGb2312());
		}
		return sb.toString();
	}
	private static String getGb2312() throws UnsupportedEncodingException {
		
		byte word[] = new byte[2];
		//  0x1A   0x1A+94
		Random random = new Random();
		word[0] = (byte)(0xA1 + 0x10 + random.nextInt(39));
		word[1] = (byte)(0xA1  + random.nextInt(94));
		return new String(word,"GBK");
		
	}
}
