Steps to Deploy Hello World

0. If the server is not already started:
   a. open a dos window, and change to the jboss bin directory
   b. start the server using the following (all one line)
   run -c all
1. Remove previous deployed applications from deploy directory
2. open a dos window
3. create the WAR file that contains the index page
jar cf web-app.war index.jsp
4. create the EAR file that packages the application and deployment info
jar cf helloworld.ear web-app.war META-INF
5. copy the EAR file to your deployment directory 
6. Test the deployment by visiting
http://localhost:8080/hello
