package com.yan2b.web.model.mapper;

import com.yan2b.web.model.entity.TestMybatis;
import org.apache.ibatis.annotations.Mapper;

/**
 * (TestMybatis)表数据库访问层
 *
 * @author Gangbb
 * @since 2021-03-07 08:39:31
 */
@Mapper
public interface TestMybatisMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TestMybatis queryById(Integer id);

}
