create table customer_ex (
	num  int  not  null  auto_increment,
	cusCode   varchar(20),
	cusName   varchar(20),	
	licenseNum varchar(20),
	cusRep   varchar(10),
	cusNumber varchar(20),
	cusAddress varchar(200),
	reg_date   datetime,
	primary key (num)
);
select *from customer_ex
select max(num) from customer_ex

delete from customer_ex


create table board (
	num  int  not  null  auto_increment,
	writer   varchar(20),
	email   varchar(30),	
	subject   varchar(100),	
	passwd   varchar(10),
	reg_date   datetime,
	readcount  int,
	ref   int,
	re_step   int,
    re_level  int,
	content    text,
    ip   varchar(20),
	primary key (num)
);

drop table customer_ex

select *from board
