#先删除原有的Pod
kubectl delete deployment remi-boot-web -n remi-top --grace-period=0 --force
kubectl delete svc remi-boot-web -n remi-top
#启动新的Pod
kubectl create -f remi-boot-web.yaml -n remi-top