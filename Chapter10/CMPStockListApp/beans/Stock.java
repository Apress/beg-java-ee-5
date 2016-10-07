package beans;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Stock implements Serializable {
  // the persistent fields
  private String tickerSymbol;
  private String name;

  // constructors
  public Stock() { }
  public Stock(String tickerSymbol, String name) {
    this.tickerSymbol = tickerSymbol;
    this.name = name;
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
}