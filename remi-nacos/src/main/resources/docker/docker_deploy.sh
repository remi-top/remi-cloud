#先删除原有的镜像
docker stop sit-remi-nacos-web
docker rm -f sit-remi-nacos-web
docker images | grep sit-remi-nacos-web | awk '{print $3}' | xargs docker rmi -f
docker build -t sit-remi-nacos-web .
docker images | grep sit-remi-nacos-web
#把新镜像推送到私服
docker login -uadmin -pYdsz1020 harbor.njydsz.com
docker tag sit-remi-nacos-web harbor.njydsz.com/remi/sit-remi-nacos-web:3.1.1
docker tag sit-remi-nacos-web harbor.njydsz.com/remi/sit-remi-nacos-web
docker push harbor.njydsz.com/remi/sit-remi-nacos-web:3.1.1
docker push harbor.njydsz.com/remi/sit-remi-nacos-web
#启动最新的镜像服务
docker run --restart=always -t -dit -p 8848:8848 --name sit-remi-nacos-web sit-remi-nacos-web
docker ps -a
