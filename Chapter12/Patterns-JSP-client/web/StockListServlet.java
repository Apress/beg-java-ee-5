package web;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import delegate.*;

public class StockListServlet extends HttpServlet {
    public void doPost(HttpServletRequest request,
		       HttpServletResponse response)
    {
	doGet(request, response);
    }

    public void doGet(HttpServletRequest request,
		      HttpServletResponse response)
    {
	try {
	    List data = null;
	    RequestDispatcher dispatcher;
	    ServletContext context = getServletContext();
	    StockListDelegate delegate = StockListDelegate.getInstance();
	    String name = request.getPathInfo();
	    name = name.substring(1);
	    System.out.println("name="+name);
	    if ("AnalystForm".equals(name)) {
		data = delegate.getAllAnalysts();
		request.setAttribute("data", data);
	    } else if ("RatingsForm".equals(name)) {
		data = delegate.getStockRatings();
		request.setAttribute("data", data);
		request.setAttribute("analysts", delegate.getAllAnalysts());
		request.setAttribute("unrated", delegate.getUnratedStocks());
	    } else if ("AddRating".equals(name)) {
		//nothing to do here, just forward request
	    } else {
		name = "Error";
	    }	

	    dispatcher = context.getNamedDispatcher(name);
	    if (dispatcher == null) {
		dispatcher = context.getNamedDispatcher("Error");
	    }	    
	    dispatcher.forward(request, response);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
