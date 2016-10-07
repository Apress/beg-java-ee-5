Steps to compile and run SimpleService:

0. Make sure the server is stopped, then copy the timer-message-service.xml file to the deploy\jms directory.
   Start the server:      
   a. open a dos window, and change to the jboss bin directory
   b. start the server using the following (all one line)
   run -c all
1. Remove the previous deployed applications from deploy directory
2. open a dos window
3. Change to SimpleService directory
4. set classpath using the following (all one line):
NOTE: THIS USES THE DOWNLOADED JBOSS CVS AS PER BOOK INSTRUCTIONS
set CLASSPATH=.;c:\jboss_cvs\server\all\deploy\jbossws.sar\jbossws.jar;c:\jboss_cvs\server\all\deploy\jbossws.sar\wsdl4j.jar;c:\jboss_cvs\server\all\deploy\ejb3.deployer\jboss-ejb3x.jar;c:\jboss_cvs\server\all\deploy\ejb3.deployer\jboss-ejb3.jar;c:\jboss_cvs\client\jboss-jaxrpc.jar

5. javac -d . webservices\*.java
6. javac -d . client\*.java
7. jar cf SimpleServiceApp.ejb3 webservices\*.class
8. copy ejb3 file to the deploy directory
9. run the app using the following (all one line):
java -classpath .;c:\jboss_cvs\client\jboss-jaxrpc.jar;c:\jboss_cvs\client\log4j.jar;c:\jboss_cvs\client\logkit.jar;c:\jboss_cvs\client\jbossws-client.jar;c:\jboss_cvs\client\activation.jar;c:\jboss_cvs\client\jboss-saaj.jar;c:\jboss_cvs\client\mail.jar;c:\jboss_cvs\client\wsdl4j.jar;c:\jboss_cvs\lib\endorsed\xercesImpl.jar;c:\jboss_cvs\client\jbossall-client.jar;c:\jboss_cvs\server\default\lib\jboss-remoting.jar;c:\jboss_cvs\server\default\lib\javax.servlet.jar client.SimpleServiceClient this is a test 

