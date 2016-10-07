package beans;

import javax.ejb.Stateless;

@Stateless
public class SimpleSessionBean implements SimpleSession {
  // The public business method. This must be coded in the
  // remote interface also, and is via extending the
  // SimpleSession interface.
  public String getEchoString(String clientString) {
    return clientString + " - from session bean";
  }
}