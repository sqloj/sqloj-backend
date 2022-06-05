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
  `id`         nvarchar(32) primary key,
  `username`   nvarchar(32),
  `password`   nvarchar(80),
  `department` nvarchar(32),
  `signature`  nvarchar(128),
  `submit_num` int null,
  `pass_num`   int null,
  `role`       int

);


INSERT INTO user
VALUES ('admin', '老师', 'admin', '管理员','', 0, 0, 2),
       ('tourist', 'tourist', 'tourist', 'cf特色班','', 0, 0, 2),
       ('test', '学生', 'test', '测试组','', 0, 0, 1),
       ('202082011012', 'test1', 'test1', '测试组','', 0, 0, 1),
       ('202084312122', 'test2', 'test2', '测试组','', 0, 0, 1);


CREATE TABLE testcase (
    `id`       int auto_increment primary key,
    `label`    nvarchar(32),
    `abstract` text,
    `content`  text,
    `judge_type_id`  int
);


INSERT INTO testcase
VALUES (1, '空表', '', '', 5),
       (2, '全校学生表', 'CREATE TABLE s(\n    snum nvarchar(66),\n    sname nvarchar(65)\n);',
        'INSERT INTO s VALUES\n(''10086'', ''aa''),\n(''10085'', ''bb''),\n(''201305010101'', ''gg'');', 5),
       (3, '计算机专业表', '', '', 2),
       (4, '数据库班级信息表', '', '', 1);

CREATE TABLE question (
    `id`          int auto_increment primary key,
    `content`     text,
    `answer`      text,
    `testcase_id` int,
    FOREIGN KEY (testcase_id) REFERENCES testcase(id)
);

INSERT INTO question
VALUES (1, '查询系编号为‘0501’学生的基本信息（学号、姓名、性别、出生日期）。', 'SELECT snum, sname, ssex, sbirth FROM s WHERE dnum = ''0501'';', 1),
       (2, '查询学号为''201305010101''的学生的姓名。', 'SELECT sname FROM s WHERE snum = ''201305010101'';', 2),
       (3, '选1', 'SELECT 1;', 1),
       (4, '输出 $\\pi = 3.141596535...$\n\n> 提示：$\\arccos(-1) = \\pi$', 'SELECT arccos(-1);', 1),
       (5, '请计算\n$$ \\sum_{n=1}^\\infty \\frac{1}{n(n+1)} $$', 'SELECT ACOS(-1) * ACOS(-1) / 6;', 1),
       (6, '![](https://pic1.zhimg.com/v2-09b42c0984429923fc5502d5bf1bf3ee_1440w.jpg?source=172ae18b)',
        'SELECT ''154476802108746166441951315019919837485664325669565431700026634898253202035277999'';
       SELECT ''36875131794129999827197811565225474825492979968971970996283137471637224634055579'';
       SELECT ''4373612677928697257861252602371390152816537558161613618621437993378423467772036'';',
        1);
CREATE TABLE record (
    `id`          int auto_increment primary key,
    `user_id`     nvarchar(32),
    `question_id` int,
    `code`        text,
    `result`      int,
    `time`        datetime
);

CREATE TABLE judge (
    `id`       int auto_increment primary key,
    `url`      nvarchar(256) UNIQUE,
    `password` nvarchar(32),
    `judge_type_id` int,
    `user_id` int
);
# INSERT INTO  judge
# VALUES (1,http://124.221.134.229:10100/,ys654321,5
#         );

CREATE TABLE judge_type (
    `id`   int auto_increment primary key,
    `name` nvarchar(20)
);

INSERT INTO judge_type
VALUES (1, 'MariaDB'),
       (2, 'SQL Server'),
       (3, 'MySQL'),
       (4, 'SQLite'),
       (5, 'H2 Database');

create table judge_manage (
    `user_id`  varchar(32) ,
    `judge_id` int(11)
);
CREATE TABLE article_manage (
    `user_id` int null,
    `article_id` int null
);
CREATE TABLE article (
    `id`      int auto_increment primary key,
    `title`   varchar(50) null,
    `content` text        null,
    `time`    datetime    null,
    `user_id` int
);
