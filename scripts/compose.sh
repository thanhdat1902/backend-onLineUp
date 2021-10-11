rm -rf build;
./gradlew bootJar &&
docker rm -v docker-my-sql --force;
docker-compose up --build;
docker-compose down --rmi all;