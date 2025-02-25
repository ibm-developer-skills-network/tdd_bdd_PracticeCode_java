# Builds an environment for Cucumber, Selenium, and Firefox

.PHONY: all help cucumber app clean

help: ## Display this help
	@awk 'BEGIN {FS = ":.*##"; printf "\nUsage:\n  make \033[36m<target>\033[0m\n"} /^[a-zA-Z_0-9-\\.]+:.*?##/ { printf "  \033[36m%-15s\033[0m %s\n", $$1, $$2 } /^##@/ { printf "\n\033[1m%s\033[0m\n", substr($$0, 5) } ' $(MAKEFILE_LIST)

all: help

cucumber: ## Install pre-requisite software for Cucumber/Java
	$(info Install Cucumber/Java prerequisite software...)
	sudo apt-get update
	sudo apt-get install -y openjdk-21-jdk maven firefox
	# Install GeckoDriver for Firefox
	wget https://github.com/mozilla/geckodriver/releases/download/v0.33.0/geckodriver-v0.33.0-linux64.tar.gz
	tar -xvzf geckodriver-v0.33.0-linux64.tar.gz
	sudo mv geckodriver /usr/local/bin/
	sudo chmod +x /usr/local/bin/geckodriver
	rm geckodriver-v0.33.0-linux64.tar.gz
 
app: ## Run the Petshop application
	$(info Running Petshop Application...)
	docker run -d --name petshop \
		-p 8080:8080 \
		-e DATABASE_URI=sqlite:///test.db \
		rofrano/lab-flask-bdd:1.0

clean: ## Stop and remove the container
	$(info Stopping and removing container...)
	docker stop petshop || true
	docker rm petshop || true

test: ## Run Cucumber tests
	$(info Running Cucumber tests...)
	mvn clean test