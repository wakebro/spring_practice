-- 환결설정에서 날짜 포맷 변경 후
-- 기본 유저 등록
DESC user_info;
INSERT INTO user_info(u_id, u_pw, u_name, u_sex, u_birth, u_area, u_intro, u_profile) 
    VALUES('wake', '1111', '형기상', 0, TO_DATE('1993-01-19 00:00:00','YYYY-MM-DD HH24:MI:SS'),'은평구', '안녕하세요', null);
    
SELECT * FROM user_info;


-- 관심사 등록
DESC interest_category;
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '아웃도어/여행');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '운동/스포츠');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '인문학/책/글');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '외국/언어');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '문화/공연/축제');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '음악/악기');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '공예/만들기');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '댄스/무용');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '봉사활동');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '사교/인맥');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '차/오토바이');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '사진/영상');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '야구관람');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '게임/오락');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '요리/제조');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '반려동물');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '가족/결혼');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '자유주제');

SELECT * FROM interest_category;


-- 상세 관심사
SELECT * FROM interest_detail;
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '자전거', 1);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '배드민턴', 1);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '볼링', 1);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '테니스', 1);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '스키/보드', 1);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '골프', 1);
    
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '등산', 0);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '산책/트래킹', 0);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '캠핑/백패킹', 0);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '국내여행', 0);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '해외여행', 0);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '낚시', 0);

INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '결혼/웨딩', 16);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '육아/맘', 16);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '아빠/대디', 16);

INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, 'DSLR', 11);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '필름카메라', 11);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '영상제작', 11);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '디지털카메라', 11);

INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '영어', 3);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '일본어', 3);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '중국어', 3);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '프랑스어', 3);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '스페인어', 3);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '러시아어', 3);

SELECT * FROM interest_detail ORDER BY i_cate_num;




































COMMIT;