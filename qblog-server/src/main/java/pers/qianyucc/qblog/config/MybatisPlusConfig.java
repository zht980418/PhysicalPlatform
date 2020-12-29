package pers.qianyucc.qblog.config;

import com.baomidou.mybatisplus.extension.plugins.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.*;
import org.mybatis.spring.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.*;

@EnableTransactionManagement
@Configuration
@MapperScan("pers.qianyucc.qblog.dao")
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        paginationInterceptor.setLimit(10);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }
}