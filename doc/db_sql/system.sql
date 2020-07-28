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

#4.小节表
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section`(
                          `id` CHAR(8) NOT NULL DEFAULT '' COMMENT '小节ID',
                          `title` VARCHAR(50) NOT NULL COMMENT '标题',
                          `course_id` CHAR(8) COMMENT '课程|course ID',
                          `chapter_id` CHAR(8) COMMENT '章节| chapter ID',
                          `video_add` VARCHAR(200) COMMENT '视频地址',
                          `time` INT COMMENT '时长-单位秒',
                          `charge` CHAR(1) COMMENT '收费-C收费-F免费',
                          `sort` INT COMMENT '顺序',
                          `create_time` DATETIME(3) COMMENT '创建时间',
                          `update_time` DATETIME(3) COMMENT '修改时间',
                          PRIMARY KEY (`id`)
)
    ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT '小节';

INSERT INTO `section` (id,title,course_id,chapter_id,video_add,time,charge,sort,create_time,update_time)
VALUES('A0000101','测试小节01','00000101','00000001','',500,'F',1,NOW(),NOW());
INSERT INTO `section` (id,title,course_id,chapter_id,video_add,time,charge,sort,create_time,update_time)
VALUES('A0000102','测试小节02','00000102','00000002','',500,'F',1,NOW(),NOW());

#5.课程表，对应枚举类名
drop table if EXISTS course;
create TABLE course(
                       `id` char(8) NOT NULL default '' COMMENT '课程ID',
                       `name` VARCHAR(50) NOT NULL COMMENT '课程名',
                       `summary` VARCHAR(2000) COMMENT '课程概述',
                       `time` INT default 0 COMMENT '时长 | 单位秒',
                       `price` DECIMAL(8,2) default 0.00 COMMENT '价格（元）',
                       `image` VARCHAR(100) COMMENT '封面',
                       `level` CHAR(1) COMMENT '级别|枚举[CourseLevelEnum]: ONE("1","初级"),TWO("2","中级"),THREE("3","高级")',
                       `charge` CHAR(1) COMMENT '收费|枚举[CourseChargeEnum]: CHARGE("C","收费"),FREE("F","免费")',
                       `status` CHAR(1) COMMENT '状态|枚举[CourseStatusEnum]: PUBLISH("P","发布"),DRAFT("D","草稿")',
                       `enroll` INTEGER default 0 COMMENT '报名数',
                       `sort` INT COMMENT '顺序',
                       `create_time` datetime(3) COMMENT '创建时间',
                       `update_time` datetime(3) COMMENT '修改时间',
                       PRIMARY KEY (`id`)
)
    ENGINE=INNODB default CHARSET=utf8mb4 COMMENT='课程表';

INSERT INTO course (id,`name`,summary,`time`,price,image,`level`,charge,`status`,enroll,`sort`,create_time,update_time)
VALUES ('0000001','测试课程01','springboot进阶springcloud',9990,9.9,'',1,'C','D',619,0,NOW(),NOW());
INSERT INTO course (id,`name`,summary,`time`,price,image,`level`,charge,`status`,enroll,`sort`,create_time,update_time)
VALUES ('0000002','测试课程02','视频网课项目进阶springcloud',9990,19.9,'',2,'C','D',700,1,NOW(),NOW());

#6.查询课程进行，小节时间累加，更新course表的time字段
update course c set `time`=(select sum(`time`) from `section` where course_id='0000001')
where c.id='0000001';

#7.分类表:支持无限级分类
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`(
                           `id` char(8) NOT NULL default '' COMMENT '分类ID',
                           `parent` CHAR(8) NOT NULL default '' COMMENT '父ID',
                           `name` VARCHAR(50) NOT NULL COMMENT '分类名称',
                           `sort` INT COMMENT '顺序',
                           PRIMARY KEY (`id`)
)
    ENGINE=INNODB default CHARSET=utf8mb4 COMMENT='分类表';
# 100为一级分类，200为2级分类,如果为1级分类，则parent-id为8个0
INSERT INTO `category` (`id`,`parent`,`name`,`sort`)
VALUES('00000100','00000000','VUE响应式界面',100);
INSERT INTO `category` (`id`,`parent`,`name`,`sort`)
VALUES('00000101','00000002','html/css布局',101);
INSERT INTO `category` (`id`,`parent`,`name`,`sort`)
VALUES('00000102','00000003','javaScript-DOM',102);
INSERT INTO `category` (`id`,`parent`,`name`,`sort`)
VALUES('00000103','00000004','freemarker',103);
INSERT INTO `category` (`id`,`parent`,`name`,`sort`)
VALUES('00000204','00000205','mybatis',104);
INSERT INTO `category` (`id`,`parent`,`name`,`sort`)
VALUES('00000205','00000206','angular',105);
INSERT INTO `category` (`id`,`parent`,`name`,`sort`)
VALUES('00000206','00000207','zepto',106);
INSERT INTO `category` (`id`,`parent`,`name`,`sort`)
VALUES('00000207','00000208','react',107);
INSERT INTO `category` (`id`,`parent`,`name`,`sort`)
VALUES('00000208','00000209','sqoop',108);

#8.课程分类表
DROP TABLE IF EXISTS `course_category`;
CREATE table `course_category`(
                                  `id` char(8) NOT NULL default '' COMMENT '课程分类ID',
                                  `course_id` CHAR(8) COMMENT '课程ID|course.id',
                                  `category_id` CHAR(8) COMMENT '分类ID|category.id',
                                  PRIMARY KEY (`id`)
)
    ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='课程分类表';

#9.课程内容表：课程表和内容表是1:1关系，可设置ID相同
#medium text:媒介文件，富文本。
#课程详情：一般有文字、图片、视频等，需要大字段来存储medium text属于大字段存储，长度>text
#数据库设计规则(分库分表-垂直分表)：大文本字段，单独设计一张表来存储，提高程序运行效率，方便DBA运维
DROP TABLE IF EXISTS `course_content`;
CREATE TABLE `course_content`(
                                 `id` CHAR(8) NOT NULL default '' COMMENT '课程内容ID',
                                 `content` MEDIUMTEXT NOT NULL COMMENT '课程内容',
                                 PRIMARY KEY (`id`)
)
    ENGINE=INNODB default CHARSET=utf8mb4 COMMENT '课程内容表';