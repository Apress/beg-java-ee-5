package beans;

import beans.Stock;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class StockListBean implements StockList {

  // the reference to the entity manager
  @PersistenceContext
  private EntityManager _manager;

  // the public business methods. these must be coded in the 
  // interface also. 

  public String getStock(String ticker) throws StockException {
    Stock stock = _manager.find(Stock.class, ticker);
    if (stock == null) {
      throw new StockException();
    }
    return stock.getName();
  }

  public void addStock(String ticker, String name)
    throws StockException {
    Stock stock = _manager.find(Stock.class, ticker);
    if (stock != null) {
      throw new StockException();
    }
    _manager.persist(new Stock(ticker, name));
  }

  public void updateStock(String ticker, String name)
    throws StockException{
    Stock stock = _manager.find(Stock.class, ticker);
    if (stock == null) {
      throw new StockException();
    }
    stock.setName(name);
  }

  public void deleteStock(String ticker)
    throws StockException {
    Stock stock = _manager.find(Stock.class, ticker);
    if (stock == null) {
      throw new StockException();
    }
    _manager.remove(stock);
  }
}