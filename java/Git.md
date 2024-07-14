> 学习git操作?一个游戏就够了: https://learngitbranching.js.org/?locale=zh_CN
>
> - 出现`refusing to merge unrelated histories`报错时，说明本地仓库和远端仓库不一致，解决方法：`git pull/push --allow-unrelated-histories`
> - rebase过程中出现 `(master|REBASE 1/10)` 解决方案：
>   使用`git rebase --abort` 指令终止提交回退。

# 问题记录1: 总是无法push或pull
> 参考方案地址：[知乎](https://zhuanlan.zhihu.com/p/521340971)
## 问题： 
git clone , git push , git pull等所有使用ssh url的命令没反应,过段时间后显示连接超时。
## 原因：
ssh协议默认使用22端口。在机器的`~/.ssh/config`文件中没设置ssh端口，或者压根就没有config文件，又或者域名设置错误，又或者系统没开启ssh服务，都有可能导致这个情况的发生。
## 解决：
1. 系统启动ssh服务，让其监听22端口
   ```
   systemctl stop ssh
   systemctl start ssh
   systemctl status ssh #查看服务状态，应该要出现listening on 0.0.0.0:22 字样，表示服务正在监听22端口
   ```
2. 配置`~/.ssh/config`文件，让git的ssh操作走22端口
    ```
    Host github.com
        Hostname ssh.github.com  #注意域名不要写错！！
        Port 22
    ```

# 问题记录2:gitignore不生效

## 问题:

在项目一开始创建的时候没有创建.gitignore文件. 经过几轮提交后才后知后觉,写了.gitignore文件. 但是却不生效

## 原因:

是因为经过了提交后,git已经track了那些原本你不想提交的文件或目录,比如.idea目录, target目录等.

## 解决:

清除git缓存中的这些文件,然后再次add, commit, push

```shell
git rm --cached -r .idea/
git rm --cached -r target/
git rm --cached *.class
git add .
git commit -m "Update .gitignore to fix ignoring issues"
```

# log树

```sh
git log --graph --oneline --all
```

# 修改commit
```sh
git rebase -i <commit id>
```

修改git message前的关键字，修改message一般改为reword
- **pick**：保留该 commit
- **reword**：保留该 commit，但我需要修改该commit的 Message
- **edit**：保留该 commit, 但我要停下来修改该提交(包括修改文件)
- **squash**：将该 commit 和前一个 commit 合并
- **fixup**：将该 commit 和前一个 commit 合并，但我不要保留该提交的注释信息
- **exec**：执行 shell 命令
- **drop**：丢弃这个 commit

修改完成 强制push到远端 : `git push origin [分支] -f`

# 回滚

分为两种情况：

- 强制回滚到某一个提交处，并且不打算保留该提交之后的所有提交

  ```sh
  git reset --hard <commit id>
  ```

- 回滚到某一个提交处，但是保留之后的提交

  ```sh
  git revert <commit id>
  ```

  revert操作是新增一个提交，但是该提交的内容是指定commit id的内容。相当于抵消了指定提交之后的所有更改，但是保留了这些更改。

