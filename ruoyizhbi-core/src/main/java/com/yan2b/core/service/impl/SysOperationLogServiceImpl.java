package com.yan2b.core.service.impl;

import com.yan2b.core.model.mapper.SysOperationLogMapper;
import com.yan2b.core.model.entity.sys.SysOperationLog;
import com.yan2b.core.service.SysOperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Gangbb
 * @ClassName : SysOperationLogServiceImpl
 * @Description :
 * @Date : 2021/3/7 17:42
 */
@Service
public class SysOperationLogServiceImpl implements SysOperationLogService {

    @Autowired
    private SysOperationLogMapper sysOperationLogMapper;

    /**
     * 新增操作日志
     *
     * @param sysOperationLog 操作日志对象
     */
    @Override
    public void insertOperationLog(SysOperationLog sysOperationLog) {
        sysOperationLogMapper.insertOperationLog(sysOperationLog);
    }

    /**
     * 查询所有记录
     */
    @Override
    public List<SysOperationLog> selectAll() {
        return sysOperationLogMapper.selectAll();
    }


}
