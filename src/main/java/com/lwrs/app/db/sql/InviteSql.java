package com.lwrs.app.db.sql;

public class InviteSql {

    private static final String TABLE_NAME = " invite ";

    private static final String COLUMNS =
        " id, master_id, guest_id, type, create_at, update_at ";

    public static final String INSERT =
        "   INSERT INTO  " + TABLE_NAME
        +"<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n"
        +"            <if test=\"pojo.id != null\"> id, </if>\n"
        +"            <if test=\"pojo.masterId != null\"> master_id, </if>\n"
        +"            <if test=\"pojo.guestId != null\"> guest_id, </if>\n"
        +"            <if test=\"pojo.type != null\"> type, </if>\n"
        +"            <if test=\"pojo.createAt != null\"> create_at, </if>\n"
        +"            <if test=\"pojo.updateAt != null\"> update_at, </if>\n"
        +"        </trim>\n"
        +"        VALUES\n"
        +"        <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n"
        +"            <if test=\"pojo.id != null\"> #{pojo.id}, </if>\n"
        +"            <if test=\"pojo.masterId != null\"> #{pojo.masterId}, </if>\n"
        +"            <if test=\"pojo.guestId != null\"> #{pojo.guestId}, </if>\n"
        +"            <if test=\"pojo.type != null\"> #{pojo.type}, </if>\n"
        +"            <if test=\"pojo.createAt != null\"> #{pojo.createAt}, </if>\n"
        +"            <if test=\"pojo.updateAt != null\"> #{pojo.updateAt}, </if>\n"
        +"        </trim>";

    public static final String UPDATE_BY_ID =
        "    UPDATE"+ TABLE_NAME
        + "        <set>"
        + "            <if test=\"pojo.masterId != null\"> master_id = #{pojo.masterId}, </if>"
        + "            <if test=\"pojo.guestId != null\"> guest_id = #{pojo.guestId}, </if>"
        + "        </set>"
        + "    WHERE id = #{pojo.id}";

    public static final String SELECT_BY_ID =
        " SELECT " + COLUMNS
            +"FROM " +  TABLE_NAME
            +"WHERE  id=#{id}";

    public static final String SELECT_BY_MASTER_ID =
        " SELECT " + COLUMNS
            +"FROM " +  TABLE_NAME
            +"WHERE  master_id=#{masterId}";

    public static final String SELECT_BY_GUEST_ID =
        " SELECT " + COLUMNS
            +"FROM " +  TABLE_NAME
            +"WHERE  guest_id=#{guestId}";
}
