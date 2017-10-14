package com.lwrs.app.db.mapper;

import com.lwrs.app.db.entity.ShopDB;
import com.lwrs.app.db.sql.ShopSql;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ShopMapper {
    @Insert({"<script>",
        ShopSql.INSERT,
        "</script>"
    })
    @SelectKey(statement="SELECT @@IDENTITY", keyProperty="pojo.id", before=false, resultType=Long.class)
    Long insert(@Param("pojo") ShopDB pojo);

    @Select(ShopSql.SELECT_BY_ID)
    ShopDB selectById(@Param("id") Long id);


    @Select({"<script>",
        ShopSql.SELECT_BY_IDS,
        "</script>"
    })
    List<ShopDB> selectByIds(@Param("ids") List<Long> ids);

    @Update(ShopSql.UPDATE_BY_ID)
    int update(@Param("pojo") ShopDB pojo);

}
