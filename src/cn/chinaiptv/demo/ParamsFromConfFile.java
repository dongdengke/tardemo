package cn.chinaiptv.demo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ParamsFromConfFile {
	
	static Properties p;
	static {
		try {
			String path = ParamsFromConfFile.class.getResource("/").getPath();
			String websiteURL = (path.replace("/build/classes", "")
					.replace("%20", " ").replace("classes/", "") + "globalParams.properties")
					.replaceFirst("/", "");
			InputStream in = new BufferedInputStream(new FileInputStream(websiteURL));
			 p = new Properties();
			 p.load(in);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String workSheetsPath_absolute() {
		return p.getProperty("workSheetsPath_absolute");
	}
	public static String tarPath_absolute() {
		return p.getProperty("tarPath_absolute");
	}
	public static String workSheetsPath_relative() {
		return p.getProperty("workSheetsPath_relative");
	}
	public static String tarPath_relative() {
		return p.getProperty("tarPath_relative");
	}
	
	
	public static String getIP(){
		return p.getProperty("ip");
	}
	public static String getPort(){
		return p.getProperty("port");
	}
	public static String getUserName(){
		return p.getProperty("username");
	}
	public static String getPwd(){
		return p.getProperty("pwd");
	}
	
}
