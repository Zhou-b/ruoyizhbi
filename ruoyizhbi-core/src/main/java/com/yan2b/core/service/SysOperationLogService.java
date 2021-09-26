package com.yan2b.core.service;

import com.yan2b.core.model.entity.sys.SysOperationLog;

/**
 * @author : Gangbb
 * @ClassName : SysOperationLogService
 * @Description : 操作日志 服务层
 * @Date : 2021/3/7 17:40
 */
public interface SysOperationLogService {

    /**
     * 新增操作日志
     *
     * @param sysOperationLog 操作日志对象
     */
    public void insertOperationLog(SysOperationLog sysOperationLog);
}
