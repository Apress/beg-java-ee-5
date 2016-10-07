Steps to compile and run StockListCmrApp:


0. If the server is not already started:
   a. open a dos window, and change to the jboss bin directory
   b. start the server using the following (all one line)
   run -c all
1. Remove previous deployed applications from deploy directory
2. open a dos window
3. change to CMR-EJB-QL-Select directory
4. set classpath using the following (all one line):
set CLASSPATH=.;C:\jboss\lib\concurrent.jar;C:\jboss\client\jboss-j2ee.jar;C:\jboss\lib\jboss-common.jar;C:\jboss\server\all\lib\jboss.jar;C:\jboss\server\all\lib\jboss-remoting.jar;C:\jboss\server\all\lib\jboss-transaction.jar;C:\jboss\server\all\lib\jnpserver.jar;C:\jboss\server\all\lib\javax.servlet.jar;C:\jboss\server\all\deploy\ejb3.deployer\jboss-ejb3.jar;C:\jboss\server\all\deploy\ejb3.deployer\jboss-ejb3x.jar;C:\jboss\server\all\deploy\ejb3.deployer\ejb3-persistence.jar;C:\jboss\server\all\deploy\jboss-aop-jdk50.deployer\jboss-aop-jdk50.jar;C:\jboss\server\all\deploy\jboss-aop-jdk50.deployer\jboss-aspect-library-jdk50.jar

5. javac -d . beans\*.java
6. javac -d . client\*.java
7. jar cf StockListCmrApp.ejb3 beans\*.class
8. copy ejb3 file to the deploy directory
9. load the tables using the following (all on one line):
java -Djava.naming.factory.initial=org.jnp.interfaces.NamingContextFactory -Djava.naming.factory.url.pkgs=org.jboss.naming:org.jnp.interfaces -Djava.naming.provider.url=localhost client.StockListAdder
10. run the app using the following (all one line):
java -Djava.naming.factory.initial=org.jnp.interfaces.NamingContextFactory -Djava.naming.factory.url.pkgs=org.jboss.naming:org.jnp.interfaces -Djava.naming.provider.url=localhost client.StockClient