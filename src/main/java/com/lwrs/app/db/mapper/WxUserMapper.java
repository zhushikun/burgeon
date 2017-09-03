package com.lwrs.app.db.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.lwrs.app.db.entity.WxUserDB;

public interface WxUserMapper {

    Long insert(@Param("pojo") WxUserDB pojo);

    List<WxUserDB> select(@Param("pojo") WxUserDB pojo);

    int update(@Param("pojo") WxUserDB pojo);

}
