docker stop startdistem-web
docker rm -f startdistem-web
docker rmi startdistem-web
docker build -t startdistem-web .
docker images
docker run --restart=always -t -dit -p 18080:18080 -e TZ="Asia/Shanghai" --name startdistem-web startdistem-web
docker ps
