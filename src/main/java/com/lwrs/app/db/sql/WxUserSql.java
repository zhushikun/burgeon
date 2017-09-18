package com.lwrs.app.db.sql;

public class WxUserSql {

    private static final String TABLE_NAME = " wx_user ";

    private static final String COLUMNS =
        "  id, user_id, open_id, nick_name, sex, province, city, country, head_img_url, privilege, union_id, create_at, update_at ";

    public static final String INSERT =
        "   INSERT INTO  " + TABLE_NAME
        +" <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">"
            +"            <if test=\"pojo.id != null\"> id, </if>"
            +"            <if test=\"pojo.userId != null\"> user_id, </if>"
            +"            <if test=\"pojo.openId != null\"> open_id, </if>"
            +"            <if test=\"pojo.nickName != null\"> nick_name, </if>"
            +"            <if test=\"pojo.sex != null\"> sex, </if>"
            +"            <if test=\"pojo.province != null\"> province, </if>"
            +"            <if test=\"pojo.city != null\"> city, </if>"
            +"            <if test=\"pojo.country != null\"> country, </if>"
            +"            <if test=\"pojo.headImgUrl != null\"> head_img_url, </if>"
            +"            <if test=\"pojo.privilege != null\"> privilege, </if>"
            +"            <if test=\"pojo.unionId != null\"> union_id, </if>"
            +"            <if test=\"pojo.createAt != null\"> create_at, </if>"
            +"            <if test=\"pojo.updateAt != null\"> update_at, </if>"
            +"        </trim>"
            +"        VALUES"
            +"        <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">"
            +"            <if test=\"pojo.id != null\"> #{pojo.id}, </if>"
            +"            <if test=\"pojo.userId != null\"> #{pojo.userId}, </if>"
            +"            <if test=\"pojo.openId != null\"> #{pojo.openId}, </if>"
            +"            <if test=\"pojo.nickName != null\"> #{pojo.nickName}, </if>"
            +"            <if test=\"pojo.sex != null\"> #{pojo.sex}, </if>"
            +"            <if test=\"pojo.province != null\"> #{pojo.province}, </if>"
            +"            <if test=\"pojo.city != null\"> #{pojo.city}, </if>"
            +"            <if test=\"pojo.country != null\"> #{pojo.country}, </if>"
            +"            <if test=\"pojo.headImgUrl != null\"> #{pojo.headImgUrl}, </if>"
            +"            <if test=\"pojo.privilege != null\"> #{pojo.privilege}, </if>"
            +"            <if test=\"pojo.unionId != null\"> #{pojo.unionId}, </if>"
            +"            <if test=\"pojo.createAt != null\"> #{pojo.createAt}, </if>"
            +"            <if test=\"pojo.updateAt != null\"> #{pojo.updateAt}, </if>"
            +"        </trim>"
        ;

    public static final String UPDATE_BY_ID =
        " update " + TABLE_NAME
        +"      <set>"
//        +"            <if test=\"pojo.id != null\"> id = #{pojo.id}, </if>"
//        +"            <if test=\"pojo.userId != null\"> user_id = #{pojo.userId}, </if>"
//        +"            <if test=\"pojo.openId != null\"> open_id = #{pojo.openId}, </if>"
        +"            <if test=\"pojo.nickName != null\"> nick_name = #{pojo.nickName}, </if>"
        +"            <if test=\"pojo.sex != null\"> sex = #{pojo.sex}, </if>"
        +"            <if test=\"pojo.province != null\"> province = #{pojo.province}, </if>"
        +"            <if test=\"pojo.city != null\"> city = #{pojo.city}, </if>"
        +"            <if test=\"pojo.country != null\"> country = #{pojo.country}, </if>"
        +"            <if test=\"pojo.headImgUrl != null\"> head_img_url = #{pojo.headImgUrl}, </if>"
        +"            <if test=\"pojo.privilege != null\"> privilege = #{pojo.privilege}, </if>"
        +"            <if test=\"pojo.unionId != null\"> union_id = #{pojo.unionId}, </if>"
        +"       </set>"
        +" WHERE id = #{pojo.id}";

    public static final String SELECT_BY_ID =
        " SELECT " + COLUMNS
            +"FROM " +  TABLE_NAME
            +"WHERE  id=#{id}";

    public static final String SELECT_BY_USERID =
        " SELECT " + COLUMNS
            +"FROM " +  TABLE_NAME
            +"WHERE  user_id=#{userId}";

    public static final String SELECT_BY_OPENID =
        " SELECT " + COLUMNS
            +"FROM " +  TABLE_NAME
            +"WHERE  open_id=#{openId}";
}
