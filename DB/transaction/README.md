# SQL Transaction Isolation Levels

<br>

面試時遇到一題，考甚麼是 Transaction Isolation Levels。我直接一臉矇逼。

來好好補一下 SQL Transaction 知識。

<br>

## Isolation（隔離性）：

<br>

資料庫允許多個並發交易同時對其數據進行讀寫和修改，
隔離性可以防止多個交易並發執行時由於交叉執行而導致資料的不一致。
交易隔離分為 4 種不同級別，包括：

* 提交讀（read committed）

* 未提交讀（Read uncommitted）

* 可重複讀（repeatable read）

* 串行化（Serializable）

<br>

我這邊用 MySQL 實驗，他的預設是用 REPEATABLE READ 等級。

<br>
<br>

首先來布置一下實驗表與資料：

<br>

```SQL
create table acidsample
(
    ACIDSample_Id    bigint auto_increment primary key,
    ACIDSample_Name  varchar(100) charset utf8 null,
    ACIDSample_Price decimal null
);

INSERT INTO acidsample (ACIDSample_Id, ACIDSample_Name, ACIDSample_Price) VALUES (1, 'Toy', 150);
INSERT INTO acidsample (ACIDSample_Id, ACIDSample_Name, ACIDSample_Price) VALUES (2, 'Shoes', 120);
```

<br>
<br>

接下來一個一個 Level 來看，這邊會需要開 2 個 Session 來模擬多個操作並行的情境。

<br>
<br>

### 提交讀（read committed）

<br>

Session-1：

<br>

我們嘗試更新 `ACIDSample_Price` 讓他的值 + 1，等待 10 秒模擬複雜耗時操作後，直接回滾。

<br>

```sql
START TRANSACTION;
UPDATE ACIDSample
SET ACIDSample_Price = ACIDSample_Price + 10
WHERE ACIDSample_Id = 1;
DO SLEEP(10);
ROLLBACK;
```

<br>

Session-2：

<br>

設定 TRANSACTION ISOLATION LEVEL 為 `READ COMMITTED`。並讀取 Session-1 中正在被修改的資料。

<br>

```sql
SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
SELECT ACIDSample_Price FROM ACIDSample WHERE ACIDSample_ID = 1
```

<br>
 
執行結果為 150，這是我們所期待的答案，也就如 `READ COMMITTED` 字面代表意思一樣，讀被 commited 過的，Session-1 中的值沒有正式 commit，所以就以原本值為準來讀取。

<br>
<br>

<br>
<br>


### 未提交讀（Read uncommitted）

<br>

Session-1：

<br>

內容跟上面一樣不變：

<br>

```sql
START TRANSACTION;
UPDATE ACIDSample
SET ACIDSample_Price = ACIDSample_Price + 10
WHERE ACIDSample_Id = 1;
DO SLEEP(10);
ROLLBACK;
```

<br>

Session-2：

我們把 TRANSACTION ISOLATION LEVEL 為 `READ UNCOMMITTED`。並讀取 Session-1 中正在被修改的資料。

<br>

```sql
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
SELECT ACIDSample_Price FROM ACIDSample WHERE ACIDSample_ID = 1
```

<br>

這次就不太一樣了，結果為 160，也就是還未被 commit 就被讀出來了。

<br>
<br>
<br>
<br>

### 可重複讀（repeatable read）

<br>

這裡會用到 3 個 Session 舉例：

<br>

Session-1：

<br>

首先設定等級為 `REPEATABLE READ`，這裡打算 SELECT 3 次表，第一次查詢後，等待 10 秒，在此過程中新增一筆資料，再次 SELECT 一次。等待 10 秒，過程中刪除剛剛新增的資料，然後再次 SELECT。來看看實際運作。

<br>

```sql
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
START TRANSACTION;
SELECT * FROM ACIDSample;
DO SLEEP(10);
SELECT * FROM ACIDSample;
DO SLEEP(10);
SELECT * FROM ACIDSample
COMMIT;
```

執行 Session-1 後馬上執行 Session-2

<br>

Session-2：

<br>

```sql
INSERT INTO ACIDSample (ACIDSample_Name,ACIDSample_Price)
VALUES ('Cat',200)
```

<br>

等待第二次 SELECT 結果出現後，馬上執行 Session-3。

<br>

Session-3

<br>

```sql
DELETE ACIDSample WHERE ACIDSample_Name = 'Cat'
```

<br>

看一下三次 SELECT 的結果：

<br>

```
------------------------
1	|Toy	|150
2	|Shoes	|120
------------------------
1	|Toy	|150
2	|Shoes	|120
4	|Cat	|200
------------------------
1	|Toy	|150
2	|Shoes	|120
4	|Cat	|200
------------------------

```

<br>

可以看到，第2次的查詢會與第3次一致，但是實際上的資料已經被刪除了。雖然確保了查詢的一致性，但實際上會查詢到不存在的資料。

<br>
<br>
<br>
<br>

### 串行化（Serializable）

<br>

串行化（Serializable）既保證了資料一致性，又不會出現本不該存在的資料。但實際上會帶來效能的耗損，在這樣嚴格的限制下，所有查詢將有序的執行。

<br>

測試方法與可重複讀（repeatable read）基本一樣，只要在 Session-1 第一行把 Levels 改一下：

<br>

```sql
SET TRANSACTION ISOLATION LEVEL Serializable;
...
```