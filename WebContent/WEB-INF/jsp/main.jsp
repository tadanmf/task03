<%@page import="com.task03.tag.vo.TagVO"%>
<%@page import="com.task03.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<!-- Main Quill library -->
<script src="//cdn.quilljs.com/1.3.4/quill.js"></script>

<!-- Theme included stylesheets -->
<link href="//cdn.quilljs.com/1.3.4/quill.snow.css" rel="stylesheet">
<!-- <link href="//cdn.quilljs.com/1.3.4/quill.bubble.css" rel="stylesheet"> -->

<!-- Core build with no theme, formatting, non-essential modules -->
<link href="//cdn.quilljs.com/1.3.4/quill.core.css" rel="stylesheet">
<!-- <script src="//cdn.quilljs.com/1.3.4/quill.core.js"></script> -->
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
	width: 1000px;
/* 	height: 100%; */
/* 	padding-top: 30px; */
	display: flex;
	flex-direction: column;
/* 	border: green solid 1px; */
}

.art_nav {
 	height: 80px !important; 
/* 	margin-bottom: 50px; */
	padding: 10px;
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	align-items: center;
  	border: #EBEBEB solid 1px;
  	border-radius: 10px;  
}

.sec_content p {
	/* 한 줄 자르기 */
	display: inline-block;
	width: 100%;
	white-space: nowrap;
	overflow: hidden;
	padding: 10px;
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

.con_head div:nth-child(1) {
	width: 80%;
}

.con_head div:nth-child(2) {
	width: 10%;
}

.con_head div:nth-child(3) {
	width: 10%;
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
	width: 250px;
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

.stat_content div:first-child {
	width: 130px;
	display: flex;
	flex-direction: column;
}

.stat_comment div:first-child {
	width: 130px;
	display: flex;
	flex-direction: column;
	justify-content: center;
}

.stat_comment div:first-child {
	width: 130px;
	display: flex;
	flex-direction: column;
	justify-content: center;
}

.ui.label {
	margin: 5px;
}

.form {
	margin-top: 1em;
}
.padding_10 {
	padding: 10px;
}

.margin_10 {
	margin: 10px;
}

#editor_container {
	height: 200px;
}
</style>
<script>

	var quill = null;

	$(document).ready(function() {
// 		alert('${ selected }');
		/* quill editor */
		quill = new Quill('#editor_container', {
		  modules: {
		    toolbar: [
		      [{ header: [1, 2, false] }],
		      ['bold', 'italic', 'underline'],
		      ['link', 'blockquote', 'code-block', 'image'],
		      [{ list: 'ordered' }, { list: 'bullet' }]
		    ]
		  },
		  placeholder: '내용을 입력하세요.',
		  theme: 'snow'  // or 'bubble'
		});
		
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
			
			$('.small.modal.login')
				.modal({blurring: true})
				.modal('show')
			;
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
// 		var tag = $('#write_form #tag').val();
// 		var list = '${ tagList }';
// 		var list_json = '${ tagList_json }';
// 		list = JSON.stringify(list);

// 		var obj = JSON.parse(list);
		
// 		console.log('obj:', obj);
		
// 		console.log('list:', list);
// 		console.log('list_json:', list_json);
// 		console.log('tag:', tag);
// 		console.log('inArray(): ', $.inArray(tag, list));
// 		console.log('indexOf(): ', list.indexOf(tag));
// 		console.log('typeof list:', typeof list);
		
// 		console.log('list_json[key]:', list_json["tag"]);
		
// 		console.log(list_json.indexOf(tag));
		
// 		var data = JSON.parse(list_json);	
// 		var data2 = JSON.parse(list);
		
// 		console.log('data:', data);
// 		console.log('data2:', data2);
		
// 		var index = data.map(function(e) {
// 			return e['tag'];
// 		}).indexOf(tag);
		
// 		console.log('index:', index);
		
// 		if((tag != '') && ($.inArray(tag, list.tag) == -1)) {
// 			var param = { "tag": tag };
// 			$.ajax({
// 				url: '${ pageContext.request.contextPath }/addTag',
// 				type: 'POST',
// 				data: param,
// 				seccess: function(result) {
// 					console.log(result);
// 				},
// 				error: function() {
// 					console.log('TT');
// 				}
// 			})
// 		}
		
		console.log('quill.getContents():', quill.getContents());

		var content = $('input[name=content]');
		content.val(JSON.stringify(quill.getContents()));
		
		console.log('content:', content.val());
		
		$('#write_form').submit();
	}
	
	$(document).on('click', '.sec_content', function() {
// 		console.log(this.getAttribute('data-value'));
// 		console.log(this.dataset.value);
// 		console.log($(this).attr('data-value'));
		
		location.href = "${ pageContext.request.contextPath }/detail?idx=" + this.dataset.value;
	});
	
	function content_by_tag(label) {
// 		console.log(label);
// 		console.log('idx:', label.dataset.idx);
// 		console.log('tag:', label.dataset.tag);
// 		console.log('text:', label.text);
// 		console.log('val:', $(label).val());
		location.href = "${ pageContext.request.contextPath }/main?t_idx=" + label.dataset.idx + "&tag=" + label.dataset.tag;
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
							<button class="ui button" onclick="modal_write();">글쓰기</button>
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
							<c:if test="${ selected != '' }">
								<div class="ui tag label">${ selected }</div>
							</c:if>
							<div class="ui mini horizontal statistic">
								<div class="label" style="padding-right: 5px;">Total</div>
								<div class="value">${ count }</div>
							</div>
						</div>
						<div class="two_per_two">
							<h4 class="ui header">
								<div class="content"> 목록
									<div class="ui inline dropdown">
										<div class="text">10</div>&nbsp;개
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
							<div class="sec_content" data-value="${ content.idx }">
								<div class="ui vertical segment">
									<div class="column con_head">
										<div class="ui medium header" style="margin: 0px">${ content.title }</div>
										<div class="sub header">${ content.format_date }</div>
										<div class="sub header">${ content.nick }</div>
									</div>
									<div class="column">
										<p>${ content.content }</p>
									</div>
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
							<a class="ui tag label" data-idx="${ tag.idx }" data-tag="${ tag.tag }" onclick="content_by_tag(this)">all</a>
							<c:forEach items="${ tagList }" var="tag" begin="0" end="9">
								<a class="ui tag label" data-idx="${ tag.idx }" data-tag="${ tag.tag }" onclick="content_by_tag(this)">
									${ tag.tag } (${ tag.count })
								</a>
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
<!-- 	  	<form> -->
<!-- 	  		<div class="row"> -->
<!-- 			    <input type="text" name="title" id="title" placeholder="제목을 입력하세요." required="required"> -->
<!-- 	  		</div> -->
<!-- 	  	</form> -->
	  	<form class="ui form" id="write_form" method="post" action="${ pageContext.request.contextPath }/write">
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