#先删除原有的Pod
kubectl delete deployment dev-remi-sys-app -n remi-top --grace-period=0 --force
kubectl delete svc dev-remi-sys-app -n remi-top
#启动新的Pod
kubectl create -f dev-remi-sys-app.yaml -n remi-vip