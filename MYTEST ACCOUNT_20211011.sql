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
commit;