# 報表語法

<br>

---

<br>

## 按照月份做統計

<br>

使用 `date_trunc('year/month/day', timestamp)` 可以做對 table 內的資料做分組：

```sql
select date_trunc('month', create_date_time) as month,
       count(id) as trans_count,
       sum(amount) as trans_amount 
from balance_audit 
group by month 
order by month;
```
