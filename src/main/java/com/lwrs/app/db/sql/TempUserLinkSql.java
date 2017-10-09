package com.lwrs.app.db.sql;

public class TempUserLinkSql {
    private static final String TABLE_NAME = " temp_user_link ";
    private static final String COLUMNS =
        " id, user_id, temp_uid, create_at, update_at ";

    public static final String INSERT =
        "    INSERT INTO" + TABLE_NAME
            + "        <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n"
            + "            <if test=\"pojo.id != null\"> id, </if>\n"
            + "            <if test=\"pojo.userId != null\"> user_id, </if>\n"
            + "            <if test=\"pojo.tempUid != null\"> temp_uid, </if>\n"
            + "            <if test=\"pojo.createAt != null\"> create_at, </if>\n"
            + "            <if test=\"pojo.updateAt != null\"> update_at, </if>\n"
            + "        </trim>\n"
            + "    VALUES\n"
            + "        <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n"
            + "            <if test=\"pojo.id != null\"> #{pojo.id}, </if>\n"
            + "            <if test=\"pojo.userId != null\"> #{pojo.userId}, </if>\n"
            + "            <if test=\"pojo.tempUid != null\"> #{pojo.tempUid}, </if>\n"
            + "            <if test=\"pojo.createAt != null\"> #{pojo.createAt}, </if>\n"
            + "            <if test=\"pojo.updateAt != null\"> #{pojo.updateAt}, </if>\n"
            + "        </trim>";


    public static final String  UPDATE_BY_ID =
        "    UPDATE"+TABLE_NAME
            + "        <set>\n"
            + "            <if test=\"pojo.id != null\"> id = #{pojo.id}, </if>\n"
            + "            <if test=\"pojo.userId != null\"> user_id = #{pojo.userId}, </if>\n"
            + "            <if test=\"pojo.tempUid != null\"> temp_uid = #{pojo.tempUid}, </if>\n"
            + "            <if test=\"pojo.createAt != null\"> create_at = #{pojo.createAt}, </if>\n"
            + "            <if test=\"pojo.updateAt != null\"> update_at = #{pojo.updateAt} </if>\n"
            + "        </set>"
            + "    WHERE id = #{pojo.id}";


    public static final String SELECT_BY_ID =
        "SELECT " + COLUMNS
            + " from" + TABLE_NAME
            + " WHERE id = #{id}";

    public static final String SELECT_BY_TEMPUID =
        " SELECT " + COLUMNS
            +"FROM " +  TABLE_NAME
            +"WHERE  temp_uid=#{tempUid}";

}
