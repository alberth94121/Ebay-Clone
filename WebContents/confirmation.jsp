<%@ page import="edu.ucla.cs.cs144.*" %>

<html>  
    <head>  
	       <p><b>Search</b></p>
           <a href="keywordSearch.html">Keyword Search</a><br>
	       <a href="getItem.html">Item Search</a>    
          
    </head> 
    <body>
			<h1>Payment Confirmation</h1>  
			<%
				if (request.getAttribute("valid").equals("false")) { 
			%>
						<p><b>Purchase Failed!</b></p>
			<%
			   } 
			   else { 
			%>
					   <p><b>Purchase Succesful!</b></p>
					   <p><b>Item ID: </b><%=request.getAttribute("ID")%> </p>  
					   <p><b>Item Name: </b><%=request.getAttribute("Name") %> </p>
					   <p><b>Buy Price: </b><%=request.getAttribute("Buy_Price")%> </p>
					   <p><b>Credit Card Number: </b><%=request.getAttribute("ccNumber") %></p>
					   <p><b>Time: </b> <%=request.getAttribute("Date") %></p>
			<% 
			   } 
			%>
    </body> 
</html>