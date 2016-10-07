package client;

import beans.StockList;
import javax.naming.InitialContext;

// general imports
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class StockListAdder {

  public static void main(String[] args) {
    try {
      InitialContext ctx = new InitialContext();

      // Get a StockList object
      StockList stockList =
        (StockList) ctx.lookup(StockList.class.getName());

      // add analysts
      System.out.println("adding analysts");
      stockList.addAnalyst(new Integer(1), "Fred");
      stockList.addAnalyst(new Integer(2), "Leonard");
      stockList.addAnalyst(new Integer(3), "Sarah");
      stockList.addAnalyst(new Integer(4), "Nancy");
      System.out.println("analysts added");
    }
    catch (Exception e) {
      System.out.println("exception adding analysts");
      e.printStackTrace();
    }

    try {
      InitialContext ctx = new InitialContext();

      // Get a StockList object
      StockList stockList =
        (StockList) ctx.lookup(StockList.class.getName());

      // add stocks
      System.out.println("adding stocks");
      stockList.addStock("ABC", "ABC Company");
      stockList.addStock("ZZZ", "Zigby Zebras");
      stockList.addStock("ICS", "Internet Corp of Slobovia");
      stockList.addStock("DDC", "Digby Door Company");
      stockList.addStock("ZAP", "Zapalopalorinski Ltd.");
      stockList.addStock("JIM", "Jimco");
      stockList.addStock("SRU", "Stocks R Us");
      stockList.addStock("SRI", "Shelves and Radios Inc");
      stockList.addStock("FBC", "Foo Bar Company");
      stockList.addStock("DDBC", "Ding Dong Bell Company");
      stockList.addStock("UDE", "Upn Down Elevator Company");
      System.out.println("stocks added");
    }
    catch (Exception e) {
      System.out.println("exception adding stocks");
      e.printStackTrace();
    }

    try {
      InitialContext ctx = new InitialContext();

      // Get a StockList object
      StockList stockList =
        (StockList) ctx.lookup(StockList.class.getName());

      // add ratings
      System.out.println("adding ratings");
      stockList.addStockRating("ZZZ", new Integer(2),
        "Take a chance!");
      System.out.println("ratings added");
    }
    catch (Exception e) {
      System.out.println("exception adding stocks");
      e.printStackTrace();
    }
  }
}