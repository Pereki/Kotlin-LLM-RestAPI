FROM mcr.microsoft.com/vscode/devcontainers/java:17
USER root

RUN apt-get update && apt-get install -y --no-install-recommends git curl unzip sudo \
    && rm -rf /var/lib/apt/lists/*

ARG USERNAME=vscode

RUN mkdir -p /home/${USERNAME}/.gradle \
    && chown -R ${USERNAME}:${USERNAME} /home/${USERNAME}/.gradle

    ENV GRADLE_USER_HOME=/home/${USERNAME}/.gradle
ENV JAVA_HOME=/usr/local/openjdk-17
USER ${USERNAME}
WORKDIR /workspace
