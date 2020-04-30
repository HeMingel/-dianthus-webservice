
#2020年4月1日14:44:56 已经执行
ALTER TABLE `d_sys_user`
    ADD COLUMN `user_order` INT NULL COMMENT '用户排序' AFTER `last_login_time`,
    ADD COLUMN `user_landline` VARCHAR (100) NULL COMMENT '用户座机' AFTER `user_order`,
    ADD COLUMN `user_duty` VARCHAR (100) NULL COMMENT '用户职位' AFTER `user_landline`,
    ADD COLUMN `user_position` VARCHAR (100) NULL COMMENT '用户职责' AFTER `user_duty`,
    ADD COLUMN `user_sex` VARCHAR (10) NULL COMMENT '用户性别' AFTER `user_position`,
    ADD COLUMN `parttime` INT DEFAULT 0 NULL COMMENT '是否兼任 1 是 0 否' AFTER `user_sex`;

#2020年4月2日22:36:40 已经执行
ALTER TABLE `d_shift_cycle` ADD COLUMN `cycle_index` INT(11) NULL COMMENT '当前循环' AFTER `cycle_type`;

# 2020年4月7日11:11:15 已经执行
ALTER TABLE `d_relief_detail`
    ADD COLUMN `source_schedule_date` TIMESTAMP NULL COMMENT ' 原值班日期' AFTER `source_schedule_id`,
    ADD COLUMN `source_schedule_name` VARCHAR (100) NULL COMMENT '原值班人员名称' AFTER `source_schedule_date`,
    ADD COLUMN `source_schedule_phone` VARCHAR (100) NULL COMMENT '原值班人员电话' AFTER `source_schedule_name`,
    ADD COLUMN `exchange_schedule_date` TIMESTAMP NULL COMMENT '交换值班日期' AFTER `exchange_schedule_id`,
    ADD COLUMN `exchange_schedule_name` VARCHAR (100) NULL COMMENT '交换值班人员名称' AFTER `exchange_schedule_date`,
    ADD COLUMN `exchange_schedule_phone` VARCHAR (100) NULL COMMENT '交换值班人员电话' AFTER `exchange_schedule_name`;

ALTER TABLE `d_shift` ADD COLUMN `shift_office` VARCHAR(255) NULL COMMENT '值班地点' AFTER `people_number`;


# 2020年4月14日09:40:53
CREATE TABLE `d_duty_kind` (
    `id` VARCHAR (32) NOT NULL,
    `duty_kind_name` VARCHAR (100) COMMENT '值班名称',
    `duty_kind_desc` VARCHAR (255) COMMENT '值班种类说明',
    `creator` VARCHAR (323) COMMENT '创建人ID',
    `create_time` TIMESTAMP COMMENT '创建时间',
    `enable_flag` INT (10) DEFAULT 1 COMMENT '逻辑删除字段 0 已删除 1正常使用',
    PRIMARY KEY (`id`)
);


CREATE TABLE `d_duty_kind_shift` (
    `duty_kind_id` VARCHAR (32) COMMENT '值班种类id',
    `shift_id` VARCHAR (32) COMMENT ' 班次ID'
);

ALTER TABLE `d_duty_schedule` ADD COLUMN `duty_kind_id` VARCHAR(32) NULL COMMENT '值班种类id' AFTER `id`;


ALTER TABLE `duty`.`d_shift` ADD COLUMN `shift_color_code1` VARCHAR(255) NULL COMMENT '颜色代码1' AFTER `shift_order`,
  ADD COLUMN `shift_color_code2` VARCHAR(255) NULL COMMENT '颜色代码2' AFTER `shift_color_code1`;
