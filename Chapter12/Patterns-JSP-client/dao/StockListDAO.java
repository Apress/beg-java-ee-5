package dao;

import java.util.List;

public interface StockListDAO {
  public List getRatedStocks();
  public List getAnalysts();
  public List getUnratedStocks();
  public void rateStock(String ticker, Integer analystId,
    String rating);
  public void addAnalyst(AnalystDAO analyst);
  public void addStock(StockDAO stock);
}