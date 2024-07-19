> 记录使用debian过程中收获的一些linux知识点

# she-bang

`#!` 符号在脚本的第一行用于指定解释器运行环境

比如，lua脚本的第一行

```lua
#! /usr/local/bin/lua
print("hello world!");
```

此时我们就可以直接运行该文件，而不需要加`lua` 指令前缀来运行。因为she-bang已经指定了脚本的运行环境。

# 终端快捷键

`ctl+L` 清屏但不删除命令。内容向上滚动 ( 比clear要好得多 )

`ctl+U` 清空当前命令(太有用了!)

`shift+pageDown/up` 翻页

`shift+home/end` 光标移至头/尾

# 命令

## kill

`kill <-信号> <pid>`

- `TERM` 正常退出程序

- `INT` 相当于ctrl+c 来中断一个程序

- `KILL` 杀死进程，强制剥离cpu释放资源

- `STOP` 暂停进程

- `CONT` 恢复进程

- `HUP` 重载，更新进程配置

- `USER1`, `USER2`用户自定义信号

> 1. **TERM (15 - SIGTERM):** 发送TERM信号通知进程正常退出。这是一种优雅的终止方式，允许进程完成正在进行的任务并清理资源。大多数进程在接收到TERM信号时会尽力进行清理操作，然后自行退出。
> 2. **INT (2 - SIGINT):** 发送INT信号通常是通过按下键盘上的Ctrl+C组合键来触发。这是一种中断信号，通常用于终止正在终端中运行的前台进程。类似于TERM，它也允许进程进行清理工作，但有时候它可能会被忽略。
> 3. **QUIT (3 - SIGQUIT):** 发送QUIT信号会引起进程的核心转储（core dump）。与TERM相似，QUIT也是一种终止信号，但它会导致进程在退出时生成核心转储文件，用于调试。
> 4. **KILL (9 - SIGKILL):** 发送KILL信号是强制终止进程的一种方式，进程无法捕获或阻止该信号。它会立即终止进程，不给进程任何清理或关闭的机会。KILL信号是一种"最后手段"，因为它不允许进程完成清理工作。

## ⛔😏💥☢️😱☠️

- `rm -rf /` 删库跑路

- `dd if=/dev/zero of=/dev/sda` 擦除系统盘数据
  
  - 从`/dev/zero`读取数据，然后写入到`/dev/sda`，相当于清空系统盘数据。`/dev/zero`提供了一个无限连续的零字节流。当你从 `/dev/zero` 读取数据时，你会得到一个无限的流，所有的数据都是零字节。

## 磁盘相关

- `df [-T] [-h]` 查看系统磁盘空间

- `du <目录>` 查看指定目录磁盘大小

- `free` 显示内存资源情况

- `top` 显示系统资源占用排行榜
  
  - `shift + e` 切换数据显示单位 ( KB,MB,GB ) 

## 系统

- `uname` 输出特定的系统信息,可以查看系统内核，系统版本等内容
- `tail` 将文件内容写到标准输出(shell) 可以持续用来打印日志文件内容 `tail -f <目录>`
- `more` 分页查看指定目录的完全内容
- `scp` 用于linux之间复制文件和目录
  - `scp abc.txt root@ipaddress:/home` 将abc.txt上传到指定ip地址的home目录下
  - `scp root@ipaddress:/home/abc.txt ./` 将指定ip地址的home目录下的abc.txt下载到当前shell目录下
- `chsh` 更改使用者shell设定(切换shell类型:bash, zsh等)
- `tree <目录>` 显示指定目录的树形结构 
- `whereis`  定位一个“命令”的二进制文件、源文件、手册文件
- `pwd` 显示当前所在完整路径
- `source` 刷新当前shell环境
- `clear` 清除控制台 ( 这个应该刻进基因里了吧... )
- `ssh` ssh协议相关
  - `ssh root@ipaddress` 连接指定ip地址的主机
  - `ssh-keygen -t rsa -C "email@email.com"` 生成包含邮箱签名的rsa密钥对
- `wget [url]` 下载url内容
- `curl -i [url]` 向指定url发送http请求并显示response头
- `lsof -i:80` 查看占用80端口的进程
- `ps [-ef] [-aux]` 
- `grep` 正则匹配

# 配置

## hosts

```shell
/etc/hosts
```

## vscode命令参数

```shell
sudo code --no-sandbox --disable-gpu-sandbox --user-data-dir=/root/.vscode/
```

为了方便可以给code再配个别名，加上这些参数就好了。我使用的debian12系统在root模式下使用vscode不能输入中文，不知道什么原因...🫠

## 服务

debian系统服务目录：

```shell
/lib/systemd/system/xxx.service
```

centos系统服务目录：

```shell
/usr/lib/systemd/system/xxx.service
```

以nginx.service内容为例：

