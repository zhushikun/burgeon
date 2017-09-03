
CREATE TABLE wx_user(
    `id`        INT(13) UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'id',
    `open_id`   VARCHAR(128)      NOT NULL DEFAULT '' COMMENT '用户的唯一标识',
    `nick_name` VARCHAR(64)       NOT NULL DEFAULT '' COMMENT '用户昵称',
    `sex`       VARCHAR(8)        NOT NULL DEFAULT '' COMMENT '用户的性别，1男性，2女性，0未知',
    `province`  VARCHAR(64)       NOT NULL DEFAULT '' COMMENT '用户个人资料填写的省份',
    `city`      VARCHAR(64)       NOT NULL DEFAULT '' COMMENT '普通用户个人资料填写的城市',
    `country`   VARCHAR(32)       NOT NULL DEFAULT '' COMMENT '国家，如中国为CN',
    `head_img_url` VARCHAR(512)   NOT NULL DEFAULT '' COMMENT '用户头像url',
    `privilege` VARCHAR(512)      NOT NULL DEFAULT '' COMMENT '用户特权信息，json 数组',
    `union_id`  VARCHAR(128)      NOT NULL DEFAULT '' COMMENT '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段',
    create_at 	datetime 	        NOT NULL 	DEFAULT CURRENT_TIMESTAMP,
    update_at 	timestamp 	      NOT NULL 	DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
    PRIMARY KEY (`id`)
)AUTO_INCREMENT = 1 ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '微信用户信息';


CREATE TABLE file_location(
    `id`        INT(13) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id`   BIGINT NOT NULL DEFAULT -1 COMMENT 'userId',
    `type`      VARCHAR(50) NOT NULL DEFAULT '' COMMENT '@see FileType#name',
    `location`  VARCHAR(50) NOT NULL DEFAULT '' COMMENT '相对位置',
    create_at 	datetime 	NOT NULL 	DEFAULT CURRENT_TIMESTAMP,
    update_at 	timestamp 	NOT NULL 	DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'file_location_d_b';


CREATE TABLE invite (
    id 			    INT(13) 	NOT NULL 					AUTO_INCREMENT	comment '主键' ,
    master_id 	INT(13) 	NOT NULL 					comment '邀请人',
    guest_id  	INT(13) 	NOT NULL 					comment '被邀请人',
    type	    	varchar(32) NOT NULL 	DEFAULT 'NORMAL'	comment  '邀请类型',
    create_at 	datetime 	NOT NULL 	DEFAULT CURRENT_TIMESTAMP,
    update_at 	timestamp 	NOT NULL 	DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
    PRIMARY KEY PK_INVITE (id),
    INDEX UIDX_INVITE_MI (master_id),
    INDEX UIDX_INVITE_GI (guest_id)
)AUTO_INCREMENT = 1,ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邀请表';



CREATE TABLE media_location (
    id 			    INT(13) 	NOT NULL 					AUTO_INCREMENT	comment '主键' ,
    user_id 	  INT(13) 	NOT NULL 					comment '用户ID',
    type 		    varchar(32) NOT NULL 					comment 'media type',
    location  	varchar(64) NOT NULL 					comment '位置',
    create_at 	datetime 	NOT NULL 	DEFAULT CURRENT_TIMESTAMP,
    update_at 	timestamp 	NOT NULL 	DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
    PRIMARY KEY PK_INVITE (id),
    INDEX UIDX_INVITE_MI (master_id),
    INDEX UIDX_INVITE_GI (guest_id)
)AUTO_INCREMENT = 1,ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邀请表';
