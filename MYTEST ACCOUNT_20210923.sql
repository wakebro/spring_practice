-- 페이지네이션

-- 기존에 들어가 있던 데이터 2배씩 늘려주는 쿼리문 작성
INSERT INTO board_tbl(bno, title, content, writer)
    (SELECT board_num.nextval, title, content, writer FROM board_tbl);

-- LIMIT를 못쓰는 Oracle의 경우
/*
    오라클 쿼리문의 조회할 때 index라고 불리는
    특수한 주소값을 조회하도록 옵션을 바꾼다.
    힌트 구문은 아래 주석처럼 쓰되, 여는 부분에 +를 추가하여 붙인다.
    힌트는 FULL, INDEX_DESC, INDEX_ASC 등이 있다
*/
SELECT * FROM board_tbl ORDER BY bno DESC;
SELECT 
    /*+ INDEX_DESC(board_tbl pk_board)*/
* FROM board_tbl;

SELECT ROWNUM, ROWID, bno FROM board_tbl;

SELECT ROWNUM, b1.* FROM 
    (SELECT /*+ INDEX_DESC(board_tbl pk_board*/
        ROWNUM rn, board_tbl.*
        FROM board_tbl WHERE ROWNUM <= 20)b1
    WHERE rn>10 AND rn<=15;

COMMIT;