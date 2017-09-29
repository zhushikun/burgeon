package com.lwrs.app.db.sql;

public class EventBookSql {
    private static final String TABLE_NAME = " event_book ";

    private static final String COLUMNS =
        " id, shop_id, user_id, user_name, gender, phone, birth_date, book_type, book_date, create_at, update_at ";

    public static final String INSERT =
        "   INSERT INTO  " + TABLE_NAME
        +"       <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">"
        + "            <if test=\"pojo.id != null\"> id, </if>"
        + "            <if test=\"pojo.shopId != null\"> shop_id, </if>"
        + "            <if test=\"pojo.userId != null\"> user_id, </if>"
        + "            <if test=\"pojo.userName != null\"> user_name, </if>"
        + "            <if test=\"pojo.gender != null\"> gender, </if>"
        + "            <if test=\"pojo.phone != null\"> phone, </if>"
        + "            <if test=\"pojo.birthDate != null\"> birth_date, </if>"
        + "            <if test=\"pojo.bookType != null\"> book_type, </if>"
        + "            <if test=\"pojo.bookDate != null\"> book_date, </if>"
        + "            <if test=\"pojo.createAt != null\"> create_at, </if>"
        + "            <if test=\"pojo.updateAt != null\"> update_at, </if>"
        + "        </trim>"
        + "   VALUES"
        + "        <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">"
        + "            <if test=\"pojo.id != null\"> #{pojo.id}, </if>"
        + "            <if test=\"pojo.shopId != null\"> #{pojo.shopId}, </if>"
        + "            <if test=\"pojo.userId != null\"> #{pojo.userId}, </if>"
        + "            <if test=\"pojo.userName != null\"> #{pojo.userName}, </if>"
        + "            <if test=\"pojo.gender != null\"> #{pojo.gender}, </if>"
        + "            <if test=\"pojo.phone != null\"> #{pojo.phone}, </if>"
        + "            <if test=\"pojo.birthDate != null\"> #{pojo.birthDate}, </if>"
        + "            <if test=\"pojo.bookType != null\"> #{pojo.bookType}, </if>"
        + "            <if test=\"pojo.bookDate != null\"> #{pojo.bookDate}, </if>"
        + "            <if test=\"pojo.createAt != null\"> #{pojo.createAt}, </if>"
        + "            <if test=\"pojo.updateAt != null\"> #{pojo.updateAt}, </if>"
        + "        </trim>"
        ;

    public static final String UPDATE_BY_ID =
        "    UPDATE"+ TABLE_NAME
        + "        <set>"
        + "            <if test=\"pojo.shopId != null\"> shop_id = #{pojo.shopId}, </if>"
        + "            <if test=\"pojo.userId != null\"> user_id = #{pojo.userId}, </if>"
        + "            <if test=\"pojo.userName != null\"> user_name = #{pojo.userName}, </if>"
        + "            <if test=\"pojo.gender != null\"> gender = #{pojo.gender}, </if>"
        + "            <if test=\"pojo.phone != null\"> phone = #{pojo.phone}, </if>"
        + "            <if test=\"pojo.birthDate != null\"> birth_date = #{pojo.birthDate}, </if>"
        + "            <if test=\"pojo.bookType != null\"> book_type = #{pojo.bookType}, </if>"
        + "            <if test=\"pojo.bookDate != null\"> book_date = #{pojo.bookDate}, </if>"
        + "            <if test=\"pojo.createAt != null\"> create_at = #{pojo.createAt}, </if>"
        + "            <if test=\"pojo.updateAt != null\"> update_at = #{pojo.updateAt} </if>"
        + "        </set>"
        + "    WHERE id = #{pojo.id}";


    public static final String SELECT_BY_ID =
        " SELECT " + COLUMNS
            +"FROM " +  TABLE_NAME
            +"WHERE  id=#{id}";
}
