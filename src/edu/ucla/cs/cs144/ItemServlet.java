package edu.ucla.cs.cs144;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

public class ItemServlet extends HttpServlet implements Servlet {
       
    public ItemServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
       try{
			String xml = AuctionSearchClient.getXMLDataForItemId(request.getParameter("id"));
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    		DocumentBuilder builder = factory.newDocumentBuilder();
   			InputSource is = new InputSource(new StringReader(xml));
    		Document doc = builder.parse(is);
			//set id
			String idx = (String) request.getParameter("id");
			request.setAttribute("ID", idx);
			//set name
    		String name = doc.getElementsByTagName("Name").item(0).getTextContent();
			request.setAttribute("Name", name);
			//set categories
			NodeList catlist = doc.getElementsByTagName("Category");
    		String[] categories = new String[catlist.getLength()];
    		for (int i = 0; i < catlist.getLength(); i++) 
    			categories[i] = catlist.item(i).getTextContent();
            request.setAttribute("Categories", categories);
			//set buy price
			String buyprice = "";
    	    if (doc.getElementsByTagName("Buy_Price").getLength() == 0) {
    			buyprice = "";
    		}
			else
			   buyprice = doc.getElementsByTagName("Buy_Price").item(0).getTextContent();
			request.setAttribute("Buy_Price", buyprice);
			//set currently
    		String currently = doc.getElementsByTagName("Currently").item(0).getTextContent();
    		request.setAttribute("Currently", currently);
			//set firstbid
    		String firstbid = doc.getElementsByTagName("First_Bid").item(0).getTextContent();
			request.setAttribute("First_Bid", firstbid);
			//set numofbids
    		String numofbids = doc.getElementsByTagName("Number_of_Bids").item(0).getTextContent();
    		request.setAttribute("Number_of_Bids", numofbids);
			//set start
			String start = doc.getElementsByTagName("Started").item(0).getTextContent();
			request.setAttribute("Started", start);
			//set end
			String end = doc.getElementsByTagName("Ends").item(0).getTextContent();
			request.setAttribute("Ends", end);
    		//get seller info
			NodeList SellerList = doc.getElementsByTagName("Seller");
    		Element sellerelement = (Element) SellerList.item(0);
            //set seller
    		String seller = sellerelement.getAttribute("UserID");
			request.setAttribute("SellerID", seller);
    		//set sellerrating
			String sellerrating = sellerelement.getAttribute("Rating");
			request.setAttribute("SellerRating", sellerrating);
			//set description
    		String description = doc.getElementsByTagName("Description").item(0).getTextContent();
	        request.setAttribute("Description", description);
            //set bidder info
			NodeList tempbid = doc.getElementsByTagName("Bidder");
            NodeList temptime = doc.getElementsByTagName("Time");
			NodeList tempamount = doc.getElementsByTagName("Amount");
			NodeList temploc = doc.getElementsByTagName("Location");
			NodeList tempcountry = doc.getElementsByTagName("Country");
    		String[] BidID = new String[tempbid.getLength()];
			String[] Bidtime = new String[temptime.getLength()];
			String[] Bidamount = new String[tempamount.getLength()];
    		String[] Bidrating = new String[tempbid.getLength()];
			String[] Bidlocation = new String[temploc.getLength()];
			String[] Bidcountry = new String[tempcountry.getLength()];	
    		for (int i = 0; (i < tempbid.getLength()) &&  (i < temptime.getLength()) && (i < tempamount.getLength()) ; i++) 
			{
    			BidID[i] = ((Element)tempbid.item(i)).getAttribute("UserID");
				Bidtime[i] = temptime.item(i).getTextContent();
				Bidamount[i] = tempamount.item(i).getTextContent();
				Bidrating[i] = ((Element)tempbid.item(i)).getAttribute("Rating");		
    		}
			for(int i=0;i < temploc.getLength();i++)
			      Bidlocation[i] = temploc.item(i).getTextContent();
			for(int i=0;  i < tempcountry.getLength();i++)
				  Bidcountry[i] = tempcountry.item(i).getTextContent();
            request.setAttribute("BidID", BidID);
			request.setAttribute("BidTime", Bidtime);
			request.setAttribute("BidAmount", Bidamount);
			request.setAttribute("BidRating", Bidrating);
			request.setAttribute("BidLocation", Bidlocation);
			request.setAttribute("BidCountry", Bidcountry);
            request.getRequestDispatcher("/getItem.jsp").forward(request, response);
    	} catch (Exception e) {
		}
    }
}
