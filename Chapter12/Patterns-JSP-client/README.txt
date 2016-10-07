Steps to compile and run StockListPatternsApp:


0. If the server is not already started:
   a. open a dos window, and change to the jboss bin directory
   b. start the server using the following (all one line)
   run -c all
1. Remove previous deployed applications from deploy directory
2. open a dos window
3. change to Patterns-Trans directory
4. set classpath using the following (all one line):
set CLASSPATH=.;C:\jboss\lib\concurrent.jar;C:\jboss\client\jboss-j2ee.jar;C:\jboss\lib\jboss-common.jar;C:\jboss\server\all\lib\jboss.jar;C:\jboss\server\all\lib\jboss-remoting.jar;C:\jboss\server\all\lib\jboss-transaction.jar;C:\jboss\server\all\lib\jnpserver.jar;C:\jboss\server\all\lib\javax.servlet.jar;C:\jboss\server\all\deploy\ejb3.deployer\jboss-ejb3.jar;C:\jboss\server\all\deploy\ejb3.deployer\jboss-ejb3x.jar;C:\jboss\server\all\deploy\ejb3.deployer\ejb3-persistence.jar;C:\jboss\server\all\deploy\jboss-aop-jdk50.deployer\jboss-aop-jdk50.jar;C:\jboss\server\all\deploy\jboss-aop-jdk50.deployer\jboss-aspect-library-jdk50.jar


5. javac -d . entity\*.java
6. javac -d . dao\*.java
7. javac -d . transfer\*.java
8. javac -d . business\*.java
9. javac -d . delegate\*.java
10. javac -d . client\*.java
11. jar cf StockListPatternsApp.ejb3 transfer\*.class entity\*.class dao\*.class business\*.class
12. copy ejb3 file to the deploy directory
13. load the tables using the following (all on one line):
java -Djava.naming.factory.initial=org.jnp.interfaces.NamingContextFactory -Djava.naming.factory.url.pkgs=org.jboss.naming:org.jnp.interfaces -Djava.naming.provider.url=localhost client.StockListAdder
13. javac -d . web\*.java
14. copy the delegate directory to web-deploy\WEB-INF\classes
15. copy the web directory to web-deploy\WEB-INF\classes

16. cd web-deploy	
17. jar cf stock.war *.html *.jsp WEB-INF/web.xml WEB-INF/classes/delegate/*.class WEB-INF/classes/web/*.class
18. copy war file to the deploy directory
19. run the app using the following url:
http://localhost:8080/stock

