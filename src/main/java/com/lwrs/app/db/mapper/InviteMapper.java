package com.lwrs.app.db.mapper;

import com.lwrs.app.db.entity.InviteDB;
import com.lwrs.app.db.sql.InviteSql;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface InviteMapper {

    @Insert({"<script>",
        InviteSql.INSERT,
        "</script>"
    })
    @SelectKey(statement="SELECT @@IDENTITY", keyProperty="pojo.id", before=false, resultType=Long.class)
    Long insert(@Param("pojo") InviteDB pojo);

    @Update(InviteSql.UPDATE_BY_ID)
    int updateById(@Param("pojo") InviteDB pojo);


    @Select(InviteSql.SELECT_BY_ID)
    InviteDB selectById(@Param("id") Long id);

    @Select(InviteSql.SELECT_BY_MASTER_ID)
    List<InviteDB> selectByMasterId(@Param("materId") Long materId);

    @Select(InviteSql.SELECT_BY_GUEST_ID)
    List<InviteDB> selectByGuestId(@Param("guestId") Long guestId);

}
