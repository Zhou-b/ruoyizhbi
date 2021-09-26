package com.yan2b.core.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.alibaba.druid.util.JdbcConstants;
import com.yan2b.core.aspectj.LogAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
　 * @Description: 连接池配置--这里使用的是单数据源
　 * @author zixiong
　 * @date 2020-12-30 02:16:23
*/
@Configuration
public class DruidDataSourceConfig {
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

	@Value("${spring.datasource.driver-class-name}")
    String driver;

    @Value("${spring.datasource.url}")
    String url;

    @Value("${spring.datasource.username}")
    String username;

    @Value("${spring.datasource.password}")
    String password;

    @Value("${spring.datasource.initialSize}")
    int initialSize;

    @Value("${spring.datasource.minIdle}")
    int minIdle;

    @Value("${spring.datasource.maxActive}")
    int maxActive;

    @Value("${spring.datasource.maxWait}")
    long maxWait;

    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    long timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    long minEvictableIdleTimeMillis;

    @Value("${spring.datasource.filters}")
    String filters;

    @Value("${spring.datasource.druid.username}")
    String druidUserName;

    @Value("${spring.datasource.druid.password}")
    String druidPassword;

    @Value("${spring.datasource.druid.login.enabled}")
    boolean druidLoginEnable;

    @Autowired
    private StatFilter logSlowSql;

    @Autowired
    private DruidStatInterceptor druidStatInterceptor;

    @Bean // 声明其为Bean实例
    @Primary // 在同样的DataSource中，首先使用被标注的DataSource
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDbType(JdbcConstants.MYSQL);
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setMaxWait(maxWait);
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        try {
            druidDataSource.setFilters(filters);
            ArrayList<Filter> arrayList = new ArrayList<>();
            arrayList.add(logSlowSql);
            druidDataSource.setProxyFilters(arrayList);
            druidDataSource.init();
        } catch (SQLException e) {
            log.error("初始化数据源出错", e);
        }

        return druidDataSource;
    }

    /**
     *  注册一个StatViewServlet
     *  Druid 提供了一个 StatViewServlet 用于展示 Druid 的统计信息
     *  这个 StatViewServlet 的用途包括：
     *   1. 提供监控信息展示的 HTML 页面
     *   2. 提供监控信息的 JSON API
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        // 添加映射路径:通过druid/index.html访问
        servletRegistrationBean.addUrlMappings("/druid/*");
        Map<String, String> initParameters = new HashMap<String, String>();
        //不设置用户名密码可以直接通过druid/index.html访问
        if (druidLoginEnable) {
            //登录查看信息的账号密码
            initParameters.put("loginUsername", druidUserName);
            initParameters.put("loginPassword", druidPassword);
        }
        initParameters.put("resetEnable", "false");
        //是否能够重置数据.
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }

    /**
     * 注册一个：filterRegistrationBean
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    @Bean
    public StatFilter logSlowSql() {
        StatFilter statFilter = new StatFilter();
        //合并SQL，有时，一些相同的慢日志过多影响阅读，开启合并功能
        statFilter.setMergeSql(true);
        // SQL执行时间超过1s种的被判定为慢日志
        statFilter.setSlowSqlMillis(1000);
        //显示慢日志
        statFilter.setLogSlowSql(true);
        return statFilter;
    }

    @Bean(name = "druid-stat-interceptor")
    public DruidStatInterceptor druidStatInterceptor() {
        DruidStatInterceptor dsInterceptor = new DruidStatInterceptor();
        return dsInterceptor;
    }

    @Bean
    public JdkRegexpMethodPointcut jdkRegexpMethodPointcut() {
        JdkRegexpMethodPointcut jdkRegexpMethodPointcut = new JdkRegexpMethodPointcut();
        // 正则拦截配置
//        jdkRegexpMethodPointcut.setPatterns("com.road.platform.service..*Service.*");
        jdkRegexpMethodPointcut.setPatterns("com.yan2b.web.service..*Service.*");
        return jdkRegexpMethodPointcut;
    }

    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor() {
        DefaultPointcutAdvisor pointcutAdvisor = new DefaultPointcutAdvisor();
        pointcutAdvisor.setPointcut(jdkRegexpMethodPointcut());
        pointcutAdvisor.setAdvice(druidStatInterceptor);
        return pointcutAdvisor;
    }
}
