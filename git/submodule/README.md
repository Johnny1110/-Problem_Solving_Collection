# Git 子專案

<br>

---

<br>


當你在Git項目中需要引入其他Git項目時，你可以使用Git子模塊（Git submodule）。Git子模塊允許你在一個Git項目中包含另一個Git項目，並跟踪子模塊的版本。

<br>

以下是Git子模塊的詳細教學：

1. 添加子模塊：
   ```
   git submodule add <repository_url> <path>
   ```
   請替換`<repository_url>`為子模塊的Git存儲庫URL，`<path>`為子模塊存放的路徑。運行該命令後，Git會在指定的路徑下克隆子模塊的存儲庫。

2. 初始化子模塊：
   ```
   git submodule init
   ```
   此命令會初始化Git配置文件以跟踪子模塊。在添加子模塊之後，你需要運行此命令以初始化相應的配置。

3. 更新子模塊：
   ```
   git submodule update
   ```
   此命令會更新子模塊的代碼。如果子模塊的版本已經更新，你需要運行此命令來獲取最新的代碼。

4. 切換子模塊到特定版本：
   ```
   cd <path_to_submodule>
   git checkout <branch_or_commit>
   ```
   你可以使用`cd`命令切換到子模塊的路徑，然後使用`git checkout`命令切換到特定的分支或提交。這允許你在父項目中使用特定版本的子模塊。

5. 克隆包含子模塊的項目：
   ```
   git clone --recurse-submodules <repository_url>
   ```
   如果你要克隆一個包含子模塊的項目，請使用`--recurse-submodules`選項，這樣Git將自動初始化和更新所有子模塊。

6. 提交子模塊的修改：
   ```
   cd <path_to_submodule>
   git checkout <branch>
   git pull
   cd ..
   git add <path_to_submodule>
   git commit -m "Update submodule"
   ```
   如果你在子模塊中進行了修改，你需要切換到子模塊的分支，拉取最新的修改，然後切換回父項目，將子模塊的修改提交到父項目。

這些是使用Git子模塊的基本命令和操作步驟。通過使用這些命令，你可以在Git項目中有效地管理子模塊並跟踪其版本。