package client;

import delegate.StockListDelegate;
import transfer.AnalystTO;
import transfer.StockTO;

public class StockListAdder {

  public static void main(String[] args) {
    try {
      StockListDelegate delegate = StockListDelegate.getInstance();

      // add analysts
      System.out.println("adding analysts");

      delegate.addAnalyst(new AnalystTO(new Integer(1), "Fred"));
      delegate.addAnalyst(new AnalystTO(new Integer(2), "Leonard"));
      delegate.addAnalyst(new AnalystTO(new Integer(3), "Sarah"));
      delegate.addAnalyst(new AnalystTO(new Integer(4), "Nancy"));
      System.out.println("analysts added");
    }
    catch (Exception e) {
      System.out.println("exception adding analysts");
      e.printStackTrace();
    }

    try {
      StockListDelegate delegate = StockListDelegate.getInstance();

      // add stocks
      System.out.println("adding stocks");
      delegate.addStock(new StockTO("ABC", "ABC Company", null));
      delegate.addStock(new StockTO("ZZZ", "Zigby Zebras", null));
      delegate.addStock(new StockTO("ICS", "Internet Corp of Slobovia", null));
      delegate.addStock(new StockTO("DDC", "Digby Door Company", null));
      delegate.addStock(new StockTO("ZAP", "Zapalopalorinski Ltd.", null));
      delegate.addStock(new StockTO("JIM", "Jimco", null));
      delegate.addStock(new StockTO("SRU", "Stocks R Us", null));
      delegate.addStock(new StockTO("SRI", "Shelves and Radios Inc", null));
      delegate.addStock(new StockTO("FBC", "Foo Bar Company", null));
      delegate.addStock(new StockTO("DDBC", "Ding Dong Bell Company", null));
      delegate.addStock(new StockTO("UDE", "Upn Down Elevator Company", null));
      System.out.println("stocks added");
    }
    catch (Exception e) {
      System.out.println("exception adding stocks");
      e.printStackTrace();
    }

    try {
      StockListDelegate delegate = StockListDelegate.getInstance();

      // add ratings
      System.out.println("adding ratings");
      StockTO stockTO = new StockTO("ZZZ", null, "Take a chance!");
      stockTO.setAnalyst(new AnalystTO(new Integer(2), null));
      delegate.addStockRating(stockTO);
      System.out.println("ratings added");
    }
    catch (Exception e) {
      System.out.println("exception adding stocks");
      e.printStackTrace();
    }
  }
}