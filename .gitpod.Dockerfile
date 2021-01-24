#!/bin/echo docker build . -f
# -*- coding: utf-8 -*-

FROM gitpod/workspace-full:latest

ARG BUILD_DATE="$(git rev-parse --short HEAD)"
ARG VCS_REF="$(date -u +\"%Y-%m-%dT%H:%M:%SZ\")"
ARG VERSION="1.0.0"

LABEL maintainer="Alexander Rogalskiy"
LABEL organization="nullables.io"
LABEL io.nullables.api.playground.image.build-date=$BUILD_DATE
LABEL io.nullables.api.playground.image.name="gradle-kotlin-sample
LABEL io.nullables.api.playground.image.description="Gradle Kotlin sample project"
LABEL io.nullables.api.playground.image.url="https://nullables.io/"
LABEL io.nullables.api.playground.image.vcs-ref=$VCS_REF
LABEL io.nullables.api.playground.image.vcs-url="https://github.com/AlexRogalskiy/gradle-kotlin-sample"
LABEL io.nullables.api.playground.image.vendor="Nullables.io"
LABEL io.nullables.api.playground.image.version=$VERSION

ENV LC_ALL en_US.UTF-8
ENV LANG ${LC_ALL}

# Downloading and installing Gradle
# Define a constant with the version of gradle you want to install
ARG GRADLE_VERSION=6.8

# Define a constant with the working directory
ARG USER_HOME_DIR="/root"

# Define the SHA key to validate the gradle download
ARG SHA=e2774e6fb77c43657decde25542dea710aafd78c4022d19b196e7e78d79d8c6c

# Define the URL where gradle can be downloaded from
ARG BASE_URL=https://services.gradle.org/distributions

# Disable coredump
RUN sudo /bin/su -c "echo 'Set disable_coredump false' >> /etc/sudo.conf"

# Create the directories, download gradle, validate the download, install it, remove downloaded file and set links
RUN sudo mkdir -p /usr/share/gradle /usr/share/gradle/ref \
  && echo "Downloading gradle" \
  && curl -fsSL -o /tmp/gradle.zip ${BASE_URL}/gradle-${GRADLE_VERSION}-bin.zip \
  \
  && echo "Checking download hash" \
  && echo "${SHA} /tmp/gradle.zip" | sha256sum -c - \
  \
  && echo "Unziping gradle" \
  && sudo unzip /tmp/gradle.zip -d /usr/share/gradle --strip-components=1 \
  \
  && echo "Cleaning and setting links" \
  && sudo rm -f /tmp/gradle.zip \
  && sudo ln -s /usr/share/gradle/bin/gradlew /usr/bin/gradlew

# Define environmental variables
ENV GRADLE_HOME /usr/share/gradle
ENV GRADLE_CONFIG "$USER_HOME_DIR/.gradle"
ENV HOME /home/gitpod

USER root
RUN sudo echo '%sudo ALL=(ALL) NOPASSWD:ALL' >> /etc/sudoers
#RUN sudo echo "Adding gitpod user and group" \
#        && useradd --system --uid 1000 --shell /bin/bash --create-home gitpod \
#        && adduser gitpod sudo \
#        && chown --recursive gitpod:gitpod /home/gitpod

USER gitpod
WORKDIR $HOME

RUN sudo chmod +x /usr/bin/gradlew
