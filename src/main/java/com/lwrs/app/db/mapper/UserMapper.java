package com.lwrs.app.db.mapper;

import com.lwrs.app.db.entity.UserDB;
import com.lwrs.app.db.sql.UserSql;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

public interface UserMapper {

    @InsertProvider(type = UserSql.class, method = "insert")
    Long insert(@Param("pojo") UserDB pojo);


    @SelectProvider(type = UserSql.class, method = "selectById")
    UserDB selectById(@Param("id") Long userId);





    @UpdateProvider(type = UserSql.class, method = "updateById")
    int updateById(@Param("pojo") UserDB pojo);

}
