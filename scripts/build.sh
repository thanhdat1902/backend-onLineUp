gradle bootJar;
docker rm -v docker-my-sql --force;
docker-compose up --build;
#docker-compose down --rmi all;
docker rm -v demo_docker-demo-spring-server_1 --force;
docker rm -v demo_docker-my-sql_1 --force;
docker rmi demo_docker-demo-spring-server --force;
docker network rm demo_docker-network;