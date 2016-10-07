package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.*;

@Entity
public class Analyst implements Serializable {
  // the persistent fields
  private Integer analystId;
  private String name;
  // the cmr fields
  private Collection<Stock> stocks;

  // constructors
  public Analyst() { }
  public Analyst(Integer analystId, String name) {
    this.analystId = analystId;
    this.name = name;
  }

  // the access methods for persistent fields
  @Id
  public Integer getAnalystId() {
    return analystId;
  }

  public void setAnalystId(Integer analystId) {
    this.analystId = analystId;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // the access methods for cmr fields
  @OneToMany(mappedBy="analyst")
  public Collection<Stock> getStocks()
  {
    return stocks;
  }

  public void setStocks(Collection<Stock> stocks)
  {
    this.stocks = stocks;
  }
  
  // business methods
  public void assignStock(Stock stock) {
    if (stocks == null) {
      stocks = new ArrayList();
    }

    stock.setAnalyst(this);
    stocks.add(stock);
  }
}