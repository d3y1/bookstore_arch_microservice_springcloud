CONTAINER = bookstore-springcloud

existed ?= $(shell docker ps -a | grep $(CONTAINER) &>/dev/null && echo "yes" || echo "no")

# -----------------------------------------START------------------------------------------------

# make
all: maven build run
# make start
start: maven build run

maven:
	mvn clean package -DskipTests=true -Dmaven.javadoc.skip=true -B -V

build:
	bash docker_build_push.sh

run:
ifeq ($(existed), yes)
	docker compose down
endif
	docker compose up

# -----------------------------------------STOP------------------------------------------------

stop:
	docker compose stop

restart:
	docker compose restart