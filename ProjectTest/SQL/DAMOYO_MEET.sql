-- ���ӹ� ����
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
VALUES(MEET_SEQ.nextval, '�Ѱ� ����', '�ȳ��ϼ���. �Ѱ����� ������ ������� �����Դϴ�.', 20, '������', null, 1, 'wake');

INSERT INTO 
meet(m_num, m_name, m_content, m_people_cnt, m_area, m_profile, i_cate_num, u_id)
VALUES(MEET_SEQ.nextval, '����', '�ȳ��ϼ���. �Ѱ����� ������ ������� �����Դϴ�.', 20, '������', null, 1, 'wake');

SELECT * FROM meet;

SELECT meet.*, interest_category.i_cate_name FROM meet INNER JOIN interest_category 
    ON meet.i_cate_num=interest_category.i_cate_num WHERE m_num=3;


    


COMMIT;