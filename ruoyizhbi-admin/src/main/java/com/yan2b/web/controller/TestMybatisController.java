package com.yan2b.web.controller;
import com.yan2b.core.service.SysOperationLogService;
import com.yan2b.web.model.entity.TestMybatis;
import com.yan2b.web.service.TestMybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (TestMybatis)表控制层
 *
 * @author Gangbb
 * @since 2021-03-07 08:39:32
 */
@RestController
@RequestMapping("test/mybatis")
public class TestMybatisController {
    /**
     * 服务对象
     */
    @Resource
    private TestMybatisService testMybatisService;

    @Autowired
    private SysOperationLogService sysOperationLogService;

    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public TestMybatis selectOne() {
        return this.testMybatisService.queryById(1);
    }

    /**
     * 查询所有记录，测试使用mybatis二级缓存
     * @return
     */
    @GetMapping("/all")
    public String testUseCache(){
        long begin = System.currentTimeMillis();
        sysOperationLogService.selectAll();
        long ing = System.currentTimeMillis();
        System.out.println(("请求时间：" + (ing - begin) + "ms"));
        return "测试使用mybatis二级缓存";
    }
}
