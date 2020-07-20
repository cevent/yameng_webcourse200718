#1. 查询测试表
SELECT t.*
FROM cevent_yameng_webcourse.test_webcourse t
LIMIT 501;

#2. 新建test表
drop table if exists `test`;
create table `test`(
    `id` char(8) not null default '' comment '测试id',
    `name` varchar(50) comment '名称',
    primary key (`id`)
)
engine=innodb default charset=utf8mb4 comment '测试表';

insert into `test` (`id`,`name`) values (1,'测试表初始化');
insert into `test` (`id`,`name`) values (2,'by cevent');

#3. 启动business模组表
drop table if exists `chapter`;
create table `chapter`(
    `id` char(8) not null comment '章节ID',
    `course_id` char(8) comment '课程ID',
    `name` varchar(50) comment '名称',
    primary key (`id`)
)
engine=innodb default charset=utf8mb4 comment='章节表';

insert into `chapter` (`id`,`course_id`,`name`) values ('0000001','111111','测试大章一');
insert into `chapter` (`id`,`course_id`,`name`) values ('0000002','111111','测试大章二');