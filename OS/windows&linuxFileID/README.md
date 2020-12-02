# Windows 與 Linux 的檔案唯一識別方法，以及 Linux 的 file createTime

<br>

紀錄日期 2020/12/02

---

<br>

遇到一個功能需求，要取得檔案的建立日期，這個功能起初在 Windows 上做順風順水，換到 Linux 上直接烙賽。原因是 除了 BSD 其他 unix like 的 OS 都不會去紀錄檔案建立時間，只會紀錄存取以及異動時間。

難道 Linux 上就真的沒有方法可以查檔案的建立日期嗎？答案是肯定的。

<br>

## 在 Linux 上取得檔案建立日期

<br>

以某檔案為例 "/home/johnny/lib/test.txt"

* 第一步取的檔案所在儲存裝置名稱：

    ```bash
    df -T /home/johnny/lib/test.txt
    ```

    結果如下：

    ```bash
    檔案系統       類型   1K-區段     已用      可用 已用% 掛載點
    /dev/sdb5      ext4 400356728 10331364 369618632    3% /home
    ```

    我們需要的資訊是： /dev/sdb5

<br>

* 第二步是查詢檔案 inode，什麼是 inode 呢，簡單說就是檔案唯一識別碼，但是事實上遠不只如此，這還關係到 metadata，有興趣可以去爬一下資料。

    ```bash
    ls -i /home/johnny/lib/test.txt
    ```

    結果如下：

    ```bash
    11013394 /home/johnny/lib/test.txt
    ```

    `11013394` 就是我們需要的 inode 資訊。

<br>

* 第三步也是最後一步，使用 debugfs 工具查詢

    ```bash
    sudo debugfs -R 'stat <11013394>' /dev/sdb5
    ```

    結果如下：

    ```bash
    Inode: 11013394   Type: regular    Mode:  0664   Flags: 0x80000
    Generation: 2902646106    Version: 0x00000000:00000001
    User:  1000   Group:  1000   Project:     0   Size: 0
    File ACL: 0
    Links: 1   Blockcount: 0
    Fragment:  Address: 0    Number: 0    Size: 0
    ctime: 0x5fc749fd:84589ba8 -- Wed Dec  2 16:02:05 2020
    atime: 0x5fc749e1:05ce959c -- Wed Dec  2 16:01:37 2020
    mtime: 0x5fc749e1:05ce959c -- Wed Dec  2 16:01:37 2020
    crtime: 0x5fc749e1:05ce959c -- Wed Dec  2 16:01:37 2020
    Size of extra inode fields: 32
    Inode checksum: 0x24c43d4a
    EXTENTS:
    ```

    建立日期是 ： `crtime: 0x5fc749e1:05ce959c -- Wed Dec  2 16:01:37 2020`

<br>
<br>
<br>

完成以上之後，順利取得 createtime 就算完成工作了，順便還發現了 Inode 的存在，後來仔細想一下，Windows 中會不會也有這種東西呢？查了一下還真的有。

<br>
<br>

## Windows 檔案唯一識別碼

<br>

舉一個檔案為例："D://lib/test.txt"

```bash
fsutil file queryfileid D://lib/test.txt
```

結果我就不放了，在 Linux 環境寫的筆記沒辦法實測。

