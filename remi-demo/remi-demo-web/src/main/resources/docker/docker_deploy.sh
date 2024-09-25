#先删除原有的镜像
docker images | grep remi-demo-web | awk '{print $3}' | xargs docker rmi -f
docker build -t remi-demo-web .
docker images | grep remi-demo-web
#把新镜像推送到私服
docker login -uadmin -pRemi123 harbor.******.com
docker tag remi-demo-web harbor.******.com/remi-top/remi-demo-web:3.1.0
docker tag remi-demo-web harbor.******.com/remi-top/remi-demo-web
docker push harbor.******.com/remi-top/remi-demo-web:3.1.0
docker push harbor.******.com/remi-top/remi-demo-web
#基于Docker启动容器
docker run --restart=always -t -dit -p 1010:1010 --name remi-demo-web harbor.******.com/remi-top/remi-demo-web
