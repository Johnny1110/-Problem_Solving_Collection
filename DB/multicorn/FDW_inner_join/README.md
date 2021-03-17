# Postgres fdw 合併查詢

<br>

---

<br>

前面提到了，Postgres 可以作合併查詢（join），那麼這一篇筆記就來紀錄一下如何實現 fdw 合併查詢。

<br>

首先我準備了一份資料在 GCP 上的 postgresql 內，內容如下：

<br>

players：


|id|name|age|team|
|:---:|:---:|:---:|:---:|
|1|LeBron James|36|Lakers|

<br>
<br>

另外還影另一份資料以 csv 的形式存放在本機上，內容如下：

<br>

/tmp/record.csv：


```csv
id,career_high,salary
1,61pts,$3922M
```

<br>

有了以上的備料就可以到 `psql` 設定 FDW 了。

<br>

設定 foreign data wrapper：

```sql
CREATE SERVER alchemy_srv foreign data wrapper multicorn options (
    wrapper 'multicorn.sqlalchemyfdw.SqlAlchemyFdw'
);
```

<br>

設定 foreign table：

<br>

```sql
create foreign table players (
    id int,
    name varchar,
    age int,
    team varchar
) server alchemy_srv options (
    tablename 'players',
    db_url 'postgresql://lbj:password@104.155.193.214:5432/nba'
);
```

<br>

建立好後測試一下是否正常運作：

<br>

```sql
select * from players;
```

<br>

![1](imgs/1.png)


<br>

這邊都沒有問題，接下來建立 csv_fdw，首先先來建立 csv foreign data wrapper：

<br>

```sql
create server csv_srv foreign data wrapper multicorn options (
    wrapper 'multicorn.csvfdw.CsvFdw'
);
```

<br>

然後再來建立 foreign table：

<br>

```sql
create foreign table record (
    id int, 
    career_high varchar, 
    salary varchar
) server csv_srv options (
    filename '/tmp/record.csv', skip_header '1', delimiter ','
);
```

<br>

查詢一下 record 是否可以正常使用：

<br>

```sql
select * from record;
```

<br>

![2](imgs/2.png)

<br>

都正常使用，接下來就來合併查詢：

<br>

```sql
select players.name, record.career_high, record.salary 
from players 
inner join record 
on players.id = record.id;
```

<br>

![3](imgs/3.png)

<br>

以上就是實現 fdw 合併查詢的細節。

