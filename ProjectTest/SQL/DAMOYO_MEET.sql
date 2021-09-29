-- 모임방 생성
/*
CREATE TABLE meet (
    m_num NUMBER PRIMARY KEY NOT NULL,
    m_name VARCHAR2(100) NOT NULL,
    m_content VARCHAR2(500) NOT NULL,
    m_people_cnt NUMBER DEFAULT 0,
    m_area VARCHAR2(50) NOT NULL,
    m_profile VARCHAR2(1000),
    i_cate_num NUMBER(3) NOT NULL,
    u_id varchar2(100) NOT NULL,
    CONSTRAINT meet_i_cate_fk FOREIGN KEY (i_cate_num) REFERENCES interest_category(i_cate_num),
    CONSTRAINT meet_u_id_fk FOREIGN KEY (u_id) REFERENCES user_info(u_id)
);
*/

INSERT INTO 
meet(m_num, m_name, m_content, m_people_cnt, m_area, m_profile, i_cate_num, u_id)
VALUES(MEET_SEQ.nextval, '한강 러닝', '안녕하세요. 한강에서 러닝할 사람들의 모임입니다.', 20, '마포구', null, 1, 'wake');

INSERT INTO 
meet(m_num, m_name, m_content, m_people_cnt, m_area, m_profile, i_cate_num, u_id)
VALUES(MEET_SEQ.nextval, '삭제', '안녕하세요. 한강에서 러닝할 사람들의 모임입니다.', 20, '마포구', null, 1, 'wake');

SELECT * FROM meet;

SELECT meet.*, interest_category.i_cate_name FROM meet INNER JOIN interest_category 
    ON meet.i_cate_num=interest_category.i_cate_num WHERE m_num=3;

DELETE FROM meet WHERE m_num=4;

UPDATE meet SET m_name='홍대신촌 댄스', m_content='홍대와 신촌에 있는 방송댄스', m_people_cnt=40, m_area='서대문구',i_cate_num=7, m_profile=null
WHERE m_num=2;


-- 모임방 가입
SELECT * FROM meet_member_list WHERE m_num=2;

SELECT user_info.u_profile,user_info.u_name,user_info.u_intro, meet_member_list.* 
FROM meet_member_list INNER JOIN user_info 
ON meet_member_list.u_id=user_info.u_id WHERE m_num=1;

-- 모임방 탈퇴
DELETE meet_member_list WHERE member_list_num=4;


COMMIT;