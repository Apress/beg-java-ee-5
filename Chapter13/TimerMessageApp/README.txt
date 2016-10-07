Steps to compile and run TimerMessageApp:

0. Make sure the server is stopped, then copy the timer-message-service.xml file to the deploy\jms directory.
   Start the server:      
   a. open a dos window, and change to the jboss bin directory
   b. start the server using the following (all one line)
   run -c all
1. Remove previous deployed applications from deploy directory
2. open a dos window
3. change to TimerMessageApp directory
4. set classpath using the following (all one line): 
set CLASSPATH=.;C:\jboss\lib\concurrent.jar;C:\jboss\client\jboss-j2ee.jar;C:\jboss\lib\jboss-common.jar;C:\jboss\server\all\lib\jboss.jar;C:\jboss\server\all\lib\jboss-remoting.jar;C:\jboss\server\all\lib\jboss-transaction.jar;C:\jboss\server\all\lib\jnpserver.jar;C:\jboss\server\all\lib\javax.servlet.jar;C:\jboss\server\all\deploy\ejb3.deployer\jboss-ejb3.jar;C:\jboss\server\all\deploy\ejb3.deployer\jboss-ejb3x.jar;C:\jboss\server\all\deploy\jboss-aop-jdk50.deployer\jboss-aop-jdk50.jar;C:\jboss\server\all\deploy\jboss-aop-jdk50.deployer\jboss-aspect-library-jdk50.jar

5. javac -d . msg\*.java
6. javac -d . timer\*.java
7. javac -d . client\*.java
8. jar cf TimerMessageApp.ejb3 msg\*.class timer\*.class
9. copy ejb3 file to the deploy directory
10. run the app using the following (all one line):
java -Djava.naming.factory.initial=org.jnp.interfaces.NamingContextFactory -Djava.naming.factory.url.pkgs=org.jboss.naming:org.jnp.interfaces -Djava.naming.provider.url=localhost client.TimeItTester

