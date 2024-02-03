# 使用docker部署clash服务
1. 拉取镜像：在docker hub中查找镜像
    - clash镜像：dreamacro/clash
    - yacd镜像：haishanh/yacd
2. 从机场下载openclash配置文件
3. 部署clash容器和yacd容器
   ```bash
    docker run -d \
    --name=clash \
    -v "$PWD/config.yaml:/root/.config/clash/config.yaml" \
    -p "7890:7890" -p "7891:7891" -p "9090:9090" \
    --restart=unless-stopped \
    dreamacro/clash
   ```
    ```bash
    docker run -p 9091:80 -d --rm haishanh/yacd
    ```
    > 7890 : clash代理服务的监听端口，所有需要代理的流量都需要走这个端口
    >
    >9090 : clash后台管理的端口,我们的yacd需要去监听这个端口
    >
    >9091 : yacd的web管理端口
## 可能出现的问题
yacd无法连接9090端口访问后台界面

一般机场下载的openclash文件中默认是
`external-controller: '127.0.0.1:9090'`
这个配置如果是原生方式部署clash一点问题没有。但是我们是通过docker部署的，这个配置文件在docker内部就专指创出来的那个docker容器。由于docker容器之间是隔离的，所以我们通过yacd无法访问127.0.0.1:9090地址。因此需要将此配置项改为
`external-controller: '0.0.0.0:9090'`
这表示允许任何ip访问clash容器的9090端口。这样我们yacd就可以通过127.0.0.1:9090访问clash后台端口管理clash配置了。

---
---
---

<details>
<summary> 原生方式部署clash </summary>

## 1. 安装clash文件


## 2. 配置相关文件

1. 配置config.yml文件

在自己的机场里找到订阅连接，复制到地址栏下载相关配置文件。然后保存到配置目录中即可

在我的环境中，配置文件位于`/root/.config/clash`中
(如果不知道配置文件在什么位置，可以使用find命令全局搜索`sudo find / -name clash`)

2. 配置Country.mmdb文件

该文件是全球IP的库，即需要这个库才可以访问国外下载地址可以访问GEOIP官网[mmdb文件](https://dev.maxmind.com/geoip/geoip2/geoip2-city-country-csv-databases/)
当然也可以从这里现成的下载[Country.mmdb](https://asset.10101.io/externalLinksController/chain/Country.mmdb?ckey=8hY08nReECvarf4iCbIHXFlx80mL43thUQmjf6KnKh512UdFMDeEn+H5I+kJOnl/)

## 3. 运行clash

</details>