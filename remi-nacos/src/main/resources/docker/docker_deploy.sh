#先删除原有的镜像
docker stop sit-remi-base-nacos
docker rm -f sit-remi-base-nacos
docker images | grep sit-remi-base-nacos | awk '{print $3}' | xargs docker rmi -f
docker build -t sit-remi-base-nacos .
docker images | grep sit-remi-base-nacos
#把新镜像推送到私服
docker login -uadmin -pYdsz1020 harbor.njydsz.com
docker tag sit-remi-base-nacos harbor.njydsz.com/remi/sit-remi-base-nacos:3.1.0
docker tag sit-remi-base-nacos harbor.njydsz.com/remi/sit-remi-base-nacos
docker push harbor.njydsz.com/remi/sit-remi-base-nacos:3.1.0
docker push harbor.njydsz.com/remi/sit-remi-base-nacos
#启动最新的镜像服务
docker run --restart=always -t -dit -p 8848:8848 --name sit-remi-base-nacos sit-remi-base-nacos
docker ps -a
