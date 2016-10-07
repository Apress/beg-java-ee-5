package client;

import beans.Calculator;
import javax.naming.InitialContext;

// general imports
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CalculatorClient extends JFrame
implements ActionListener { 
    
  private JButton _clear = new JButton("Clear");
  private JButton _equals = new JButton("=");
  private JTextField _topNumber = new JTextField("0");
  private JTextField _bottomNumber = new JTextField("0");
  private JComboBox _operator = new JComboBox();
  private Calculator _calculator;
  
  public CalculatorClient() {
    // get the calculator
    _calculator = getCalculator();
    
    // add the title
    JLabel title = new JLabel("My Simple Calculator");
    title.setHorizontalAlignment(JLabel.CENTER);
    getContentPane().add(title, BorderLayout.NORTH);
    
    // add the calculation panel
    JPanel calcPanel = new JPanel(new GridLayout(2, 2));
    calcPanel.add(new JLabel("Calculator value"));
    _topNumber.setEditable(false);
    calcPanel.add(_topNumber);
    _operator.addItem("+");
    _operator.addItem("-");
    calcPanel.add(_operator);
    calcPanel.add(_bottomNumber);
    getContentPane().add(calcPanel, BorderLayout.CENTER);
    
    // add the buttons
    JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
    _equals.addActionListener(this);
    buttonPanel.add(_equals);
    _clear.addActionListener(this);
    buttonPanel.add(_clear);
    getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    setSize(300, 150);
    setVisible(true);
  }
  
  private Calculator getCalculator() {
    Calculator calculator = null;
    try {
      // Get a naming context
      InitialContext ctx = new InitialContext();

      // Get a Calculator
      calculator
        = (Calculator) ctx.lookup(Calculator.class.getName());
    } catch(Exception e) {
      e.printStackTrace();
    }
    
    return calculator;
  }
  
  public void actionPerformed(ActionEvent ae) {
    // if equals was clicked, run the calculation
    if (ae.getSource() == _equals) {
      runCalculator();
    }
    
    // if clear was clicked, clear the calculator
    if (ae.getSource() == _clear) {
      clearCalculator();
    }
  }
  
  private void runCalculator() {
    try {
      // get the bottom value to be added to the calculator
      int operVal = 0;
      String textVal = _bottomNumber.getText();
      if (textVal != null) {
        try {
          operVal = Integer.parseInt(textVal);
        }
        catch (NumberFormatException nfe) { }         
      }
      
      // get the operator
      String oper = (String) _operator.getSelectedItem();
      
      // calculate the new value
      _calculator.calculate(oper, operVal);
      
      // display the new value
      _topNumber.setText("" + _calculator.getValue());
    }
    catch (Exception e) {
      e.printStackTrace(); 
    }
    
  }
  
  private void clearCalculator() {
    try {
      // clear it out
      _calculator.clearIt();
      _topNumber.setText("0");
      _bottomNumber.setText("0");
      _operator.setSelectedIndex(0);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static void main(String[] args) {
    CalculatorClient calcClient = new CalculatorClient();
  }
}