FROM openjdk:17-jdk-slim
RUN apt-get update \
    && apt-get install -y python3 python3-pip \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY . .

RUN javac -d . TrafficAnalysis.java
RUN pip install -r ./plugin/requirements.txt
