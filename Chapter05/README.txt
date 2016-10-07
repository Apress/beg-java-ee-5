Steps to Deploy examples from Chapter 05

1. Each of these examples need to have the JSTL and JSF libraries installed.
   a. The JSF files can be downloaded from http://java.sun.com/j2ee/javaserverfaces/download.html. Copy the JAR files to the WEB-INF/lib directory of each application.
   b. The JSTL files can be downloaded from http://jakarta.apache.org/taglibs/index.html#Downloads. Copy the jstl.jar and standard.jar files to the WEB-INF/lib directory of each application.
2. Copy the war files to your application server's deployment directory
   a. For Tomcat, copy the war files to the Tomcat webapps directory
   b. For JBoss, copy the war files to the JBoss server\all\deploy directory
3. Access each application through the URL http://localhost:8080/Jsf_Ex0n
   a. Replace localhost with host:port if your server is accessed on a different machine and different port
   b. Replace the 'n' in Jsf_Ex0n with the correct example number, e.g. Jsf_Ex03
4. For Jsf_Ex05, you will need to set the language accepted by your browser to see the example in Spanish.

If you want to experiment with the applications, or rebuild an application, you can expand each WAR file to a development directory to edit the application. Each WAR file includes an Ant build.xml file and an ant.properties file. Modify the ant.properties file to include the correct path to the server deploy directory. The build.xml file contains these targets:

compile - compiles the class files
stage-web - calls compile, then creates the WAR file
deploy - calls stage stage-web, then copies the WAR file to the deploy directory
clean - removes the build directory
cleanall - removes the compiled class files and the build directory
