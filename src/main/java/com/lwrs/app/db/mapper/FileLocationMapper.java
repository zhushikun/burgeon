package com.lwrs.app.db.mapper;

import com.lwrs.app.db.entity.FileLocationDB;
import com.lwrs.app.db.entity.UserDB;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface FileLocationMapper {
//    @Insert({"<script>",
//        "//todo"
//    })
    Long insert(@Param("pojo") FileLocationDB pojo);

    FileLocationDB selectById(@Param("id") Long id);
}
