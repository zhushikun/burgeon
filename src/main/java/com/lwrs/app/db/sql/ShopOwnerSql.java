package com.lwrs.app.db.sql;

public class ShopOwnerSql {
    private static final String TABLE_NAME = " shop_owner ";

    private static final String COLUMNS =
        " id, user_id, alias, code, shop_ids, phone, create_at, update_at ";

    public static final String INSERT =
        "   INSERT INTO  " + TABLE_NAME
        +"       <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">"
        + "            <if test=\"pojo.id != null\"> id, </if>"
        + "            <if test=\"pojo.userId != null\"> user_id, </if>"
        + "            <if test=\"pojo.alias != null\"> alias, </if>"
        + "            <if test=\"pojo.code != null\"> code, </if>"
        + "            <if test=\"pojo.shopIds != null\"> shop_ids, </if>"
        + "            <if test=\"pojo.phone != null\"> phone, </if>"
        + "            <if test=\"pojo.createAt != null\"> create_at, </if>"
        + "            <if test=\"pojo.updateAt != null\"> update_at, </if>"
        + "        </trim>"
        + "    VALUES"
        + "        <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">"
        + "            <if test=\"pojo.id != null\"> #{pojo.id}, </if>"
        + "            <if test=\"pojo.userId != null\"> #{pojo.userId}, </if>"
        + "            <if test=\"pojo.alias != null\"> #{pojo.alias}, </if>"
        + "            <if test=\"pojo.code != null\"> #{pojo.code}, </if>"
        + "            <if test=\"pojo.shopIds != null\"> #{pojo.shopIds}, </if>"
        + "            <if test=\"pojo.phone != null\"> #{pojo.phone}, </if>"
        + "            <if test=\"pojo.createAt != null\"> #{pojo.createAt}, </if>"
        + "            <if test=\"pojo.updateAt != null\"> #{pojo.updateAt}, </if>"
        + "        </trim>"
        ;

    public static final String UPDATE_BY_ID =
        "    UPDATE"+ TABLE_NAME
        + "        <set>"
        + "            <if test=\"pojo.id != null\"> id = #{pojo.id}, </if>"
        + "            <if test=\"pojo.userId != null\"> user_id = #{pojo.userId}, </if>"
        + "            <if test=\"pojo.alias != null\"> alias = #{pojo.alias}, </if>"
        + "            <if test=\"pojo.code != null\"> code = #{pojo.code}, </if>"
        + "            <if test=\"pojo.shopIds != null\"> shop_ids = #{pojo.shopIds}, </if>"
        + "            <if test=\"pojo.phone != null\"> phone = #{pojo.phone}, </if>"
        + "            <if test=\"pojo.createAt != null\"> create_at = #{pojo.createAt}, </if>"
        + "            <if test=\"pojo.updateAt != null\"> update_at = #{pojo.updateAt} </if>"
        + "        </set>"
        + "    WHERE id = #{pojo.id}";


    public static final String SELECT_BY_ID =
        " SELECT " + COLUMNS
            +"FROM " +  TABLE_NAME
            +"WHERE  id=#{id}";

    public static final String SELECT_BY_ALIAS =
        " SELECT " + COLUMNS
            +"FROM " +  TABLE_NAME
            +"WHERE  alias=#{alias}";
}
