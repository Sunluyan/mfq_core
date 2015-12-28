
ALTER TABLE `product`
ADD COLUMN `city_id` int(6) NOT NULL COMMENT '产品城市id' AFTER `hospital_id`;

ALTER TABLE `product`
ADD COLUMN `flag` int(4) NOT NULL DEFAULT 0 COMMENT '是否推荐 0--普通 1--首页推荐' AFTER `city_id`;

ALTER TABLE `users_extend`
ADD COLUMN `mobile_type` varchar(100) NOT NULL DEFAULT '' COMMENT '设备' AFTER `invite_code`;

ALTER TABLE `order_info`
ADD COLUMN `security_code` varchar(10) NOT NULL DEFAULT "" COMMENT '到院安全码' AFTER `hospital_pay`;

ALTER TABLE `product`
ADD COLUMN `img`  varchar(500) NULL COMMENT '产品图片' AFTER `flag`;

测试数据
INSERT INTO `product` VALUES ('1', '手术', '1', '1', '1500.00', '750.00', '2', '2015-09-06 20:47:26', '2015-09-06 20:47:28', '1', '1', '2015-09-06 20:47:39', '2015-09-06 20:47:42', '1', '1');
INSERT INTO `product` VALUES ('2', '双眼皮', '1', '1', '1500.00', '7500.00', '2', '2015-09-07 13:46:21', '2015-09-07 13:46:23', '1', '1', '2015-09-07 13:46:27', '2015-09-07 13:46:32', '1', '1');

INSERT INTO `product_classify` VALUES ('1', '眼部', '1', '1', '0');
INSERT INTO `product_classify` VALUES ('2', '胸部', '1', '1', '0');
INSERT INTO `product_classify` VALUES ('3', '双眼皮', '1', '1', '1');
INSERT INTO `product_classify` VALUES ('4', '开眼角', '1', '1', '1');
INSERT INTO `product_classify` VALUES ('5', '隆胸', '1', '1', '2');

