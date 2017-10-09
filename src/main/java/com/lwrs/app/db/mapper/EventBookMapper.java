package com.lwrs.app.db.mapper;

import com.lwrs.app.db.entity.EventBookDB;
import com.lwrs.app.db.sql.EventBookSql;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface EventBookMapper {
    @Insert({"<script>",
        EventBookSql.INSERT,
        "</script>"
    })
    @SelectKey(statement="SELECT @@IDENTITY", keyProperty="pojo.id", before=false, resultType=Long.class)
    Long insert(@Param("pojo") EventBookDB pojo);

    @Update(EventBookSql.UPDATE_BY_ID)
    int update(@Param("pojo") EventBookDB pojo);

    @Select(EventBookSql.SELECT_BY_ID)
    EventBookDB selectById(@Param("id") Long id);

    @Select(EventBookSql.SELECT_BY_SHOPID)
    List<EventBookDB> selectByShopId(@Param("shopId") Long shopId);

}
