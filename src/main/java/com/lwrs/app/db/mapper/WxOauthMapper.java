package com.lwrs.app.db.mapper;

import com.lwrs.app.db.entity.WxOauthDB;
import com.lwrs.app.db.sql.WxOauthSql;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface WxOauthMapper {

    @Insert({"<script>",
        WxOauthSql.INSERT,
        "</script>"
    })
    @SelectKey(statement="SELECT @@IDENTITY", keyProperty="pojo.id", before=false, resultType=Long.class)
    Long insert(@Param("pojo") WxOauthDB pojo);

    @Update(WxOauthSql.UPDATE_BY_ID)
    int updateById(@Param("pojo") WxOauthDB pojo);

    @Select(WxOauthSql.SELECT_BY_ID)
    WxOauthDB selectById(@Param("id") Long id);


    @Select(WxOauthSql.SELECT_BY_USERID)
    WxOauthDB selectByUserId(@Param("userId") Long userId);


    @Select(WxOauthSql.SELECT_BY_OPENID)
    WxOauthDB selectByOpenId(@Param("openId") String openId);

}
