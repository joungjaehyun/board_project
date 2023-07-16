## 테이블 생성
create table tbl_board1(
	bno int auto_increment primary key,
	title varchar(100) not null,
	content varchar(1000) not null,
	writer varchar(50) not null,
	regdate timestamp default now(),
	moddate timestamp default now()
)
;
## 1개의 로우값 넣는 쿼리
insert into tbl_board1 (title,content,writer) values (#{title},#{content},#{writer})

## 더미데이터 넣기 
insert into tbl_board1 (title,content,writer) (select title,content,writer from tbl_board1);

## select 쿼리
select * from tbl_board1 order by bno desc;
## select 1개의 객체
select * from tbl_board1 where bno= #{bno}

## update 하는 쿼리

update tbl_board1 set 
title = concat(title, (bno%10)),
content = concat(content, (bno%10)),
writer = concat(writer,(bno%10))
where bno = #{bno}
;

# delete 하는 쿼리
delete from tbl_board1 where bno =#{bno}

select * from t_board2 tb 


select b.bno ,b.title, b.writer , count(r.rno) 
from t_board2 b left join t_reply2 r on r.board_bno = b.bno 
group by b.bno order by b.bno desc 	

select * from t_reply2 tr 


## 댓글 관련
create table tbl_reply1(

	rno int auto_increment primary key,
	bno int,
	reply varchar(500) not null,
	replyer varchar(300) not null,
	replyDate dateTime default now(),
	gno int default 0
)

## data start

insert into tbl_reply1 (bno, reply,replyer)

(

select bno,

concat('Reply...',tno) reply,

concat('user',mod(tno,10)) replyer

from tbl_board1 order by bno desc limit 50,100

)

;

## 재귀복사

insert into tbl_reply1 (tno, reply,replyer)

(select tno,reply,replyer from tbl_reply1);

select * from tbl_reply1

where bno >0 and rno > 0

order by bno desc, rno asc;


select * from tbl_board1 tb ;

alter table tbl_board1 add viewCnt int default 0


### 파일 입출력관련 
create table tbl_board1_image (
uuid varchar(50) primary key,
fileName varchar(200) not null,
bno int not null,
ord int default 0
)
;


## uuid함수 확인 oracle은 없음

SELECT UUID();

## pno에 맞는 더미데이터 넣기

insert into tbl_board1_image (uuid,filename,pno, ord)
values(uuid(),'fi.jpg',3315,0)

insert into tbl_board1_image (uuid,filename,pno, ord)
values(uuid(),'f2.jpg',3315,0)

insert into tbl_board1_image (uuid,filename,pno, ord)
values(uuid(),'f3.jpg',3313,0)

insert into tbl_board1_image (uuid,filename,pno, ord)
values(uuid(),'f4.jpg',3313,1)

## 확인

select * from tbl_board1_image

## Join 전통적인

select
b.bno, b.pname,  concat(pi.uuid,'_',pi.filename) fileName
from tbl_board1 b
left outer join tbl_board1_image bi on bi.pno = p.pno
where bi.ord = 0 or bi.ord is null

order by b.bno desc
limit 0, 10
;

## index 생성

create index idx_board1_image1 on tbl_board1_image (pno desc, ord asc);

## 조회후 ajax로 이미지를 가져오는방법

## 조회할때 한번에 가져오는방법

select *
from tbl_board b

## mySQL은 limit조건이 맨아래에 있어야된다.

## 부분범위 처리기법 join의 select로 범위를 줄여놓고 조인한다

select *
from
(select * from tbl_board1 b where bno>0 order by bno desc limit 0, 10) b2
left outer join tbl_board1_image bi on bi.pno = b2.bno
where bi.ord = 0 or bi.ord is null
order by b2.bno desc
;


## member

## cookie DB

create table persistent_logins (
       username varchar(64) not null,
       series varchar(64) primary key,
       token varchar(64) not null,
       last_used timestamp not null
)
;

## 멤버테이블 생성

create table tbl_memeber (
  email varchar(100) primary key,
  mpw varchar(100) not null,
  mname varchar(100) not null
)
;

## 권한 테이블 생성
create table tbl_member_role (
  email varchar(100) not null,
  rolename varchar(50) not null
)
;

## 더미 데이터 넣기
insert into tbl_memeber (email, mpv, mname) values (#(email), #(mpw), #(mname))

insert into tbl_member_role (email, rolename) values(#(email), #(rolename))


select * from tbl_memeber tm ;
select * from tbl_member_role tmr ;


## join
select * from tbl_memeber tm inner join tbl_member_role tmr on tmr.email = tm.email where tm.email = "test2gmail.com";
