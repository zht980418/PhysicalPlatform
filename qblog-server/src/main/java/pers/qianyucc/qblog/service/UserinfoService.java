package pers.qianyucc.qblog.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.qianyucc.qblog.dao.UserinfoMapper;
import pers.qianyucc.qblog.exception.BlogException;
import pers.qianyucc.qblog.model.dto.UserinfoDTO;
import pers.qianyucc.qblog.model.entity.UserinfoPO;
import pers.qianyucc.qblog.model.enums.UserRoleEnum;
import pers.qianyucc.qblog.model.vo.UserinfoVO;
import pers.qianyucc.qblog.utils.JwtUtils;
import retrofit2.http.QueryMap;

import java.util.*;

import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.INVALID_ID;
import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.USERNAME_PASSWORD_ERROR;

@Slf4j
@Service
public class UserinfoService {
    @Autowired
    private UserinfoMapper userinfoMapper;
    @Transactional(rollbackFor = Exception.class)

//    增加用户
    public void insUserinfo(UserinfoDTO userinfoDTO){
        UserinfoPO userinfoPO = userinfoDTO.toUserinfoPO();
        userinfoMapper.insert(userinfoPO);
    }
//    删除用户
    public void deleteUserinfo(String userid){
        Map<String, Object> map = new HashMap<>();
        map.put("userid", userid);
        userinfoMapper.deleteByMap(map);
    }
//    修改用户
    public void updateUserinfo(UserinfoDTO userinfoDTO, String userid){
        UserinfoPO dbUserinfo = userinfoMapper.selectById(userid);
        if(Objects.isNull(dbUserinfo)){
            throw new BlogException(INVALID_ID);
        }
        UserinfoPO userinfoPO = userinfoDTO.toUserinfoPO();
        userinfoPO.setUserid(userid);
        userinfoMapper.updateById(userinfoPO);
    }
//    查询用户列表
    public List<UserinfoVO> getAlluserinfo(){
        ArrayList res = new ArrayList();
        QueryWrapper<UserinfoPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("userid", "password", "rolename");
        List<Map<String, Object>> maps = userinfoMapper.selectMaps(queryWrapper);
        for(int i = 0; i <maps.size(); i++){
            res.add(maps.get(i));
        }
        return res;
    }
//    查询单个用户
    public UserinfoVO getUserinfo(String userid){
        UserinfoPO dbUserinfo = userinfoMapper.selectById(userid);
        UserinfoVO userinfoVO = UserinfoVO.fromUserinfoPO(dbUserinfo);
        return  userinfoVO;
    }
//    核对用户密码
    public String checkUsernamePassword(UserinfoDTO userinfoDTO, String Userid, String Password){
        if(Objects.equals(Userid, userinfoDTO.getUserid())&&Objects.equals(Password, userinfoDTO.getPassword())){
            return JwtUtils.createToken(Userid, CollUtil.newArrayList(UserRoleEnum.ADMIN.getValue()));
        }
        else {
            throw new BlogException(USERNAME_PASSWORD_ERROR);
        }
    }
}
