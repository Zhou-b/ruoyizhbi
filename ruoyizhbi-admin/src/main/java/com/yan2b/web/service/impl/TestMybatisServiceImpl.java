package com.yan2b.web.service.impl;

import com.yan2b.web.model.dao.TestMybatisMapper;
import com.yan2b.web.model.entity.TestMybatis;
import com.yan2b.web.service.TestMybatisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (TestMybatis)表服务实现类
 *
 * @author Gangbb
 * @since 2021-03-07 08:39:32
 */
@Service("testMybatisService")
public class TestMybatisServiceImpl implements TestMybatisService {
    @Resource
    private TestMybatisMapper testMybatisMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TestMybatis queryById(Integer id) {
        return this.testMybatisMapper.queryById(id);
    }

}
