INSERT INTO tb_member(memID, name, pwd, phone) values(1,1,1,1)

create table tb_member(
	memID varchar(20) not null,
	name varchar(30) not null,
	pwd varchar(20) not null,
	phone varchar(20) not null
)

drop table tb_member
select * from tb_member
delete from tb_member