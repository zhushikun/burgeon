package com.lwrs.app.db.mapper;

import com.lwrs.app.db.entity.FileLocationDB;
import com.lwrs.app.db.entity.UserDB;
import com.lwrs.app.db.sql.FileLocationSql;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface FileLocationMapper {
    @Insert({"<script>",
        FileLocationSql.INSERT,
        "</script>"
    })
    @Options(useGeneratedKeys = true)
    Long insert(@Param("pojo") FileLocationDB pojo);

    @Select(FileLocationSql.SELECT_BY_ID)
    FileLocationDB selectById(@Param("id") Long id);
}