CREATE TABLE `favorites` (
	`uid` bigint(20) NOT NULL COMMENT '用户ID' ,
	`pid` bigint(20) NOT NULL COMMENT '产品ID' ,
	`updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	UNIQUE KEY `uniq_upid` (`uid`, `pid`),
	KEY `idx_updated`(`updated`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收藏夹';


申请面签字段

ALTER TABLE `users_extend`
ADD COLUMN `promotion_code`  varchar(32) NOT NULL DEFAULT '' AFTER `invite_code`,
ADD COLUMN `auth_status`  tinyint(2) NOT NULL DEFAULT 0 AFTER `quota_left`;


#意见反馈
CREATE TABLE `feedback` (
	`id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id', 
	`uid` bigint(20) NOT NULL COMMENT '用户ID',
	`check_flag` tinyint(2) DEFAULT 0 COMMENT '审核标识',
	`check_user` varchar(30) DEFAULT '' COMMENT '审核人',
	`content` blob NOT NULL DEFAULT '' COMMENT '意见内容',
	`created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
	`updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='意见反馈表';


ALTER TABLE `product_classify`
ADD COLUMN `icon`  varchar(500) NOT NULL DEFAULT '' AFTER `root_id`,
ADD COLUMN `hg_image`  varchar(500) NOT NULL DEFAULT '' AFTER `icon`;


ALTER TABLE `users_quota`
CHANGE COLUMN `is_auth` `auth_status`  tinyint(2) NOT NULL DEFAULT '0' AFTER `quota_left`,
ADD COLUMN `remark`  varchar(500) NOT NULL DEFAULT '' COMMENT '备注' AFTER `auth_status`;


ALTER TABLE `product`
MODIFY COLUMN `img`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品图片' AFTER `flag`,
ADD COLUMN `hospital_pay`  decimal(10,0) NOT NULL COMMENT '团购，到院支付' AFTER `img`,
ADD COLUMN `down_price`  decimal(10,0) NOT NULL COMMENT '团购，定金' AFTER `hospital_pay`;

ALTER TABLE `sys_user`
ADD COLUMN `hospital_id`  bigint(20) NOT NULL DEFAULT 0 COMMENT '医院ID' AFTER `role_id`,
ADD COLUMN `classes`  varchar(100) NOT NULL DEFAULT '' COMMENT '班级' AFTER `grade`;


ALTER TABLE `order_info`
ADD COLUMN `refund_type`  tinyint(2) NOT NULL COMMENT '退款方式 0 -无退款 1--原路退回 2--退回余额' AFTER `status`;


ALTER TABLE `users_quota`
ADD COLUMN `location_id`  int(10) NOT NULL DEFAULT 0 COMMENT '学校地址' AFTER `quota_left`,
ADD COLUMN `location`  varchar(128) NOT NULL DEFAULT '' COMMENT '学校地址' AFTER `location_id`,
ADD COLUMN `homesite`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT 'homesites,separated by comma' AFTER `location`;

ALTER TABLE `users_quota`
ADD COLUMN `wish_plastic`  tinyint(2) NOT NULL DEFAULT 0 COMMENT '-1--不愿意 1--愿意' AFTER `auth_status`;

ALTER TABLE `product`
ADD COLUMN `url`  varchar(500) NOT NULL DEFAULT '' AFTER `img`;

alter table order_info add column `online_pay` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '在线支付' after period;
alter table order_info add column `coupon_num` varchar(50) NOT NULL COMMENT '优惠券编码' after hospital_pay;


ALTER TABLE `product`
ADD COLUMN `order_no`  int(10) NOT NULL DEFAULT 0 AFTER `img`;

ALTER TABLE `product`
ADD COLUMN `market_price`  decimal(10,2) NOT NULL DEFAULT 0 AFTER `price`;


CREATE TABLE `notification` (
  `id` bigint(100) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `img` varchar(100) NOT NULL DEFAULT '' COMMENT '图片',
  `title` varchar(100) NOT NULL DEFAULT '' COMMENT 'title',
  `msg` varchar(100) NOT NULL DEFAULT '' COMMENT '消息体',
  `status` int(4) NOT NULL DEFAULT 0 COMMENT '状态'
  `type` int(4) NOT NULL DEFAULT 0 COMMENT '类别',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


CREATE TABLE `notification` (
  `id` bigint(100) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `to_user_name` varchar(100) NOT NULL DEFAULT '' COMMENT '消息接收用户',
  `open_id` varchar(100) NOT NULL DEFAULT '' COMMENT '发送用户openId',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `msg_type` varchar(30) NOT NULL DEFAULT '' COMMENT '消息类型',
  `pic_url` varchar(300) NOT NULL DEFAULT '' COMMENT '图片地址',
  `media_id` varchar(100) NOT NULL DEFAULT '' COMMENT '媒体id',
  `msg_id` varchar(100) NOT NULL DEFAULT '' COMMENT '消息id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


CREATE TABLE `notification` (
  `id` bigint(100) NOT NULL AUTO_INCREMENT,
  `img` varchar(100) NOT NULL DEFAULT '' COMMENT '图片',
  `title` varchar(100) NOT NULL DEFAULT '' COMMENT 'title',
  `msg` varchar(100) NOT NULL DEFAULT '' COMMENT '消息',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '类型',
  `created` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE `product`
ADD COLUMN `type`  int(4) NOT NULL DEFAULT 0 COMMENT '类别' AFTER `hospital_pay`;

ALTER TABLE `wechat_msg`
ADD COLUMN `content`  varchar(300) NOT NULL DEFAULT '' COMMENT '文本' AFTER `msg_type`;


ALTER TABLE `product`
ADD COLUMN `remain_num`  bigint(20) NOT NULL COMMENT '产品剩余量' AFTER `date_end`,
ADD COLUMN `total_num`  bigint(20) NOT NULL DEFAULT 0 COMMENT '产品总量' AFTER `remain_num`;


CREATE TABLE `home_banner` (
`id`  bigint(100) UNSIGNED NOT NULL AUTO_INCREMENT ,
`img`  varchar(100) NOT NULL DEFAULT '' ,
`url`  varchar(100) NOT NULL DEFAULT '' ,
`index`  int(10) NOT NULL DEFAULT 0 ,
`type`  tinyint(4) NOT NULL DEFAULT 0 ,
`flag`  tinyint(2) NOT NULL DEFAULT 0 ,
`created_at`  timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP ,
PRIMARY KEY (`id`)
)


ALTER TABLE `home_banner`
ADD COLUMN `name`  varchar(100) NOT NULL DEFAULT '' COMMENT '名称' AFTER `id`,
ADD COLUMN `p_type`  tinyint(4) NOT NULL DEFAULT 0 AFTER `img`,
ADD COLUMN `p_id`  bigint(100) NOT NULL DEFAULT 0 COMMENT '项目id' AFTER `p_type`;


ALTER TABLE `users_quota`
ADD COLUMN `startschool_at`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间' AFTER `classes`,
ADD COLUMN `origin`  varchar(100) NOT NULL DEFAULT '' COMMENT '籍贯' AFTER `location_id`;

ALTER TABLE `hospital`
ADD COLUMN `city_id`  bigint(100) NOT NULL DEFAULT 0 COMMENT '城市ID' AFTER `name`,



//投保

CREATE TABLE `NewTable` (
`id`  bigint(100) NOT NULL DEFAULT 0 COMMENT 'ID' ,
`order_no`  varchar(100) NOT NULL DEFAULT '' ,
`order_time`  timestamp NOT NULL DEFAULT '' ON UPDATE CURRENT_TIMESTAMP COMMENT '订单成交时间' ,
`service_stime`  date NOT NULL DEFAULT '' COMMENT '服务开始时间' ,
`product_name`  varchar(100) NOT NULL DEFAULT '' COMMENT '产品名称' ,
`order_sum`  bigint(100) NOT NULL DEFAULT 0 COMMENT '订单消费产品' ,
`order_pay_type`  varchar(100) NOT NULL ,
`nurse_id`  bigint(100) NOT NULL DEFAULT 0 COMMENT '陪护人员ID' ,
`uid`  bigint(100) NOT NULL ,
`policy_pid`  varchar(100) NOT NULL DEFAULT '' COMMENT '保单ID' ,
`insurance_code`  varchar(100) NOT NULL DEFAULT '' COMMENT '保险编码' ,
`phone`  varchar(100) NOT NULL DEFAULT '' COMMENT '被保险人手机号' ,
`policy_no`  varchar(100) NOT NULL DEFAULT '' COMMENT '悟空保单号' ,
`premium`  bigint(100) NOT NULL DEFAULT 0 COMMENT '保费' ,
`sum_insured`  bigint(100) NOT NULL COMMENT '保额' ,
`policy_bdate`  timestamp NOT NULL DEFAULT '' ON UPDATE CURRENT_TIMESTAMP COMMENT '保险开始时间' ,
`policy_edate`  timestamp NOT NULL DEFAULT '' ON UPDATE CURRENT_TIMESTAMP COMMENT '保险结束时间' ,
`policy_status`  varchar(20) NOT NULL DEFAULT '' COMMENT '保单状态' ,
`request_str`  varchar(200) NOT NULL DEFAULT '' COMMENT '请求字符串' ,
`reponse_str`  varchar(200) NOT NULL DEFAULT '' COMMENT '响应字符串' ,
`request_time`  timestamp NOT NULL DEFAULT '' ON UPDATE CURRENT_TIMESTAMP COMMENT '请求时间' ,
`reponse_time`  timestamp NOT NULL DEFAULT '' ON UPDATE CURRENT_TIMESTAMP COMMENT '响应时间' ,
`created_at`  timestamp NOT NULL DEFAULT '' ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间' ,
`updated_at`  timestamp NOT NULL DEFAULT '' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
CONSTRAINT `orderno` FOREIGN KEY (`order_no`) REFERENCES `order_info` (`order_no`) ON DELETE RESTRICT ON UPDATE CASCADE,
CONSTRAINT `uid` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE NO ACTION ON UPDATE CASCADE
)
;



CREATE TABLE `NewTable` (
`id`  bigint(100) NOT NULL DEFAULT 0 COMMENT 'ID' ,
`order_no`  varchar(100) NOT NULL DEFAULT '' ,
`order_time`  timestamp NOT NULL DEFAULT '' ON UPDATE CURRENT_TIMESTAMP COMMENT '订单成交时间' ,
`service_stime`  date NOT NULL DEFAULT '' COMMENT '服务开始时间' ,
`product_name`  varchar(100) NOT NULL DEFAULT '' COMMENT '产品名称' ,
`order_sum`  bigint(100) NOT NULL DEFAULT 0 COMMENT '订单消费产品' ,
`order_pay_type`  varchar(100) NOT NULL ,
`nurse_id`  bigint(100) NOT NULL DEFAULT 0 COMMENT '陪护人员ID' ,
`uid`  bigint(100) NOT NULL ,
`policy_pid`  varchar(100) NOT NULL DEFAULT '' COMMENT '保单ID' ,
`policy_id`  bigint(100) NOT NULL DEFAULT 0 COMMENT '保单ID' ,
`request_str`  varchar(200) NOT NULL DEFAULT '' COMMENT '请求字符串' ,
`reponse_str`  varchar(200) NOT NULL DEFAULT '' COMMENT '响应字符串' ,
`request_time`  timestamp NOT NULL DEFAULT '' ON UPDATE CURRENT_TIMESTAMP COMMENT '请求时间' ,
`reponse_time`  timestamp NOT NULL DEFAULT '' ON UPDATE CURRENT_TIMESTAMP COMMENT '响应时间' ,
`created_at`  timestamp NOT NULL DEFAULT '' ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间' ,
`updated_at`  timestamp NOT NULL DEFAULT '' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
CONSTRAINT `orderno` FOREIGN KEY (`order_no`) REFERENCES `order_info` (`order_no`) ON DELETE RESTRICT ON UPDATE CASCADE,
CONSTRAINT `uid` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE NO ACTION ON UPDATE CASCADE
)
;


CREATE TABLE `NewTable` (
`id`  bigint(100) NOT NULL AUTO_INCREMENT COMMENT 'ID' ,
`order_no`  varchar(100) NOT NULL DEFAULT '' ,
`order_time`  timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '订单成交时间' ,
`service_stime`  date NOT NULL COMMENT '服务开始时间' ,
`product_name`  varchar(100) NOT NULL DEFAULT '' COMMENT '产品名称' ,
`order_sum`  bigint(100) NOT NULL DEFAULT 0 COMMENT '订单消费产品' ,
`order_pay_type`  varchar(100) NOT NULL ,
`nurse_id`  bigint(100) NOT NULL DEFAULT 0 COMMENT '陪护人员ID' ,
`uid`  bigint(100) NOT NULL ,
`policy_pid`  varchar(100) NOT NULL DEFAULT '' COMMENT '保单ID' ,
`policy_id`  bigint(100) NOT NULL DEFAULT 0 COMMENT '保单ID' ,
`request_str`  varchar(200) NOT NULL DEFAULT '' COMMENT '请求字符串' ,
`reponse_str`  varchar(200) NOT NULL DEFAULT '' COMMENT '响应字符串' ,
`request_time`  timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '请求时间' ,
`reponse_time`  timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '响应时间' ,
`created_at`  timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间' ,
`updated_at`  timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
PRIMARY KEY (`id`),
CONSTRAINT `FK_orderno` FOREIGN KEY (`order_no`) REFERENCES `order_info` (`order_no`) ON DELETE NO ACTION ON UPDATE CASCADE,
CONSTRAINT `FK_uid` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE NO ACTION ON UPDATE CASCADE
)
;



ALTER TABLE `users_quota`
CHANGE COLUMN `graduated_at` `startschool_at`  datetime NULL DEFAULT NULL COMMENT '开学日期' AFTER `classes`,

CHANGE COLUMN `balance` `balancew`  decimal(10,2) NOT NULL DEFAULT 0.00 COMMENT '帐户余额' AFTER `idcard_front`,
CHANGE COLUMN `school_location` `school_locationw`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '学校地址' AFTER `school_location_id`,
ADD COLUMN `e`  timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'tt ' AFTER `work_years`,
ADD COLUMN `ttt`  varchar(100) NOT NULL DEFAULT '' COMMENT 'safd' AFTER `e`;



ALTER TABLE `policy_insure`
ADD COLUMN `status`  tinyint(4) NOT NULL DEFAULT 0 AFTER `reponse_time`;

ALTER TABLE `users_quota`
ADD COLUMN `position` varchar(45) NOT NULL DEFAULT '' COMMENT '职位' AFTER `work_years`,
ADD COLUMN `scholastic_years`  int(4) NOT NULL DEFAULT 0 COMMENT '学制' AFTER `school_level`,
ADD COLUMN `department`  varchar(45) NOT NULL DEFAULT '' COMMENT '部门' AFTER `position`;


ALTER TABLE `order_info`
ADD COLUMN `policy_status`  tinyint(4) NOT NULL DEFAULT 0 COMMENT '保单状态' AFTER `security_code`;



