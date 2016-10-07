package web;

import javax.servlet.*;
import javax.servlet.http.*;
import delegate.*;
import transfer.*;

public class AddRating extends HttpServlet {
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
    {
        try {
            String analyst = request.getParameter("analysts");
            Integer id = new Integer(analyst);
            String ticker = request.getParameter("stocks");
            String rating = request.getParameter("ratings");
            StockTO stockTO = new StockTO(ticker, null, rating);
            stockTO.setAnalyst(new AnalystTO(id, null));
            StockListDelegate delegate = StockListDelegate.getInstance();
            delegate.addStockRating(stockTO);
            request.setAttribute("data", delegate.getStockRatings());
            request.setAttribute("analysts", delegate.getAllAnalysts());
            request.setAttribute("unrated", delegate.getUnratedStocks());
            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = 
                context.getNamedDispatcher("RatingsForm");
            dispatcher.forward(request, response);
        } catch (Exception e) {

        }
    }
}
