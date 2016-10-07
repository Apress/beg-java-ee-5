package webservices;


import org.jboss.annotation.ejb.RemoteBinding;
import org.jboss.ws.annotation.PortComponent;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@WebService(name = "EndpointInterface", targetNamespace = "http://localhost", serviceName = "SimpleService")
@PortComponent(contextRoot="/jbosswstest ", urlPattern="/*")
@Remote(SimpleService.class)
@Stateless
public class SimpleServiceImpl implements SimpleService
{
   @WebMethod
   public String echo(String input)
   {
      return input;
   }
}
