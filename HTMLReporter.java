package com.samplereprot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.TestListenerAdapter;
import org.testng.xml.XmlSuite;

public class HTMLReporter extends TestListenerAdapter implements IReporter
{
	private static PrintWriter f_out;
	private static String outputDir;


	public static void main (String[] aaaa) throws Exception {

		String statusFlag="Y";
		List<String> list = new ArrayList<String>();
		list.add("Selenium app");
		list.add("UFT/QTP app");
		list.add("Oracle app");
		list.add("SOA Service");

		try
		{
			f_out = createWriter("C:\\Users\\user\\Downloads\\seleniumReporting\\html");
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		startHtmlPage(f_out, list, statusFlag);
		endHtmlPage(f_out);

	}

	private static PrintWriter createWriter(String outdir) throws IOException
	{
		new File(outdir).mkdirs();
		return new PrintWriter(new BufferedWriter(
				new FileWriter(new File(outputDir, "AUTOMATION_EXECUTION_DASHBOARD.html"))));
	}

	/** Starts HTML Stream */
	private static void startHtmlPage(PrintWriter out, List<String> appNames, String statusFlag)
	{
		out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">");
		out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		out.println("<head>");

		out.println("<title>Test Automation  Execution Report</title>");
		out.println("</head>");

		out.println("<body><div style=\"margin:0 auto; padding:15px; min-height:450px; min-width: 450px; height:auto;\">"
				+ "<div style=\"height:auto; border-style: groove;border-color:pink;border-width: 5px;background: ##e5e5e5;padding:5px;box-shadow: 0 10px 6px -6px #777 \">"
				+ "<h1 style=\"background-color: #CF1034; color: white; text-align: center;border-style: groove;border-color:pink; padding:3px;font-family: Lucida Sans Unicode, sans-serif !important;\">Sample Test Automation Execution Report</h1>");

		Calendar currentdate = Calendar.getInstance();
		String strdate = null;
		DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy 'at' HH:mm:ss z");
		strdate = formatter.format(currentdate.getTime());
		out.println("<br/><div id = \"ReportTime\"align=\"right\"><b><font color=\"red\" face=\"Candara\">Report generated on: </font></b><font face=\"calibri\" size=\"2\"><b>" + strdate + "</b><font></div><br></br>");

		////
		f_out.println("<br><b><font color=\"red\" face=\"Lucida Sans Unicode\" size=\"4\"><u>Overall Execution Summary:</u></font></b>");


		f_out.println("<table style=\"border-collapse: collapse; width: 50%;\"><thead><tr><th style=\"background-color: #93b874; border: 1px solid grey; height: 25px; color: white; font-family: Calibri;\" >Application Name</th><th style=\"background-color: #93b874; border: 1px solid grey; height: 25px; color: white; font-family: Calibri;\" >Status</th></tr> </thead> "
				+ "<tbody>");
		for (int row=0; row<appNames.size();row++) {
			f_out.println(" <tr>"
					+ "<td style=\"text-align: center; border: 1px solid grey; height: 25px; color: #1C1C1C; font-family: Calibri;\">"+ "<b>" + appNames.get(row) + "</b></td>"
					+ "<td style=\"text-align: center; border: 1px solid grey; height: 25px; color: #1C1C1C; font-family: Calibri;\">");

			if (statusFlag.equalsIgnoreCase("Y")) {
				f_out.println(" PASS");	
			}else {
				f_out.println(" FAIL");	
			}

			f_out.println("</td>"
					+ "</tr>");
		}
		f_out.println("</tbody></table>");      
		out.flush();
	}

	/** Finishes HTML Stream */
	private static void endHtmlPage(PrintWriter out)
	{	
		out.println("</body></html>");
	}


	@Override
	public void generateReport(List<XmlSuite> arg0, List<ISuite> arg1, String arg2) {
		// TODO Auto-generated method stub

	}
}
