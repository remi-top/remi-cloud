#先删除原有的镜像
docker images | grep remi-boot-web | awk '{print $3}' | xargs docker rmi -f
docker build -t remi-boot-web .
docker images | grep remi-boot-web
#把新镜像推送到私服
docker login -uadmin -pRemi123 harbor.******.com
docker tag remi-boot-web harbor.******.com/remi-top/remi-boot-web:3.1.0
docker tag remi-boot-web harbor.******.com/remi-top/remi-boot-web
docker push harbor.******.com/remi-top/remi-boot-web:3.1.0
docker push harbor.******.com/remi-top/remi-boot-web
#基于Docker启动容器
docker run --restart=always -t -dit -p 1010:1010 --name remi-boot-web harbor.******.com/remi-top/remi-boot-web
