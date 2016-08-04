package cn.chinaiptv.demo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class CreateXml {
	/**
	 * 生成xml文件，存在到ftp
	 * 
	 * @param idValue
	 * @param platformValue
	 * @param wPathValue
	 * @param mPathValue
	 * @param sValue
	 * @param tValue
	 */
	public void create(String idValue, String platformValue, String wPathValue,
			String mPathValue, String sValue, String tValue) {
		// 创建文档并设置根元素
		Element root = DocumentHelper.createElement("Workorder");
		Document document = DocumentHelper.createDocument(root);
		// 给根节点添加元素
		root.addAttribute("id", idValue);
		// 给根节点添加孩子节点
		Element platformElemment = root.addElement("下发平台");
		platformElemment.addAttribute("platform", platformValue).addText(
				platformValue);

		Element workorderElement = root.addElement("工单路径");
		workorderElement.addAttribute("WorkorderPath", wPathValue).addText(
				wPathValue);

		Element modulePathElement = root.addElement("模板路径");
		modulePathElement.addAttribute("ModulePath", mPathValue).addText(
				mPathValue);

		Element statusElement = root.addElement("下发状态");
		statusElement.addAttribute("status", sValue).addText(sValue);

		Element timeElement = root.addElement("下发时间");
		timeElement.addAttribute("time", tValue).addText(tValue);
		Logger logger = Logger.getLogger(CreateXml.class);
		// 把生成的xml文档存放在硬盘上,true表示是否换行
		OutputFormat format = new OutputFormat("", true);
		format.setEncoding("utf-8");
		String path = ParamsFromConfFile.tarPath_absolute() + "/";
		String uploadPath = path + platformValue + "WorkOrder_" + idValue
				+ ".xml";
		try {
			XMLWriter xmlwriter = new XMLWriter(
					new FileOutputStream(uploadPath), format);
			xmlwriter.write(document);
			xmlwriter.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error("出现异常，字符编码格式不支持");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error("出现异常，找不到的文件路径");
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("出现异常，文件读写出现异常 ");
		}
		System.out.println("dong");
	}

}
