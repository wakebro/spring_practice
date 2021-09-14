/* Oracle은 auto_increment가 없으므로
board_num이라는 시퀀스를 만들면 처음에 0이 저장된다.
이후 primary key가 들어갈 자리에
board_num.nextval이라고 기입하면
실행할 때 마다 1씩 증가된 새로운 값을 그 위치에 넣는다.
*/
CREATE SEQUENCE board_num;

CREATE TABLE board_tbl (
    bno NUMBER(10, 0),
    title   VARCHAR2(200) NOT NULL,
    content VARCHAR2(2000) NOT NULL,
    writer VARCHAR2(50) NOT NULL,
    regdate DATE DEFAULT SYSDATE,
    updatedate DATE DEFAULT SYSDATE
);

ALTER TABLE board_tbl ADD CONSTRAINT pk_board PRIMARY KEY(bno);

INSERT INTO board_tbl(bno, title, content, writer) VALUES(board_num.NEXTVAL, '테스트글1', '테스트1본문', '유저1');
INSERT INTO board_tbl(bno, title, content, writer) VALUES(board_num.NEXTVAL, '테스트글2', '테스트2본문', '유저1');
INSERT INTO board_tbl(bno, title, content, writer) VALUES(board_num.NEXTVAL, '테스트글3', '테스트3본문', '유저2');
INSERT INTO board_tbl(bno, title, content, writer) VALUES(board_num.NEXTVAL, '테스트글4', '테스트4본문', '유저2');
INSERT INTO board_tbl(bno, title, content, writer) VALUES(board_num.NEXTVAL, '테스트글5', '테스트5본문', '유저3');

SELECT * FROM board_tbl;
SELECT * FROM board_tbl WHERE bno<3 ORDER BY bno DESC;
DELETE TABLE board_tbl WHERE bno=21;

COMMIT;