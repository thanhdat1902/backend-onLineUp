rm -rf build;
./gradlew bootJar &&
heroku container:push web --app spring-demo-5464 &&
heroku container:release web --app spring-demo-5464