package beans_2x;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.FinderException;
import javax.ejb.NoSuchEntityException;
import javax.ejb.ObjectNotFoundException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class StockBean implements EntityBean {

  // persistent fields
  private String tickerSymbol;
  private String name;

  // keeps the reference to the context
  private EntityContext context;

  // keeps the reference to the db connection
  private Connection connection;

  // the access methods for persistent fields
  public String getTickerSymbol() {
    return tickerSymbol;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // standard entity bean methods

  public String ejbFindByPrimaryKey(String primaryKey)
    throws FinderException {

    boolean result;

    try {
      String stmt =
        "select tickerSymbol " +
        "from stock where tickerSymbol = ? ";
      PreparedStatement pstmt =
        connection.prepareStatement(stmt);
      pstmt.setString(1, primaryKey);

      ResultSet rs = pstmt.executeQuery();
      result = rs.next();
      pstmt.close();
    }
    catch (SQLException ex) {
      throw new EJBException("ejbFindByPrimaryKey: " +
        ex.getMessage());
    }

    if (result) {
      return primaryKey;
    }
    else {
      throw new ObjectNotFoundException
        ("Ticker " + primaryKey + " not found.");
    }
  }

  public String ejbCreate(String tickerSymbol, String name)
    throws CreateException {

    try {
      String findstmt =
        "select tickerSymbol " +
        "from stock where tickerSymbol = ? ";
      PreparedStatement pfindstmt =
        connection.prepareStatement(findstmt);
      pfindstmt.setString(1, tickerSymbol);

      ResultSet rs = pfindstmt.executeQuery();
      boolean findresult = rs.next();
      if (findresult) {
        throw new CreateException("Ticker already exists!");
      }

      String stmt =
        "insert into stock values ( ? , ? )";
      PreparedStatement pstmt = connection.prepareStatement(stmt);

      pstmt.setString(1, tickerSymbol);
      pstmt.setString(2, name);

      pstmt.executeUpdate();
      pstmt.close();
    }
    catch (SQLException ex) {
      throw new EJBException("ejbCreate: " + ex.getMessage());
    }

    this.tickerSymbol = tickerSymbol;
    this.name = name;

    return tickerSymbol;
  }

  public void ejbPostCreate(String tickerSymbol, String name)
    throws CreateException { }

  public void ejbRemove() {
    try {
      String stmt =
        "delete from stock where tickerSymbol = ? ";
      PreparedStatement pstmt =
        connection.prepareStatement(stmt);

      pstmt.setString(1, tickerSymbol);
      pstmt.executeUpdate();
      pstmt.close();
    }
    catch (SQLException ex) {
      throw new EJBException("ejbRemove: " + ex.getMessage());
    }
  }

  public void ejbLoad() {
    try {
      String stmt =
        "select name from stock where tickerSymbol = ? ";
      PreparedStatement pstmt =
        connection.prepareStatement(stmt);

      pstmt.setString(1, tickerSymbol);

      ResultSet rs = pstmt.executeQuery();

      if (rs.next()) {
        this.name = rs.getString(1);
        pstmt.close();
      }
      else {
         pstmt.close();
         throw new NoSuchEntityException("Ticker: " +
           tickerSymbol + " not in database.");
      }
    }
    catch (SQLException ex) {
      throw new EJBException("ejbLoad: " + ex.getMessage());
    }
  }

  public void ejbStore() {
    try {
      String stmt =
            "update stock set name =  ? " +
            "where tickerSymbol = ?";
      PreparedStatement pstmt =
        connection.prepareStatement(stmt);

      pstmt.setString(1, name);
      pstmt.setString(2, tickerSymbol);
      int rowCount = pstmt.executeUpdate();
      pstmt.close();

      if (rowCount == 0) {
        throw new EJBException("Store for " +
          tickerSymbol + " failed.");
      }
    }
    catch (SQLException ex) {
      throw new EJBException("ejbStore: " + ex.getMessage());
    }
  }

  public void ejbPassivate() { }

  public void ejbActivate() { }

  public void setEntityContext(EntityContext ctx) {
    context = ctx;

    try {
      getDatabaseConnection();
    }
    catch (Exception ex) {
      throw new EJBException("Unable to connect to database. " +
      ex.getMessage());
    }
  }

  public void unsetEntityContext() {
    context = null;
    try {
      connection.close();
    }
    catch (SQLException ex) {
      throw new EJBException("unsetEntityContext: " +
        ex.getMessage());
    }
  }


  private void getDatabaseConnection()
    throws NamingException, SQLException {

    InitialContext ctx = new InitialContext();
    DataSource ds =
      (DataSource) ctx.lookup("java:comp/env/jdbc/StockDB");
    connection =  ds.getConnection();
  }
}