Steps to compile and run BMPStockListApp:

0. If the server is not already started:
   a. open a dos window, and change to the jboss bin directory
   b. start the server using the following (all one line)
   run -c all
1. Remove previous deployed applications from deploy directory
2. open a dos window
3. change to BMPStockListApp directory
4. set classpath using the following (all one line):
set CLASSPATH=.;C:\jboss\lib\concurrent.jar;C:\jboss\client\jboss-j2ee.jar;C:\jboss\lib\jboss-common.jar;C:\jboss\server\all\lib\jboss.jar;C:\jboss\server\all\lib\jboss-remoting.jar;C:\jboss\server\all\lib\jboss-transaction.jar;C:\jboss\server\all\lib\jnpserver.jar;C:\jboss\server\all\lib\javax.servlet.jar;C:\jboss\server\all\deploy\ejb3.deployer\jboss-ejb3.jar;C:\jboss\server\all\deploy\ejb3.deployer\jboss-ejb3x.jar;C:\jboss\server\all\deploy\ejb3.deployer\ejb3-persistence.jar;C:\jboss\server\all\deploy\jboss-aop-jdk50.deployer\jboss-aop-jdk50.jar;C:\jboss\server\all\deploy\jboss-aop-jdk50.deployer\jboss-aspect-library-jdk50.jar

5. javac -d . beans_2x\*.java
6. javac -d . beans\*.java
7. javac -d . client\*.java
8. jar cf StockList2xBmp.jar META-INF\ejb-jar.xml META-INF\jboss.xml beans_2x\*.class
9. jar cf StockListBmpApp.ear META-INF\application.xml StockList2xBmp.jar
10. jar cf StockListApp.ejb3 beans\*.class
11. Use the jmx console to start the database manager.  Open a browser an go to
   http://localhost:8080/jmx-console. From there find the link "service=Hypersonic"
   and click on it.  On the next page, scroll down to the startDatabaseManager
   section and click the invoke button under the method name.  A new application
   should start.
12. Select File->Open Script and load the create_table.sql script file from the example
    directory. Now click the Execute button on the right of the loaded sql script.
    Finally commit the data by selecting Options->Commit.
13. copy ear file to the deploy directory (you must do this before the next step; the EJB3 hosts the session bean, which is dependent on the entity beans in the EAR file)
14. copy ejb3 file to the deploy directory
15. run the app using the following (all one line):
java -Djava.naming.factory.initial=org.jnp.interfaces.NamingContextFactory -Djava.naming.factory.url.pkgs=org.jboss.naming:org.jnp.interfaces -Djava.naming.provider.url=localhost client.StockClient
16. delete the ejb3 and jar from the deploy directory
17. after you are done testing, restart the database manager if not running.
18. Select File->Open Script and load the drop_table.sql script file from the example
    directory. Now click the Execute button on the right of the loaded sql script.
    Finally commit the data by selecting Options->Commit.
