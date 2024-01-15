# Git 子專案 （套娃）

<br>

---

<br>


當你在Git項目中需要引入其他Git項目時，你可以使用Git子專案（Git submodule）。Git子專案允許你在一個Git項目中包含另一個Git項目，並跟踪子專案的版本。

<br>

以下是Git子專案的詳細教學：

<br>
<br>

## 添加子專案

<br>

1. 添加子專案：
   ```
   git submodule add <repository_url> <path>
   ```
   請替換`<repository_url>`為子專案的Git存儲庫URL，`<path>`為子專案存放的路徑。運行該命令後，Git會在指定的路徑下克隆子專案的存儲庫。

   <br>

   例如：
   ```
   git submodule add git@gitlab.com:johnnywang1/test_son.git src/main/resource/test_son
   ```

   <br>
   <br>


2. 查看 submodule 狀態：
   ```
   git submodule status
   ```
   此命令可以看出目前已加入該子專案


   ```
   pnt1091567m:test_parent johnnywang$ git submodule status
      4cd5a5396fbc497102137913130922609a67bd69 src/main/resource/test_son (remotes/origin/HEAD)
   ```

   <br>
   <br>

3. 查看主專案狀態

   ```
   git status
   ```

   查看主專案狀態，會得到提示發現新增了子專案的內容

<br>
<br>

4. 對主專案進行 commit，並推至遠端

   ```
   git add -A
   git commit -m "add submodule"
   git push -u origin master
   ```

<br>
<br>

6. 查看 gitlab 或者 github 的主專案 commit，可以發現，主專案已經包含了子專案。

<br>
<br>
<br>
<br>

## 更新子專案

<br>

假如子專案被其他人更新過了，那要在我們自己的開發環境更新子專案，可以照如下步驟：

<br>

1. 更新子專案：
   ```
   git submodule update --recursive --remote
   ```
   此命令會更新子專案的代碼。如果子專案的版本已經更新，你需要運行此命令來獲取最新的代碼。

<br>
<br>

2. 查看主專案狀態，可以發現，子專案有新的 commit

   ```
   git status

   ...
   modified: src/main/resource/test_son (new commits)
   ...
   ```

   直接 commit 推上遠端就可以了。

<br>
<br>
<br>
<br>

## 克隆一個還有子專案的項目

<br>

1. 克隆包含子專案的項目：
   ```
   git clone --recurse-submodules <repository_url>
   ```
   如果你要克隆一個包含子專案的項目，請使用 `--recurse-submodules` 選項，這樣Git將自動初始化和更新所有子專案。

<br>
<br>
<br>
<br>

---

<br>

更多關於 __刪除子專案__，或者 __主專案套用子專案某一分支__ 請參考 https://www.astralweb.com.tw/use-submodule-to-manage-git-subproject/

<br>

---
