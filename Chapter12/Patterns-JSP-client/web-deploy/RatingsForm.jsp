<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Stock Ratings</title>
  </head>

  <body>
    <h1>Stock Ratings</h1>

  <%@ page import="java.util.*, transfer.*" %>
<%
    ArrayList stocks = (ArrayList) request.getAttribute("data");
    if (stocks != null && stocks.size() > 0) {
%>
    <form action="/stock/servlet/StockList/AddRating" method="post">
    <table border="1">
	<tr>
	  <th>Ticker</th>
	  <th>Analyst</th>
	  <th>Rating</th>
	</tr>
<%
      for (int i = 0; i < stocks.size(); i++) {
        StockTO stockInfo = (StockTO) stocks.get(i);
        String ticker = stockInfo.getTickerSymbol();
        String analyst = stockInfo.getAnalyst().getName();
        String rating = stockInfo.getRating();
%>
    <tr>
      <td><%= ticker %></td>
      <td><%= analyst %></td>
      <td><%= rating %></td>
    </tr>
<%
      }
%>
    </table>
    <table>
      <tr>
	<td>
	  <select name="analysts">
<%
	  ArrayList analysts = (ArrayList) request.getAttribute("analysts");
	  for (int i = 0; i < analysts.size(); i++) {
	    AnalystTO analyst = (AnalystTO) analysts.get(i);
%>
	    <option value="<%= analyst.getAnalystId() %>">
              <%= analyst.getName() %>
<%
	  }
%>
	  </select>
	</td>
	<td>
	  <select name="stocks">
<%
	  ArrayList unratedStocks =
	      (ArrayList) request.getAttribute("unrated");
	  for (int i = 0; i < unratedStocks.size(); i++) {
	    StockTO stock = (StockTO) unratedStocks.get(i);
%>
	    <option value="<%= stock.getTickerSymbol() %>">
              <%= stock.getTickerSymbol() %>
<%
	  }
%>
          </select>
	</td>
	<td>
	  <select name="ratings">
	    <option value="Run away! Run away!">Run away! Run away!
	    <option value="Could be worse!">Could be worse!
	    <option value="A bit of OK!">A bit of OK!
	    <option value="Take a chance!">Take a chance!
	    <option value="Smashing!">Smashing!
	  </select>
	</td>
      </tr>
      <tr>
	<td>
	  <input type="submit" value="Submit Rating">
	</td>
      </tr>
    </table>
    </form>
<%
    } else {
%>
    No stock information found
<%
    }
%>
    <hr>
    <address><a href="mailto:kmukhar@earthlink.net"></a></address>
<!-- Created: Thu Jan 02 13:25:09 Mountain Standard Time 2003 -->
<!-- hhmts start -->
Last modified: Thu Jan 02 17:52:35 Mountain Standard Time 2003
<!-- hhmts end -->
  </body>
</html>
