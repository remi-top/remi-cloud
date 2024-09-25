# Docker image for springboot application
# VERSION 0.0.1
# Author: DianJiu
# 指定基础镜像，这是分阶段构建的前期阶段
FROM harbor.njydsz.com/library/jdk:17 as builder
# 维护者
MAINTAINER DianJiu <dianjiuxyz@gmail.com>
#VOLUME：挂载目录
VOLUME /temp

#ADD  将app.jar文件将拷贝到容器中，默认拷贝到根路径下
#也可以直接写成ADD app.jar /app.jar 或者 ADD app.jar /
ADD startdistem-web-1.0.0.jar startdistem-web.jar

#修改app.jar这个文件的访问时间和修改时间为当前时间，而不会修改文件的内容。
RUN bash -c "touch /startdistem-web.jar"

#ENTRYPOINT 应用启动命令 参数设定
#由于tomcat的session id的生成主要通过java.security.SecureRandom生成随机数来实现，导致启动过程很慢
#通过-Djava.security.egd=file:/dev/./urandom参数可以加快随机数产生过程
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/startdistem-web.jar"]

#EXPOSE 容器暴露端口
EXPOSE 18080
