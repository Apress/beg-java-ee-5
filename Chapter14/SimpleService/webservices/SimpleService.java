package webservices;

import java.rmi.Remote;

public interface SimpleService extends Remote
{
String echo(String input);
}
