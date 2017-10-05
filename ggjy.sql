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