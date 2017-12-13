<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- semantic-ui -->
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.css">
<base>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
<style type="text/css">
.wrap {
	width: 100%;
	height: 100%;
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: center;
}

.container {
	width: 1500px;
	height: 900px;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
 	border: red solid 1px; 
}

.container>div {
	width: 90%;
}

.nav {
	display: flex;
	flex-direction: row;
	justify-content: space-between;
 	border: blue solid 1px; 
}

.con_head {
	display: flex;
	flex-direction: row;
	justify-content: space-around;
	align-items: center;
 	border: red solid 1px;
}

.con_head div:nth-child(1) {
	width: 80%;
}

.con_head div:nth-child(2) {
	width: 10%;
}

.con_head div:nth-child(3) {
	width: 10%;
}

.padding_10 {
	padding: 10px;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<div class="wrap">
		<div class="container">
			<div class="nav">
				<div class="two_per_one">
					<button class="ui button">수정</button>
					<button class="ui button">삭제</button>
				</div>
				<div class="two_per_two">
					<button class="ui button">글목록</button>
				</div>
			</div>
			<div class="column con_head">
				<div class="ui medium header" style="margin: 0px">제목</div>
				<div class="sub header">날짜</div>
				<div class="sub header">닉네임</div>
			</div>
			<div class="con_body">
				내~용
			</div>
			<div class="padding_10"></div>
			<div class="sec_comment">
				<div class="ui minimal comments">
					<div class="ui form" id="comment_form">
						<div class="field">
							<input type="text" name="comment" id="comment" placeholder="악플 달지 맙시다." required="required">
							<button class="ui button square" onclick="write_comment();">댓글 등록</button>
						</div>
					</div>
					<c:forEach begin="1" end="5">
						<div class="comment">
							<div class="content">
								<a class="author">Matt</a>
								<div class="metadata">
									<span class="date">Today at 5:42PM</span>
								</div>
								<div class="text">How artistic!</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>			
		</div>
	</div>
</body>
</html>