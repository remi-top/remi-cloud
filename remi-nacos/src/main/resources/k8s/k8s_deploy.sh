#先删除原有的Pod
kubectl delete deployment remi-nacos-web -n remi-top --grace-period=0 --force
kubectl delete svc remi-nacos-web -n remi-top
# 首次启动 需加载配置文件
kubectl create -f nacos-config.yaml -n remi-top
#启动新的Pod
kubectl create -f nacos-deploy.yaml -n remi-top