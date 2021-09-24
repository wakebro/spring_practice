SELECT * FROM board_tbl ORDER BY bno DESC;
SELECT 
    /*+ INDEX_DESC(board_tbl pk_board)*/
* FROM board_tbl;

-- ROWNUM, ROWID 사용
SELECT 
/*+ INDEX_DESC(board_tbl pk_board)*/
ROWNUM, ROWID, board_tbl.* FROM board_tbl; -- *은 어떤 테이블인지 모호하기 때문에 확실히 정해준다.
-- 출력이 다 된 후 ROWID > ROWNUM 순으로 컬럼이 정해진다

-- 20개의 데이터만 출력
SELECT /*+INDEX_DESC(board_tbl pk_board) */
ROWNUM rn, board_tbl.* FROM board_tbl WHERE ROWNUM <= 20;

-- 서브쿼리로 뽑은 20개의 데이터 중 ROWNUM 11번 이상 데이터 출력
SELECT ROWNUM, tbl.* FROM
(SELECT /*+INDEX_DESC(board_tbl pk_board) */
ROWNUM rn, board_tbl.* FROM board_tbl WHERE ROWNUM <= 20) tbl
WHERE rn >10;