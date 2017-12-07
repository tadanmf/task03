<%@page import="com.task03.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
/* 	border: red solid 1px; */
}

.head {
	width: 100%;
	height: 70px;
	display: flex;
	justify-content: flex-end;
/* 	border: orange solid 1px; */
}

.contents {
	width: 100%;
	height: 830px;
	display: flex;
	flex-direction: row;
	justify-content: space-around;
	align-items: flex-start;
	overflow: auto;
/* 	border: yellow solid 1px; */
}

.article {
	width: 900px;
	height: 100%;
	padding-top: 30px;
	display: flex;
	flex-direction: column;
/* 	border: green solid 1px; */
}

.art_nav {
	margin-bottom: 50px;
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	align-items: center;
/*  	border: green solid 1px;  */
}

.sec_content p {
	/* 한 줄 자르기 */
	display: inline-block;
	width: 100%;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	
	/* 여러 줄 자르기 추가 스타일 */
 	white-space: normal; 
	line-height: 1.3;
	height: 3.7em;
	text-align: left;
	word-wrap: break-word;
	display: -webkit-box;
	-webkit-line-clamp: 3;
	-webkit-box-orient: vertical;
}

.con_head {
	display: flex;
	flex-direction: row;
	justify-content: space-around;
	align-items: center;
/* 	border: navy solid 1px; */
}

.sec_comment {
	padding-left: 50px;
}

.sec_comment .square {
	width: 120px;
	height: 110px;
	padding: 10px;
}

.sec_comment .field {
	flex-direction: row;
	align-items: center;
	display: flex;
}

.paging {
	display: flex;
	justify-content: center;
}

.aside {
	width: 400px;
	height: 600px;
	display: flex;
	flex-direction: column;
/* 	border: blue solid 1px; */
}

.aside_stat {
	display: flex;
	flex-direction: row;
	justify-content: space-between;
}
.padding_10 {
	padding: 10px;
}

.margin_10 {
	margin: 10px;
}
</style>
<script>
	$(document).ready(function() {
		$('.ui.dropdown').dropdown();
	});
	
	function modal_write() {
		if(<%= (MemberVO)session.getAttribute("member") != null %>) {
			$('.small.modal.write')
				.modal({blurring: true})
				.modal('show')
			;
		} else {
			alert("로그인 하세요");
		}
	};
	
	function modal_login() {
		$('.small.modal.login')
			.modal({blurring: true})
			.modal('show')
		;
	};
	
	function modal_signup() {
		$('.small.modal.signup')
			.modal({blurring: true})
			.modal('show')
		;
	};
	
	function modal_logout() {
		$('.mini.modal.logout')
			.modal({blurring: true})
			.modal('show')
		;
	};
	
	function do_login() {
		console.log("do_login()");
		
		$('#login_form').submit();
	}
	
	function do_logout() {
		location.href = '${ pageContext.request.contextPath }/logout';
	}
	
	function do_signup() {
		$('#signup_form').submit();
	}
	function do_write() {
		$('#write_form').submit();
	}
