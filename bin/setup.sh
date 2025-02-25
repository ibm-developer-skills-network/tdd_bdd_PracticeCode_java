#!/bin/bash
echo "****************************************"
echo " Setting up Cucumber/Java Environment"
echo "****************************************"

echo "Checking and installing Java 21..."
if type -p java; then
    java_version=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}')
    if [[ $java_version == 21* ]]; then
        echo "Java 21 is already installed"
    else
        echo "Installing Java 21..."
        sudo apt-get update
        sudo apt-get install -y openjdk-21-jdk
    fi
else
    echo "Installing Java 21..."
    sudo apt-get update
    sudo apt-get install -y openjdk-21-jdk
fi

echo "Checking Java version..."
java -version

echo "Installing Maven..."
sudo apt-get install -y maven

echo "Checking Maven version..."
mvn --version

echo "Configuring the developer environment..."
echo "# Cucumber/Java Lab Additions" >> ~/.bashrc
echo 'export PS1="\[\e]0;\u:\W\a\]${debian_chroot:+($debian_chroot)}\[\033[01;32m\]\u\[\033[00m\]:\[\033[01;34m\]\W\[\033[00m\]\$ "' >> ~/.bashrc
echo "export PATH=$HOME/local/bin:$PATH" >> ~/.bashrc
echo "export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64" >> ~/.bashrc

echo "Installing Firefox and GeckoDriver for Selenium"
sudo apt-get update
sudo DEBIAN_FRONTEND=noninteractive apt-get install -y firefox

# Install GeckoDriver
GECKO_VERSION="v0.33.0"
wget https://github.com/mozilla/geckodriver/releases/download/${GECKO_VERSION}/geckodriver-${GECKO_VERSION}-linux64.tar.gz
tar -xvzf geckodriver-${GECKO_VERSION}-linux64.tar.gz
sudo mv geckodriver /usr/local/bin/
sudo chmod +x /usr/local/bin/geckodriver
rm geckodriver-${GECKO_VERSION}-linux64.tar.gz

echo "Starting the Petshop Docker container..."
make app

echo "Checking the Petshop Docker container..."
docker ps

echo "****************************************"
echo " Cucumber/Java Environment Setup Complete"
echo "****************************************"
echo ""