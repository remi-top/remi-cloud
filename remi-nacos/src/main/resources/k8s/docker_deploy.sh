#先删除原有的镜像
docker images | grep remi-nacos | awk '{print $3}' | xargs docker rmi -f
docker build -t remi-nacos .
docker images | grep remi-nacos
#把新镜像推送到私服
docker login -uadmin -pRemi123 harbor.******.com
docker tag remi-nacos harbor.******.com/remi-top/remi-nacos:3.1.0
docker tag remi-nacos harbor.******.com/remi-top/remi-nacos
docker push harbor.******.com/remi-top/remi-nacos:3.1.0
docker push harbor.******.com/remi-top/remi-nacos
