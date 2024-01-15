package com.frizo.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

/** 對應 k8s 上啟動的 pods */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContainerInfo {

    private Date startTime; // pod 啟動時間

    private String uuid; // 每一個 pods 啟動後產生的身分證字號

    private Integer containerQty; // pods 數量
    private AtomicBoolean active; // 是否是開啟狀態

    public void disActive(){
        this.active.set(false);
    }

}
