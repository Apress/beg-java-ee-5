package client;

import beans.StockList;
import javax.naming.InitialContext;

// general imports
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class StockClient extends JFrame 
  implements ActionListener, ChangeListener { 
  private StockList _stockList;
  private String _ticker;
  private JPanel _stockPanel = new JPanel();
  private JCheckBox _threeOnly =
    new JCheckBox("3 Letter Tickers Only");
      
  public StockClient() {
    // get the stock lister
    _stockList = getStockList();

    // add the title
    JLabel title = new JLabel("Stock List");
    title.setHorizontalAlignment(JLabel.CENTER);
    getContentPane().add(title, BorderLayout.NORTH);

    try {
      // add the stock list
      String[] stocks = _stockList.getAllStocks();
      populateStockPanel(stocks);
      JScrollPane scroller = new JScrollPane(_stockPanel);
      getContentPane().add(scroller, BorderLayout.CENTER);
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    // add the buttons panel
    JPanel buttons = new JPanel(new GridLayout(1, 2));
    JButton get = new JButton("Get");
    get.addActionListener(this);
    buttons.add(get);
    buttons.add(_threeOnly);
    _threeOnly.addChangeListener(this);
    getContentPane().add(buttons, BorderLayout.SOUTH);

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    setSize(330, 150);
    setVisible(true);
  }

  private StockList getStockList() {
    StockList stockList = null;
    try {
      // Get a naming context
      InitialContext ctx = new InitialContext();

      // Get a StockList object
      stockList =
        (StockList) ctx.lookup(StockList.class.getName());
    } catch(Exception e) {
      e.printStackTrace();
    }
    return stockList;
  }

  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() instanceof JRadioButton) {
      JRadioButton rdo = (JRadioButton) ae.getSource();
      _ticker = rdo.getText();
    }

    // if get was clicked, get the stock
    if (ae.getSource() instanceof JButton) {
      if (_ticker == null || _ticker.length() == 0) {
        JOptionPane.showMessageDialog(this, "Select a stock!");
        return;
      }

      // get the stock
      try {
        String stockName = _stockList.getStock(_ticker);
        String msg = _ticker + " - " + stockName;
        JOptionPane.showMessageDialog(this, msg);
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void stateChanged(ChangeEvent ce) {
    try {
      if (_threeOnly.isSelected()) {
        String[] stocks = _stockList.getSizeStocks(3);
        populateStockPanel(stocks);
      } else {
        String[] stocks = _stockList.getAllStocks();
        populateStockPanel(stocks);
      }    
    }
    catch (Exception e) {
      e.printStackTrace(); 
    }
  }

  private void populateStockPanel(String[] stocks) {
    _stockPanel.removeAll();
    _stockPanel.setLayout(new GridLayout(stocks.length, 1));

    ButtonGroup bg = new ButtonGroup();
    for (int i = 0; i < stocks.length; i++) {
      JRadioButton stockButton = new JRadioButton(stocks[i]);
      bg.add(stockButton);
      stockButton.addActionListener(this);
      _stockPanel.add(stockButton);
    }
    _stockPanel.invalidate();
    _stockPanel.validate();
  }

  public static void main(String[] args) {
    StockClient stockClient = new StockClient();
  }
}