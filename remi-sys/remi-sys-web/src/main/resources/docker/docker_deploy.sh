#先删除原有的镜像
docker stop sit-remi-sys-web
docker rm -f sit-remi-sys-web
docker images | grep sit-remi-sys-web | awk '{print $3}' | xargs docker rmi -f
docker build -t sit-remi-sys-web .
docker images | grep sit-remi-sys-web
#把新镜像推送到私服
docker login -uadmin -pYdsz1020 harbor.njydsz.com
docker tag sit-remi-sys-web harbor.njydsz.com/remi/sit-remi-sys-web:3.1.0
docker tag sit-remi-sys-web harbor.njydsz.com/remi/sit-remi-sys-web:latest
docker push harbor.njydsz.com/remi/sit-remi-sys-web:3.1.0
docker push harbor.njydsz.com/remi/sit-remi-sys-web:latest
#启动最新的镜像服务
docker run --restart=always -t -dit -p 1010:1010 --name sit-remi-sys-web sit-remi-sys-web
docker ps -a
docker logs -f sit-remi-sys-web
