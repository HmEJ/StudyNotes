# 1��docker����

## 1��ʲô��docker

Docker ��һ����Դ��Ӧ���������棬�ÿ����߿��Դ�����ǵ�Ӧ���Լ���������һ������ֲ��[����](https://baike.baidu.com/item/����/1574?fromModule=lemma_inlink)�У�Ȼ�󷢲����κ����е� [Linux](https://baike.baidu.com/item/Linux?fromModule=lemma_inlink)��[Windows](https://baike.baidu.com/item/Windows/165458?fromModule=lemma_inlink)����ϵͳ�Ļ����ϣ�Ҳ����ʵ��[���⻯](https://baike.baidu.com/item/���⻯/547949?fromModule=lemma_inlink)����������ȫʹ��[ɳ��](https://baike.baidu.com/item/ɳ��/393318?fromModule=lemma_inlink)���ƣ��໥֮�䲻�����κνӿڡ�

Docker�����ÿ����ߴ���Լ���Ӧ�ó�һ�����񣬷������������ϣ����߷�������Դ������ʵ�������ϵ���Դ�������񷢲������������������ķ�ʽ���У�Ҳ�����⻯��һ�����ԡ�����֮���ǳ�ָ���ģ�����ɳ�л��ƣ�����֮�䲻����Ӱ�졣����Docker�ǿ���ֲ�ģ���ƽ̨�ġ�

## 2��ʲô��ɳ��

**ɳ��**��Ӣ�sandbox������Ϊ**ɳ��**����[�����רҵ](https://baike.baidu.com/item/�����רҵ/10586245?fromModule=lemma_inlink)����ڼ������ȫ��������һ�ְ�ȫ���ƣ�Ϊ�����еĳ����ṩ�ĸ��뻷����ͨ������ΪһЩ��Դ�����š����ƻ������޷��ж�������ͼ�ĳ����ṩʵ��֮�á�

![img](img/001.jpg) 

## 3��docker�����������������

��ͬ�㣺docker�����������������������ļ�����

��ͬ�㣺docker�൱����������ԣ��������������ϵͳ�㣬����һ������˺ܶ����Դ�˷ѣ�����docker��Ч�ʸ��ߡ�

![img](img/002.jpg) 



## 4��Docker�ܹ�

docker�����󲿷֣��ͻ��ˡ�docker�����;���ֿ⡣

�ͻ������˺�docker�Ի��õġ�����ִ��docker�����������ִ�С�

docker�����Ǳ��ش���docker��ز����ġ������ڲ���ϵͳ�ϵģ����ǲ���ֱ�ӹ۲�Ͳ�����

����ֿ�������׼���õľ���ء��ṩ�˷ḻ�ľ��񣬹�ÿ��dockerʹ�á�

�ͻ��˺�docker�������ǰ�װ�����ǵĲ���ϵͳ�У�����ֿ����ڹ����ϻ����������Լ�������˽�п⡣

�����ؼ��ʣ�

����images����������һ��ϵͳ�ľ����ļ������緢����ҵ�centOS����

������containers�� �����Ǿ���������ʱ��һ��״̬��

images�൱��Ӳ���ϵ��ļ���containers�൱������Ӳ���ϵ��ļ����������ڴ�״̬��

![img](img/001.png)

![img](img/002.png)

## 5�����ĸ���

![img](img/003.jpg) 

��������������������������clients����װ��docker������Hosts���ͱ��澵���ļ���registries

## 6��docker�����Լ��ŵ�

�ص㣺

���룬�ļ����롢ϵͳ���롢��Դ���롢������롢��־����ȡ�ÿ����������ȫ����������һ��ɳ���У��������ݡ���Դ���Ǹ���ģ����磬CPU���ڴ�ȵȣ������ϻ�Ϊÿ����������һ��ip��ַ��dockerΪÿ���������ṩ�Լ�����־������Ӱ�졣

�ŵ㣺

�������⻯���ı������Ҫ�ã�����ʡ���������Կ�ƽ̨������Ҫ���Ĳ���ϵͳ��Ӱ�죬����docker��Ӧ��Ҳ�ܹ㷺������˾����Ӧ�á�

ȱ�㣺

docker�Գ������Ѻõģ����ǲ����ڴ����ļ������ݣ�һ������Ὣ�ļ���������·��ӳ��ķ�ʽ���ص������⡣

# 2������docker����

�����ֲ᣺https://docs.docker.com/engine/install/centos/

## 1���鿴һ��Linux�ں˰汾

docker֧��3.10�����ϰ汾��һ��Ҫ��һ���ں˰汾

�����unmae -r

![img](img/003.png)

## 2��ж���ϰ汾docker���������Ӱ��

![img](img/004.png)

```shell
yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
```

![img](img/005.png)

## 3������yum������Դ

![img](img/006.png)

```
yum install -y yum-utils

yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo
```

## 4����������а�װdocker

�˴������°汾�İ�װ�����鲻Ҫ��װ���°汾��

![img](img/007.png)

## 5���鿴��ʷ�汾��docker

![img](img/008.png)

## 6����װָ���汾��docker

����ͳһ��װ��20.10.8�汾

![img](img/009.png)

```
yum install --setopt=obsoletes=0 docker-ce-<VERSION_STRING> docker-ce-selinux-<VERSION_STRING>.noarch
```

��<VERSION_STRING>�滻�ɰ汾�ż���

yum install --setopt=obsoletes=0 docker-ce-20.10.8-3.el7 docker-ce-selinux-20.10.8-3.el7.noarch

## 7����װ�ɹ�

![img](img/010.png)

## 8������������֤һ��

ִ�����docker --version ���鿴һ��docker�汾������Ϣ����װ�ɹ�

![img](img/011.png)

docker��װ�ɹ�����Ҫ����docker����ſ�����

![img](img/012.png)

���

docker images ���鿴docker����

systemctl start docker ������docker����

systemctl stop docker ��ֹͣdocker����

systemctl enable docker ����������docker����

# 3��docker�ĳ��ò���

## 0. �鿴����״̬

`docker inspect <����id/����name>`

## 1���鿴���ء�����

docker images ���鿴docker����

![img](img/013.png)

REPOSITORY�����������

TAG������ı�ǩ

IMAGE ID�������Ψһ��ʶ

CREATED��������������ʱ��

SIZE������Ĵ�С

## 2������ָ������

### 1��������ȥ��

��վ�� https://hub.docker.com/

![img](img/015.png)

![img](img/016.png)

ѡ����������֤�ľ���

### 2���������

docker search ��������

![img](img/014.png)



## 3����ȡ����

docker pull ��������

docker pull tomcat

![img](img/017.png)

![img](img/018.png)

![img](img/019.png)

TAG��latest����ǰ����ı�ǩ��latest�����µ�һ������

docker pull ��������:��ǩ��

һ���ǩ���л�����汾�š�

����ͳһ��tomcat8

docker pull tomcat:8

![img](img/020.png)

## 4���鿴����

docker ps ���鿴�������е�����

docker ps -a ���鿴�����������������еĺ�ֹͣ��

![img](img/021.png)

CONTAINER ID������Ψһ��ʶ

IMAGE����Ӧ�ľ�������

COMMAND��ִ�е�����

CREATED������ʱ��

STATUS��������״̬

PORTS�������Ķ˿ںţ����Զ����

NAMES�����������֣����Զ����

## 5���Ƴ�����

docker rm ���������ֻ�Ψһ��ʶ

![img](img/022.png)

����id�Ƴ�ʱ������ֻ����id��ǰ�������ţ�docker���Զ�ȥ���ã�����Ҫע���б����Ƿ������Ƶģ�Ϊ��ȷ���Ƴ�ʱһ���ǶԵģ����鸴������Ψһ��ʶ�Ƴ���

�Ƴ�����

docker rmi �����id

�Ƴ�����Ҫ��id�Ƴ���������Ȼ֧�ְ��������Ƴ������ǰ����������׳���Ҫ��id�Ƴ���

docker��������ʱ������õ������֣���ô��Ĭ�ϼ��ϱ�ǩ����Ӧ����latest�����ǩ��

## 6����������

docker start ����id/����

![img](img/023.png)

docker stop ����id/����

docker restart ����id/����

## 7������Tomcat

docker run --name tc -p 8080:8080 tomcat:8����id

docker run --name tc1 -p 8080:8080 -d 73365378bc27

![img](img/024.png)

--name��������ȡһ������

-p���˿�ӳ�䣬�������Ķ˿�ӳ�䵽�������ϣ�ð��ǰ�Ķ˿��Ƿ������Ķ˿ںţ�������������Ķ˿ںš����磺-p 8081:8080����������8080�˿�ӳ�䵽��������8081�˿ڣ�����ʱ����8081�˿ھ��൱�ڷ���������8080�˿ڡ�

-d����̨���С���tomcat���ֳ������еĳ�����Ҫ�ú�̨���еķ�ʽ��������Ӱ���ն�������������



ע�⣺������������Ψһ������������״̬��ʲô�����ƶ��������ظ���

## 8������������

docker exec -it ���������ֻ�id /bin/bash

-it�����뵽�����У���-i -t����д��

/bin/bash����������Ҫ��·������

![img](img/025.png)

�����ڲ���Ȼ������Linux���

## 9��Tomcat������404����

�ⲿ����tomcatʱ�����404������ΪwebappsĿ¼�ļ���û���κ��ļ���tomcat�Դ���ҳ�涼��webapps.distĿ¼�ļ��£����ƹ������ɣ�ֻ����ROOTĿ¼�ļ��Ϳ����ˡ�

## 10������

ִ��exit��Ctrl+d���Դ��������˳������������ϡ�

����������-it���-i���Խ���ģʽ���������

?								  -t��Ϊ��������һ��α�ն�

docker logs �������ƻ�id -f�����������־��-f�������������ᱣ����־�����״̬��һֱ��ӡ��־��Ctrl+C�˳���





## 11��MySQL���ݿⰲװ������

### 1����ȡMySQL����

ͳһ����8�汾

![img](img/026.png)

### 2������MySQL

docker run --name mm -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -d mysql:8

-e��������Ϣ������root�����롣

![img](img/027.png)

![img](img/028.png)

### 3��Ŀ¼ӳ��

```
docker run --name mmy -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -d \
-v /usr/local/docker/mysql/logs:/var/log/mysql \
-v /usr/local/docker/mysql/conf:/etc/mysql \
-v /usr/local/docker/mysql/data:/var/lib/mysql  mysql:8
```

-v��Ŀ¼ӳ�䣬ͬ-p��ʽ��Ŀ¼�ļ������ֶ���������ӳ�䡣

![img](img/029.png)

## 12������docker����

### 1������Dockerfile

����һ���ļ���ΪDockerfile���ļ�������ļ���û�к�׺����

FROM openjdk:8
ADD t-springboot-1.0-SNAPSHOT-exec.jar /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

### 2���ϴ�jar

�ϴ�����׼���õ�springboot��õİ����ڰ�Dockerfile�ϴ�����ͬ��Ŀ¼�£�ע�⣬Ŀ¼�ﲻҪ���������ļ���

![img](img/030.png)



### 3��ִ������

����Dockerfile���ڵ�Ŀ¼�У�ִ������

docker build -t �������֣��Լ�ȡ�ģ������д�д��ĸ��:��ǩ������ʡ�ԣ�ʡ�Դ������latest�� Ŀ¼����.������ǰĿ¼)

docker build -t testspringboot . 

![img](img/031.png)

-t���Ǹ��������һ����ǩ

Ŀ¼��Dockerfile����Ŀ¼

### 4������

docker run --name tsb -p 8898:8898 -d testspringboot

���ʲ��ԣ����Ե�ַ��http://172.19.186.149:8898/start/test









