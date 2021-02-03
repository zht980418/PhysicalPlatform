package pers.qianyucc.qblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.qianyucc.qblog.dao.DoctorMapper;
import pers.qianyucc.qblog.exception.BlogException;
import pers.qianyucc.qblog.model.entity.ArticlePO;
import pers.qianyucc.qblog.model.entity.DoctorPO;
import pers.qianyucc.qblog.model.vo.ArticleVO;
import pers.qianyucc.qblog.model.vo.DoctorVO;
import pers.qianyucc.qblog.model.vo.TimelineVO;

import java.util.*;

import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.INVALID_ID;

@Slf4j
@Service
public class DoctorService {
    @Autowired
    private DoctorMapper doctorMapper;
    @Transactional(rollbackFor = Exception.class)
    public List findByName(String name) {
//        Map<String, Object> columnMap = new HashMap<String, Object>();
//        columnMap.put("name",name);
//        List<DoctorPO> doctorPOList = doctorMapper.selectByMap(wrapper);
//        System.out.println("#############doctorlist"+doctorPOList);
        ArrayList res = new ArrayList<>();
        QueryWrapper<DoctorPO> wrapper = new QueryWrapper<>();
//        查询语句
        wrapper.eq("name",name)
                .select("daysOfWeek","startTime","endTime");
        List<Map<String, Object>> maps = doctorMapper.selectMaps(wrapper);
//        System.out.println("#############doctorlist"+maps);
        for(int i =0; i<maps.size(); i++){
            res.add(maps.get(i));
        }
        return res;
    }
}