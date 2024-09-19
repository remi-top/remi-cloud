#先删除原有的镜像
docker images | grep remi-nacos-web | awk '{print $3}' | xargs docker rmi -f
docker build -t remi-nacos-web .
docker images | grep remi-nacos-web
#把新镜像推送到私服
docker login -uadmin -pRemi123 harbor.******.com
docker tag remi-nacos-web harbor.******.com/remi-top/remi-nacos-web:3.1.0
docker tag remi-nacos-web harbor.******.com/remi-top/remi-nacos-web
docker push harbor.******.com/remi-top/remi-nacos-web:3.1.0
docker push harbor.******.com/remi-top/remi-nacos-web
#基于Docker启动容器
docker run --restart=always -t -dit -p 8848:8848 --name remi-nacos-web harbor.******.com/remi-top/remi-nacos-web
