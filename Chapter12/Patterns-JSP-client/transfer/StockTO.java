package transfer;

import java.io.Serializable;
import java.util.*;

public class StockTO implements Serializable {

  // holds references to the attribute data
  private String tickerSymbol;
  private String name;
  private String rating;

  // holds references to the relationships
  private AnalystTO analyst;

  public StockTO(String tickerSymbol, String name, String rating) {
    this.tickerSymbol = tickerSymbol;
    this.name = name;
    this.rating = rating;
    analyst = null;
  }

  // get ticker symbol. no setter because primary key
  public String getTickerSymbol() {
    return tickerSymbol;
  }

  // get, set name
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // get, set rating
  public String getRating() {
    return rating;
  }

  public void setRating(String rating) {
    this.rating = rating;
  }

  // get, set analyst
  public AnalystTO getAnalyst() {
    return analyst;
  }

  public void setAnalyst(AnalystTO analyst) {
    this.analyst = analyst;
  }
}