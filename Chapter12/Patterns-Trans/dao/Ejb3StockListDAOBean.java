package dao;

import entity.Analyst;
import entity.Stock;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.*;

@Stateless
public class Ejb3StockListDAOBean implements Ejb3StockListDAO {
  // the reference to the entity manager
  @PersistenceContext
  private EntityManager _manager;

  public List getRatedStocks() {
    Query query =
      _manager.createQuery("SELECT DISTINCT OBJECT(s) " +
                           "FROM Stock s " +
                           "WHERE s.analyst IS NOT NULL " +
                           "ORDER BY s.tickerSymbol");
    // get the rated stocks
    List stocks = query.getResultList();
    List stockList = new ArrayList();
    for (int i = 0; i < stocks.size(); i++) {
      Stock stockBean = (Stock) stocks.get(i);
      StockDAO stock = new StockDAO();
      stock.setTickerSymbol(stockBean.getTickerSymbol());
      stock.setName(stockBean.getName());
      stock.setRating(stockBean.getRating());
      Analyst analystBean = stockBean.getAnalyst();
      AnalystDAO analyst = new AnalystDAO();
      analyst.setAnalystId(analystBean.getAnalystId());
      analyst.setName(analystBean.getName());
      stock.setAnalyst(analyst);
      stockList.add(stock);
    }

    return stockList;
  }

  public List getAnalysts() {
    Query query =
      _manager.createQuery("SELECT Object(a) " +
                           "FROM Analyst a " +
                           "ORDER BY a.name");

    // get the analysts
    List analysts = query.getResultList();
    List analystList = new ArrayList();
    for (int i = 0; i < analysts.size(); i++) {
      Analyst analystBean = (Analyst) analysts.get(i);
      AnalystDAO analyst = new AnalystDAO();
      analyst.setAnalystId(analystBean.getAnalystId());
      analyst.setName(analystBean.getName());
      analystList.add(analyst);
    }

    return analystList;
  }

  public List getUnratedStocks() {
    Query query =
      _manager.createQuery("SELECT DISTINCT OBJECT(s) " +
                               "FROM Stock s " +
                               "WHERE s.analyst IS NULL " +
                               "ORDER BY s.tickerSymbol");
    // get the unrated stocks
    List stocks = query.getResultList();
    List stockList = new ArrayList();
    for (int i = 0; i < stocks.size(); i++) {
      Stock stockBean = (Stock) stocks.get(i);
      StockDAO stock = new StockDAO();
      stock.setTickerSymbol(stockBean.getTickerSymbol());
      stock.setName(stockBean.getName());
      stockList.add(stock);
    }

    return stockList;
  }

  public void rateStock(String ticker, Integer analystId,
    String rating) {
    Stock stock = _manager.find(Stock.class, ticker);
    Analyst analyst = _manager.find(Analyst.class, analystId);
    analyst.assignStock(stock);
    stock.setRating(rating);
  }

  public void addAnalyst(AnalystDAO analyst) {
    _manager.persist(
      new Analyst(analyst.getAnalystId(), analyst.getName()));
  }

  public void addStock(StockDAO stock) {
    _manager.persist(
      new Stock(stock.getTickerSymbol(), stock.getName()));
  }
}