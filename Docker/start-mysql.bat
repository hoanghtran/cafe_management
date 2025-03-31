@echo off
docker run -d --rm --name mysql-for-cafe ^
  -e MYSQL_ROOT_PASSWORD=123456 ^
  -e MYSQL_USER=cafe_admin ^
  -e MYSQL_PASSWORD=admin123 ^
  -e MYSQL_DATABASE=cafe_db ^
  -p 3306:3306 ^
  --volume mysql-cafe-volume:/var/lib/mysql ^
  mysql:latest
pause