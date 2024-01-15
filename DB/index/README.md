# 索引設計與雷區

<br>

---

<br>

看過3篇關於 MySQL 索引講解的文章，這裡記錄一下使用 index 上的重點與注意事項

<br>

參考原文：

IThome 鐵人賽 - 30天之從 0 至 1 盡可能的建立一個好的系統 (性能基礎篇)系列

[30-11 之資料庫層的核心 - 索引結構演化論 B+樹](https://ithelp.ithome.com.tw/articles/10221111)

[30-12 之資料庫層的核心 - MySQL 的索引實現](https://ithelp.ithome.com.tw/articles/10221572)

[30-13 之資料庫層的優化 - 索引設計與雷區](https://ithelp.ithome.com.tw/articles/10221971)

<br>
<br>

---

<br>
<br>

## 觀念

<br>

### 觀念1. 索引不是越多越好

<br>

索引可以加快查詢速度，但注意它是以空間換取時間。

基本上它使用的資源如下 :

每個索引都會建立一顆 b+ 樹。
每次新增、更新資料時都會改變 b+ 樹。
所以當你索引越多時，你所需要的記憶體與維護索引的 cpu 運算就需要越多。

<br>
<br>

### 觀念2. 使用 Explain 來分析你的 SQL 索引性能解析

<br>

```sql
EXPLAIN SELECT * FROM user_no WHERE name = 'mark';
```

[EXPLAIN 使用說明文章](https://segmentfault.com/a/1190000008131735)

<br>
<br>

### 觀念3. 建立了索引，MYSQL 也不一定會採用

<br>

一張表如下：

```
table : user
field: name, sex
index: {sex}
```

<br>

執行 SQL

```sql
SELECT * FROM user WHERE sex = 0 ( 0 代表女生 )
```

這個索引建立，當資料較少時，全表掃描可能會比 B+樹搜尋更加快速，節省 I/O 資源。那這種情況下，MYSQL 就會採用全表搜尋方式。（一個欄位只有男與女兩種，那這時你要找女的，你覺得全掃一個一個找比較快，還是先用索引找出所有女的 pk，然後再去 clustered Index 抓資料呢 ?）

<br>
<br>


### 觀念4. 使用的索引類型

<br>

* 一般索引(覆蓋索引) : 在一些常用單獨 query，如果常常需要與其它欄位一起查詢，可考慮建連合索引。

* 連合索引 : 常常需要與其它欄位一起查詢，適合建立。但是要注意先後順序，基本最常查詢的與基數高的適合放最左邊。

* 前綴索引 : 這種大致上用在如果要針對某段文字進行前綴搜尋的情況。

* 唯一索引 : 這種適合在此欄位放貨物編號或啥這種編號的，它可以順到幫你檢查是不是唯一。


<br>
<br>

---

<br>
<br>

## 索引使用注意事項



<br>

### 注意事項1. 連合索引的欄位順序

<br>

假設有一個連合索引如下，由三個欄位 a、b、c 組合而成，而事實上在使用時，可以將它想成有下以三種組合，當然這不代表他有存放三顆樹。

```
聯合索引: {a, b, c}

使用情境1: {a}
使用情境2: {a, b}
使用情境3: {a, b, c}
```

__<p style="color:red;">注意，聯合索引使用時可以有不同組合，但是最左邊的索引必須被使用到。<p>__

<br>

舉例：

```
SELECT * FROM Table WHERE a = ? ( good 索引 )
SELECT * FROM Table WHERE a = ? AND b = ? ( good 索引 )
SELECT * FROM Table WHERE b = ? AND a = ? ( good 索引 )


SELECT * FROM Table WHERE b = ? ( bad 全掃 )
SELECT * FROM Table WHERE c = ? ( bad 全掃 )
SELECT * FROM Table WHERE b = ? AND c = ? ( bad 全掃 )
```

<br>
<br>


### 注意事項1. 儘可能使用索引的排序

<br>

索引本身就有排序了，借用索引排序比較省時間，如果要用非索引欄位排序，就必須另外使用空間並計算    

```
索引欄位: { age }

SELECT * FROM user WHERE age <= 30 ORDER BY age; ( good 索引 )

SELECT * FROM user WHERE age <= 30 ORDER BY name; ( bad using filesort )
```

<br>

聯合索引也有排序，他使用最左邊的欄位排序

<br>
<br>

### 注意事項3. 有時太多索引，反而會讓優化器混亂

<br>

簡單的說，你有以下三欄位，a、b、c 然後請不要想說讓性能好一點，來個所有組合的索引。這樣不但多化了不少空間建 b+ 樹，而且還會讓 mysql 優化器選錯索引。

```
a、b、c
a、c、b
b、c、a
b、a、c
c、a、b
c、b、a
```

__do not do that !__


<br>
<br>
<br>
<br>

## 地雷區

<br>

雷區1. 使用 != 、 <>、NOT 查詢會變全掃

```
SELECT * FROM user WHERE age != 20 ( bad 全掃 )
SELECT * FROM user WHERE age <> 20 ( bad 全掃 )
SELECT * FROM user WHERE age NOT IN(20) ( bad 全掃 )
```

<br>
<br>

雷區 2 : 用 like 且 % 在前面有索引也會變全掃
如果在 name 有建立索引，那有下面這種 sql 會變全掃。

```
SELECT * FROM user WHERE name like '%-Mark' ( bad 全掃 )
```

<br>
<br>

雷區 3 : 誤用 or 時會變全掃

```
索引欄位: {age}

SELECT * FROM user WHERE age = 18 OR name = 'C-Ian'; ( bad 全掃 )

索引欄位: {age},{name} 

SELECT * FROM user WHERE age = 18 OR name = 'C-Ian'; ( good 索引 )

索引欄位: {age}

SELECT * FROM user WHERE age = 18 AND name = 'C-Ian'; ( good 索引 )
```

<br>
<br>

雷區 4 : SELECT * FROM

因為這樣可能會讓你所建立的『 覆蓋索引 』失效，導致要先至 secondary index 查找，再去 clustered Index 抓資料。

```
索引欄位: {name}

SELECT * FROM user; ( bad 會走 secondary index 再至 clustered Index )

SELECT name FROM user; ( good 只會走 secondary index )
```