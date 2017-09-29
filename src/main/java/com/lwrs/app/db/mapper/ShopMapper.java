package com.lwrs.app.db.mapper;

import com.lwrs.app.db.entity.ShopDB;
import com.lwrs.app.db.sql.EventBookSql;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ShopMapper {
    @Insert({"<script>",
        EventBookSql.INSERT,
        "</script>"
    })
    @Options(useGeneratedKeys = true)
    Long insert(@Param("pojo") ShopDB pojo);

    @Select(EventBookSql.SELECT_BY_ID)
    List<ShopDB> select(@Param("id") Long id);

    @Update(EventBookSql.UPDATE_BY_ID)
    int update(@Param("pojo") ShopDB pojo);

}
