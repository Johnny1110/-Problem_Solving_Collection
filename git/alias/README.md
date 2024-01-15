# git 我常用的 alias

<br>

----

<br>
<br>

## 設置 alias 介紹

<br>

在 Git 中，您可以使用以下步骤设置别名（alias）：

1. 全局配置（对所有仓库生效）：
   使用下面的命令设置全局别名：
   ```shell
   git config --global alias.<alias-name> <git-command>
   ```

2. 仓库配置（仅对当前仓库生效）：
   使用下面的命令设置仓库别名：
   ```shell
   git config alias.<alias-name> <git-command>
   ```

在上述命令中，将`<alias-name>`替换为您要设置的别名，将`<git-command>`替换为您想要绑定到别名的 Git 命令。

例如，如果您希望将 `git status` 命令设置为别名 `st`，可以执行以下命令：

- 全局配置：
  ```shell
  git config --global alias.st status
  ```

- 仓库配置：
  ```shell
  git config alias.st status
  ```

设置完成后，您可以在命令行中使用 `git st` 来替代 `git status` 来执行相同的操作。

使用别名可以简化常用命令的输入，并提高命令行的效率。您可以根据需要为 Git 命令设置任意数量的别名。

请注意，如果您使用全局配置设置别名，则在所有仓库中都可以使用该别名。如果使用仓库配置，则只能在当前仓库中使用该别名。


<br>
<br>
<br>
<br>

## 我常用的 alias

<br>

### 看 commit 相關

```
alias.lg1 log --graph --abbrev-commit --decorate --format=format:'%C(bold blue)%h%C(reset) - %C(bold green)(%ar)%C(reset) %C(white)%s%C(reset) %C(dim white)- %an%C(reset)%C(bold yellow)%d%C(reset)' --all
```

```
alias.lg2 log --graph --abbrev-commit --decorate --format=format:'%C(bold blue)%h%C(reset) - %C(bold cyan)%aD%C(reset) %C(bold green)(%ar)%C(reset)%C(bold yellow)%d%C(reset)%n''          %C(white)%s%C(reset) %C(dim white)- %an%C(reset)' --all
```

```
alias.lg !git lg1
```

<br>
<br>
<br>
<br>

## ChatGPT 推薦

<br>

```
git config --global alias.co checkout        # git co 来代替 git checkout
git config --global alias.br branch          # git br 来代替 git branch
git config --global alias.ci commit          # git ci 来代替 git commit
git config --global alias.st status          # git st 来代替 git status
git config --global alias.unstage 'reset HEAD --'    # git unstage 来撤销暂存
git config --global alias.last 'log -1 HEAD'          # git last 来查看最后一次提交
git config --global alias.visual '!gitk'              # git visual 来打开图形化界面
```
