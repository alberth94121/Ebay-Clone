<%@ page import="edu.ucla.cs.cs144.*" %>
<html>
      <head>
	        <p><b>Search</b></p>
            <a href="keywordSearch.html">Keyword Search</a><br>
	        <a href="getItem.html">Item Search</a>  
      </head>
      <body>
        	<h1>Buy Now</h1>   
            <% 
			    if(request.getAttribute("valid").equals("false")) { 
			%>
                   <p>Invalid Request</p>
            <% 
			    } 
			    else {
			%>
					<p>Enter payment information to purchase item.</p>			
					<p><b>Item ID: </b><%=request.getAttribute("ID")%> </p>  
					<p><b>Item Name: </b><%=request.getAttribute("Name") %> </p>
					<p><b>Buy Price: </b><%=request.getAttribute("Buy_Price")%> </p>
					<form action="https://<%=request.getServerName()%>:8443<%= request.getContextPath()%>/confirmation" method="POST">
					<label for="cc"><b>Credit Card: </b></label>
					<input class="form-control" id="cc" name="ccNumber" type="text"/> 
					<input type="hidden" name="id" value="<%=request.getAttribute("ID")%>" />
					<input type="hidden" name="name" value="<%=request.getAttribute("Name")%>" />
					<input type="hidden" name="buyprice" value="<%=request.getAttribute("Buy_Price")%>" />
					<button type="submit" class="btn btn-default">Purchase</button> <br>
					</form>
            <% 
			   } 
			%>  
      </body> 
</html>
          