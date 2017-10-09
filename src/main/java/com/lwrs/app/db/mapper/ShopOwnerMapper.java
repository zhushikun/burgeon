package com.lwrs.app.db.mapper;

import com.lwrs.app.db.entity.ShopOwnerDB;
import com.lwrs.app.db.sql.ShopOwnerSql;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ShopOwnerMapper {
    @Insert({"<script>",
        ShopOwnerSql.INSERT,
        "</script>"
    })
    @SelectKey(statement="SELECT @@IDENTITY", keyProperty="pojo.id", before=false, resultType=Long.class)
    Long insert(@Param("pojo") ShopOwnerDB pojo);

    @Update(ShopOwnerSql.UPDATE_BY_ID)
    int update(@Param("pojo") ShopOwnerDB pojo);

    @Select(ShopOwnerSql.SELECT_BY_ID)
    ShopOwnerDB selectById(@Param("id") Long id);

    @Select(ShopOwnerSql.SELECT_BY_ALIAS)
    ShopOwnerDB selectByAlias(@Param("alias") String alias);

}
