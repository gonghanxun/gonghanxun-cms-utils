package com.gonghanxun.cms.utils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.codec.digest.DigestUtils;

public class FileUtils {
public static synchronized String getSuffixName(String fileName) {
		
		//鑾峰彇鏈�鍚庝竴涓偣鐨勪綅缃�
		int dotPos = fileName.lastIndexOf('.');
		if(dotPos<0)
			return "";
		
		return fileName.substring(dotPos);
	}
	
	/**
	 * 
	 * @param fileName
	 */
	public static void delFile(String fileName) {
		
		File file = new File(fileName);
		
		// 鑾峰彇鏂囦欢鐨勫垎闅旂鍙�
		String fileSeperator = getProperty("file.separator");
		
		
		
		//鏂囦欢涓嶅瓨鍦�
		if(!file.exists())
			return ;
		
		// 濡傛灉鏄洰褰�
		if(file.isDirectory()) {
			//閫掑綊鍒犻櫎瀛愮洰褰曟垨鑰呮枃浠�
			String[] list = file.list();
			for (int i = 0; i < list.length; i++) {
				String childFileName = fileName+ fileSeperator + list[i];
				delFile(childFileName);
			}
		}
		// 濡傛灉鏄枃浠� 鎴栬�呭垹闄ゅ瓙鐩綍涔嬪悗 鍒犻櫎鏈韩
		file.delete();
		
	}
	
	/**
	 * 鏍规嵁灞炴�ey 鑾峰彇绯荤粺鐜鍙橀噺鍊�
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		
		Properties properties = System.getProperties();
		/*//鑾峰彇鐜鍙橀噺
		//System.getenv();
		
		//ArrayList<String> arrayList = new ArrayList<String>();
		//
		//arrayList.forEach(x->{System.out.println(x);});
		 Set<Object> keySet = properties.keySet();
		for (Object hKey: keySet) {
			System.out.println("hKey is " + hKey);
			System.out.println("hValue is " + properties.getProperty((String) hKey));
		}*/
		return properties.getProperty(key);
	} 
	
	/**
	 * 鑾峰彇绯荤粺鐨勭幆澧冨彉閲�
	 * @param key
	 * @return
	 */
	public static String getEnv(String key) {
		
		Map<String, String> getenv = System.getenv();
		/*getenv.forEach((hKey,hValue)->{
			System.out.println("key is " + hKey );
			System.out.println("vlaue is " + hValue );
		});*/
		return getenv.get(key);
	}
	
	/**
	 * 鑾峰彇鏂囦欢鐨勫ぇ灏�
	 * @param fileName
	 * @return
	 */
	public static long getSize(String fileName) {
		
		File file = new File(fileName);
		if(!file.exists() || ! file.isFile())
			return 0;
		return file.length();
		
	}
	
	/**
	 *  姣旇緝涓や釜涓や釜鏂囦欢鏋跺唴瀹规槸鍚︾浉鍚�
	 * @param src  鍘熺洰褰�
	 * @param dst  鐩爣鐩綍
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void comparePath(String src,String dst) throws FileNotFoundException, IOException {
		
		 File srcFile = new File(src);// 
		 File dstFile = new File(dst);//
		 if(!srcFile.exists()) {
			 System.out.println(" 婧愭枃浠� 涓嶅瓨鍦�  "  + src);
			 return;
		 }
		 
		 if(!dstFile.exists()) {
			 System.out.println(" 鐩爣鏂囦欢 涓嶅瓨鍦�  "  + dst);
			 return;
		 }
		 if(srcFile.isFile() && dstFile.isFile()) {
			 if(srcFile.length() != dstFile.length()) {
				 System.out.println(" 鏂囦欢闀垮害涓嶄竴鑷�" + src);
			 }else {
				 byte[] md5Src = DigestUtils.md5(new FileInputStream(srcFile));
				 byte[] md5Dst = DigestUtils.md5(new FileInputStream(dstFile));
				 String strMd5Src = new String(md5Src);
				 String strMd5Dst = new String(md5Dst);
				 if(!strMd5Src.equals(strMd5Dst)) {
					 System.out.println(" 鏂囦欢鍐呭涓嶄竴鑷�  " +  src);
				 }
			 }
			 return ;
		 }
		 
		 if(srcFile.isDirectory()) {
			 // 閫掑綊锛� 閬嶅巻
			 String[] list = srcFile.list();
			 for (int i = 0; i < list.length; i++) {
				 //婧愭枃浠剁殑瀛愭枃浠惰矾寰�
				 String childSrcFile = src + "\\" + list[i];
				//鐩爣鏂囦欢鐨勫瓙鏂囦欢璺緞
				 String childDstFile = dst + "\\" + list[i];
				 comparePath(childSrcFile,childDstFile);
			}
			 
		 }
		
	}
	
	/**
	 * 
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	public static String read(String fileName) throws IOException {
		
		//鐢ㄤ簬瀛樺偍鏂囦欢鍐呭
		StringBuilder sb = new StringBuilder();
		
		// 鍒涘缓鏂囦欢瀵硅薄
		File file = new File(fileName);
		
		//鍒涘缓鏂囦欢杈撳叆娴�
		FileInputStream fis = new FileInputStream(file);
		// 鍒涘缓缂撳啿娴�
		BufferedReader br = new BufferedReader(new InputStreamReader(fis,"GBK"));
		String ln=null;
		//鎸夎璇诲叆
		while ((ln= br.readLine())!=null) {
			sb.append(ln);
		}
		
		closeStream(br,fis);
		
		return sb.toString();
	}
	
	
	public static List<String> readByLines(String fileName) throws IOException {
		
		//鐢ㄤ簬瀛樺偍鏂囦欢鍐呭
		List<String> lines = new ArrayList();
		
		// 鍒涘缓鏂囦欢瀵硅薄
		File file = new File(fileName);
		
		//鍒涘缓鏂囦欢杈撳叆娴�
		FileInputStream fis = new FileInputStream(file);
		// 鍒涘缓缂撳啿娴�
		BufferedReader br = new BufferedReader(new InputStreamReader(fis,"UTF-8"));
		String ln=null;
		//鎸夎璇诲叆
		while ((ln= br.readLine())!=null) {
			//sb.append(ln);
			lines.add(ln);
		}
		
		closeStream(br,fis);
		
		return lines;
	}
	
	
	public static void closeStream(Closeable ... stream) throws IOException {
		
		for (int i = 0; i < stream.length; i++) {
			stream[i].close();
		}
	}
	
	public synchronized static void copy(String srcFileName ,String dstFileName) throws IOException {
		// 婧愭枃浠�
		File srcFile = new File(srcFileName);
		File dstFile = new File(dstFileName);
		
		FileInputStream fis = new FileInputStream(srcFile);
		FileOutputStream fos  = new FileOutputStream(dstFile);
		byte b[]=new byte[1024];
		
		while(fis.read(b)>0) {
			fos.write(b);
		}
		closeStream(fos,fis);
	}
}
