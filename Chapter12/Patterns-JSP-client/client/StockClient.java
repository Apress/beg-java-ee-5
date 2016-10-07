package client;

import delegate.StockListDelegate;
import transfer.AnalystTO;
import transfer.StockTO;

import java.util.*;

// general imports
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StockClient extends JFrame 
  implements ActionListener { 
  private Integer[] _analystIds;
  private JButton _get = new JButton("Add Rating");
  private JPanel _stockPanel = new JPanel();
  private JComboBox _analysts = new JComboBox();
  private JComboBox _tickers = new JComboBox();
  private JComboBox _ratings = new JComboBox();
      
  public StockClient() {
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
    java.util.List stoks =
      StockListDelegate.getInstance().getStockRatings();
    _stockPanel.removeAll();
    _stockPanel.setLayout(new GridLayout(stoks.size(), 1));
    for (int i = 0; i < stoks.size(); i++) {
      StockTO stokInfo = (StockTO) stoks.get(i);
      Box stokLine = Box.createHorizontalBox();
      String stokDesc = stokInfo.getTickerSymbol() + " : " + 
        stokInfo.getName() + " ==> " +
        stokInfo.getAnalyst().getName() + " rates it: " + 
        stokInfo.getRating();
      stokLine.add(new JLabel(stokDesc));
      _stockPanel.add(stokLine);
    }
    _stockPanel.invalidate();
    _stockPanel.validate();
  }
  
  private void populateAnalysts() throws Exception {
    java.util.List anlysts =
      StockListDelegate.getInstance().getAllAnalysts();
    _analystIds = new Integer[anlysts.size()];
    for (int i = 0; i < anlysts.size(); i++) {
      AnalystTO analystData = (AnalystTO) anlysts.get(i);
      _analystIds[i] = analystData.getAnalystId();
      _analysts.addItem(analystData.getName());
    }
  }
  
  private void populateTickers() throws Exception {
    _tickers.removeAllItems();
    java.util.List tkrs =
      StockListDelegate.getInstance().getUnratedStocks();
    for (int i = 0; i < tkrs.size(); i++) {
      StockTO stockTO = (StockTO) tkrs.get(i);
      _tickers.addItem(stockTO.getTickerSymbol());
    }
    _tickers.invalidate();
    _tickers.validate();
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
        StockTO stockTO = new StockTO(tkr, null, rtg);
        stockTO.setAnalyst(new AnalystTO(aId, null));
        StockListDelegate.getInstance().addStockRating(stockTO);
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