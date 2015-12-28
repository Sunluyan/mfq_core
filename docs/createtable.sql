use meifenqi;
set names utf8;

#user 用户表

CREATE TABLE `users` (
  `uid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'user id',
  `status` tinyint(3) unsigned NOT NULL COMMENT 'user status,0 for normal',
  `nick` varchar(32) NOT NULL DEFAULT '' COMMENT 'user login name',
  `email` varchar(80) NOT NULL DEFAULT '' COMMENT 'user login email,md5(mobile) while empty',
  `mobile` varchar(32) NOT NULL DEFAULT '' COMMENT 'user login mobile,md5(email) while empty',
  `gender` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT 'user gender(1 for boy,2 for girl,0 for unset)',
  `icon` varchar(128) NOT NULL DEFAULT '' COMMENT 'tiny avatar',
  `img` varchar(128) NOT NULL DEFAULT '' COMMENT 'media avatar',
  `pic` varchar(128) NOT NULL DEFAULT '' COMMENT 'large avatar',
  `location_id` int(10) NOT NULL DEFAULT '0' COMMENT 'location id',
  `location` varchar(128) NOT NULL DEFAULT '' COMMENT 'locations, separated by comma',
  `birthday` int(11) NOT NULL DEFAULT '0' COMMENT 'birthday using big number',
  `intro` varchar(1024) NOT NULL DEFAULT '' COMMENT 'user intro',
  `interest` varchar(128) NOT NULL DEFAULT '',
  `homesite` varchar(256) NOT NULL DEFAULT '' COMMENT 'homesites,separated by comma',
  `career` varchar(64) NOT NULL DEFAULT '' COMMENT 'carees,separated by comma',
  `created_at` datetime NOT NULL COMMENT 'record created time',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'record updated time',
  `sign` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'some useful status',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `index_email` (`email`),
  UNIQUE KEY `index_mobile` (`mobile`),
  KEY `createat` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


#额度表
#uid, 帐户余额, 总额度, 额度余额
CREATE TABLE users_quota ( 
  `uid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '帐户ID',
  `realname` varchar(32) NOT NULL DEFAULT '' COMMENT 'user real name,maybe empty',
  `school` varchar(100) NOT NULL DEFAULT '' COMMENT '学校,maybe empty',
  `contact` varchar(32) NOT NULL DEFAULT '',
  `grade` int(4) NOT NULL DEFAULT '0' COMMENT '年级',
  `classes` varchar(100) NOT NULL DEFAULT '' COMMENT '班级',
  `graduated_at` datetime DEFAULT NULL COMMENT '毕业日期',
  `id_card` varchar(32) NOT NULL DEFAULT '' COMMENT 'user ID card number',
  `balance` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '帐户余额',
  `quota_all` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '总额度',
  `quota_left` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '剩余额度',
  `location_id` int(10) NOT NULL DEFAULT '0' COMMENT '学校地址',
  `location` varchar(128) NOT NULL DEFAULT '' COMMENT '学校地址',
  `homesite` varchar(256) NOT NULL DEFAULT '' COMMENT 'homesites,separated by comma',
  `auth_status` tinyint(2) NOT NULL DEFAULT '0',
  `present` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '赠送金额',
  `wish_plastic` tinyint(2) NOT NULL DEFAULT '0' COMMENT '-1--不愿意 1--愿意',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `created_at` datetime NOT NULL COMMENT '用户额度创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  UNIQUE KEY `uniq_uid` (`uid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='额度表';

＃赠送金额－历史记录表
CREATE TABLE `present_record` (
	`uid` bigint(20) NOT NULL COMMENT '用户ID',
	`amount` decimal(10,2) NOT NULL DEFAULT 0 COMMENT '赠送金额',
	`type` int(4) NOT NULL,
	`remark` varchar(500) NOT NULL DEFAULT '' ,
	`created_at` datetime NOT NULL ,
	KEY `idx_uid`(`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='赠送金额记录';

#users_login表：用户登录记录
CREATE TABLE `users_login` (
  `uid` bigint(20) NOT NULL COMMENT 'user id',
  `reg_time` datetime NOT NULL COMMENT 'registed time',
  `reg_ip` varchar(16) NOT NULL DEFAULT '' COMMENT 'registed ip address',
  `last_login_time` datetime NOT NULL COMMENT 'last login time',
  `last_login_ip` varchar(64) NOT NULL DEFAULT '' COMMENT 'last login ip address',
  `login_fail_count` int(11) NOT NULL DEFAULT '0' COMMENT 'count of login failed',
  `login_fail_time` datetime DEFAULT NULL COMMENT 'time of last login failed',
  `actived_time` datetime DEFAULT NULL,
  `source` varchar(128) NOT NULL DEFAULT '' COMMENT '注册来源',
  `referer` varchar(2048) NOT NULL DEFAULT '' COMMENT '用户来网站时的refer',
  `reg_track` varchar(100) NOT NULL DEFAULT '' COMMENT '注册时用户的追踪信息, web用nxid',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


#passports表记录登录票据信息
CREATE TABLE `passports` (
  `uid` bigint(20) NOT NULL COMMENT 'user id',
  `ticket` varchar(32) NOT NULL DEFAULT '' COMMENT 'login ticket',
  `created_at` datetime NOT NULL COMMENT 'ticket created time',
  `expired_at` datetime NOT NULL COMMENT 'ticket expired time',
  `actived_at` datetime DEFAULT NULL COMMENT 'ticket last actived time',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT 'md5(plainpasswd+salt)',
  `salt` varchar(64) NOT NULL DEFAULT '' COMMENT 'uuid salt',
  `salt2` varchar(64) NOT NULL DEFAULT '' COMMENT 'random salt for login',
  `password2` varchar(32) NOT NULL DEFAULT '' COMMENT 'random password for login',
  PRIMARY KEY (`uid`),
  KEY `uid_ticket_expire` (`uid`,`ticket`,`expired_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


#passports_oauth表－记录第三方站点登录－注意有效期
CREATE TABLE `passports_oauth` (
  `uid` bigint(20) NOT NULL,
  `site_type` tinyint(2) NOT NULL COMMENT 'weibo/qq/renren/kaixin: 1/2/3/4',
  `access_token` varchar(512) NOT NULL DEFAULT '',
  `uuid` varchar(64) NOT NULL,
  `status` tinyint(2) NOT NULL COMMENT '0 for noral, 99 for deleted',
  `extra` varchar(4096) NOT NULL DEFAULT '',
  `expired_at` datetime NOT NULL COMMENT 'access token expired time',
  `created_at` datetime NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sync` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`site_type`,`uuid`),
  KEY `inx_uid_site_type` (`uid`,`site_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


#vcode
CREATE TABLE `vcode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `vcode` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `emkey` varchar(64) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `expire_at` datetime NOT NULL,
  `resend_at` datetime NOT NULL,
  `retry_time` int(4) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `seq_id` int(11) NOT NULL DEFAULT '0' COMMENT '发送的序列id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `emkey` (`emkey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


#SMS-Config
CREATE TABLE `sms_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'provider id',
  `sn` varchar(11) NOT NULL COMMENT '短信服务商－缩写',
  `name` varchar(11) NOT NULL COMMENT '短信服务商',
  `detail` varchar(1000) NOT NULL DEFAULT '' COMMENT '服务商配置',
  `normal` tinyint(2) NOT NULL COMMENT '是否normal提供方',
  `vcode` tinyint(2) NOT NULL COMMENT '是否vcode提供方',
  `batch` tinyint(2) NOT NULL COMMENT '是否batch提供方',
  `backup` tinyint(2) NOT NULL COMMENT '是否backup提供方',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into sms_config values(1, "uc", "ucpaas", "{\"baseURL\":\"https://api.ucpaas.com\", \"softVersion\":\"2014-06-30\", \"accountSid\":\"43da40f7780b4b9bd76e537ec52d9f7c\", \"authToken\":\"f3c559b296ddaa61dd069a0aecfd1f2f\"}", 1, 1, 1, 1, "2015-08-22 18:33:00");

#SMS-LOGS
CREATE TABLE `smslogs` (
  `sid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '顺序号',
  `mobile` varchar(32) NOT NULL DEFAULT '' COMMENT '手机号',
  `content` varchar(512) NOT NULL DEFAULT '' COMMENT '手机内容',
  `send_status` tinyint(2) NOT NULL COMMENT '短信发送状态',
  `provider` varchar(11) NOT NULL DEFAULT '' COMMENT '发送使用服务商',
  `send_type` tinyint(2) NOT NULL COMMENT '发送类型',
  `resend_sid` int(11) NOT NULL DEFAULT '0' COMMENT '重发关联',
  `extra` varchar(512) NOT NULL DEFAULT ' ' COMMENT '额外信息',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `timed_at` datetime NOT NULL COMMENT '定时时间',
  `sent_at` datetime NOT NULL COMMENT '发送时间',
  PRIMARY KEY (`sid`),
  KEY `INDEX_MOBILE` (`mobile`),
  KEY `INDEX_STATUS_TYPE_CREATEAT` (`send_status`,`send_type`,`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


#安装设备在线表
CREATE TABLE `ms_installations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `device_type` varchar(32) NOT NULL COMMENT 'ios, android',
  `device_token` varchar(80) NOT NULL,
  `app_id` varchar(128) NOT NULL,
  `app_version` varchar(64) NOT NULL,
  `channels` varchar(512) NOT NULL,
  `badge` smallint(6) NOT NULL,
  `uid` int(11) NOT NULL DEFAULT '0',
  `created_at` datetime NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_type_token` (`device_type`,`device_token`),
  KEY `ix_uid` (`uid`,`app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#forbidden
CREATE TABLE `forbidden_words` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `category_id` tinyint(3) NOT NULL DEFAULT '0',
  `sub_category_id` tinyint(3) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_name_category` (`name`,`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


#分期账单表：定时每天0点30执行,提前15天生成,账单表，用于还款使用
#id, uid, 订单号, 还款金额, 当前期数, 总期数, 帐单状态, 交易日期, 记账日期, 最后更新日期
CREATE TABLE finance_bill ( 
	`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'bill ID', 
	`uid` bigint(20) unsigned NOT NULL COMMENT '用户ID', 
	`bill_no` varchar(30) NOT NULL COMMENT '订单号',
	`order_no` varchar(30) NOT NULL COMMENT '订单号', 
	`new_balance` decimal(10, 2) NOT NULL COMMENT '本期应还金额', 
	`late_fee` decimal(10, 2) NOT NULL DEFAULT 0 COMMENT '滞纳金', 
	`cur_period` int(4) NOT NULL COMMENT '当前期数', 
	`all_period` int(4) NOT NULL COMMENT '总期数', 
	`status` int(4) NOT NULL COMMENT '帐单状态', 
	`trade_at` datetime NOT NULL COMMENT '交易日期', 
	`charge_at` datetime NOT NULL COMMENT '记账日期', 
	`bill_at` datetime NOT NULL COMMENT '帐单生成时间', 
	`due_at` datetime NOT NULL COMMENT '帐单应还款日期', 
	`pay_at` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '实还款日期',
	`updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (`id`), 
	KEY `idx_usl` (`uid`, `status`, `late_fee`), 
	KEY `idx_uid` (`uid`), 
	UNIQUE KEY `idx_billno` (`bill_no`),
	KEY `idx_bill` (`bill_at`), 
	KEY `idx_due` (`due_at`), 
	KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分期帐单';


#pay_record支付记录表
#id, 流水号, 金额, uid, 支付平台, 支付网关, 充值时间, 回调时间, updated
CREATE TABLE pay_record ( 
	`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '充值ID', 
	`order_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '类型,1:订单 2 支付 3还款  ',
	`trade_no` varchar(30) NOT NULL DEFAULT '' COMMENT '交易流水号', 
	`order_no` varchar(30) NOT NULL COMMENT '订单号', 
	`amount` decimal(10, 2) NOT NULL DEFAULT 0 COMMENT '交易金额', 
	`balance` decimal(10, 2) NOT NULL DEFAULT 0 COMMENT '使用余额部分', 
	`present` decimal(10, 2) NOT NULL DEFAULT 0 COMMENT '使用赠送金额部分', 
	`uid` bigint(20) unsigned NOT NULL COMMENT 'user id', 
	`tpp` varchar(20) NOT NULL DEFAULT '' COMMENT '支付平台',
	`bank_code` varchar(20) NOT NULL DEFAULT '' COMMENT '支付银行 default:-1',
	`card_type` int(4) NOT NULL DEFAULT 0 COMMENT '卡类型',
	`card_no` varchar(30) NOT NULL DEFAULT '' COMMENT '支付卡号标示',
	`status` int(4) NOT NULL COMMENT '充值状态', 
	`pay_at` datetime DEFAULT NULL COMMENT '支付时间', 
	`callback_at` datetime DEFAULT NULL COMMENT '回调时间', 
	`updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (`id`), 
	UNIQUE KEY `uniq_order_no` (`order_no`),
	UNIQUE KEY `uniq_trade_no` (`trade_no`), 
	KEY `idx_uid_cno`(`uid`, `order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付记录表';
	
#order_info订单表
CREATE TABLE `order_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `order_no` varchar(30) NOT NULL COMMENT '订单号',
  `price` decimal(10,2) NOT NULL COMMENT '总价',
  `uid` bigint(20) unsigned NOT NULL COMMENT 'user id',
  `pid` bigint(20) unsigned NOT NULL COMMENT 'product id',
  `pay_type` tinyint(2) NOT NULL COMMENT '订单类型；1分期付款，0全额付款',
  `period_pay` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '每期应还款',
  `period` int(4) NOT NULL DEFAULT '0' COMMENT '总期数',
  `online_pay` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '在线支付',
  `hospital_pay` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '医院到付',
  `coupon_num` varchar(50) NOT NULL COMMENT '优惠券编码',
  `security_code` varchar(10) NOT NULL DEFAULT '' COMMENT '订单安全码',
  `status` tinyint(4) NOT NULL COMMENT '订单状态',
  `refund_type` tinyint(2) NOT NULL COMMENT '退款方式 0 -无退款 1--原路退回 2--退回余额',
  `created_at` datetime NOT NULL COMMENT '订单创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_order_no` (`order_no`), 
  KEY `idx_price` (`price`),
  KEY `idx_uid` (`uid`),
  KEY `idx_pid` (`pid`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='订单表';


#product_classify产品分类
CREATE TABLE product_classify ( 
	`id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '类别ID', 
	`name` varchar(100) NOT NULL COMMENT '类型名字', 
	`flag` varchar(100) NOT NULL COMMENT '标签', 
	`desp` varchar(100) NOT NULL COMMENT '类型描述', 
	`root_id` int(10) unsigned NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`),
	KEY `idx_rid` (`root_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品分类';

#product
CREATE TABLE `product` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '产品名字',
  `tid` int(10) NOT NULL COMMENT '产品类别',
  `hospital_id` bigint(20) NOT NULL COMMENT '医院ID',
  `city_id` int(6) NOT NULL COMMENT '产品城市id',
  `flag` int(4) NOT NULL DEFAULT '0' COMMENT '是否推荐 0--普通 1--首页推荐',
  `img` varchar(500) NOT NULL COMMENT '产品图片',
  `order_no` int(10) NOT NULL DEFAULT '0' COMMENT '排序码',
  `hospital_pay` decimal(10,2) NOT NULL COMMENT '团购，到院支付',
  `online_pay` decimal(10,2) NOT NULL COMMENT '团购，定金',
  `price` decimal(10,2) NOT NULL COMMENT '团购价格',
  `p_price` decimal(10,2) NOT NULL COMMENT '分期价格',
  `p_num` int(5) NOT NULL COMMENT '分期数',
  `date_start` datetime NOT NULL COMMENT '有效期开始时间',
  `date_end` datetime NOT NULL COMMENT '有效期结束时间',
  `view_num` bigint(20) NOT NULL COMMENT '浏览量',
  `sale_num` bigint(20) NOT NULL COMMENT '销量',
  `online` tinyint(2) NOT NULL DEFAULT '0' COMMENT '上下线状态',
  `created_at` datetime NOT NULL COMMENT '创建日期',
  `updated_at` datetime NOT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_created_at` (`created_at`),
  KEY `idx_price` (`price`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='产品表';

#product_detail 内容详情表
CREATE TABLE product_detail ( 
	`pid` bigint(20) unsigned NOT NULL AUTO_INCREMENT, 
	`consume_step` varchar(1000) NOT NULL COMMENT '消费流程', 
	`reserve` varchar(1000) NOT NULL COMMENT '如何预约',
	`special_note` varchar(1000) NOT NULL COMMENT '特殊说明',
	`body` mediumtext NOT NULL COMMENT '内容详情介绍', 
	UNIQUE KEY `uniq_pid` (`pid`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='内容详情表';

CREATE TABLE `coupon` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) unsigned NOT NULL COMMENT 'user id', 
  `batch_id` bigint(20) NOT NULL COMMENT '批次ID', 
  `coupon_num` varchar(50) NOT NULL COMMENT '优惠券编码',
  `money` decimal(10,2) NOT NULL COMMENT '抵扣金额',
  `status` tinyint(2) NOT NULL COMMENT '状态 0初始状态 1冻结 2已使用',
  `updated_at` timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '最后更新时间', 
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_coupon_num` (`coupon_num`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠券信息';
#上线前需要批量插入一堆优惠券，给推广的用户

CREATE TABLE `2` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `batch` varchar(30) NOT NULL COMMENT '批次说明',
  `period_beg` datetime NOT NULL COMMENT '有效开始日期',
  `period_end` datetime NOT NULL COMMENT '有效结束日期',
  `money` decimal(10,2) NOT NULL COMMENT '抵扣金额',
  `condition` decimal(10,2) NOT NULL COMMENT '折扣条件',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠券批次信息';
insert into coupon_batch_info values(1, "微信现金红包", "2015-09-20 00:00:00", "2015-12-31 23:59:59", 500, 5000);

#hospital
CREATE TABLE hospital ( 
	`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '医院ID', 
	`name` varchar(300) NOT NULL COMMENT '医院名字', 
	`img` varchar(500) NOT NULL COMMENT '医院图片', 
	`address` varchar(1000) NOT NULL COMMENT '医院地址', 
	`created_at` datetime NOT NULL comment '创建日期', 
	`updated_at` datetime NOT NULL COMMENT '最后更新时间', 
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='医院表';


#hospital 内容详情表
#CREATE TABLE hospital_detail ( 
#	`hid` int(11) unsigned NOT NULL AUTO_INCREMENT, 
#	`body` mediumtext NOT NULL COMMENT '医院详情介绍', 
#	UNIQUE KEY `uniq_hid` (`hid`) 
#) DEFAULT CHARSET=utf8 COMMENT='医院详情表';



#文章中心、帮助中心
CREATE TABLE article (
	`id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'comment id', 
	`t_id` int(10) NOT NULL COMMENT '唯一标识 artile_type',
	`name` varchar(100) NOT NULL COMMENT '文章名字', 
	`order` int(10) NOT NULL DEFAULT 0 COMMENT '排序', 
	`last_update_time` datetime NOT NULL COMMENT '最后更新时间', 
	PRIMARY KEY (`id`), 
	KEY `idx_tid` (`t_id`) 	
) DEFAULT CHARSET=utf8 COMMENT='文章类别表';


#article_content 文章内容
CREATE TABLE article_content ( 
	`a_id` int(11) unsigned NOT NULL COMMENT '文章唯一标识id', 
	`body` mediumtext default '' COMMENT '内容详情', 
	KEY `idx_aid` (`a_id`) 
) DEFAULT CHARSET=utf8 COMMENT='文章内容详情表';


#comment 评论
CREATE TABLE `comment` (
	`id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'comment id', 
	`t_id` int(10) NOT NULL COMMENT '唯一标识 type ID', 
	`p_id` bigint(20) NOT NULL COMMENT '唯一标识 id', 
	`order_no` varchar(30) NOT NULL DEFAULT '' COMMENT '订单号',
	`src_site` VARCHAR(100) DEFAULT '' COMMENT '如果是抓取，取值是来源网站，如果是UGC，取值是meifenqi', 
	`type` tinyint(2) DEFAULT 0 COMMENT '用于区分UGC 0,抓取 1和改写的2', 
	`rank` int(2) DEFAULT 0 COMMENT '评价星级0-5', 
	`uid` BIGINT(20) DEFAULT 0 COMMENT '如果是UGC，则有值', 
	`user_name` varchar(30) DEFAULT '' COMMENT '评论发表用户', 
	`create_date` DATETIME NOT NULL COMMENT '发布日期', 
	`abs_content` varchar(255) DEFAULT '' COMMENT '如果是抓取的，取值是评论摘要，否则为空', 
	`content` varchar(1000) DEFAULT '' COMMENT '评论内容', 
	`user_ip` varchar(30) DEFAULT '' COMMENT '评论用户ip', 
	`check_flag` tinyint(2) DEFAULT 0 COMMENT '审核通过标识1通过 0未通过', 
	`check_user` varchar(30) DEFAULT '' COMMENT '审核人', 
	`root_cid` BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'root cid，回复的哪一条 默认为0', 
	`last_update_time` datetime NOT NULL COMMENT '最后更新时间', 
	PRIMARY KEY (`id`), 
	UNIQUE KEY `uniq_tid_pid` (`t_id`, `p_id`), 
	KEY `idx_root_cid` (`root_cid`) 
) DEFAULT CHARSET=utf8 COMMENT='评论表';
