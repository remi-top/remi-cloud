docker stop remi-demo-web
docker rm -f remi-demo-web
docker rmi remi-demo-web
docker build -t remi-demo-web .
docker images
docker run --restart=always -t -dit -p 8080:8080 -e TZ="Asia/Shanghai" --name remi-demo-web remi-demo-web
docker ps
