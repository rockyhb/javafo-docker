FROM openjdk:7
MAINTAINER Claas Rockmann-Buchterkirche <claas@rockbu.de>
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN javac -cp main.jar:. JaVaFoHTTPD.java
CMD ["sh", "run.sh" ]
