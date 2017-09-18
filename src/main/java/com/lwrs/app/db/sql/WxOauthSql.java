package com.lwrs.app.db.sql;

public class WxOauthSql {
    private static final String TABLE_NAME = " wx_oauth ";
    private static final String COLUMNS =
        " id, user_id, open_id, scope_type, access_token, access_expire, refresh_token, refresh_expire, scope, create_at, update_at ";

    public static final String INSERT =
        "    INSERT INTO" + TABLE_NAME
            + "        <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n"
            + "            <if test=\"pojo.id != null\"> id, </if>\n"
            + "            <if test=\"pojo.userId != null\"> user_id, </if>\n"
            + "            <if test=\"pojo.openId != null\"> open_id, </if>\n"
            + "            <if test=\"pojo.scopeType != null\"> scope_type, </if>\n"
            + "            <if test=\"pojo.accessToken != null\"> access_token, </if>\n"
            + "            <if test=\"pojo.accessExpire != null\"> access_expire, </if>\n"
            + "            <if test=\"pojo.refreshToken != null\"> refresh_token, </if>\n"
            + "            <if test=\"pojo.refreshExpire != null\"> refresh_expire, </if>\n"
            + "            <if test=\"pojo.scope != null\"> scope, </if>\n"
            + "            <if test=\"pojo.createAt != null\"> create_at, </if>\n"
            + "            <if test=\"pojo.updateAt != null\"> update_at, </if>\n"
            + "        </trim>\n"
            + "    VALUES\n"
            + "        <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n"
            + "            <if test=\"pojo.id != null\"> #{pojo.id}, </if>\n"
            + "            <if test=\"pojo.userId != null\"> #{pojo.userId}, </if>\n"
            + "            <if test=\"pojo.openId != null\"> #{pojo.openId}, </if>\n"
            + "            <if test=\"pojo.scopeType != null\"> #{pojo.scopeType}, </if>\n"
            + "            <if test=\"pojo.accessToken != null\"> #{pojo.accessToken}, </if>\n"
            + "            <if test=\"pojo.accessExpire != null\"> #{pojo.accessExpire}, </if>\n"
            + "            <if test=\"pojo.refreshToken != null\"> #{pojo.refreshToken}, </if>\n"
            + "            <if test=\"pojo.refreshExpire != null\"> #{pojo.refreshExpire}, </if>\n"
            + "            <if test=\"pojo.scope != null\"> #{pojo.scope}, </if>\n"
            + "            <if test=\"pojo.createAt != null\"> #{pojo.createAt}, </if>\n"
            + "            <if test=\"pojo.updateAt != null\"> #{pojo.updateAt}, </if>\n"
            + "        </trim>";


    public static final String  UPDATE_BY_ID =
        "    UPDATE"+TABLE_NAME
            + "        <set>\n"
            + "            <if test=\"pojo.id != null\"> id = #{pojo.id}, </if>\n"
            + "            <if test=\"pojo.userId != null\"> user_id = #{pojo.userId}, </if>\n"
            + "            <if test=\"pojo.openId != null\"> open_id = #{pojo.openId}, </if>\n"
            + "            <if test=\"pojo.scopeType != null\"> scope_type = #{pojo.scopeType}, </if>\n"
            + "            <if test=\"pojo.accessToken != null\"> access_token = #{pojo.accessToken}, </if>\n"
            + "            <if test=\"pojo.accessExpire != null\"> access_expire = #{pojo.accessExpire}, </if>\n"
            + "            <if test=\"pojo.refreshToken != null\"> refresh_token = #{pojo.refreshToken}, </if>\n"
            + "            <if test=\"pojo.refreshExpire != null\"> refresh_expire = #{pojo.refreshExpire}, </if>\n"
            + "            <if test=\"pojo.scope != null\"> scope = #{pojo.scope}, </if>\n"
            + "            <if test=\"pojo.createAt != null\"> create_at = #{pojo.createAt}, </if>\n"
            + "            <if test=\"pojo.updateAt != null\"> update_at = #{pojo.updateAt} </if>\n"
            + "        </set>"
            + "    WHERE id = #{pojo.id}";


    public static final String SELECT_BY_ID =
        "SELECT " + COLUMNS
            + " from" + TABLE_NAME
            + " WHERE id = #{id}";

    public static final String SELECT_BY_USERID =
        " SELECT " + COLUMNS
            +"FROM " +  TABLE_NAME
            +"WHERE  user_id=#{userId}";

    public static final String SELECT_BY_OPENID =
        " SELECT " + COLUMNS
            +"FROM " +  TABLE_NAME
            +"WHERE  open_id=#{openId}";
}
