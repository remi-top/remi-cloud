docker stop remi-demo-app
docker rm -f remi-demo-app
docker rmi remi-demo-app
docker build -t remi-demo-app .
docker images
docker run --restart=always -t -dit -p 8081:8081 -e TZ="Asia/Shanghai" --name remi-demo-app remi-demo-app
docker ps
