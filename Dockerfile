FROM tomcat:11-jdk25
ARG run_workdir=/usr/local/tomcat
COPY target/javaee-demo.war $run_workdir/webapps/javaee-demo.war
LABEL author="netbuffer" version="1.0"
WORKDIR $run_workdir
EXPOSE 8080