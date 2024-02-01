> 记录使用debian过程中收获的一些linux知识点(持续更新)

# 命令

## 磁盘相关

- `df [-T] [-h]` 查看系统磁盘空间
  
- `du <目录>` 查看指定目录磁盘大小
- `free` 显示内存资源情况
- `top` 显示系统资源占用排行榜

## 系统

- `scp` 用于linux之间复制文件和目录,scp是加密的(ssh远程连接时上传文件用)

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
- `ps [-ef] [-aux]` 列出所有进程
- `|` 管道符 用于连接多个命令
- `grep` 正则匹配

# 配置

## 配置命令别名

在 ~/.bashrc 下 

注意：root用户和普通用户的 .bashrc 是隔离的

```bash
alias ll='ls $LS_OPTIONS -l'
alias dps='docker ps --format "table {{.ID}}\t{{.Image}}\t{{.Ports}}\t{{.Status}}\t{{>
alias dis='docker images'
```

## 配置apt源

apt源位置：

```bash
/etc/apt/sources.list
```

ali源：
```
deb https://mirrors.aliyun.com/debian/ bullseye main contrib non-free
deb-src https://mirrors.aliyun.com/debian/ bullseye main contrib non-free

deb https://mirrors.aliyun.com/debian/ bullseye-updates main contrib non-free
deb-src https://mirrors.aliyun.com/debian/ bullseye-updates main contrib non-free

deb https://mirrors.aliyun.com/debian/ bullseye-backports main contrib non-free
deb-src https://mirrors.aliyun.com/debian/ bullseye-backports main contrib non-free

deb https://mirrors.aliyun.com/debian-security/ bullseye-security main contrib non-free
deb-src https://mirrors.aliyun.com/debian-security/ bullseye-security main contrib non-free
```

参考: [url](https://wph.im/190.html)


## 配置终端显示的路径前缀

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

### 我的配置

这里我记录一个我自己稍微改装了一下的比较满意的配置：

```bash
export PS1='[\[\e[1;35m\]\u@\h:\[\e[0m\]\[\e[1;33m\]\W\[\e[1;35m\]\[\e[0m\]]\[\e[1;34m\]\$ \[\e[0m\]'
```

### 自定义规则

顺便记录一下这个自定义规则，方便之后更改：

```
\d ：代表日期，格式为weekday month date，例如："Mon Aug 1"   

\e： ASCII转义字符
 
\H ：完整的主机名称   
 
\h ：仅取主机的第一个名字  
 
\j：shell当前管理的作业数
 
\1：shell终端设备名的基本名称
 
\n：ASCII换行字符
 
\r：ASCII回车
 
\s：shell的名称
 
\t ：显示时间为24小时格式，如：HH：MM：SS   
 
\T ：显示时间为12小时格式   
 
\@：格式为am/pm的12小时制的当前时间
 
\A ：显示时间为24小时格式：HH：MM   
 
\u ：当前用户的账号名称   
 
\v ：BASH的版本信息   
 
\V：bash shell的发布级别
 
\w ：完整的工作目录名称   
 
\W ：利用basename取得工作目录名称，所以只会列出最后一个目录 
 
\ ：下达的第几个命令   
 
\!：该命令的bash shell历史数
 
\#：该命令的命令数量
 
\$ ：提示字符，如果是普通用户，则为美元符号$；如果超级用户（root 用户），则为井号#。
 
\nnn：对应于八进制值 nnn 的字符
 
\\：斜杠
 
\[：控制码序列的开头
 
\]：控制码序列的结尾
```

字体色：

```
	30m==黑色;　　　　31m==红色;　　　　32m==绿色;　　　　33m==黄色;
	34m==蓝色;　　　　35m==洋红;　　　　36m==青色;　　　　37m==白色
```

背景色：

```
 	40m==黑色;　　　　41m==红色;　　　　42m==绿色;　　　　43m==黄色;
 	44m==蓝色;　　　  45m==洋红;　　　  46m==青色;　　　　47m==白色
```

颜色截止

```
\[\e[0m\]
```

其他数字功能

```
0==OFF
1==高亮显示
4==underline
5==闪烁
7==反白显示
8==不可见
```
## zsh shell
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

8. 配置 `~/.zshrc`

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

### 主题和插件

zsh支持插件扩展和主题功能，并且官方提供了很多插件和主题，可以自行选择安装。
在oh-my-zsh官方的[github wiki](https://github.com/ohmyzsh/ohmyzsh/wiki)中列出了很多内置的主题，这些主题都被安装在`~/.oh-my-zsh/themes/`下。我们不仅可以应用这些内置主题，还可以在这些内置主题上进行二次定制，非常银杏化！

zsh插件安装目录是`~/.oh-my-zsh/pulgins/`

- 更换主题
   
   - 修改`~/.zshrc`

   ```bash
   ZSH_THEME="itchy" #我比较中意这个主题
   ```
- 安装插件
 
  - 修改`~/.zshrc`
   
   ```bash
   plugins=(git zsh-syntax-highlighting zsh-autosuggestions) #这里我安装了两个插件：语法高亮和自动提示
   ```