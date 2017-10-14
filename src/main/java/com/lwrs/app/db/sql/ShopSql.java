package com.lwrs.app.db.sql;

public class ShopSql {
    private static final String TABLE_NAME = " shop ";

    private static final String COLUMNS =
        " id, shop_name, group_id, address, phone, create_at, update_at ";

    public static final String INSERT =
        "   INSERT INTO  " + TABLE_NAME
        +"       <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">"
        + "            <if test=\"pojo.id != null\"> id, </if>"
        + "            <if test=\"pojo.shopName != null\"> shop_name, </if>"
        + "            <if test=\"pojo.groupId != null\"> group_id, </if>"
        + "            <if test=\"pojo.address != null\"> address, </if>"
        + "            <if test=\"pojo.phone != null\"> phone, </if>"
        + "            <if test=\"pojo.createAt != null\"> create_at, </if>"
        + "            <if test=\"pojo.updateAt != null\"> update_at, </if>"
        + "        </trim>"
        + "    VALUES"
        + "        <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">"
        + "            <if test=\"pojo.id != null\"> #{pojo.id}, </if>"
        + "            <if test=\"pojo.shopName != null\"> #{pojo.shopName}, </if>"
        + "            <if test=\"pojo.groupId != null\"> #{pojo.groupId}, </if>"
        + "            <if test=\"pojo.address != null\"> #{pojo.address}, </if>"
        + "            <if test=\"pojo.phone != null\"> #{pojo.phone}, </if>"
        + "            <if test=\"pojo.createAt != null\"> #{pojo.createAt}, </if>"
        + "            <if test=\"pojo.updateAt != null\"> #{pojo.updateAt}, </if>"
        + "        </trim>"
        ;

    public static final String UPDATE_BY_ID =
        "    UPDATE"+ TABLE_NAME
        + "        <set>"
        + "            <if test=\"pojo.id != null\"> id = #{pojo.id}, </if>"
        + "            <if test=\"pojo.shopName != null\"> shop_name = #{pojo.shopName}, </if>"
        + "            <if test=\"pojo.groupId != null\"> group_id = #{pojo.groupId}, </if>"
        + "            <if test=\"pojo.address != null\"> address = #{pojo.address}, </if>"
        + "            <if test=\"pojo.phone != null\"> phone = #{pojo.phone}, </if>"
        + "            <if test=\"pojo.createAt != null\"> create_at = #{pojo.createAt}, </if>"
        + "            <if test=\"pojo.updateAt != null\"> update_at = #{pojo.updateAt} </if>"
        + "        </set>"
        + "    WHERE id = #{pojo.id}";


    public static final String SELECT_BY_ID =
        " SELECT " + COLUMNS
            +"FROM " +  TABLE_NAME
            +"WHERE  id=#{id}";

    public static final String SELECT_BY_IDS =
        " SELECT " + COLUMNS
            +"FROM " +  TABLE_NAME
            +"WHERE  id in"
            + " <foreach collection=\"ids\" item=\"id\" open=\"(\" separator=\",\" close=\")\">"
            + "     #{id}"
            + " </foreach>";
}
