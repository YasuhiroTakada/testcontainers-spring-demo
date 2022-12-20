FROM eclipse-temurin:17.0.5_8-jdk as jre-build

RUN $JAVA_HOME/bin/jlink \
         --add-modules java.base,java.compiler,java.desktop,java.instrument,java.management,java.net.http,java.prefs,java.rmi,java.scripting,java.security.jgss,java.security.sasl,java.sql.rowset,jdk.jfr,jdk.unsupported \
         --strip-debug \
         --no-man-pages \
         --no-header-files \
         --compress=2 \
         --output /javaruntime

FROM debian:buster-slim
USER root
RUN apt update
RUN apt -y upgrade
RUN apt install -y locales
RUN sed -i '/ja_JP.UTF-8/s/^# //g' /etc/locale.gen
RUN locale-gen

ENV LANG ja_JP.UTF-8
ENV LANGUAGE ja_JP.UTF-8
ENV LC_ALL ja_JP.UTF-8
ENV TZ Asia/Tokyo

FROM debian:buster-slim
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH "${JAVA_HOME}/bin:${PATH}"
COPY --from=jre-build /javaruntime $JAVA_HOME

COPY build/libs/testcontainer-spring-demo-2022.12.jar /app.jar

ENTRYPOINT ["java","-XX:StartFlightRecording=name=app-profile,filename=/app-profile.jfr,dumponexit=true,maxsize=10m,settings=profile,disk=true","-jar","/app.jar"]
EXPOSE 8080