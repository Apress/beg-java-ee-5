package client;

import webservices.SimpleService;
import javax.xml.rpc.ServiceFactory;
import javax.xml.rpc.Service;
import javax.xml.namespace.QName;
import java.net.URL;

public class SimpleServiceClient {
	private static final String _namespace="http://localhost";
	private static final String _service="SimpleService";
	private static final String _wsdl="http://localhost:8080/jbosswstest?wsdl";

  public static void main(String[] args) {
    try {

    	URL defUrl=new URL(_wsdl);

    	// create the service factory
    	ServiceFactory serviceFactory = ServiceFactory.newInstance();

    	// load the service implementation class
    	Service remoteService=serviceFactory.createService(defUrl,new QName(_namespace,_service));

    	// load a proxy for our class
    	SimpleService invoker = (SimpleService)remoteService.getPort(SimpleService.class);
    	// invoke our interface for each argument
    	for (int i = 0; i < args.length; i++) {
    		String returnedString = invoker.echo(args[i]);
    		System.out.println("sent string: " + args[i]
    		                                          + ", received string: " + returnedString);
    	}
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}