```nginx
[Unit]
Description=nginx web service
Documentation=http://nginx.org/en/docs/
After=network.target #服务在network.target启动之后启动

[Service]
Type=forking #服务类型 在启动时会创建子进程
PIDFile=/usr/local/nginx/logs/nginx.pid #pid文件位置
ExecStartPre=/usr/local/nginx/sbin/nginx -t -c /usr/local/nginx/conf/nginx.conf  #执行前预处理指令(nginx -t测试)
ExecStart=/usr/local/nginx/sbin/nginx #执行指令
ExecReload=/usr/local/nginx/sbin/nginx -s reload #重载指令
ExecStop=/usr/local/nginx/sbin/nginx -s stop #停止指令
PrivateTmp=true #启用一个私有的临时文件系统，服务将在其中操作，这有助于增加服务的安全性

[Install]
WantedBy=default.target #服务应该被添加到 default.target，即系统的默认目标，以确定服务在系统启动时是否启用。
```

## 环境变量

全局配置是/etc/profile

同样是在~/.bashrc文件下(或者~/.zshrc 取决于你的shell)
添加：

```shell
export PATH=$PATH:/usr/local/nginx/sbin
```

将指定目录添加到环境变量中

## 命令别名

在 ~/.bashrc 下 

注意：root用户和普通用户的 .bashrc 是隔离的

```bash
alias ll='ls $LS_OPTIONS -l'
alias dps='docker ps --format "table {{.ID}}\t{{.Image}}\t{{.Ports}}\t{{.Status}}\t{{>
alias dis='docker images'
```

## 源

