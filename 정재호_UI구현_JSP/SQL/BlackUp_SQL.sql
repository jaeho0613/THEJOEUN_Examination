drop database if exists blackup;
create database BlackUp;
use BlackUp;

create table Orders(
	odID int primary key auto_increment comment '주문 번호',
    userID varchar(20) not null comment '유저 아이디',
    pdID int not null comment '상품 번호',
    pdName varchar(64) not null comment '상품 이름',
    pdPrice int not null comment '상품 금액',
    Color varchar(20) comment '상품 색상',
    Size varchar(10) comment'상품 사이즈'
);

create table User(
	userID varchar(20) not null comment '유저 아이디',     
	userRating int not null comment '유저 등급',
	userPassword varchar(45) not null comment '유저 비밀번호',
	userPasswordHash varchar(120) not null comment '유저 보안 비밀번호',
	userName varchar(20) not null comment '유저 이름',
	userAddress varchar(64) not null comment '유저 주소',
	userPhone varchar(11) not null comment '유저 핸드폰 번호',
	userSex varchar(10) not null comment '유저 성별',
	primary key(userID)
);

create table Product(
	pdID int not null comment '상품 번호',
	pdPrice int not null comment '상품 금액',
	pdName varchar(64) not null comment '상품 이름',
	pdGPA5 int, 
	pdGPA4 int,
	pdGPA3 int,
	pdGPA2 int,
	pdGPA1 int,
	primary key(pdID)
);

create table SizeSet(
	pdID int not null comment '상품 번호',
	Size varchar(10) comment'상품 사이즈'
);

create table ColorSet(
	pdID int not null comment '상품 번호',
	Color varchar(20) comment '상품 색상',
    ColorCode varchar(20) comment '색상 HTML 코드'
);

create table ImagePath(
	pdID int not null comment '상품 번호',
	ImgPath varchar(200) not null comment '상품 이미지 경로',
	ImgName varchar(45) not null comment '상품 이미지 이름'
);

create table Category(
	pdID int not null comment '상품 번호',
	cgName varchar(20) not null comment '상품 카테고리 이름',
	cgType varchar(45) not null comment '상품 카테고리 타입'
);