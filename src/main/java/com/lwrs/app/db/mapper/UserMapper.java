package com.lwrs.app.db.mapper;

import com.lwrs.app.db.entity.User;
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
    int insert(@Param("pojo") User pojo);

//    int insertList(@Param("pojo") List<UserAccount> pojo);

    @Results(id = "userAccountResult", value = {
        @Result(column="id",            property="id"),
        @Result(column="alias",         property="alias"),
        @Result(column="pwd",           property="pwd"),
        @Result(column="name",          property="name"),
        @Result(column="gender",        property="gender"),
        @Result(column="phone",         property="phone"),
        @Result(column="birthday",          property="birthday"),
        @Result(column="address",    property="address"),
        @Result(column="create_at",     property="createAt"),
        @Result(column="update_at",     property="updateAt"),
    })
    @SelectProvider(type = UserSql.class, method = "selectById")
    List<User> selectById(@Param("pojo") User pojo);





    @UpdateProvider(type = UserSql.class, method = "updateById")
    int updateById(@Param("pojo") User pojo);

}
