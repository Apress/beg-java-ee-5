package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Stock implements Serializable {
  // the persistent fields
  private String tickerSymbol;
  private String name;
  private String rating;
  // the cmr fields
  private Analyst analyst;

  // constructors
  public Stock() { }
  public Stock(String tickerSymbol, String name) {
    this.tickerSymbol = tickerSymbol;
    this.name = name;
    this.rating = null;
  }

  // the access methods for persistent fields
  // tickerSymbol is the id
  @Id
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

  // the access methods for cmr fields
  @ManyToOne
  public Analyst getAnalyst() {
    return analyst;
  }

  public void setAnalyst(Analyst analyst) {
    this.analyst = analyst;
  }
}