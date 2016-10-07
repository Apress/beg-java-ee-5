package beans;

import javax.ejb.Remote;

@Remote
public interface SimpleSession
{
  public String getEchoString(String clientString);
}