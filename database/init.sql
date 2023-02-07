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

# 用户信息
CREATE TABLE `user` (
    `id`           nvarchar(32) primary key,
    `username`     nvarchar(32),
    `password`     nvarchar(80),
    `department`   nvarchar(32),
    `signature`    nvarchar(128),
    `role`         int
);

CREATE TABLE `student` (
    `user_id`    nvarchar(32),
    `submit_num` int,
    `pass_num`   int,
    FOREIGN KEY (user_id) REFERENCES `user`(id)
);

INSERT INTO user VALUES
    ('admin', '老师', 'admin', '管理员','我和我的日子都会发光。', 2),
    ('tourist', 'tourist', 'tourist', 'cf特色班','NO.1', 2),
    ('ycy', 'ycy', 'ycy', '计算机','roger young', 2),
    ('zy', 'zy', 'zy', '新工科','', 1),
    ('sy', 'sy', 'sy', '新工科','clumsy', 1),
    ('202082011012', 'test1', 'test1', '测试组','', 1),
    ('202084312122', 'test2', 'test2', '测试组','', 1);

INSERT INTO `student` VALUES
    ('202082011012', 0, 0),
    ('202084312122', 0, 0),
    ('zy', 0, 0),
    ('sy', 0, 0);

CREATE TABLE judge_type (
    `id`           int auto_increment primary key,
    `name`         nvarchar(20)
);

INSERT INTO judge_type VALUES
    (1, 'MariaDB'),
    (2, 'SQL Server'),
    (3, 'MySQL'),
    (4, 'H2 Database'),
    (5, 'REDIS');

CREATE TABLE judge (
    `id`              int auto_increment primary key,
    `url`             nvarchar(256) UNIQUE ,
    `password`        nvarchar(32),
    `judge_type_id`   int,
    FOREIGN KEY (judge_type_id) REFERENCES `judge_type`(id)
);

CREATE TABLE testcase(
    `id`             int auto_increment primary key,
    `label`          nvarchar(32),
    `abstract`       text,
    `content`        text,
    `judge_type_id`  int,
    FOREIGN KEY (judge_type_id) REFERENCES `judge_type`(id)
);