- apt源位置：
  
  ```shell
  /etc/apt/sources.list
  ```
  
  ​    参考: [url](https://developer.aliyun.com/mirror/)

- yum源位置:
  
  ```shell
  /etc/yum.repos.d/
  #这里的.d 是一种命名风格，表示directory 目录的意思
  ```

## 美化

我的终端一直显示的完整的路径前缀，这样如果我操作的文件非常深入，那么就很难受。因此我就把他改成了只显示最后一级目录，这样清爽一些

配置方式：在`/etc/profile`中配置就可以应用到全局。当然也可在普通用户或者root用户的.bashrc中配置

```bash
# 完全显示完整的工作目录名称
export PS1='[\u@\h $PWD]\$ '
# 只列出最后一个目录
export PS1='[\u@\h \W]\$'  # 我用的就是这个
# 显示完整工作目录，当前用户目录会以 ~代替
export PS1='[\u@\h \w]\$'
```

修改完成后重新加载该配置文件即可 `source /etc/profile`

这里我记录一个我自己稍微改装了一下的比较满意的配置：

```bash
export PS1='[\[\e[1;35m\]\u@\h:\[\e[0m\]\[\e[1;33m\]\W\[\e[1;35m\]\[\e[0m\]]\[\e[1;34m\]\$ \[\e[0m\]'
```

### zsh shell

zsh支持插件扩展和主题功能。

[oh-my-zsh](https://ohmyz.sh/)是zsh的一个实现。是管理zsh配置的框架。

在oh-my-zsh官方的[github wiki](https://github.com/ohmyzsh/ohmyzsh/wiki)中列出了很多内置的主题。我们不仅可以应用这些内置主题，还可以在这些内置主题上进行二次定制，非常银杏化！

- 插件位于:   `~/.oh-my-zsh/pulgins/`

- 主题位于:   `~/.oh-my-zsh/themes/`

- 修改插件/主题： 在`~/.zshrc`中配置。 

#### 安装oh-my-zsh

1. 检查当前可用shell
   
   ```bash
   cat /etc/shells
   ```

2. 查看当前使用的shell
   
   ```bash
   echo $SHELL
   ```

3. 安装zsh shell
   
   ```bash
   apt install zsh
   ```

4. 切换shell
   
   ```bash
   chsh -s /bin/zsh
   ```

5. 安装oh my zsh
   
   ```bash
   sh -c "$(curl -fsSL https://raw.github.com/robbyrussell/oh-my-zsh/master/tools/install.sh)"
   ```

6. 下载语法高亮插件
   
   ```bash
   git clone https://github.com/zsh-users/zsh-syntax-highlighting.git ${ZSH_CUSTOM:-~/.oh-my-zsh}/plugins/zsh-syntax-highlighting
   ```

7. 下载自动提示插件
   
   ```bash
   git clone https://github.com/zsh-users/zsh-autosuggestions ${ZSH_CUSTOM:-~/.oh-my-zsh}/plugins/zsh-autosuggestions
   ```

8. 安装插件
   
   ```bash
   nano ~/.zshrc
   ```
   
   ```bash
   plugins=(git zsh-syntax-highlighting zsh-autosuggestions)
   ```

9. 更新`.zshrc`
   
   ```bash
   source ~/.zshrc
   ```

# 用户

`/etc/passwd` 文件中保存了机器上所有的用户信息

## 用户

添加用户

```shell
adduser www
```

更改密码

```shell
passwd www
```

删除用户

```shell
userdel -r www
```

## 修改所属用户和用户组

修改所属用户以及用户组

```sh
chown [-R] ownname:groupname filename
```

修改所属用户组

```sh
chgrp [-R] groupname filename
```

# 优化

## 扩展

我使用的是debian gnome桌面

记录一些提升体验的扩展程序：

1. Plank
   
   桌面底部dock栏
   
   ```shell
   sudo apt update && sudo apt install plank
   ```

2. 系统默认输入法Fcitx5
   
   我喜欢极简风格，摒弃那些花里胡哨还占用资源的第三方输入法程序

3. [gnome-shell-extension-appindicator](https://github.com/ubuntu/gnome-shell-extension-appindicator)
   
   因为gnome桌面无法显示后台运行的程序，有的应用程序已关闭就进入后台运行，无法再次唤起。这种情况太让人头疼。这个扩展程序可以让桌面系统显示托盘程序。实现类似win的右下角托盘效果。

## 体验

有时候在 `/etc/profile` 中配置了别名或者环境变量，但是每次重启shell或者重启系统，这些配置都会失效，都必须再执行一遍`source /etc/profile`才行。

解决： 在`~/.bashrc` 中添加 `source /etc/profile`

# 网络

`netstat` 显示系统网络信息 常用参数: 

- `-tunlp` 显示使用tcp协议的进程名，端口，ip等信息

> `netstat -tunlp | grep 781` 查看781进程监听的端口号.

`ifconfig` 用于配置网络接口

`iwconfig` 用于配置无线网络接口

## 名词解析

使用`ifconfig`查看网络配置，关注一下几个项：

`lo`:  表示本地 local

`wlp3s0`: 表示WLAN PCI接口位置：bus=3(总线), slot=0(插槽)

`enp2s0`: 表示Etnernet PCI接口位置：bus=2(总线), slot=0(插槽)

## 防火墙

debian使用ufw防火墙：

```shell
apt install ufw
ufw status 
```

centos直接使用systemctl :

```shell
systemctl status firewalld
```

# shell脚本

## 后台执行shell脚本

执行命令后面加个 `&` 就好了

```shell
sh jmeter.sh &
```

## 符号

> ${}, [], $(), $(()), [[]], (())的作用

1. `${}`与`$`
   
   都是用来引用变量的。${}可以指定引用的边界，也可以对字符串变量进行截取等

2. `[] `与 `test`
   
   [ ]是test命令的另一种形式，用于判断某个表达式的返回值是0或者非0，常用于if命令的判断条件
   `test $a == "linux"` 等于 `[ $a == "linux" ]`
   
   ```shell
   if test $a == "linux"
   then
       echo "i am linux"
   elif [ $a == "java" ] 
   then
       echo "i am java"
   fi
   ```
   
   > 注意"[“后和”]“前都需要有空格，并且”=="两边也都要有空格

3. `$()` 和 ``
   
   $()和``的作用一致，都是用来做命令替换用，一般用于将命令返回的结果传递给变量
   
   ```shell
   a=$(ls /home/hadoop101/)
   a=`ls /home/hadoop101/\`
   # a保存的是上述命令的返回值，即一个目录列表
   ```

4. `$[ ] `和` $(( ))`
   
   $[]和$(())的作用一样，都是进行数学运算的，支持±*/%,并且在$[ ]和$(( ))中使用变量不需要$引用，可以直接使用变量名
   
   ```shell
   echo $[2+7]
   # 9
   a=3;b=4;echo $[$a+$b]
   # 7
   echo $((2+7))
   # 9
   a=3;b=4;echo $(($a+$b))
   # 7
   a=3;b=4;echo $((a+b))
   # 7
   ```

5. `[[]]`
   
   [[ ]]是[ ]的增强版，其返回值也是0或者非0
   
   - 在`[[]]`中使用> < 不需要转义字符
     
     ```shell
     if [[ $1 > 5 ]]
     then
             echo "$1的值大于5"
     else
             echo "$1的值小于5"
     fi
     ```
   
   - 支持&&和||，但是仅支持==和!=的连接
     
     ```shell
     # 正确示例
     if [[ $a != 3 && $a != 10 ]] 
     then
         echo "hello i am linux"
     fi
     # 错误示例：
     if [[ $a > 3 && $a != 10 ]] 
     then
         echo "hello i am linux"
     fi
     ```
   
   - `[[]]`比较字符串支持正则匹配和通配符匹配
     
     ```shell
     # 通配符匹配
     if [[ linux == l?nu? ]]
     then
         echo "i am linux"
     else
         echo "i am not linux"
     fi
     ```
     
     ```shell
     # 正则匹配
     if [[ linux =~ ^li[abn]ux ]]
     then
         echo "i am linux"
     else
         echo "i am not linux"
     fi
     ```

6. `(())`
   
   - 与$结合使用进行数学运算$(( ))
   - 在for循环命令中控制循环，类似于c语言
   - 改变变量的值，且不需要$引用
   
   ```shell
   for((i=1;i<10;i++))
   do
           echo "this is $i"
   done
   ```
   
   ```shell
   i=0
   while [ $i -le 10 ]
   do
           echo "this is $i"
           ((i++))
   done
   
   # 或者
   
   i=0
   while [ $i -le 10 ]
   do
           echo "this is $i"
           ((i=i+1))
   done
   ```

# WSL

windows subsystem of linux ( windows下的linux子系统 )

WSL允许你在windows操作系统上运行linux子系统. 比虚拟机方便很多. 

WSL安装: 参考[MS官方文档](https://learn.microsoft.com/zh-cn/windows/wsl/install-manual)

如何安装, 官方给文档很详细了. 我这里主要记录一些wsl的常见操作

## 常见命令

- `wsl --help` - 帮助文档

- `wsl` - 启动子系统

- `wsl --shutdown` - 关闭子系统

- `wsl --list -v`  - 列出所有的子系统及其对应的wsl版本号

- `wsl --list --online` - 列出全部可用的子系统

- `wsl -s <子系统>` - 设置默认的子系统

## 导入和导出

这个有点类似于挂载和卸载. 使用wsl安装的子系统在win上都会有一个磁盘映像来存储数据, 这个磁盘默认是装在C盘的, 但是我们可以手动更改他的位置. 

对于linux系统来说, 在安装的时候不要让他自动安装.  选择手动安装. 自己去官网下载发行版的安装包. 然后将下载下来的.AppBundle后缀的文件改为.zip进行解压. 会得到系统映像文件 .appx后缀的文件, 再次将其后缀改为.zip进行解压, 最后会得到 .exe的可执行文件. 在想要安装的位置运行该exe可执行文件即可安装linux子系统. 然后会发现 有一个ext4.vhdx的磁盘映像文件, 这个就是子系统的数据盘.

重点是在win上安装docker desktop. docker desktop的使用需要基于wsl . 如果默认安装的话, 他会生成两个磁盘映像文件, 一个是docker-desktop, 一个是docker-desktop-data. 他们都默认在 `C:\\user\\xx\\AppData\\Local\\Docker\\wsl` 位置下,  这样一旦docker镜像多了后, 非常占用c盘空间. 我们需要将这两个盘挂载到D盘下以节省C盘空间

这就会用到wsl的导入和导出命令

### 步骤

1. 退出docker desktop , 关闭wsl
   
   ```cmd
   wsl --shutdown
   ```

2. 查看wsl的状态, 确认关闭
   
   ```cmd
   wsl --list -v
   ```

3. 导出docker-desktop-data磁盘文件
   
   ```cmd
   wsl --export docker-desktop-data "D:\\docker-desktop-data.tar"
   ```

4. 卸载docker-desktop-data磁盘文件 ( 此命令会删除 docker-desktop-data 的磁盘映像文件)
   
   ```cmd
   wsl --unregister docker-desktop-data
   ```

5. 将导出的磁盘文件重新导入wsl, 并指定导入的位置
   
   ```cmd
   wsl --import docker-desktop-data "D:\\.WSL\\Docker\\wsl" "D:\\docker-desktop-data.tar" --version 2
   ```

完成!

但实际上, docker desktop设置中提供了更改disk image location功能, 可以让我们很方便的更改这两个磁盘映像文件的位置. 

这里只是以docker desktop作为一个例子, 能学会举一反三, 应用到其他情况下. 同时也有助于我对wsl的理解. 

有了wsl这么方便的东西, 还要什么虚拟机, 什么vmware, 什么virtualbox 都靠边站 !  😝. 

## 问题记录

1. wsl运行时出现警告信息: 
   
   ```cmd
   wsl: 检测到 localhost 代理配置，但未镜像到 WSL。
   NAT 模式下的 WSL 不支持 localhost 代理。
   ```

**解决方法:**

在`C:\\user\\xxx` 下创建一个 .wslconfig 文件, 内容如下:

        ```yaml
        [experimental]
        autoMemoryReclaim=gradual  
        networkingMode=mirrored
        dnsTunneling=true
        firewall=true
        autoProxy=true
        ```

然后关闭wsl, 重新启动即可.

# 查看机器的公网ip

通过一些第三方服务可以实现. 

```shell
curl ip.sb
```

# 监控系统流量

`iftop` 命令用于监控系统流量。

```shell
iftop -i eth0
```

进入交互界面后 按 `h` 显示帮助菜单。
