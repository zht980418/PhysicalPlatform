package pers.qianyucc.qblog.service;

import cn.hutool.core.util.*;
import lombok.extern.slf4j.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.junit4.*;
import pers.qianyucc.qblog.*;
import pers.qianyucc.qblog.dao.*;
import pers.qianyucc.qblog.model.entity.*;
import pers.qianyucc.qblog.model.vo.*;

import java.time.*;
import java.util.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BlogApplication.class})
public class ArticleServiceTest {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleService articleService;

    @Test
    public void insArticle() {
        ArticlePO article = articleMapper.selectById("5f008d6c4acfc62d3e96d00a");
        for (int i = 0; i < 20; i++) {
            article.setId(IdUtil.objectId());
            article.setTitle("测试数据" + i);
            articleMapper.insert(article);
        }
    }

    @Test
    public void solveTime() {
        List<ArticlePO> articles = articleMapper.selectList(null);
        for (int i = 0; i < articles.size(); i++) {
            ArticlePO po = articles.get(i);
            LocalDateTime dateTime = LocalDateTime.now().plusYears(RandomUtil.randomInt(-5, 5));
            long epochMilli = dateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
            po.setGmtCreate(epochMilli);
            po.setGmtUpdate(epochMilli);
            articleMapper.updateById(po);
        }
    }

    @Test
    public void timeline() {
        articleService.timeline();
    }

    @Test
    public void testCountArticlesByMonth() {
        StatisticsVO statisticsVO = articleService.countArticlesByMonth();
        System.out.println(statisticsVO);
    }
}