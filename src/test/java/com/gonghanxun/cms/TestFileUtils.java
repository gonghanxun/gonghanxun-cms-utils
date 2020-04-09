package com.gonghanxun.cms;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.gonghanxun.cms.utils.FileUtils;

public class TestFileUtils {
	@Test
	public void testGetSuffix() {
		
		String suffix = FileUtils.getSuffixName("D:\\program\\nodejs\\node_cache\\anonymous-cli-metrics.json");
		System.out.println("suffix = " + suffix);
		
	}
	
	@Test
	public void testDelFile() {
		FileUtils.delFile("D:\\mvntest - 鍓湰zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz	");
	}
	
	@Test
	public void testGetpro() {
		FileUtils.getProperty("");
		
	}
	
	
	@Test 
	public void testGetEvn() {
		System.out.println( "java_home is " +  FileUtils.getEnv("JAVA_HOME"));
	}
	
	
	@Test
	public void testCompare() throws FileNotFoundException, IOException {
		System.out.println("姣旇緝寮�濮�");
		FileUtils.comparePath("D:\\project\\07b", "D:\\project\\07b - 鍓湰");
		System.out.println("姣旇緝缁撴潫");
	}
	
	@Test
	public void testReadLines() throws IOException {
		
		List<String> lines = FileUtils.readByLines("D:\\2019\\cms\\day2.7\\src\\main\\java"
				+ "\\com\\gonghanxun\\controller"
				+ "\\UserController.java");
		
		lines.forEach(x->{System.out.println(" x is " + x);});
	}
	
	
	@Test
	public void testCopy() throws IOException {
		String src="‪H:\\nginx-1.16.1.zip";
		String dst="‪H:\\nginx-1.16.12.zip";

		FileUtils.copy(src,dst );
	} 
}
