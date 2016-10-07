package business;

import dao.AnalystDAO;
import dao.StockDAO;
import dao.StockListDAO;
import dao.StockListDAOFactory;
import transfer.AnalystTO;
import transfer.StockTO;
import javax.ejb.Stateless;

// general imports
import java.util.*;

@Stateless
public class StockListBean implements StockList {
  
  // the public business methods. these must be coded in the 
  // interface also. 
  
  public List getStockRatings() {
    List stockList = new ArrayList();

    // get the rated stocks
    StockListDAO dao = StockListDAOFactory.getStockListDAO();
    List stocks = dao.getRatedStocks();
    for (int i = 0; i < stocks.size(); i++) {
      StockDAO stock = (StockDAO) stocks.get(i);
      StockTO stockTO = new StockTO(stock.getTickerSymbol(),
        stock.getName(), stock.getRating());
      AnalystDAO analyst = stock.getAnalyst();
      AnalystTO analystTO = new AnalystTO(analyst.getAnalystId(),
        analyst.getName());
      stockTO.setAnalyst(analystTO);
      stockList.add(stockTO);
    }
      
    return stockList;
  }
  
  public List getAllAnalysts() {
    List analystList = new ArrayList();
        
    // get the analysts
    StockListDAO dao = StockListDAOFactory.getStockListDAO();
    List analysts = dao.getAnalysts();
    for (int i = 0; i < analysts.size(); i++) {
      AnalystDAO analyst = (AnalystDAO) analysts.get(i);
      AnalystTO analystTO = new AnalystTO(analyst.getAnalystId(),
        analyst.getName());
      analystList.add(analystTO);
    }
      
    return analystList;
  }
  
  public List getUnratedStocks() {
    List stockList = new ArrayList();

    // get the rated stocks
    StockListDAO dao = StockListDAOFactory.getStockListDAO();
    List stocks = dao.getUnratedStocks();
    for (int i = 0; i < stocks.size(); i++) {
      StockDAO stock = (StockDAO) stocks.get(i);
      StockTO stockTO = new StockTO(stock.getTickerSymbol(),
        stock.getName(), stock.getRating());
      stockList.add(stockTO);
    }
      
    return stockList;
  }
  
  public void addStockRating(StockTO stockTO) {
    StockListDAO dao = StockListDAOFactory.getStockListDAO();
    dao.rateStock(stockTO.getTickerSymbol(),
      stockTO.getAnalyst().getAnalystId(),
      stockTO.getRating());
  }
  
  public void addAnalyst(AnalystTO analystTO) {
    StockListDAO dao = StockListDAOFactory.getStockListDAO();
    AnalystDAO analyst = new AnalystDAO();
    analyst.setAnalystId(analystTO.getAnalystId());
    analyst.setName(analystTO.getName());
    dao.addAnalyst(analyst);
  }
  
  public void addStock(StockTO stockTO) {
    StockListDAO dao = StockListDAOFactory.getStockListDAO();
    StockDAO stock = new StockDAO();
    stock.setTickerSymbol(stockTO.getTickerSymbol());
    stock.setName(stockTO.getName());
    dao.addStock(stock);
  }
}