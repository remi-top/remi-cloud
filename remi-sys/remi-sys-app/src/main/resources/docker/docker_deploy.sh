docker stop remitem-web
docker rm -f remitem-web
docker rmi remitem-web
docker build -t remitem-web .
docker images
docker run --restart=always -t -dit -p 18080:18080 -e TZ="Asia/Shanghai" --name remitem-web remitem-web
docker ps
