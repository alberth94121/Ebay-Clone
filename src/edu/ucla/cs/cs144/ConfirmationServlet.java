package edu.ucla.cs.cs144;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.Date;

import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class ConfirmationServlet extends HttpServlet implements Servlet {

      protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {
	     HttpSession session = request.getSession(true);
		 if(session.isNew() == true || !request.isSecure()){
		     request.setAttribute("valid","false");
			 session.invalidate();
		 }
		 else{
		    String id = (String) request.getParameter("id");
            request.setAttribute("ID", id);
			request.setAttribute("Name",  request.getParameter("name"));
			request.setAttribute("Buy_Price",  request.getParameter("buyprice"));
			String cc = (String) request.getParameter("ccNumber");
			request.setAttribute("ccNumber",cc);
			Date date = new Date();
			request.setAttribute("Date",date.toString());
			request.setAttribute("valid","true");
			session.removeAttribute(id);
		 }
		 request.getRequestDispatcher("./confirmation.jsp").forward(request,response);
	   }
}