-- ȯ�ἳ������ ��¥ ���� ���� ��
-- �⺻ ���� ���
DESC user_info;
INSERT INTO user_info(u_id, u_pw, u_name, u_sex, u_birth, u_area, u_intro, u_profile) 
    VALUES('wake', '1111', '�����', 0, TO_DATE('1993-01-19 00:00:00','YYYY-MM-DD HH24:MI:SS'),'����', '�ȳ��ϼ���', null);
    
SELECT * FROM user_info;


-- ���ɻ� ���
DESC interest_category;
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '�ƿ�����/����');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '�/������');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '�ι���/å/��');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '�ܱ�/���');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '��ȭ/����/����');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '����/�Ǳ�');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '����/�����');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '��/����');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '����Ȱ��');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '�米/�θ�');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '��/�������');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '����/����');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '�߱�����');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '����/����');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '�丮/����');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '�ݷ�����');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '����/��ȥ');
INSERT INTO interest_category(I_CATE_NUM, I_CATE_NAME)
    VALUES(INTEREST_CATEGORY_SEQ.nextval, '��������');

SELECT * FROM interest_category;


-- �� ���ɻ�
SELECT * FROM interest_detail;
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '������', 1);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '������', 1);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '����', 1);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '�״Ͻ�', 1);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '��Ű/����', 1);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '����', 1);
    
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '���', 0);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '��å/Ʈ��ŷ', 0);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, 'ķ��/����ŷ', 0);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '��������', 0);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '�ؿܿ���', 0);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '����', 0);

INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '��ȥ/����', 16);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '����/��', 16);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '�ƺ�/���', 16);

INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, 'DSLR', 11);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '�ʸ�ī�޶�', 11);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '��������', 11);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '������ī�޶�', 11);

INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '����', 3);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '�Ϻ���', 3);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '�߱���', 3);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '��������', 3);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '�����ξ�', 3);
INSERT INTO interest_detail(I_DETAIL_NUM, I_DETAIL_NAME, I_CATE_NUM) 
    VALUES(INTEREST_DETAIL_SEQ.nextval, '���þƾ�', 3);

SELECT * FROM interest_detail ORDER BY i_cate_num;




































COMMIT;