<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body>
<h1>Pay화면</h1>
<div class="itemSection">
	<div class="itemCard">
		<div class="itemTitle">
			<h2>헬창을 위한 근육보충제</h2>
		</div>
		<div class="itemContent">
			<h2>맛없지만 단백질 보충이 됩니다.</h2>
		</div>
		<div class="itemPrice">
			<p data-price=4000>4000원</p>
		</div>
		<div class="itemButton">
			<button class="orderBtn">주문하기</button>
		</div>
	</div>
	
	<div class="itemCard">
		<div class="itemTitle">
			<h2>개발자를 위한 키보드</h2>
		</div>
		<div class="itemContent">
			<h2>타건감이 죽여주는 키보드</h2>
		</div>
		<div class="itemPrice">
			<p data-price=5000>5000원</p>
		</div>
		<div class="itemButton">
			<button class="orderBtn">주문하기</button>
		</div>
	</div>
	
	<div class="itemCard">
		<div class="itemTitle">
			<h2>개발자를 Python</h2>
		</div>
		<div class="itemContent">
			<h2>초보 개발자 탈출을 위한 지침서</h2>
		</div>
		<div class="itemPrice">
			<p data-price=1000>1000원</p>
		</div>
		<div class="itemButton">
			<button class="orderBtn">주문하기</button>
		</div>
	</div>
	
	<div class="itemCard">
		<div class="itemTitle">
			<h2>데이터베이스 타파</h2>
		</div>
		<div class="itemContent">
			<h2>DBA가 되기 위한 길</h2>
		</div>
		<div class="itemPrice">
			<p data-price=2000>2000원</p>
		</div>
		<div class="itemButton">
			<button class="orderBtn">주문하기</button>
		</div>
	</div>
</div>
<script>
/* function iamport() {
	IMP.init('imp95189136');					// 가맹점번호(본인 아이디에 입력된 번호로)
	IMP.request_pay({
		pg	:	'html5_inicis',					// KG이니시스
		pay_method	:	'card',					// 결제수단
		merchant_uid:	"order_no_0001",		// 상점에서 관리하는 주문 번호를 전달
		name	:	'주문형:결제테스트',				// 결제창에 뜰 상품명
		amount	:	1400,						// 금액
		buyer_email	:	'wakebro119@naver.com',	// 구매자 이메일
		buyer_name	:	'형기상',					// 구매자 이름
		buyer_tel	:	'010-1234-5678',		// 구매자 번호
		buyer_addr	:	'서울시 은평구 응암동',		// 구매자 주소
		buyer_postcode	:	'123-456'			// 구매자 우편번호
	}, function (rsp) {
		console.log(rsp);
		if(rsp.success){						//	결제 성공 시 처리량 내역
			var msg = '결제가 완료되었습니다.';
			msg += '고유 ID : ' + rsp.imp_uid;
			msg += '상점 거래ID' + rsp.merchant_uid;
			msg += '결제 금액 : ' + rsp.paid_amount;
			msg += '카드 승인번호 : ' + rsp.apply_num;
		} else {								// 결제 실패시 처리할 내역
			var msg = '결제에 실패하였습니다.';
			msg += '에러내용 : ' + rsp.error_msg;
		}
		alert(msg);	// 여기서는 alert창만 띄우고 끝나지만 리다이렉트로 등의 방법이 있다.
	});
} */
function iamport() {
	IMP.init('imp95189136');					// 가맹점번호(본인 아이디에 입력된 번호로)
	IMP.request_pay({
		pg	:	'html5_inicis',					// KG이니시스
		pay_method	:	'card',					// 결제수단
		merchant_uid:	merchant_uid,			// 상점에서 관리하는 주문 번호를 전달
		name	:	itemTitle,					// 결제창에 뜰 상품명
		amount	:	itemPrice,						// 금액
		buyer_email	:	'wakebro119@naver.com',	// 구매자 이메일
		buyer_name	:	'형기상',					// 구매자 이름
		buyer_tel	:	'010-1234-5678',		// 구매자 번호
		buyer_addr	:	'서울시 은평구 응암동',		// 구매자 주소
		buyer_postcode	:	'123-456'			// 구매자 우편번호
	}, function (rsp) {
		console.log(rsp);
		if(rsp.success){						//	결제 성공 시 ajax로 DB에 데이터를 전송해 입력
			$.ajax({
				type:	'post',
				url	:	'/order',
				headers:	{
					"Content-Type":"application/json",
					"X-HTTP-Method-Override":"POST"
				},
				dataType:	'text',
				data	:	JSON.stringify({
					itemname	:	itemTitle,
					amount		:	itemPrice,
					merchant_uid:	merchant_uid
				}),
				success	:	function () {
					alert(itemTitle + "결제완료!");
				}
			});
		} else {								// 결제 실패시 처리할 내역
			var msg = '결제에 실패하였습니다.';
			msg += '에러내용 : ' + rsp.error_msg;
			alert(msg);	// 여기서는 alert창만 띄우고 끝나지만 리다이렉트로 등의 방법이 있다.
		}
	});
}

	// 미리 받아와야 할 정보의 변수를 전역 변수처럼 쓰기 위해 선언해두기
	var itemPrice = 0;	// 가격
	var itemTitle = "";	// 물건 이름
	var merchant_uid = "";	// 주문 번호
	// 위임처리로 어떤 상품을 클릭했을 때 그 상품에 대한 정보
	$(".itemSection").on("click", ".orderBtn", function () {
		itemPrice = $(this).parent().siblings(".itemPrice").children().attr("data-price");
		itemTitle = $(this).parent().siblings(".itemTitle").children().text();
		d = new Date();	// 현 시간
		merchant_uid = "order" + d.getTime();	// 현 시간을 유닉스시간으로 변경해 order에 붙임
		// 입력 정보를 가져오고 나서 함수 호출
		iamport();	// 실제로 모듈 호출하기		
	});
	
</script>
</body>
</html>