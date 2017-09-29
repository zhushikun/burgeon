package com.lwrs.app.db.mapper;

import com.lwrs.app.db.entity.EventBookDB;
import com.lwrs.app.db.sql.EventBookSql;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface EventBookMapper {
    @Insert({"<script>",
        EventBookSql.INSERT,
        "</script>"
    })
    @Options(useGeneratedKeys = true)
    Long insert(@Param("pojo") EventBookDB pojo);

    @Select(EventBookSql.SELECT_BY_ID)
    List<EventBookDB> select(@Param("id") String id);

    @Update(EventBookSql.UPDATE_BY_ID)
    int update(@Param("pojo") EventBookDB pojo);

}
