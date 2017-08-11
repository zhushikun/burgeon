package com.lwrs.app.db.sql;

public class UserSql {
    private static final String TABLE_NAME = " user ";
    private static final String COLUMNS =
        " id, alias, pwd, name, gender, phone, birthday, address, create_at, update_at ";

    public String insert(){
        return
            "<script>"
                + "    INSERT INTO" + TABLE_NAME
                + "        <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">"
                + "            <if test=\"pojo.id != null\"> id, </if>"
                + "            <if test=\"pojo.alias != null\"> alias, </if>"
                + "            <if test=\"pojo.pwd != null\"> pwd, </if>"
                + "            <if test=\"pojo.name != null\"> name, </if>"
                + "            <if test=\"pojo.gender != null\"> gender, </if>"
                + "            <if test=\"pojo.phone != null\"> phone, </if>"
                + "            <if test=\"pojo.birthday != null\"> birthday, </if>"
                + "            <if test=\"pojo.address != null\"> address, </if>"
                + "            <if test=\"pojo.createAt != null\"> create_at, </if>"
                + "            <if test=\"pojo.updateAt != null\"> update_at, </if>"
                + "        </trim>"
                + "    VALUES"
                + "        <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">"
                + "            <if test=\"pojo.id != null\"> #{pojo.id}, </if>"
                + "            <if test=\"pojo.alias != null\"> #{pojo.alias}, </if>"
                + "            <if test=\"pojo.pwd != null\"> #{pojo.pwd}, </if>"
                + "            <if test=\"pojo.name != null\"> #{pojo.name}, </if>"
                + "            <if test=\"pojo.gender != null\"> #{pojo.gender}, </if>"
                + "            <if test=\"pojo.phone != null\"> #{pojo.phone}, </if>"
                + "            <if test=\"pojo.birthday != null\"> #{pojo.birthday}, </if>"
                + "            <if test=\"pojo.address != null\"> #{pojo.address}, </if>"
                + "            <if test=\"pojo.createAt != null\"> #{pojo.createAt}, </if>"
                + "            <if test=\"pojo.updateAt != null\"> #{pojo.updateAt}, </if>"
                + "        </trim>"
                + "</script>";
    }


    public String updateById(){
        return
            "<script>"
                + "    UPDATE"+TABLE_NAME
                + "        <set>"
                + "            <if test=\"pojo.id != null\"> id = #{pojo.id}, </if>"
                + "            <if test=\"pojo.alias != null\"> alias = #{pojo.alias}, </if>"
                + "            <if test=\"pojo.pwd != null\"> pwd = #{pojo.pwd}, </if>"
                + "            <if test=\"pojo.name != null\"> name = #{pojo.name}, </if>"
                + "            <if test=\"pojo.gender != null\"> gender = #{pojo.gender}, </if>"
                + "            <if test=\"pojo.phone != null\"> phone = #{pojo.phone}, </if>"
                + "            <if test=\"pojo.birthday != null\"> name = #{pojo.birthday}, </if>"
                + "            <if test=\"pojo.address != null\"> address = #{pojo.address}, </if>"
                + "            <if test=\"pojo.createAt != null\"> create_at = #{pojo.createAt}, </if>"
                + "            <if test=\"pojo.updateAt != null\"> update_at = #{pojo.updateAt} </if>"
                + "        </set>"
                + "    WHERE id = #{pojo.id}"
                + "</script>";

    }


    public String selectById() {
        return "SELECT " + COLUMNS + " from" + TABLE_NAME
//            + " WHERE id = #{pojo.id}"
            ;
    }

}
