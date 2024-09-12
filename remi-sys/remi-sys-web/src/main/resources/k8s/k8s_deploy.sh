#先删除原有的Pod
kubectl delete deployment dev-remi-sys-web -n remi-top --grace-period=0 --force
kubectl delete svc dev-remi-sys-web -n remi-top
#启动新的Pod
kubectl create -f dev-remi-sys-web.yaml -n remi-vip