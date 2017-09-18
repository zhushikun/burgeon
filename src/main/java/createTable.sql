CREATE TABLE user(
    `id`            INT  UNSIGNED   NOT NULL AUTO_INCREMENT COMMENT 'id',
    `alias`         VARCHAR(50)     NULL                    COMMENT '别名',
    `pwd`           VARCHAR(64)     NULL                    COMMENT '密码',
    `name`          VARCHAR(32)     NULL                    COMMENT '姓名',
    `gender`        VARCHAR(2)      NOT NULL   DEFAULT '0'  COMMENT '性别，1男性，2女性，0未知',
    `phone`         VARCHAR(16)     NULL                    COMMENT 'phone',
    `birthday`      VARCHAR(10)     NULL                    COMMENT 'yyyy-mm-dd',
    `address`       VARCHAR(64)     NULL                    COMMENT '地址',
    `avatar_id`     INT UNSIGNED    NULL                    COMMENT 'fileLocation头像ID',
    use_wx_avatar   TINYINT         NOT NULL DEFAULT 1      COMMENT '1 使用微信头像, 0 不用',
    `create_at`     DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_at`     timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY PK_USER(`id`)
)AUTO_INCREMENT = 101 ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户';

CREATE TABLE wx_oauth(
    `id`            INT  UNSIGNED   NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id`       INT  UNSIGNED   NOT NULL                COMMENT 'userID',
    `open_id`       VARCHAR(128)    NOT NULL                COMMENT '用户的唯一标识',
    `scope_type`    VARCHAR(32)     NOT NULL                COMMENT 'accessToken的类型',
    `access_token`  VARCHAR(128)    NOT NULL                COMMENT 'accessToken',
    `access_expire` DATETIME        NOT NULL                COMMENT 'accessToken 过期时间',
    refresh_token   VARCHAR(128)    NOT NULL                COMMENT 'refreshToken',
    refresh_expire  DATETIME        NOT NULL                COMMENT 'refreshToken 过期时间',
    `scope`         VARCHAR(128)    NULL                    COMMENT '用户授权的作用域，使用逗号分隔',
    create_at 	    DATETIME 	      NOT NULL 	DEFAULT CURRENT_TIMESTAMP,
    update_at 	    timestamp 	    NOT NULL 	DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
    PRIMARY KEY PK_WXOAT(`id`),
    INDEX UIDX_WXOAT_UI (user_id),
    INDEX UIDX_WXOAT_OI (open_id)
)AUTO_INCREMENT = 1 ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '微信oauth表';

CREATE TABLE wx_user(
    `id`            INT  UNSIGNED   NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id`       INT  UNSIGNED   NOT NULL                COMMENT 'userID',
    `open_id`       VARCHAR(128)    NOT NULL                COMMENT '用户的唯一标识',
    `nick_name`     VARCHAR(64)     NULL                    COMMENT '用户昵称',
    `sex`           VARCHAR(2)      NOT NULL  DEFAULT '0'   COMMENT '用户的性别，1男性，2女性，0未知',
    `province`      VARCHAR(64)     NULL                    COMMENT '用户个人资料填写的省份',
    `city`          VARCHAR(64)     NULL                    COMMENT '普通用户个人资料填写的城市',
    `country`       VARCHAR(32)     NULL                    COMMENT '国家，如中国为CN',
    `head_img_url`  VARCHAR(512)    NULL                    COMMENT '用户头像url',
    `privilege`     VARCHAR(512)    NULL                    COMMENT '用户特权信息，json 数组',
    `union_id`      VARCHAR(128)    NULL                    COMMENT '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段',
    create_at 	    datetime 	      NOT NULL 	DEFAULT CURRENT_TIMESTAMP,
    update_at 	    timestamp 	    NOT NULL 	DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
    PRIMARY KEY PK_WXUSR(`id`),
    INDEX UIDX_WXUSR_UI (user_id),
    INDEX UIDX_WXUSR_OI (open_id)
)AUTO_INCREMENT = 1 ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '微信用户信息';


CREATE TABLE file_location(
    `id`        INT  UNSIGNED     NOT NULL  AUTO_INCREMENT  COMMENT 'id',
    `user_id`   INT  UNSIGNED     NOT NULL                  COMMENT 'userId',
    `type`      VARCHAR(32)       NOT NULL                  COMMENT '类型',
    `location`  VARCHAR(128)      NOT NULL                  COMMENT '相对位置',
    create_at 	datetime 	        NOT NULL 	DEFAULT CURRENT_TIMESTAMP,
    update_at 	timestamp 	      NOT NULL 	DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
    PRIMARY KEY PK_FILELOC(`id`),
    INDEX UIDX_FILELOC_UI (user_id)
)AUTO_INCREMENT=1 ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '文件位置';


CREATE TABLE invite (
    id 			    INT UNSIGNED	NOT NULL 		AUTO_INCREMENT	  comment '主键' ,
    master_id 	INT UNSIGNED	NOT NULL 					            comment '邀请人',
    guest_id  	INT UNSIGNED 	NOT NULL 					            comment '被邀请人',
    type	    	varchar(32)   NOT NULL 	DEFAULT 'NORMAL'	  comment  '邀请类型',
    create_at 	datetime 	    NOT NULL 	DEFAULT CURRENT_TIMESTAMP,
    update_at 	timestamp 	  NOT NULL 	DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
    PRIMARY KEY PK_INVITE (id),
    INDEX UIDX_INVITE_MI (master_id),
    INDEX UIDX_INVITE_GI (guest_id)
)AUTO_INCREMENT = 1,ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邀请表';














