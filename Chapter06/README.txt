Steps to Deploy examples from Chapter 06

1. Copy the war files to your application server's deployment directory
   a. For Tomcat, copy the war files to the Tomcat webapps directory
   b. For JBoss, copy the war files to the JBoss server\all\deploy directory
2. Access each application through the URL http://localhost:8080/Servlet_Ex0n
   a. Replace localhost with host:port if your server is accessed on a different machine and different port
   b. Replace the 'n' in Servlet_Ex0n with the correct example number, e.g. Servlet_Ex03

If you want to experiment with the applications, or rebuild an application, you can expand each WAR file to a development directory to edit the application. Each WAR file includes an Ant build.xml file and an ant.properties file. Modify the ant.properties file to include the correct path to the server deploy directory. The build.xml file contains these targets:

compile - compiles the class files
stage-web - calls compile, then creates the WAR file
deploy - calls stage stage-web, then copies the WAR file to the deploy directory
clean - removes the build directory
cleanall - removes the compiled class files and the build directory
