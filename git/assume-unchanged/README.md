# 讓 git 永久忽略某個文件的方法 (不使用 .gitignore)

<br>

---

<br>

專案上有一些環境設定檔，不可以推上 gitlab，每次推之前還要檢查就很麻煩，忽略檔案異動定義在 .gitingore 又會影嚮到其他開發者，所以當只需要在自己 local 端讓 git 忽略某個文件的方法可以這樣做：


<br>

git 永久忽略某文件：

```
git update-index --assume-unchanged <file>
```

<br>

git 解除永久忽略某文件：

```
git update-index --no-assume-unchanged <file>
```

<br>
<br>

永久忽略某文件後，無論如和改動該文件，git 每次 add commit 時都不會管這份文件。