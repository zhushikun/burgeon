package com.lwrs.app.db.mapper;

import com.lwrs.app.db.entity.TempUserLinkDB;
import com.lwrs.app.db.sql.TempUserLinkSql;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface TempUserLinkMapper {

    @Insert({"<script>",
        TempUserLinkSql.INSERT,
        "</script>"
    })
    @SelectKey(statement="SELECT @@IDENTITY", keyProperty="pojo.id", before=false, resultType=Long.class)
    Long insert(@Param("pojo") TempUserLinkDB pojo);



    @Update(TempUserLinkSql.UPDATE_BY_ID)
    int updateById(@Param("pojo") TempUserLinkDB pojo);

    @Select(TempUserLinkSql.SELECT_BY_ID)
    TempUserLinkDB selectById(@Param("id") Long id);

    @Select(TempUserLinkSql.SELECT_BY_TEMPUID)
    TempUserLinkDB selectByTempUid(@Param("tempUid") String tempUid);

}
