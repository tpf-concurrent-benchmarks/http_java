create_directories:
	mkdir -p graphite

init:
	docker swarm init || true

build:
	docker rmi http_java -f || true
	docker build -t http_java .

remove:
	if docker stack ls | grep -q http_java; then \
		docker stack rm http_java; \
	fi

deploy: remove build
	until \
	docker stack deploy \
	-c docker-compose.yml \
	http_java; \
	do sleep 1; \
	done

logs:
	docker service logs http_java_app -f