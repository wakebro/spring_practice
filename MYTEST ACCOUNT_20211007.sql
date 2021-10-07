-- 댓글 갯수를 게시판 글에 표시해주는 컬럼
ALTER TABLE board_tbl ADD(replycnt NUMBER DEFAULT 0);

-- 현재 엮인 댓글을 계산해서 replycnt에 입력해주는 쿼리문
UPDATE board_tbl SET replycnt = 
    (SELECT COUNT(*) FROM reply_tbl 
    WHERE reply_tbl.bno = board_tbl.bno);

SELECT * FROM board_tbl ORDER BY replycnt DESC;
COMMIT;