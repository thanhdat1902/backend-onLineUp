# run container mysql
#cd .. && docker run -d --name=docker-my-sql -p 3306:3306 --env="MYSQL_ROOT_PASSWORD=root" --env="MYSQL_PASSWORD=root" --env="MYSQL_DATABASE=Test" --env="DATABASE_HOST=docker-my-sql " mysql

echo "Test"