</script>
<title>__' ___</title>
</head>
<body>
	<div class="wrap">
		<div class="container">
			<div class="head">
				<div class="user">
					<c:choose>
						<c:when test="${ member != null }">
							${ member.nick } 님
							<button class="ui button" onclick="modal_logout();">logout</button>
						</c:when>
						<c:otherwise>
							<button class="ui left attached button" onclick="modal_login();">login</button>
							<button class="right attached ui button" onclick="modal_signup();">signup</button>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="contents">
				<div class="article">
					<div class="art_nav">
						<div class="two_per_one">
							<button class="ui button" onclick="modal_write();">글쓰기</button>
							<div class="ui mini horizontal statistic">
								<div class="label" style="padding-right: 5px;">Total</div>
								<div class="value">100</div>
							</div>
							<div class="ui tag label">selected</div>
						</div>
						<div class="two_per_two">
							<h4 class="ui header">
								<div class="content"> 목록
									<div class="ui inline dropdown">
										<div class="text">10 개</div>
										<i class="dropdown icon"></i>
										<div class="menu">
											<div class="active item" data-text="10">10 개</div>
											<div class="item" data-text="20">20 개</div>
											<div class="item" data-text="30">30 개</div>
										</div>
									</div>
								</div>
							</h4>
						</div>
					</div>
					<c:forEach items="${ contentList }" var="content" >
						<div class="ui segment section padded vertical"  style="border-bottom: #EBEBEB solid 1px;">
							<div class="sec_content">
								<div class="ui vertical segment">
									<div class="column con_head">
										<div class="ui medium header">${ content.title }</div>
										<div class="sub header">2017. 12. 5</div>
										<div class="sub header">대장</div>
									</div>
									<div class="column">
										<p>아이들의 이름과, 어머님, 때 어머님, 봄이 밤을 토끼, 있습니다. 별 계절이 이 프랑시스 위에
											있습니다. 잔디가 하나의 우는 하나 파란 멀듯이, 아침이 이름과, 이네들은 까닭입니다. 벌써 별 밤을 자랑처럼
											아침이 묻힌 어머님, 까닭입니다. 못 이런 차 어머니, 청춘이 듯합니다. 이름을 새워 까닭이요, 내 아침이
											봅니다. 나의 언덕 가난한 슬퍼하는 있습니다. 다하지 내 별빛이 버리었습니다. 나의 새워 슬퍼하는 토끼,
											자랑처럼 까닭입니다. 흙으로 헤는 밤이 하나에 거외다. 부끄러운 내 사람들의 청춘이 경, 하나에 무덤
											까닭입니다. 자랑처럼 별 강아지, 까닭입니다. 무성할 까닭이요, 프랑시스 나의 거외다. 이제 위에 가슴속에 없이
											나의 이름과, 봅니다. 청춘이 부끄러운 다 별빛이 때 봄이 있습니다. 쓸쓸함과 어머님, 아직 지나고 그리워 묻힌
											어머니, 까닭입니다. 부끄러운 흙으로 어머님, 딴은 가득 속의 위에 둘 거외다. 별 차 강아지, 잔디가 별
											봅니다.</p>
									</div>
								</div>
							</div>
							<div class="padding_10"></div>
							<div class="sec_comment">
								<div class="ui minimal comments">
									<div class="comment">
										<div class="content">
											<a class="author">Matt</a>
											<div class="metadata">
												<span class="date">Today at 5:42PM</span>
											</div>
											<div class="text">How artistic!</div>
										</div>
									</div>
									<form class="ui reply form">
										<div class="field">
											<textarea class="com_text" style="height: 30px !important;"></textarea>
											<button class="ui button square">댓글 등록</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</c:forEach>
					<div class="padding_10"></div>
					<div class="paging">
						<div>
							<a>&lt;</a>
							<c:forEach begin="1" end="10" var="i">
								<a>${ i }</a>
							</c:forEach>
							<a>&gt;</a>
						</div>
					</div>
				</div>
				<div class="aside">
					<div class="aside_tag">
						<div class="ui segment">
							<div class="ui header">tag</div>
							<c:forEach begin="1" end="10" var="i">
								<a class="ui tag label">tag ${ i }</a>
							</c:forEach>
						</div>
					</div>
					<div class="margin_10"></div>
					<div class="aside_stat">
						<div class="stat_content">
							<div class="ui segment">
								<div class="ui header">content top 5</div>
								<c:forEach begin="1" end="5" var="i">
									<div class="ui label">작성자 ${ i }</div>
								</c:forEach>
							</div>
						</div>
						<div class="margin_10"></div>
						<div class="stat_comment">
							<div class="ui segment">
								<div class="ui header">comment top 5</div>
								<c:forEach begin="1" end="5" var="i">
									<div class="ui label">댓쓴이 ${ i }</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 글쓰기 모달 -->
	<div class="ui modal small write">
	  <i class="close icon"></i>
	  <div class="header">글쓰기</div>
	  <div class="content">
	  	<form class="ui form" id="write_form" method="post" action="${ pageContext.request.contextPath }/write">
		  <div class="field">
		    <input type="text" name="title" placeholder="제목을 입력하세요." required="required">
		  </div>
		  <div class="field">
		    <textarea rows="2" placeholder="내용" required="required"></textarea>
		  </div>
		</form>
	  </div>
	  <div class="actions">
	    <div class="ui black deny button" onclick="do_write();">등록</div>
	  </div>
	</div>
	<!-- 글쓰기 모달 -->
	<!-- 로그인 모달 -->
	<div class="ui modal small login">
	  <i class="close icon"></i>
	  <div class="header">로그인</div>
	  <div class="content">
	  	<form class="ui form" id="login_form" method="post" action="${ pageContext.request.contextPath }/login">
		  <div class="field">
		    <input type="text" name="id" placeholder="아이디를 입력하세요." required="required">
		  </div>
		  <div class="field">
		    <input type="password" name="pw" placeholder="비밀번호를 입력하세요." required="required">
		  </div>
		</form>
	  </div>
	  <div class="actions">
	    <div class="ui black deny button" onclick="do_login();">로그인</div>
	  </div>
	</div>
	<!-- 로그인 모달 -->
	<!-- 가입 모달 -->
	<div class="ui modal small signup">
	  <i class="close icon"></i>
	  <div class="header">가입</div>
	  <div class="content">
	  	<form class="ui form" id="signup_form" method="post" action="${ pageContext.request.contextPath }/signup">
		  <div class="field">
		    <input type="text" name="id" placeholder="아이디를 입력하세요." required="required">
		  </div>
		  <div class="field">
		    <input type="text" name="pw" placeholder="비밀번호를 입력하세요." required="required">
		  </div>
		  <div class="field">
		    <input type="text" name="nick" placeholder="닉네임을 입력하세요." required="required">
		  </div>
		</form>
	  </div>
	  <div class="actions">
	    <div class="ui black deny button" onclick="do_signup();">가입</div>
	  </div>
	</div>
	<!-- 가입 모달 -->
	<!-- 로그아웃 모달 -->
	<div class="ui modal mini logout">
	  <i class="close icon"></i>
	  <div class="header">로그아웃</div>
	  <div class="content">
	  	정말 로그아웃 하시겠습니까
	  </div>
	  <div class="actions">
	    <div class="ui black deny button" onclick="do_logout();">네</div>
	  </div>
	</div>
	<!-- 로그아웃 모달 -->
</body>
</html>