package client;

import beans.StockList;
import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.InitialContext;

// general imports
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StockClient extends JFrame 
  implements ActionListener { 
  private StockList _stockList;
  private JTextField _ticker = new JTextField();
  private JTextField _name = new JTextField();
  private JButton _get = new JButton("Get");
  private JButton _add = new JButton("Add");
  private JButton _update = new JButton("Update");
  private JButton _delete = new JButton("Delete");
      
  public StockClient() {
    // get the stock lister
    _stockList = getStockList();
    
    // add the title
    JLabel title = new JLabel("Stock List");
    title.setHorizontalAlignment(JLabel.CENTER);
    getContentPane().add(title, BorderLayout.NORTH);
    
    // add the stock label panel
    JPanel stockLabelPanel = new JPanel(new GridLayout(2, 1));
    stockLabelPanel.add(new JLabel("Symbol"));
    stockLabelPanel.add(new JLabel("Name"));
    getContentPane().add(stockLabelPanel, BorderLayout.WEST);
    
    // add the stock field panel
    JPanel stockFieldPanel = new JPanel(new GridLayout(2, 1));
    stockFieldPanel.add(_ticker);
    stockFieldPanel.add(_name);
    getContentPane().add(stockFieldPanel, BorderLayout.CENTER);
    
    // add the buttons
    JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
    _get.addActionListener(this);
    buttonPanel.add(_get);
    _add.addActionListener(this);
    buttonPanel.add(_add);
    _update.addActionListener(this);
    buttonPanel.add(_update);
    _delete.addActionListener(this);
    buttonPanel.add(_delete);
    getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    setSize(330, 130);
    setVisible(true);
  }
  
  private StockList getStockList() {
    StockList stockList = null;
    try {
      // Get a naming context
      InitialContext ctx = new InitialContext();

      // Get a StockList object
      stockList 
        = (StockList) ctx.lookup(StockList.class.getName());
    } catch(Exception e) {
      e.printStackTrace();
    }
    
    return stockList;
  }
  
  public void actionPerformed(ActionEvent ae) {
    // if get was clicked, get the stock
    if (ae.getSource() == _get) {
      getStock();
    }
    
    // if add was clicked, add the stock
    if (ae.getSource() == _add) {
      addStock();
    }
    
    // if update was clicked, update the stock
    if (ae.getSource() == _update) {
      updateStock();
    }
    
    // if delete was clicked, delete the stock
    if (ae.getSource() == _delete) {
      deleteStock();
    }
  }
  
  private void getStock() {
    // get the ticker
    String ticker = _ticker.getText();
    if (ticker == null || ticker.length() == 0) {
      JOptionPane.showMessageDialog(this, "Ticker is required");
      return;
    }
    
    // get the stock
    try {
      String name = _stockList.getStock(ticker);
      _name.setText(name);
    }
    catch (FinderException fe) {
      JOptionPane.showMessageDialog(this, "Not found!");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private void addStock() {
    // get the ticker
    String ticker = _ticker.getText();
    if (ticker == null || ticker.length() == 0) {
      JOptionPane.showMessageDialog(this, "Ticker is required");
      return;
    }
    
    // get the name
    String name = _name.getText();
    if (name == null || name.length() == 0) {
      JOptionPane.showMessageDialog(this, "Name is required");
      return;
    }
    
    // add the stock
    try {
      _stockList.addStock(ticker, name);
      JOptionPane.showMessageDialog(this, "Stock added!");
    }
    catch (CreateException fe) {
      JOptionPane.showMessageDialog(this, "Already found!");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private void updateStock() {
    // get the ticker
    String ticker = _ticker.getText();
    if (ticker == null || ticker.length() == 0) {
      JOptionPane.showMessageDialog(this, "Ticker is required");
      return;
    }
    
    // get the name
    String name = _name.getText();
    if (name == null || name.length() == 0) {
      JOptionPane.showMessageDialog(this, "Name is required");
      return;
    }
    
    //update the stock
    try {
      _stockList.updateStock(ticker, name);
      JOptionPane.showMessageDialog(this, "Stock updated!");
    }
    catch (FinderException fe) {
      JOptionPane.showMessageDialog(this, "Not found!");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private void deleteStock() {
    // get the ticker
    String ticker = _ticker.getText();
    if (ticker == null || ticker.length() == 0) {
      JOptionPane.showMessageDialog(this, "Ticker is required");
      return;
    }
    
    // delete the stock
    try {
      _stockList.deleteStock(ticker);
      JOptionPane.showMessageDialog(this, "Stock deleted!");
    }
    catch (FinderException fe) {
      JOptionPane.showMessageDialog(this, "Not found!");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static void main(String[] args) {
    StockClient stockClient = new StockClient();
  }
}