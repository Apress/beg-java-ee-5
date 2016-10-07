package transfer;

import java.io.Serializable;
import java.util.*;

public class AnalystTO implements Serializable {

  // holds references to the attribute data
  private Integer analystId;
  private String name;

  // holds references to the relationships
  private List stocks;

  public AnalystTO(Integer analystId, String name) {
    this.analystId = analystId;
    this.name = name;
    stocks = new ArrayList();
  }

  // get analyst id. no setter because primary key
  public Integer getAnalystId() {
    return analystId;
  }

  // get, set name
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // get stocks
  public List getStocks() {
    return stocks;
  }
}