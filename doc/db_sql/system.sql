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
# alt键拖拉
insert into `chapter` (`id`,`course_id`,`name`) values ('0000003','111111','测试大章03');
insert into `chapter` (`id`,`course_id`,`name`) values ('0000004','111111','测试大章04');
insert into `chapter` (`id`,`course_id`,`name`) values ('0000005','111111','测试大章05');
insert into `chapter` (`id`,`course_id`,`name`) values ('0000006','111111','测试大章06');
insert into `chapter` (`id`,`course_id`,`name`) values ('0000007','111111','测试大章07');
insert into `chapter` (`id`,`course_id`,`name`) values ('0000008','111111','测试大章08');
insert into `chapter` (`id`,`course_id`,`name`) values ('0000009','111111','测试大章09');
insert into `chapter` (`id`,`course_id`,`name`) values ('0000010','111111','测试大章10');
insert into `chapter` (`id`,`course_id`,`name`) values ('0000011','111111','测试大章11');
insert into `chapter` (`id`,`course_id`,`name`) values ('0000012','111111','测试大章12');
insert into `chapter` (`id`,`course_id`,`name`) values ('0000013','111111','测试大章13');
insert into `chapter` (`id`,`course_id`,`name`) values ('0000014','111111','测试大章14');
insert into `chapter` (`id`,`course_id`,`name`) values ('0000015','111111','测试大章15');
insert into `chapter` (`id`,`course_id`,`name`) values ('0000016','111111','测试大章16');
