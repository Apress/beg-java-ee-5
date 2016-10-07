package msg;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activateConfig =
  {
    @ActivationConfigProperty(propertyName="destinationType",
                              propertyValue="javax.jms.Queue"),
    @ActivationConfigProperty(propertyName="destination", 
                              propertyValue="queue/LogWriter")
  })
public class MessageWriter implements MessageListener {
  // must implement this method for MessageDriven
  public void onMessage(Message message) {
    TextMessage msg = null;

    try {
      if (message instanceof TextMessage) {
        msg = (TextMessage) message;
        System.out.println("Got message: " + msg.getText());
      }
      else {
        System.out.println("Got message of type: "
          + message.getClass().getName() + " ==> ignored!");
      }
    }
    catch (Throwable te) {
      te.printStackTrace();
    }
  }
}
