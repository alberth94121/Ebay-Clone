package edu.ucla.cs.cs144;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class CreditCardServlet extends HttpServlet implements Servlet {

	 public CreditCardServlet() {}
	 
	 protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
     {
	     HttpSession session = request.getSession(true);
		 if(session.isNew()==true){
		     request.setAttribute("valid","false");
			 session.invalidate();
		 }
		 else{
		      try{  
			        String xml = AuctionSearchClient.getXMLDataForItemId(request.getParameter("id"));
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					InputSource is = new InputSource(new StringReader(xml));
					Document doc = builder.parse(is);
					//set id
					String idx = (String) request.getParameter("id");
					request.setAttribute("ID",idx);
					//set name
					String name = doc.getElementsByTagName("Name").item(0).getTextContent();
					request.setAttribute("Name", name);
					//set buy price
					String buyprice = "";
					if (doc.getElementsByTagName("Buy_Price").getLength() != 0) {
    			           buyprice = doc.getElementsByTagName("Buy_Price").item(0).getTextContent();
    		        }
					request.setAttribute("Buy_Price",buyprice);
					request.setAttribute("valid","true");
			   }catch (Exception e){
			   }
		 }
		 request.getRequestDispatcher("./creditCard.jsp").forward(request,response);
	 }
	 
}