package beans;

import javax.ejb.Stateful;

@Stateful
public class CalculatorBean implements Calculator {
  // Holds the calculator value
  private int _value = 0;

  // The public business methods. These must be coded in the
  // remote interface also.

  // Clear the calculator
  public void clearIt() {
    _value = 0;
  }

  // Add or subtract
  public void calculate(String operation, int value) {
    // If "+", add it
    if (operation.equals("+")) {
      _value = _value + value;
      return;
    }

    // If "-", subtract it
    if (operation.equals("-")) {
      _value = _value - value;
      return;
    }

  }

  // Return the value
  public int getValue() {
    return _value;
  }
}
