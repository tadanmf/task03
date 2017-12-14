<%@page import="com.task03.member.vo.MemberVO"%>
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
	width: 1200px;
	height: 900px;
	display: flex;
	flex-direction: column;
/* 	justify-content: center; */
	align-items: center;
/*  	border: red solid 1px;  */
}

.container>div {
	width: 90%;
}

.head {
	height: 70px;
	display: flex;
	justify-content: flex-end;
/* 	border: orange solid 1px; */
}

.nav {
	display: flex;
	flex-direction: row;
	justify-content: space-between;
/*  	border: blue solid 1px;  */
}

.con_head {
	display: flex;
	flex-direction: row;
	justify-content: space-around;
	align-items: center;
	padding: 10px;
/*  	border: red solid 1px; */
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

.con_body {
	height: 300px;
	padding: 10px;
	border-bottom: #CACBCD solid 1px;
}

.sec_comment {
	width: 100%;
	overflow: auto;
}

#comment_form > div {
	display: flex;
	flex-direction: row;
}

.padding_10 {
	padding: 10px;
}
</style>
<script type="text/javascript">
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
	
	function do_content_edit() {
		
	}

	function write_comment() {
		var object_param = new Object();
		object_param.c_idx = $('input[name=c_idx]').val();
		object_param.comment = $('input[name=comment]').val();
		object_param.m_idx = 2;
		
		if('${ member }') {
			object_param.m_idx = '${ member.idx }';
		}
		
		var params = JSON.stringify(object_param);
// 		var params = {
// 				c_idx : $('input[name=c_idx]').val(), 
// 				comment : $('input[name=comment]').val()
// 		};
		
		console.log('params:', params);
		
		$.ajax({
			url: '${ pageContext.request.contextPath }/writeComment',
			type: 'POST',
			data: params,
			contentType : "application/json",
			success: function(result) {
				var now = new Date();
				var hours = now.getHours();
				var minutes = now.getMinutes();
				if(hours < 10) hours = '0' + hours;
				if(minutes < 10) minutes = '0' + minutes;
				
				var time = '' + hours + ':' + minutes;
				var nick = '익명';
				
				console.log(result);
				$('.comment').first().before(
						'<div class="comment">'
						+ '<div class="content">'
						+ '<a class="author">${ member.nick }</a>'
						+ '<div class="metadata">'
						+	'<span class="date">' + time + '</span>'
						+ '</div>'
						+ '<div class="text">' + $('input[name=comment]').val() + '</div>'
						+ '</div>'
						+ '</div>');
				$('input[name=comment]').val('');
			},
			error: function() {
				console.log(`TT`);
			}
		});
	};
</script>
<title>Insert title here</title>
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
			<div class="nav">
				<div class="two_per_one">
					<button class="ui button" onclick="do_content_edit();">수정</button>
					<button class="ui button">삭제</button>
				</div>
				<div class="two_per_two">
					<button class="ui button" onclick="location.href='${ pageContext.request.contextPath }/'">글목록</button>
				</div>
			</div>
			<div class="padding_10"></div>
			<div class="column con_head">
				<div class="ui medium header" style="margin: 0px">
					${ content.title } 
					<c:if test="${ tag.tag != null }">
						<div class="ui tag label" style="width: auto; margin-left: 20px;">${ tag.tag }</div>
					</c:if>
				</div>
				<div class="sub header">${ content.format_date }</div>
				<div class="sub header">${ content.nick }</div>
			</div>
			<div class="con_body">${ content.content }</div>
			<div class="padding_10"></div>
			<div class="sec_comment">
				<div class="ui minimal comments">
					<div class="ui form" id="comment_form">
						<input type="hidden" name="c_idx" value="${ content.idx }"/>
						<div class="field">
							<input type="text" name="comment" id="comment" placeholder="악플 달지 맙시다." required="required">
							<button class="ui button" onclick="write_comment()">등록</button>
						</div>
					</div>
					<c:forEach items="${ commentList }" var="comment">
						<div class="comment">
							<div class="content">
								<a class="author">${ comment.nick }</a>
								<div class="metadata">
									<span class="date">${ comment.format_date }</span>
								</div>
								<div class="text">${ comment.comment }</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>			
		</div>
		<!-- 글 수정 모달 -->
		<div class="ui modal small write">
		  <i class="close icon"></i>
		  <div class="header">글 수정</div>
		  <div class="content">
	<!-- 	  	<form> -->
	<!-- 	  		<div class="row"> -->
	<!-- 			    <input type="text" name="title" id="title" placeholder="제목을 입력하세요." required="required"> -->
	<!-- 	  		</div> -->
	<!-- 	  	</form> -->
		  	<form class="ui form" id="edit_form" method="post" action="${ pageContext.request.contextPath }/edit">
			  <div class="field">
			    <input type="text" name="title" id="title" placeholder="제목을 입력하세요." required="required">
			  </div>
			  <div class="field">
			  	<input type="hidden" name="content" />
				<div id="editor_container"></div>
	<!-- 		    <textarea rows="2" name="content" id="content" placeholder="내용을 입력하세요." required="required"></textarea> -->
			  </div>
			  <div class="field">
			    <input type="text" id="tag" name="tag" placeholder="태그를 입력하세요." required="required">
			  </div>
			</form>
		  </div>
		  <div class="actions">
		    <div class="ui black deny button" onclick="do_write();">수정</div>
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
	</div>
</body>
</html>