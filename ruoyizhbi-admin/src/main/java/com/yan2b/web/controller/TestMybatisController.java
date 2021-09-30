package com.yan2b.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yan2b.core.base.BaseController;
import com.yan2b.core.model.entity.sys.SysOperationLog;
import com.yan2b.core.model.page.TableDataInfo;
import com.yan2b.core.service.SysOperationLogService;
import com.yan2b.web.mapstruct.SysOperatorLogTranslateMapper;
import com.yan2b.web.model.entity.TestMybatis;
import com.yan2b.web.model.vo.SysOperationLogVO;
import com.yan2b.web.service.TestMybatisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import java.util.List;

/**
 * (TestMybatis)表控制层
 *
 * @author Gangbb
 * @since 2021-03-07 08:39:32
 */
@RestController
@RequiredArgsConstructor    // 写在类上可以代替@AutoWired注解，需要注意的是在注入时需要用final定义
@RequestMapping("test/mybatis")
public class TestMybatisController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private TestMybatisService testMybatisService;
    @Autowired
    private SysOperationLogService sysOperationLogService;
    /**
     * 类型转换用
     */
    private final SysOperatorLogTranslateMapper sysOperatorLogMapper;
//    @Autowired
//    private SysOperatorLogTranslateMapper sysOperatorLogMapper;

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
     *
     * @return
     */
    @GetMapping("/all")
    public String testUseCache() {
        long begin = System.currentTimeMillis();
        sysOperationLogService.selectAll();
        long ing = System.currentTimeMillis();
        System.out.println(("请求时间：" + (ing - begin) + "ms"));
        return "测试使用mybatis二级缓存";
    }

    /**
     * 查询列表，分页。 返回若依封装的 TableDataInfo
     *
     * @return
     */
    @GetMapping("/page")
    public TableDataInfo testPageHelper() {
        // 开启分页
        startPage();
        List<SysOperationLog> sysOperationLogs = sysOperationLogService.selectAll();
        return getDataTable(sysOperationLogs);
    }

    /**
     * 查询列表，分页。返回pageHelper内置 PageInfo 对象
     *
     * @return
     */
    @GetMapping("/page2")
    public PageInfo testPageHelper2() {
        startPage();
        List<SysOperationLog> sysOperationLogs = sysOperationLogService.selectAll();
        PageInfo pageInfo = new PageInfo(sysOperationLogs);
        return pageInfo;
    }

    /**
     * 查询列表，分页。返回pageHelper内置 PageInfo 对象  加入参数校验
     *
     * @return
     */
    @GetMapping("/page3")
    public PageInfo testPageHelper3(@Max(9999) @RequestParam Integer pageNum, @Max(9999) @RequestParam Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysOperationLog> sysOperationLogs = sysOperationLogService.selectAll();
        PageInfo pageInfo = new PageInfo(sysOperationLogs);
        return pageInfo;
    }


    /**
     *  测试MapStruct实现类型转换
     * @return
     */
    @GetMapping("/mapStruct")
    public void testMapStruct() {
//        SysOperatorLogTranslateMapper sysOperatorLogMapper = Mappers.getMapper(SysOperatorLogTranslateMapper.class);
        List<SysOperationLog> sysOperationLogs = sysOperationLogService.selectAll();
        System.out.println(sysOperationLogs);
        List<SysOperationLogVO> sysOperationLogVO = sysOperatorLogMapper.toVo(sysOperationLogs);
        System.out.println(sysOperationLogVO);
    }
}
