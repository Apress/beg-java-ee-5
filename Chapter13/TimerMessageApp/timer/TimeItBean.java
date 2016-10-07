package timer;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.jms.Queue;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

// general imports
import java.text.*;
import java.util.*;

@Stateless
public class TimeItBean implements TimeIt {
	private @Resource TimerService timer;
	private @Resource(mappedName="java:/ConnectionFactory")
	  ConnectionFactory factory;
	private @Resource(mappedName="queue/LogWriter")
	  Queue queue;
	private SimpleDateFormat sdf =
	  new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss.SSS");

  // public business method to start the timer
  public void startTimer()
  {
    // after initial five seconds, then every ten seconds
    timer.createTimer(5000, 10000, "timer");
  }

  // timer method - timer expires - send message to queue
  @Timeout
  public void timeoutHandler(Timer timer) {
    Connection connection = null;
    try {
      // get a connection from the factory
      connection = factory.createConnection();
      // create a session
      Session session =
        connection.createSession(false,
        Session.AUTO_ACKNOWLEDGE);
      // create a sender for the session to the queue
      MessageProducer sender = session.createProducer(queue);
      // create a text message
      TextMessage message = session.createTextMessage();
      // set the text of the message
      message.setText
        ("log entry, the time is: " + sdf.format(new Date()));
      // send the message
      sender.send(message);
    }
    catch (Exception e) {
      System.out.println("Exception in message: " + e.toString());
      e.printStackTrace();
    }
    finally {
      if (connection != null) {
        try {
          connection.close();
        }
        catch (Exception e) {}
      }
    }
  }
}
