package dao;

import java.io.Serializable;

public class Stock implements Serializable {
  private String tickerSymbol;
  private String name;
  private String rating;

  // relationship attributes
  private Analyst analyst;

  public String getTickerSymbol() {
    return tickerSymbol;
  }

  public void setTickerSymbol(String tickerSymbol) {
    this.tickerSymbol = tickerSymbol;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRating() {
    return rating;
  }

  public void setRating(String rating) {
    this.rating = rating;
  }

  public Analyst getAnalyst() {
    return analyst;
  }

  public void setAnalyst(Analyst analyst) {
    this.analyst = analyst;
  }
}