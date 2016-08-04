package cn.chinaiptv.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FtpFileServlet
 */
public class FtpFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FtpFileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		GenerateWorkSheet generateWorkSheet = new GenerateWorkSheet();
		// generateWorkSheet.generateXML("zte", tarFileName, xmlName, fileSetID,
		// fileID, Action, group)
		try {
			// String
			// str=generateWorkSheet.generateXML("zte","zxyj0605_10.tar","12345zte"
			// ,"fileSetID", "fileID","REGIST-UPDATE","gdgaoqingn");
			CreateXml str = new CreateXml();
			str.create("1", "111", "zte", "fefefe", "222", "222");
			PrintWriter writer = response.getWriter();
			writer.write("1111");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
