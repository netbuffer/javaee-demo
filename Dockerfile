FROM maven:3.8.6-openjdk-8 as compile-stage
ARG compile_workdir=/usr/local/project
WORKDIR $compile_workdir
COPY pom.xml $compile_workdir
COPY src/ $compile_workdir/src/
RUN ls -afl
RUN mvn -f $compile_workdir/pom.xml clean package -T 1C -Dmaven.test.skip=true -Dmaven.compile.fork=true
FROM tomcat:8.5.81-jdk8
ARG compile_workdir=/usr/local/project
ARG run_workdir=/usr/local/tomcat
COPY --from=compile-stage $compile_workdir/target/javaee-demo.war $run_workdir/webapps/javaee-demo.war
LABEL author="netbuffer" version="1.0"
WORKDIR $run_workdir
EXPOSE 8080