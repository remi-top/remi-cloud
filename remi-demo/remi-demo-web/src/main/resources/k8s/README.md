
## 如果基于K8S部署项目 直接运行如下脚本即可
```shell
sh ./docker_deploy.sh

sh ./k8s_deploy.sh

kubectl get pods -n remi-top

kubectl describe pod remi-demo-web -n remi-top

kubectl get deployment remi-demo-web -n remi-top -o yaml
```