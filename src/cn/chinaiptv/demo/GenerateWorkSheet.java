package cn.chinaiptv.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 产生工单
 * 
 * @author ddk
 *
 */
public class GenerateWorkSheet {
	/**
	 * 生成工单
	 */
	public static String generateXML(String platform, String tarFileName,
			String xmlName, String fileSetID, String fileID, String Action,
			String group) throws Exception {
		// 计算MD5
		String md5Value = FileMd5Utils.getFileMD5String(ParamsFromConfFile
				.tarPath_absolute() + "/" + tarFileName);
		String sourceUrl = "ftp://" + ParamsFromConfFile.getUserName() + ":"
				+ ParamsFromConfFile.getPwd() + "@"
				+ ParamsFromConfFile.getIP() + ":"
				+ ParamsFromConfFile.getPort() + "/"
				+ ParamsFromConfFile.tarPath_relative() + "/" + tarFileName;
		EPGFileSetProperty fsp = new EPGFileSetProperty();
		fsp.setEPGGroup(group);
		fsp.setNeedUnTar("1");
		EPGFileProperty fp = new EPGFileProperty();
		fp.setSourceUrl(sourceUrl);
		fp.setDestPath("/en");
		fp.setDestFile(tarFileName);
		fp.setMD5(md5Value);
		File xmlFile = File.createTempFile(xmlName, ".xml", new File(
				ParamsFromConfFile.workSheetsPath_absolute()));
		initEmptyXML(xmlFile);
		Document doc = getDocument(xmlFile.getPath());
		// 得到根元素
		Element root = doc.getRootElement();
		Element objectsElement = root.element("Objects");
		addEPGFileSetChildElement(objectsElement, fileSetID, fsp);
		addEPGFileChildElement(objectsElement, fileID, Action, fp);
		write2xml(doc, xmlFile.getPath());
		String xmlAccessPath = "ftp://" + ParamsFromConfFile.getUserName()
				+ ":" + ParamsFromConfFile.getPwd() + "@"
				+ ParamsFromConfFile.getIP() + ":"
				+ ParamsFromConfFile.getPort() + "/"
				+ ParamsFromConfFile.workSheetsPath_relative() + "/"
				+ xmlFile.getName();
		return xmlAccessPath;
	}

	private static void addEPGFileSetChildElement(Element objectsElement,
			String ID, EPGFileSetProperty fsp) {
		Element object = objectsElement.addElement("Object");
		object.addAttribute("ElementType", "EPGFileSet");
		object.addAttribute("ID", ID);
		// 利用反射优化重复代码
		Class clazz = fsp.getClass();
		Field[] fileds = clazz.getDeclaredFields();
		for (Field f : fileds) {
			f.setAccessible(true);
			String filedName = f.getName();
			String fieldValue = null;
			try {
				fieldValue = (String) (f.get(fsp));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (fieldValue != null) {
				Element EPGGroupProperty = object.addElement("Property");
				EPGGroupProperty.addAttribute("Name", filedName);
				EPGGroupProperty.setText(fieldValue);
			}
		}
	}

	private static void addEPGFileChildElement(Element objectsElement,
			String ID, String Action, EPGFileProperty fp) {
		Element object = objectsElement.addElement("Object");
		object.addAttribute("ElementType", "EPGFile");
		object.addAttribute("ID", ID);
		object.addAttribute("Action", Action);

		// 利用反射优化重复代码
		Class clazz = fp.getClass();
		Field[] fileds = clazz.getDeclaredFields();
		for (Field f : fileds) {
			f.setAccessible(true);
			String filedName = f.getName();
			String fieldValue = null;
			try {

				fieldValue = (String) (f.get(fp));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (fieldValue != null) {
				Element EPGGroupProperty = object.addElement("Property");
				EPGGroupProperty.addAttribute("Name", filedName);
				EPGGroupProperty.setText(fieldValue);
			}
		}
	}

	public static Document getDocument(String path) throws Exception {
		SAXReader sa = new SAXReader();
		Document doc = sa.read(path);
		return doc;
	}

	public static void write2xml(Document doc, String path) throws Exception {
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer = new XMLWriter(new FileOutputStream(path), format);
		writer.write(doc);
		writer.close();
	}

	private static void initEmptyXML(File xmlFile) throws IOException {
		FileWriter fw = new FileWriter(xmlFile);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		bw.newLine();
		bw.write("<ADI xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
		bw.newLine();
		bw.write("  <Objects>");
		bw.newLine();
		bw.write("  </Objects>");
		bw.newLine();
		bw.write("</ADI>");
		bw.newLine();
		bw.close();

	}

}