insert into sqloj.testcase (id, label, abstract, content, judge_type_id)
values  (1, '空表', '', '', 5),
        (2, '学生表', 'CREATE TABLE s(
    snum nvarchar(66),
    sname nvarchar(65)
);', 'INSERT INTO s VALUES
(''10086'', ''aa''),
(''10085'', ''bb''),
(''201305010101'', ''gg'');', 5),
        (3, '全校学生表', 'CREATE TABLE [dbo].[s](
	[snum] [nchar](14) NOT NULL,
	[sname] [nvarchar](50) NOT NULL,
	[ssex] [nchar](1) NOT NULL,
	[dnum] [nchar](4) NULL,
 CONSTRAINT [PK_s] PRIMARY KEY CLUSTERED
(
	[snum] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

ALTER TABLE [dbo].[s]  WITH CHECK ADD  CONSTRAINT [CK_s] CHECK  (([ssex]=''男'' OR [ssex]=''女''))

CREATE TABLE [dbo].[c](
	[cnum] [nchar](12) NOT NULL,
	[cname] [nvarchar](50) NULL,
	[credit] [numeric](5, 1) NULL,
 CONSTRAINT [PK_c] PRIMARY KEY CLUSTERED
(
	[cnum] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

CREATE TABLE [dbo].[sc](
	[snum] [nchar](14) NOT NULL,
	[cnum] [nchar](12) NOT NULL,
	[score] [tinyint] NULL,
 CONSTRAINT [PK_sc] PRIMARY KEY CLUSTERED
(
	[snum] ASC,
	[cnum] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]', '
INSERT INTO [s] ([snum],[sname],[ssex],[dnum])
VALUES
    (''1000000'',''徐娜'',''男'',''6''),
    (''1000001'',''阎丽'',''女'',''3''),
    (''1000002'',''何娜'',''男'',''6''),
    (''1000003'',''沈芳'',''男'',''2''),
    (''1000004'',''余娟'',''男'',''4''),
    (''1000005'',''彭杰'',''女'',''7''),
    (''1000006'',''邹娟'',''女'',''8''),
    (''1000007'',''刘娜'',''女'',''3''),
    (''1000008'',''朱娟'',''男'',''9''),
    (''1000009'',''谢军'',''女'',''7''),
    (''1000010'',''武丽'',''男'',''10''),
    (''1000011'',''傅勇'',''女'',''3''),
    (''1000012'',''熊杰'',''女'',''1''),
    (''1000013'',''余超'',''男'',''8''),
    (''1000014'',''梁军'',''女'',''9''),
    (''1000015'',''赵强'',''男'',''9''),
    (''1000016'',''蔡丽'',''男'',''3''),
    (''1000017'',''高敏'',''男'',''6''),
    (''1000018'',''赖勇'',''女'',''2''),
    (''1000019'',''金超'',''男'',''3''),
    (''1000020'',''丁刚'',''女'',''7''),
    (''1000021'',''韩敏'',''女'',''9''),
    (''1000022'',''吴芳'',''女'',''4''),
    (''1000023'',''贺静'',''男'',''4''),
    (''1000024'',''谢军'',''男'',''9''),
    (''1000025'',''唐艳'',''女'',''7''),
    (''1000026'',''常静'',''男'',''1''),
    (''1000027'',''乔涛'',''女'',''5''),
    (''1000028'',''黄平'',''女'',''9''),
    (''1000029'',''朱艳'',''男'',''4''),
    (''1000030'',''张洋'',''女'',''8''),
    (''1000031'',''姜杰'',''男'',''10''),
    (''1000032'',''汤磊'',''男'',''5''),
    (''1000033'',''夏洋'',''女'',''4''),
    (''1000034'',''白刚'',''男'',''9''),
    (''1000035'',''卢刚'',''男'',''8''),
    (''1000036'',''张秀英'',''男'',''2''),
    (''1000037'',''高刚'',''女'',''5''),
    (''1000038'',''尹敏'',''男'',''6''),
    (''1000039'',''杜秀英'',''女'',''10''),
    (''1000040'',''谭静'',''女'',''7''),
    (''1000041'',''薛娟'',''女'',''10''),
    (''1000042'',''杜磊'',''男'',''2''),
    (''1000043'',''顾敏'',''男'',''6''),
    (''1000044'',''段娟'',''女'',''7''),
    (''1000045'',''蔡洋'',''女'',''2''),
    (''1000046'',''卢静'',''女'',''10''),
    (''1000047'',''张强'',''女'',''6''),
    (''1000048'',''吴刚'',''男'',''6''),
    (''1000049'',''许秀兰'',''男'',''8'');

INSERT INTO [c] ([cnum],[cname],[credit])
VALUES
    (''1'',''Docker'',''3.6''),
    (''2'',''Linux'',''2.8''),
    (''3'',''C++'',''4.9''),
    (''4'',''JavaScript'',''3.2''),
    (''5'',''Kotlin'',''2.1''),
    (''6'',''Css'',''4.8''),
    (''7'',''C'',''3.6''),
    (''8'',''Vue'',''3.5''),
    (''9'',''SQL'',''2.1''),
    (''10'',''TypeScript'',''2.5'');

INSERT INTO [sc] ([snum],[cnum],[score])
VALUES
    (''1000000'',''7'',''68''),
    (''1000001'',''9'',''70''),
    (''1000002'',''6'',''74''),
    (''1000003'',''10'',''65''),
    (''1000004'',''2'',''64''),
    (''1000005'',''7'',''77''),
    (''1000006'',''4'',''85''),
    (''1000007'',''5'',''94''),
    (''1000008'',''6'',''75''),
    (''1000009'',''3'',''80''),
    (''1000010'',''3'',''84''),
    (''1000011'',''3'',''95''),
    (''1000012'',''3'',''69''),
    (''1000013'',''6'',''81''),
    (''1000014'',''7'',''71''),
    (''1000015'',''6'',''87''),
    (''1000016'',''1'',''64''),
    (''1000017'',''9'',''77''),
    (''1000018'',''9'',''94''),
    (''1000019'',''3'',''79''),
    (''1000020'',''7'',''87''),
    (''1000021'',''5'',''69''),
    (''1000022'',''4'',''70''),
    (''1000023'',''7'',''83''),
    (''1000024'',''9'',''93''),
    (''1000025'',''7'',''85''),
    (''1000026'',''7'',''97''),
    (''1000027'',''9'',''65''),
    (''1000028'',''2'',''74''),
    (''1000029'',''3'',''71''),
    (''1000030'',''10'',''72''),
    (''1000031'',''7'',''76''),
    (''1000032'',''8'',''74''),
    (''1000033'',''7'',''63''),
    (''1000034'',''10'',''60''),
    (''1000035'',''9'',''79''),
    (''1000036'',''5'',''95''),
    (''1000037'',''10'',''99''),
    (''1000038'',''9'',''85''),
    (''1000039'',''10'',''84''),
    (''1000040'',''6'',''88''),
    (''1000041'',''7'',''75''),
    (''1000042'',''9'',''62''),
    (''1000043'',''2'',''76''),
    (''1000044'',''5'',''91''),
    (''1000045'',''9'',''74''),
    (''1000046'',''1'',''83''),
    (''1000047'',''2'',''72''),
    (''1000048'',''6'',''80''),
    (''1000049'',''4'',''94'');
', 2);

CREATE TABLE question (
    `id`           int auto_increment primary key,
    `content`      text,
    `answer`       text,
    `testcase_id`  int,
    `label`        nvarchar(64),
    FOREIGN KEY (testcase_id) REFERENCES `testcase`(id)
);

insert into sqloj.question (id, content, answer, testcase_id, label)
values  (1, '1. 姓名为‘gg’学生的基本信息。
2. 查询学号为''10086''的学生的姓名。', 'SELECT * FROM s WHERE sname = ''gg'';

SELECT sname FROM s WHERE snum = ''10086'';', 2, 'SELECT 语句'),
        (2, '输出 $\\pi = 3.141596535...$

> 提示：$\\arccos(-1) = \\pi$', 'SELECT acos(-1);', 1, '计算圆周率'),
        (3, '请计算
$$ \\sum_{n=1}^\\infty \\frac{1}{n(n+1)} $$', 'SELECT ACOS(-1) * ACOS(-1) / 6;', 1, '数论'),
        (4, '![](https://pic1.zhimg.com/v2-09b42c0984429923fc5502d5bf1bf3ee_1440w.jpg?source=172ae18b)', 'SELECT ''154476802108746166441951315019919837485664325669565431700026634898253202035277999'';
    SELECT ''36875131794129999827197811565225474825492979968971970996283137471637224634055579'';
    SELECT ''4373612677928697257861252602371390152816537558161613618621437993378423467772036'';', 1, '小游戏'),
        (5, '### 简单查询

1. 查询 `s` 表中性别为 `女` 的学生信息（学号，姓名, 性别）
2. 查询考试成绩不在 `80 ~ 90` 之间的学生的学号
3. 查询姓 `杜` 的学生的所有信息
4. 将 `sc` 表按 成绩 降序输出', 'SELECT snum, sname, ssex
FROM s
WHERE ssex = ''女'';

SELECT DISTINCT snum
FROM sc
WHERE score NOT BETWEEN 80 AND 90;

SELECT *
FROM s
WHERE sname LIKE ''杜%'';

SELECT *
FROM sc
ORDER BY score DESC;', 3, '单表查询'),
        (6, '1. 查询选修了 SQL 课程的学生的人数
2. 查询平均成绩 `>80` 的课程编号（cnum） 和 平均成绩（average）
', 'SELECT COUNT(DISTINCT snum)
FROM sc
WHERE cnum IN (
                SELECT cnum
                FROM c
                WHERE cname = ''SQL'' );

SELECT cnum, AVG(score) as average
FROM sc
GROUP BY cnum
HAVING AVG(score) > 80;', 3, '聚合函数'),
        (7, '创建学生学号及平均成绩视图(stu_avg) -> (snum, savg)，并查询。', 'CREATE VIEW [stu_avg](snum, savg)
AS
SELECT snum, AVG(score)
FROM sc
GROUP BY snum;

SELECT * FROM [stu_avg];', 3, '视图'),
        (8, '1. 利用事务，输出向学生表中插入一行信息（学号：''1''，姓名：''李华''，性别：''男''，学院编号：''1''）后的学生表，回滚后再输出学生表。', 'BEGIN TRANSACTION ;
INSERT INTO s VALUES (''1'', ''李华'', ''男'', ''1'');
SELECT * FROM s;
ROLLBACK;
SELECT * FROM s;', 3, '事务');

CREATE TABLE record (
    `id`           int auto_increment primary key,
    `user_id`      nvarchar(32),
    `question_id`  int,
    `code`         text,
    `result`       int,
    `time`         datetime
);

CREATE TABLE article (
    `id`               int auto_increment primary key,
    `user_id`          nvarchar(32),
    `title`            nvarchar(50),
    `content`          text,
    `update_time`      datetime
);

insert into sqloj.article (id, user_id, title, content, update_time)
values  (1, 'admin', 'SQL OJ', '# SQL OJ 指南

## 简介

SQL OJ 是一个 SQL 语句在线评判系统，意旨为学生和老师在学习 SQL 语句时带来方便。

## 设计思路

在学习 SQL 语句时，学生一般是找一个数据库或者表，在 DBMS 中新建查询，然后在里面写好代码选中执行。
所以，我们设计了测试集作为题目依赖的数据库，由老师提供测试集，学生可以查看测试集中的建表代码。
由老师提供题目以及得到正确答案的标准程序，学生可以提交自己的代码，我们会将两份代码的运行结果进行比对，返回答题情况。


**答案不能为空，如（`UPDATE, INSERT, DELETE`等语句应当将更新后的表 `SELECT` 出来作为答案）**

项目主要分成三个部分：前端，后端，测评端

### 前端

负责页面设计，我们提供了**编辑器（monaco editor）**，**Markdown 支持**，**代码高亮**，**多表展示**，**数据生成器**等功能。

### 后端

负责管理系统，提供**连接测判端**，**判题**，**表格解析**等功能。

### 测评机
我们独立出来了测评端的理由

1. 由于数据库种类繁多，支持更多的数据库
2. 防止 SQL 注入对后端产生影响
3. 支持分散的测评机

测评端主要负责利用 JDBC**执行代码**，**解析返回值**， **抓取报错**等功能。

', '2022-06-13 06:30:59.0');

CREATE VIEW article_detail AS
SELECT
    article.id,
    article.user_id,
    `user`.username,
    `user`.signature,
    article.title,
    article.content,
    article.update_time
FROM `article`
LEFT JOIN `user` on article.user_id = `user`.id;

CREATE VIEW student_detail AS
SELECT
    `user`.id,
    `user`.username,
    `user`.department,
    `user`.signature,
    `student`.submit_num,
    `student`.pass_num,
    `user`.role
FROM student
LEFT JOIN `user` on student.user_id = `user`.id;


CREATE VIEW question_detail AS
SELECT question.id,
       question.content,
       question.answer,
       question.testcase_id,
       question.label,
       testcase.abstract as testcase_acstract,
       testcase.content  as testcase_content,
       testcase.label as testcase_label,
       testcase.judge_type_id,
       judge_type.name   as type_name
FROM question
LEFT JOIN testcase on question.testcase_id = testcase.id
LEFT JOIN judge_type on testcase.judge_type_id = judge_type.id;

DELIMITER ;;

CREATE TRIGGER `record_add`
    AFTER INSERT ON `record` FOR EACH ROW
BEGIN
    DECLARE `stu_role` INT;
    SELECT `role` INTO `stu_role`
        FROM `user`
        WHERE `user`.id = NEW.user_id;
    IF `stu_role` = 1 THEN
        UPDATE `student`
        SET `submit_num` = `submit_num` + 1
        WHERE `student`.user_id = NEW.user_id;
    END IF;
END;;

CREATE PROCEDURE proc_update_ac(
    IN user_id nvarchar(32)
)
BEGIN
    DECLARE ac_count INT;
    SELECT count(DISTINCT question_id) INTO ac_count
    FROM record
    WHERE
        result = 1
        and record.user_id = user_id;

    UPDATE student
    SET pass_num = ac_count
    WHERE user_id = student.user_id;
END;;


CREATE TRIGGER record_update
    AFTER UPDATE ON record FOR EACH ROW
BEGIN
    DECLARE stu_role INT;
    SELECT `role` INTO stu_role FROM `user` WHERE `user`.id = NEW.user_id;
    IF `stu_role` = 1 THEN
        CALL proc_update_ac(NEW.user_id);
    END IF;
END;;

DELIMITER ;
