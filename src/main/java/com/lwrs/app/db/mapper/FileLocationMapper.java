package com.lwrs.app.db.mapper;

import com.lwrs.app.db.entity.FileLocationDB;
import com.lwrs.app.db.entity.UserDB;
import com.lwrs.app.db.sql.FileLocationSql;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

public interface FileLocationMapper {
    @Insert({"<script>",
        FileLocationSql.INSERT,
        "</script>"
    })
    @SelectKey(statement="SELECT @@IDENTITY", keyProperty="pojo.id", before=false, resultType=Long.class)
    Long insert(@Param("pojo") FileLocationDB pojo);

    @Select(FileLocationSql.SELECT_BY_ID)
    FileLocationDB selectById(@Param("id") Long id);
}
