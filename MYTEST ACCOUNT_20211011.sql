-- 기본 로직
CREATE TABLE users(
    username varchar2(50) not null primary key,
    password varchar(100) not null,
    enabled char(1) default '1'
);

CREATE TABLE authorities(
    username varchar2(50) not null,
    authority varchar(100) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);

create unique index ix_auth_username on authorities (username, authority);

insert into users(username, password) values('user00', 'pw00');
insert into users(username, password) values('member00', 'pw00');
insert into users(username, password) values('admin00', 'pw00');

insert into authorities (username, authority) values('user00', 'ROLE_USER');
insert into authorities (username, authority) values('member00', 'ROLE_MANAGER');
insert into authorities (username, authority) values('admin00', 'ROLE_MANAGER');
insert into authorities (username, authority) values('admin00', 'ROLE_ADMIN');

select * from users;
select * from authorities;


-- custom
CREATE TABLE member_tbl(
    userid VARCHAR2(50) NOT NULL PRIMARY KEY,
    userpw VARCHAR2(100) NOT NULL,
    username VARCHAR2(100) NOT NULL,
    regdate DATE DEFAULT SYSDATE,
    updatedate DATE DEFAULT SYSDATE,
    enabled CHAR(1) DEFAULT '1'
);

CREATE TABLE member_auth(
    userid VARCHAR2(50) NOT NULL,
    auth VARCHAR2(50) NOT NULL,
    CONSTRAINT fk_member_auth FOREIGN KEY(userid) REFERENCES member_tbl(userid)
);
select * from member_tbl;
select * from member_auth;

SELECT member_tbl.*, member_auth.auth 
FROM member_tbl RIGHT OUTER JOIN member_auth
ON member_tbl.userid = member_auth.userid;

commit;