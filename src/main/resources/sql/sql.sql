drop table if EXISTS USER_INFO;
create table USER_INFO(
	id  int auto_increment primary key,
	userNo varchar(100),
	name varchar(100),
	password varchar(200),
	createTime datetime
)