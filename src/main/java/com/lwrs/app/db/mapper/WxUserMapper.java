package com.lwrs.app.db.mapper;

import com.lwrs.app.db.entity.WxUserDB;
import com.lwrs.app.db.sql.WxUserSql;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface WxUserMapper {

    @Insert({"<script>",
        WxUserSql.INSERT,
        "</script>"
    })
    @Options(useGeneratedKeys = true)
    Long insert(@Param("pojo") WxUserDB pojo);

    @Update(WxUserSql.UPDATE_BY_ID)
    int updateById(@Param("pojo") WxUserDB pojo);

    @Select(WxUserSql.SELECT_BY_ID)
    WxUserDB selectById(@Param("id") Long id);


    @Select(WxUserSql.SELECT_BY_USERID)
    WxUserDB selectByUserId(@Param("userId") Long userId);


    @Select(WxUserSql.SELECT_BY_OPENID)
    WxUserDB selectByOpenId(@Param("openId") String openId);


}
