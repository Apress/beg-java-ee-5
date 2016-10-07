package client;

import beans.StockList;
import javax.naming.InitialContext;

import java.util.*;

// general imports
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StockClient extends JFrame 
  implements ActionListener { 
  private StockList _stockList;
  private Integer[] _analystIds;
  private JButton _get = new JButton("Add Rating");
  private JPanel _stockPanel = new JPanel();
  private JComboBox _analysts = new JComboBox();
  private JComboBox _tickers = new JComboBox();
  private JComboBox _ratings = new JComboBox();
      
  public StockClient() {
    // get the stock lister
    _stockList = getStockList();
    
    // add the title
    JLabel title = new JLabel("Stock Rating List");
    title.setHorizontalAlignment(JLabel.CENTER);
    getContentPane().add(title, BorderLayout.NORTH);
    
    JPanel activityPanel = new JPanel(new BorderLayout());
    try {
      // add the stock list
      buildStockList();
      JScrollPane scroller = new JScrollPane(_stockPanel);
      activityPanel.add(scroller, BorderLayout.CENTER);
      
      // add the rating panel
      JPanel ratingPanel = new JPanel(new GridLayout(1, 3));
      // add the analysts
      populateAnalysts();
      ratingPanel.add(_analysts);
      // add the unrated stocks
      populateTickers();
      ratingPanel.add(_tickers);
      // add the ratings to pick from
      _ratings.addItem("Run away! Run away!");
      _ratings.addItem("Could be worse!");
      _ratings.addItem("A bit of OK!");
      _ratings.addItem("Take a chance!");
      _ratings.addItem("Smashing!");
      ratingPanel.add(_ratings);
      activityPanel.add(ratingPanel, BorderLayout.SOUTH);
      
      getContentPane().add(activityPanel, BorderLayout.CENTER);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    
    // add the buttons panel
    JPanel buttons = new JPanel(new GridLayout(1, 1));
    _get.addActionListener(this);
    buttons.add(_get);
    getContentPane().add(buttons, BorderLayout.SOUTH);
    
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    setSize(480, 250);
    setVisible(true);
  }
  
  private void buildStockList() throws Exception {
    java.util.List stoks = _stockList.getStockRatings();
    _stockPanel.removeAll();
    _stockPanel.setLayout(new GridLayout(stoks.size(), 1));
    for (int i = 0; i < stoks.size(); i++) {
      String[] stokInfo = (String[]) stoks.get(i);
      Box stokLine = Box.createHorizontalBox();
      String stokDesc = stokInfo[0] + " : " + stokInfo[1]
        + " ==> " + stokInfo[2] + " rates it: " + stokInfo[3];
      stokLine.add(new JLabel(stokDesc));
      _stockPanel.add(stokLine);
    }
    _stockPanel.invalidate();
    _stockPanel.validate();
  }
  
  private void populateAnalysts() throws Exception {
    java.util.List anlysts = _stockList.getAllAnalysts();
    _analystIds = new Integer[anlysts.size()];
    for (int i = 0; i < anlysts.size(); i++) {
      Object[] analystData = (Object[]) anlysts.get(i);
      _analystIds[i] = (Integer) analystData[0];
      _analysts.addItem((String) analystData[1]);
    }
  }
  
  private void populateTickers() throws Exception {
    _tickers.removeAllItems();
    java.util.List tkrs = _stockList.getUnratedStocks();
    for (int i = 0; i < tkrs.size(); i++) {
      String ticker = (String) tkrs.get(i);
      _tickers.addItem(ticker);
    }
    _tickers.invalidate();
    _tickers.validate();
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
    // get was clicked
    if (ae.getSource() == _get) {
      try {
        int anlystNo = _analysts.getSelectedIndex();
        if (anlystNo < 0) {
          JOptionPane.showMessageDialog(this, "No analyst selected!");
          return;
        }
        Integer aId = _analystIds[anlystNo];
        if (_tickers.getSelectedIndex() < 0) {
          JOptionPane.showMessageDialog(this, "No ticker selected!");
          return;
        }
        String tkr = (String) _tickers.getSelectedItem();
        if (_ratings.getSelectedIndex() < 0) {
          JOptionPane.showMessageDialog(this, "No rating selected!");
          return;
        }
        String rtg = (String) _ratings.getSelectedItem();
        _stockList.addStockRating(tkr, aId, rtg);
        buildStockList();
        populateTickers();
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
  
  public static void main(String[] args) {
    StockClient stockClient = new StockClient();
  }
}