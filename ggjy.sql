create database guigujingying;
create table menu(
       id int(2) not null primary key,
       name varchar(30) default null
)default charset=utf8;

create table `submenu`(
       `id` int(2) not null primary key,
       `name` varchar(30) default null,
       `parentId` int(2) not null,
       foreign key(parentId) references menu(id)

)default charset=utf8;

create table `post`(
       `id` int(11) not null primary key,
       `title` varchar(100) default null,
       `time` datetime,
       `author` varchar(20) default null,
       `editor` varchar(20) default null,
       `content` varchar(2000) default null,
       `parentId` int(2) not null,
       foreign key(parentId) references submenu(id)
)default charset=utf8;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(10) NOT NULL,
  `userName` varchar(10) NOT NULL,
  `password` varchar(20) NOT NULL,
  `phoneNumber` varchar(11) NOT NULL,
  `qq` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into menu(id,`name`) values (1,'guiGuZiXun');
insert into menu(id,`name`) values (2,'gaoXinJiShu');
insert into menu(id,`name`) values (3,'xiaoYuanGuiGu');
insert into menu(id,`name`) values (4,'chuangYeXianFeng');
insert into menu(id,`name`) values (5,'guanLiZhiDao');
insert into menu(id,`name`) values (6,'chuangYePingTai');
insert into menu(id,`name`) values (7,'chuangYeZiYuan');

insert into submenu(id,`name`,parentId) values (1,'guiGuYaoWen',1);
insert into submenu(id,`name`,parentId) values (1,'guiGuZhuanQu',1);
insert into submenu(id,`name`,parentId) values (1,'IT',2);
insert into submenu(id,`name`,parentId) values (1,'tongXin',2);
insert into submenu(id,`name`,parentId) values (1,'qiChe',2);
insert into submenu(id,`name`,parentId) values (1,'jiaDian',2);
insert into submenu(id,`name`,parentId) values (1,'shengWuYiYao',2);
insert into submenu(id,`name`,parentId) values (1,'xinCaiLiao',2);
insert into submenu(id,`name`,parentId) values (1,'xinNengYuan',2);
insert into submenu(id,`name`,parentId) values (1,'keJiChuangXin',3);
insert into submenu(id,`name`,parentId) values (1,'xueZiJingJi',3);
insert into submenu(id,`name`,parentId) values (1,'gaoXiaoPingTaiZhanShi',3);
insert into submenu(id,`name`,parentId) values (1,'chuangYeTuanDui',3);
insert into submenu(id,`name`,parentId) values (1,'chuangYeZhiXing',3);
insert into submenu(id,`name`,parentId) values (1,'yeJieJingYing',4);
insert into submenu(id,`name`,parentId) values (1,'chuangYeXinXiu',4);
insert into submenu(id,`name`,parentId) values (1,'chuangYeXinDe',4);
insert into submenu(id,`name`,parentId) values (1,'guanLiLiLun',5);
insert into submenu(id,`name`,parentId) values (1,'qiYeZhanLue',5);
insert into submenu(id,`name`,parentId) values (1,'chuangYeGuanLi',5);
insert into submenu(id,`name`,parentId) values (1,'chuangYeWenHua',5);
insert into submenu(id,`name`,parentId) values (1,'shuJiTuiJian',5);
insert into submenu(id,`name`,parentId) values (1,'renCaiTuiJie',6);
insert into submenu(id,`name`,parentId) values (1,'chuangYeRongZi',6);
insert into submenu(id,`name`,parentId) values (1,'jiShuTuiGuang',6);
insert into submenu(id,`name`,parentId) values (1,'gaoXinYuanQu',7);
insert into submenu(id,`name`,parentId) values (1,'fuHuaQi',7);
insert into submenu(id,`name`,parentId) values (1,'teSeChanYeJiDi',7);
insert into submenu(id,`name`,parentId) values (1,'chuangYeJiJin',7);
insert into submenu(id,`name`,parentId) values (1,'chuangYeZhengCe',7);