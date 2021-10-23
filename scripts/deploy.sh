rm -rf build;
./gradlew bootJar &&
heroku container:push web --app onlineup-server &&
heroku container:release web --app onlineup-server