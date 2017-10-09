package com.lwrs.app.db.mapper;

import com.lwrs.app.db.entity.UserDB;
import com.lwrs.app.db.entity.WxUserDB;
import com.lwrs.app.db.sql.UserSql;
import com.lwrs.app.db.sql.WxUserSql;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {

    @Insert({"<script>",
        UserSql.INSERT,
        "</script>"
    })
    @SelectKey(statement="SELECT @@IDENTITY", keyProperty="pojo.id", before=false, resultType=Long.class)
    Long insert(@Param("pojo") UserDB pojo);



    @Update(UserSql.UPDATE_BY_ID)
    int updateById(@Param("pojo") UserDB pojo);

    @Select(UserSql.SELECT_BY_ID)
    UserDB selectById(@Param("id") Long userId);

}
