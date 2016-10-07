<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Analyst Management</title>
  </head>
  <body>
    <%@ page import="java.util.*,transfer.*" %>
    <h1>Analyst Management Form</h1>
    <form action="/stock/servlet/ProcessAnalyst" method="POST">
      <table>
        <%
        ArrayList anlysts = (ArrayList) request.getAttribute("data");
        if (anlysts == null) {
        %>
          <h2> Attribute is null </h2>
        <%
        } else {
          for (int i = 0; i < anlysts.size(); i++) {
            AnalystTO analystData = (AnalystTO) anlysts.get(i);
        %>
          <tr>
            <td>
              <input type="checkbox" name="checkbox" 
                     value="<%= analystData.getName() %>"
            </td>
            <td>
              <%= analystData.getName() %>
            </td>
          </tr>
        <%
          }
        }
        %>
      </table>
      <input type="submit" value="Delete Selected" name="delete">
      <p>
        <input type="text" size="40" name="addname">
        <input type="submit" value="Add New Analyst" name="add">
    </form>
    <hr>
    <address><a href="mailto:kmukhar@FIORE"></a></address>
  </body>
</html>
