set character_set_client = utf8;
set character_set_server = utf8;
set character_set_connection = utf8;
set character_set_database = utf8;
set character_set_results = utf8;
set collation_connection = utf8_general_ci;
set collation_database = utf8_general_ci;
set collation_server = utf8_general_ci;
set time_zone = "+8:00";

CREATE DATABASE sqloj;

USE sqloj;

CREATE TABLE `user` (
    `id`           nvarchar(32) primary key,
    `username`     nvarchar(32),
    `password`     nvarchar(32),
    `department`   nvarchar(32),
    `role`         int
);


INSERT INTO user VALUES
('admin',  '老师', 'admin', '管理员', 2),
('tourist', 'tourist', 'tourist', 'cf特色班', 2),
('test', '学生',  'test',  '测试组', 1),
('202082011012', 'test1', 'test1', '测试组', 1),
('202084312122', 'test2', 'test2', '测试组', 1);


CREATE TABLE testcase (
    `id`              int auto_increment primary key,
    `label`           nvarchar(32),
    `abstract`        text,
    `content`         text,
    `lang`            int
);


INSERT INTO testcase VALUES
(1,  '空表', '', '', 1),
(2,  '全校学生表', '', '', 1),
(3,  '计算机专业表', '', '', 2),
(4,  '数据库班级信息表', '', '', 1);

CREATE TABLE question (
    `id`           int auto_increment primary key,
    `content`      text,
    `answer`       text,
    `testcase_id`  int
);

INSERT INTO question VALUES
(1, '查询系编号为‘0501’学生的基本信息（学号、姓名、性别、出生日期）。', 'SELECT snum, sname, ssex, sbirth FROM s WHERE dnum = ''0501'';', 1),
(2, '查询学号为''201305010101''的学生的姓名。', 'SELECT sname FROM s WHERE snum = ''201305010101'';', 2);

CREATE TABLE record (
    `id`           int auto_increment primary key,
    `user_id`      nvarchar(32),
    `question_id`  int,
    `code`         text,
    `result`       int,
    `time`         datetime
);

CREATE TABLE judge (
    `id`           int auto_increment primary key,
    `address`      nvarchar(20),
    `password`     nvarchar(32)
);
