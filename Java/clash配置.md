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

