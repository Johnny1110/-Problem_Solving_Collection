package com.frizo.schedule;

import org.quartz.Job;

/** 所有專案中的 job 都應繼承這個 class */
public abstract class BaseQuartzJob implements Job {

    public abstract Thread getCurrentThread();

}
