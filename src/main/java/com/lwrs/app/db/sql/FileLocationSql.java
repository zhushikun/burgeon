package com.lwrs.app.db.sql;

public class FileLocationSql {
    private static final String TABLE_NAME = " file_location ";

    private static final String COLUMNS =
        " id, user_id, type, location, create_at, update_at ";

    public static final String INSERT =
        "   INSERT INTO  " + TABLE_NAME
        +"       <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">"
        +"            <if test=\"pojo.id != null\"> id, </if>"
        +"            <if test=\"pojo.userId != null\"> user_id, </if>"
        +"            <if test=\"pojo.type != null\"> type, </if>"
        +"            <if test=\"pojo.location != null\"> location, </if>"
        +"            <if test=\"pojo.createAt != null\"> create_at, </if>"
        +"            <if test=\"pojo.updateAt != null\"> update_at, </if>"
        +"        </trim>"
        +"  VALUES"
        +"        <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">"
        +"            <if test=\"pojo.id != null\"> #{pojo.id}, </if>"
        +"            <if test=\"pojo.userId != null\"> #{pojo.userId}, </if>"
        +"            <if test=\"pojo.type != null\"> #{pojo.type}, </if>"
        +"            <if test=\"pojo.location != null\"> #{pojo.location}, </if>"
        +"            <if test=\"pojo.createAt != null\"> #{pojo.createAt}, </if>"
        +"            <if test=\"pojo.updateAt != null\"> #{pojo.updateAt}, </if>"
        +"        </trim>"
        ;

    public static final String SELECT_BY_ID =
        " SELECT " + COLUMNS
        +"FROM " +  TABLE_NAME
        +"WHERE  id=#{id}";
}
