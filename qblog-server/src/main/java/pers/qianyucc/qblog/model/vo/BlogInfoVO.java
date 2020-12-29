package pers.qianyucc.qblog.model.vo;

import cn.hutool.core.bean.*;
import lombok.*;
import pers.qianyucc.qblog.model.comm.*;

import java.util.*;

@Data
public class BlogInfoVO {
    private String title;
    private String desc;
    private List<String> covers;
    private String avatar;
    private String nickname;
    private int articleCount;
    private int totalViews;

    public static BlogInfoVO fromBlogSetting(BlogSetting blogSetting) {
        return new BlogInfoVO.Converter().convertToVO(blogSetting);
    }

    private static class Converter implements IConverter<BlogSetting, BlogInfoVO> {
        @Override
        public BlogInfoVO convertToVO(BlogSetting blogSetting) {
            BlogInfoVO vo = new BlogInfoVO();
            BeanUtil.copyProperties(blogSetting, vo);
            return vo;
        }
    }
}
