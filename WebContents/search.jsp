<%@ page import="edu.ucla.cs.cs144.SearchResult" %>
<%
   String query = request.getAttribute("query").toString();
   String skip = request.getAttribute("numResultsToSkip").toString();
   String ret = request.getAttribute("numResultsToReturn").toString();
   int numtoreturn = 0;
   int numtoskip = 0;
   if(skip != null)
       numtoskip = Integer.valueOf(skip);
   if(ret != null)
	   numtoreturn = Integer.valueOf(ret);
 
%>
<html>
	<head>
		<script type="text/javascript" src="autosuggest.js"></script>
		<link rel="stylesheet" type="text/css" href="autosuggest.css" />    
		<script type="text/javascript">
    	window.onload = function () {
				var oTextbox = new AutoSuggestControl(document.getElementById("searchbox"));        
      }
		</script>	
    </head>	
	   <body>
	        <form action="/eBay/search">
			     Enter: <input type="text" name="q" id="searchbox" value="<%= query%>" autocomplete="off" style="width:500px;"/>
				        <input type="hidden" name="numResultsToSkip" value="0" />
						<input type="hidden" name="numResultsToReturn" value="11" />
					    <input type="submit"/> <br />
			</form>
			<%
			    SearchResult[] results = (SearchResult[]) request.getAttribute("results");
				int count = results.length;
				if(count > 0)
				{ 
				    if(!results[0].getItemId().equals("-1"))
					{
			%>	
			<p><b>Results</b></p>
		    <%
					   if(numtoskip > 0)
					   {
			%>
			             <a href="/eBay/search?q=<%= query %>&numResultsToSkip=<%= numtoskip-10 %>&numResultsToReturn=11">Previous</a>
			<%
			           }
				       if(results.length - 10 > 0)
				       {
			%>
			            <a href="/eBay/search?q=<%= query %>&numResultsToSkip=<%=numtoskip+10 %>&numResultsToReturn=11">Next</a>
			<% 
			           }
			    
			%>
			<br /><br />
			<%
			           count = 0;
			           for(SearchResult result : results)
			           {
						    if(count == 10)
							    break;
			%>
			                <%=result.getName()%> : <a href="/eBay/item?id=<%= result.getItemId() %>" > <%= result.getItemId() %> </a><br /><br />
			<%
						    count++;
					   }
				    }
				}
			%>
       </body> 
</html>
			
			  