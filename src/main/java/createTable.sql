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
    use_wx_avatar   TINYINT         NOT NULL DEFAULT 0      COMMENT '1 使用微信头像, 0 不用',
    `create_at`     DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_at`     timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY PK_USER(`id`),
    UNIQUE INDEX UIDX_USER_AS (alias)
)AUTO_INCREMENT=101 ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户';

CREATE TABLE temp_user_link(
    `id`            INT  UNSIGNED   NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id`       INT  UNSIGNED   NOT NULL                COMMENT 'userID',
    `temp_uid`      VARCHAR(64)     NOT NULL                COMMENT 'uuid',
    `create_at`     DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_at`     timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY PK_TUSRL(`id`),
    UNIQUE INDEX UIDX_TUSRL_TU (temp_uid)
)AUTO_INCREMENT=1 ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'uuid关联user';

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
    UNIQUE INDEX UIDX_WXOAT_UI (user_id),
    UNIQUE INDEX UIDX_WXOAT_OI (open_id)
)AUTO_INCREMENT=1 ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '微信oauth表';

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
    UNIQUE INDEX UIDX_WXUSR_UI (user_id),
    UNIQUE INDEX UIDX_WXUSR_OI (open_id)
)AUTO_INCREMENT=101 ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '微信用户信息';


CREATE TABLE file_location(
    `id`        INT  UNSIGNED     NOT NULL  AUTO_INCREMENT  COMMENT 'id',
    `user_id`   INT  UNSIGNED     NOT NULL                  COMMENT 'userId',
    `type`      VARCHAR(32)       NOT NULL                  COMMENT '类型',
    `location`  VARCHAR(128)      NOT NULL                  COMMENT '相对位置',
    create_at 	datetime 	        NOT NULL 	DEFAULT CURRENT_TIMESTAMP,
    update_at 	timestamp 	      NOT NULL 	DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
    PRIMARY KEY PK_FILELOC(`id`),
    INDEX IDX_FILELOC_UI (user_id)
)AUTO_INCREMENT=1 ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '文件位置';


CREATE TABLE event_book (
    id 			    INT UNSIGNED	NOT NULL 		AUTO_INCREMENT	  comment '主键' ,
    shop_id 	  INT UNSIGNED	NOT NULL 					            comment '商户Id',
    user_id  	  INT UNSIGNED 	NOT NULL 					            comment '用户ID',
    user_name   VARCHAR(32)   NOT NULL                      comment '用户名',
    gender      VARCHAR(2)    NOT NULL   DEFAULT 0          COMMENT '性别，M男性，F女性',
    phone       VARCHAR(16)   NOT NULL                      comment '手机号',
    birth_date  DATE          NOT NULL                      comment '用户出生日期',
    book_type   VARCHAR(32)   NOT NULL                      comment '选择类型',
    book_date   DATE          NOT NULL                      comment '用户预定日期',
    create_at 	datetime 	    NOT NULL 	DEFAULT CURRENT_TIMESTAMP,
    update_at 	timestamp 	  NOT NULL 	DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
    PRIMARY KEY PK_EVENTBK (id),
    INDEX IDX_EVENTBK_UI (user_id),
    INDEX IDX_EVENTBK_SI (shop_id)
)AUTO_INCREMENT=1 ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动预订';

CREATE TABLE shop (
    id 			    INT UNSIGNED	NOT NULL 		AUTO_INCREMENT	  comment 'shopId' ,
    shop_name 	varchar(32) 	NOT NULL 					            comment '商户名',
    group_id  	INT UNSIGNED 	NOT NULL 	DEFAULT 0           comment 'shopGroupId',
    address	    varchar(128)  NOT NULL 	                	  comment  '地址',
    phone	      varchar(16)   NOT NULL 	                	  comment  '电话',
    create_at 	datetime 	    NOT NULL 	DEFAULT CURRENT_TIMESTAMP,
    update_at 	timestamp 	  NOT NULL 	DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
    PRIMARY KEY PK_SHOP (id),
    UNIQUE INDEX UIDX_SHOP_NAME (shop_name),
    INDEX  IDX_SHOP_GI (group_id)
)AUTO_INCREMENT=101 ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商户表';


CREATE TABLE shop_owner (
    id 			    INT UNSIGNED	NOT NULL 		AUTO_INCREMENT	  comment 'shop_owner ID' ,
    user_id	    INT UNSIGNED	NULL 		              	      comment 'userId' ,
    alias 	    varchar(32) 	NOT NULL 					            comment '用于登录名',
    code        VARCHAR(32)   NOT NULL                      comment 'MD5-校验登录',
    shop_ids     varchar(128) 	NOT NULL 					            comment 'own shopIds逗号隔开',
    phone	      varchar(16)   NULL 	                	      comment  '电话',
    create_at 	datetime 	    NOT NULL 	DEFAULT CURRENT_TIMESTAMP,
    update_at 	timestamp 	  NOT NULL 	DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
    PRIMARY KEY PK_SHOPOWN (id),
    UNIQUE INDEX UIDX_SHOPOWN_UI (user_id),
    UNIQUE INDEX UIDX_SHOPOWN_as (alias)
)AUTO_INCREMENT=101 ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商户老板表';

-- init
insert into shop (shop_name, address, phone)
value
('咱部落烧烤','水产西路519号','13524993957');

insert into shop_owner(alias, `code`, shop_ids, phone)
value
('13524993957','VcWi2MjtnWipChzZelphpg==','101', '13524993957') ;

-- test
insert into event_book(shop_id, user_id, user_name, gender, phone, birth_date, book_type, book_date)
values
(101, 101,'测试1','M','18516602446', date('1990-10-01'), '999套餐', date('2017-11-11')),
(101, 102,'测试2','M','18516602446', date('1990-10-01'), '888套餐', date('2017-11-11')) ;






