<%@ page import="edu.ucla.cs.cs144.SearchResult" %>
<html>
      <body onload="initialize()">
	  <form action="/eBay/item">
	       Search: <input type="text" name="id" />
		           <input type="submit" />
	  </form>
	  <h1><%=request.getAttribute("Name")%></h1>
	  <p><b>ID: </b> <%=request.getAttribute("ID")%></p>
	  <p><B>Categories:</b>
	  <% String[] categories = (String[]) request.getAttribute("Categories");

      %>
	  <%=categories[0]%>
	  <%
	     for(int i=1;i < categories.length;i++)
		 {
	  %>
	  <%= ", " + categories[i]%>
	  <%
	     }
	  %>
	  </p>
	  <% 
	       String buyprice = (String) request.getAttribute("Buy_Price");
	       if(!buyprice.equals("")){
	  %>
	  <p><b>Buy Price:</b> <%=request.getAttribute("Buy_Price")%> <a href="./creditCard?id=<%=request.getAttribute("ID")%>">Buy Now</a></p>
	  <%
	       }
	  %>
	  <p><b>Currently:</b>  <%=request.getAttribute("Currently")%></p>
	  <p><b>Number of Bids:</b>  <%=request.getAttribute("Number_of_Bids") %></p>
	  <p><b>First Bid:</b>  <%= request.getAttribute("First_Bid")%></p>
	  <p><b>Started:</b>  <%=request.getAttribute("Started")%></p>
	  <p><b>Ends:</b>  <%=request.getAttribute("Ends")%></p>
	  <p><b>Seller:</b>  <%=request.getAttribute("SellerID")%></p>
	  <p><b>Rating:</b>  <%=request.getAttribute("SellerRating") %></p>
	  <p><b>Description:</b> <%=request.getAttribute("Description") %></p>
	  <p>
      <% String[] BidID = (String[]) request.getAttribute("BidID");
	  	 String[] Bidtime = (String[]) request.getAttribute("BidTime");
     	 String[] Bidamount = (String[]) request.getAttribute("BidAmount");
	     String[] Bidrating = (String[]) request.getAttribute("BidRating");
	     String[] Bidlocation = (String[]) request.getAttribute("BidLocation");
	     String[] Bidcountry = (String[]) request.getAttribute("BidCountry");
		 for(int i = 0; i < BidID.length; i++) 
		 {
		   if(i == 0) 
		   {
	   %>
	     <b>Bids</b> <br />
		 <table border="2" cellpadding="5">
			<tr>
				<th>User</th>
				<th>Time</th>
				<th>Amount</th>
				<th>Rating</th>
				<th>Location</th>
				<th>Country</th>
			</tr>
	  <%
	       }
	  %>
		<tr> 
            <td><%= BidID[i]%></td>
			<td><%= Bidtime[i] %></td>
			<td><%= Bidamount[i] %></td>
			<td><%= Bidrating[i]%></td>
			<td><%= Bidlocation[i] %></td>
			<td><%= Bidcountry[i] %></td>

		</tr>
	   <% if (i == (BidID.length-1)) 
	       {
	   %>
		      </table>
	   <%
		   }
		 }		
	   %>
       </p>
	   <p><b>Location:</b><%= Bidlocation[Bidlocation.length-1] %></p>
       <p><b>Country:</b> <%= Bidcountry[Bidcountry.length-1] %></p>
       <% String geoloc = Bidlocation[Bidlocation.length-1] + ", " + Bidcountry[Bidcountry.length-1]; %>
	   <meta name="viewport" content="initial-scale=1.0, user-scalable=no" /> 
	   <script type="text/javascript"
	       src="http://maps.google.com/maps/api/js?sensor=false">
       </script>
       <script type="text/javascript">
       function initialize() { 
                      geocoder = new google.maps.Geocoder();
  	                  var latlng = "<%=geoloc%>";
                      geocoder.geocode({ 'address': latlng}, function(results, status)
					      {
							  var myOptions = {
												zoom: 14,
												center: results[0].geometry.location,
												mapTypeId: google.maps.MapTypeId.ROADMAP
											  };
							 var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions); 
							 map.setCenter(results[0].geometry.location);
                         }
					  );
       } 
	   </script>
	   <div id="map_canvas" style="width:50%; height:50%"></div> 
       </body>
</html>
	   