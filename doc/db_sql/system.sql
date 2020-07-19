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