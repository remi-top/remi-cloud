#先删除原有的Pod
kubectl delete deployment remi-demo-web -n remi-top --grace-period=0 --force
kubectl delete svc remi-demo-web -n remi-top
#启动新的Pod
kubectl create -f remi-demo-web.yaml -n remi-top