package edu.ucla.cs.cs144;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet extends HttpServlet implements Servlet {
       
    public SearchServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String query = request.getParameter("q");
		String skip = request.getParameter("numResultsToSkip");
		String ret = request.getParameter("numResultsToReturn");
		int numResultsToReturn = 0;
		int numResultsToSkip = 0;
		if(skip != null)
		   numResultsToSkip = Integer.valueOf(skip);
		if(ret != null)
		   numResultsToReturn = Integer.valueOf(ret);
		SearchResult[] results = AuctionSearchClient.basicSearch(query,numResultsToSkip,numResultsToReturn);
		request.setAttribute("results",results);
		request.setAttribute("query",query);
		request.setAttribute("numResultsToSkip", numResultsToSkip);
	    request.setAttribute("numResultsToReturn", numResultsToReturn);
   	    request.getRequestDispatcher("/search.jsp").forward(request,response);
    }
}
