package dao;

import javax.naming.InitialContext;

public class StockListDAOFactory {
  public static StockListDAO getStockListDAO() {
    // this is a simple implementation of a
    // factory. if other impementations of
    // the dao interface are needed, this
    // method can be changed to get the
    // appropriate implementation.
    try {
      // Get a naming context
      InitialContext ctx = new InitialContext();

      // Get a StockListDAO object
      StockListDAO stockListDAO =
        (StockListDAO) ctx.lookup(Ejb3StockListDAO.class.getName());
      return stockListDAO;
    } catch(Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}