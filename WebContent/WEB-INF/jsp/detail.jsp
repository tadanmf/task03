<%@page import="com.task03.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- semantic-ui -->
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.css">
<base>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>

<!-- axios -->
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<!-- lodash -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.4/lodash.min.js"></script>
<!-- loglevel -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/loglevel/1.6.0/loglevel.min.js"></script>
<!-- handlebars -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.min.js"></script>

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
	height: 500px;
	padding: 10px;
	border-bottom: #CACBCD solid 1px;
}

.sec_comment {
	width: 100%;
	overflow: auto;
}

#comment_form>div {
	display: flex;
	flex-direction: row;
}

.loading_bar {
	display: none;
	width: 100%;
	height: 100%;
	position: absolute;
	text-align: center;
	background: rgba(0, 0, 0, 0.7);
	z-index: 8000;
}

.spinner {
  margin: 350px auto;
  width: 40px;
  height: 40px;
  position: relative;
  text-align: center;
  
  -webkit-animation: sk-rotate 2.0s infinite linear;
  animation: sk-rotate 2.0s infinite linear;
}

.dot1, .dot2 {
  width: 60%;
  height: 60%;
  display: inline-block;
  position: absolute;
  top: 0;
  background-color: #fff;
  border-radius: 100%;
  
  -webkit-animation: sk-bounce 2.0s infinite ease-in-out;
  animation: sk-bounce 2.0s infinite ease-in-out;
}

.dot2 {
  top: auto;
  bottom: 0;
  -webkit-animation-delay: -1.0s;
  animation-delay: -1.0s;
}

@-webkit-keyframes sk-rotate { 100% { -webkit-transform: rotate(360deg) }}
@keyframes sk-rotate { 100% { transform: rotate(360deg); -webkit-transform: rotate(360deg) }}

@-webkit-keyframes sk-bounce {
  0%, 100% { -webkit-transform: scale(0.0) }
  50% { -webkit-transform: scale(1.0) }
}

@keyframes sk-bounce {
  0%, 100% { 
    transform: scale(0.0);
    -webkit-transform: scale(0.0);
  } 50% { 
    transform: scale(1.0);
    -webkit-transform: scale(1.0);
  }
}

.padding_10 {
	padding: 10px;
}
</style>
<script type="text/javascript">
	var edit_quill = null;
	$(document).ready(function() {
		console.log(log);
		log.setDefaultLevel("debug");
		log.debug('init');
		init();
	});
	
	function init() {
		/* quill editor */
		var quill = new Quill('#con_body', {
			modules: {
			    toolbar:  false
			}, 
		    theme: 'snow'  // or 'bubble'
		});
		/* quill editor */
		
		var json = ${ content.content };
// 		console.log(json.ops);
		quill.enable(false);
		quill.setContents(
			  json.ops
// 			  { insert: 'World!', attributes: { bold: true } },
// 			  { insert: '\n' },
// 			  { insert: {
// 				  'image' :  'data:image/png;base64,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'
// 			  	}
// 			  }
		);
		
		edit_quill = new Quill('#editor_container', {
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
		
		edit_quill.setContents(json.ops);
		
		axios.interceptors.request.use(function (config) {
			$('.loading_bar').show();
			log.info('log test');
// 			console.log('safd');
		    // Do something before request is sent
		    return config;
		  }, function (error) {
		    // Do something with request error
		    return Promise.reject(error);
		  });
		
		axios.interceptors.response.use(function (response) {
			_.delay(function() {
					$('.loading_bar').hide();
				}, 1000);
// 			console.log('3541635');
		    // Do something with response data
		    return response;
		  }, function (error) {
		    // Do something with response error
		    return Promise.reject(error);
		  });
		
		getComment();

	}
	
	function getComment() {
		log.debug('content.idx: ${ coidxntent.idx }');
		var idx = '${ content.idx }';
		log.debug('idx:', idx);
		var param = {
				'idx' : idx
		}		
		axios.get('${ pageContext.request.contextPath }/getComment?idx='+idx)
				.then(function(response) {
					/* comment handlebars */
					var source = $("#comment_list").html(); 
					//핸들바 템플릿 컴파일
					var template = Handlebars.compile(source); 
					
					log.debug('template:', template);
					
					var result = response.data;
					var comment_list =  [];
					var item = {};
					for(var i = 1 ; i<= result.length ; i++) {
						var vo = result[i-1];
						item = {
							_nick: vo._nick, format_date: vo.format_date, comment: vo.comment
						} 
						comment_list.push(item);
					}
					var comment_data = {
							comment_list : comment_list
					};
					log.info('comment_data', comment_data);
				
					//핸들바 템플릿에 바인딩할 데이터
				
					//핸들바 템플릿에 데이터를 바인딩해서 HTML 생성
					var html = template(comment_data);
					
					log.info('html:', html);
				
					//생성된 HTML을 DOM에 주입
					$('.comments').append(html);
				});
	}

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
	
	function modal_edit() {
		$('.small.modal.edit')
			.modal({blurring: true})
			.modal('show')
		;
	}
	
	function modal_del() {
		$('.mini.modal.del')
			.modal({blurring: true})
			.modal('show')
		;
	}
	
	function do_edit() {
		console.log('edit_quill.getContents():', edit_quill.getContents());
		
		var content = $('input[name=content]');
		content.val(JSON.stringify(edit_quill.getContents()));
		
		$('#edit_form').submit();
	}
	
	function do_del(idx) {
		location.href = '${ pageContext.request.contextPath }/del?idx=' + idx;
	}
	
	function write_comment() {
		var object_param = new Object();
		object_param.c_idx = $('input[name=c_idx]').val();
		object_param.comment = $('input[name=comment]').val();
		
		if('${ member }') {
			object_param.m_idx = '${ member.idx }';
			object_param.nick = '${ member.nick }';
		} else {
			object_param.m_idx = '2';
			object_param.nick = $('input[name=nick]').val();
		}
		
// 		console.log('object_param:', object_param);
		
		axios.post('${ pageContext.request.contextPath }/writeComment', object_param)
		  	 .then(function (response) {
			    var now = new Date();
				var hours = now.getHours();
				var minutes = now.getMinutes();
				if(hours < 10) hours = '0' + hours;
				if(minutes < 10) minutes = '0' + minutes;
				
				var time = '' + hours + ':' + minutes;
				var nick = '';
				if('${ member}') {
					nick = '${ member.nick }';
				} else {
					nick = $('input[name=comment]').val();
				}
				
// 				console.log(result);
// 				console.log($('.comment').first());
				
				var html = '<div class="comment">'
					+ '<div class="content">'
					+ '<a class="author">' + $('input[name=nick]').val() + '</a>'
					+ '<div class="metadata">'
					+	'<span class="date">' + time + '</span>'
					+ '</div>'
					+ '<div class="text">' + nick + '</div>'
					+ '</div>'
					+ '</div>';
				
				if($('.comment').first().length == 0) {
					console.log("하하");
					$('.comments').append(html);
				} else {
				
				$('.comment').first().before(
						'<div class="comment">'
						+ '<div class="content">'
						+ '<a class="author">' + $('input[name=nick]').val() + '</a>'
						+ '<div class="metadata">'
						+	'<span class="date">' + time + '</span>'
						+ '</div>'
						+ '<div class="text">' + nick + '</div>'
						+ '</div>'
						+ '</div>');
				}
				$('input[name=comment]').val('');
				$('input[name=nick]').val('');
		    console.log(response);
		  })
	}
	
	function write_comment2() {
		var object_param = new Object();
		object_param.c_idx = $('input[name=c_idx]').val();
		object_param.comment = $('input[name=comment]').val();
		
		if('${ member }') {
			object_param.m_idx = '${ member.idx }';
			object_param.nick = '${ member.nick }';
		} else {
			object_param.m_idx = '2';
			object_param.nick = $('input[name=nick]').val();
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
				var nick = '';
				if('${ member}') {
					nick = '${ member.nick }';
				} else {
					nick = $('input[name=comment]').val();
				}
				
				console.log(result);
				console.log($('.comment').first());
				
				var html = '<div class="comment">'
					+ '<div class="content">'
					+ '<a class="author">' + $('input[name=nick]').val() + '</a>'
					+ '<div class="metadata">'
					+	'<span class="date">' + time + '</span>'
					+ '</div>'
					+ '<div class="text">' + nick + '</div>'
					+ '</div>'
					+ '</div>';
				
				if($('.comment').first().length == 0) {
					console.log("하하");
					$('.comments').append(html);
				} else {
				
				$('.comment').first().before(
						'<div class="comment">'
						+ '<div class="content">'
						+ '<a class="author">' + $('input[name=nick]').val() + '</a>'
						+ '<div class="metadata">'
						+	'<span class="date">' + time + '</span>'
						+ '</div>'
						+ '<div class="text">' + nick + '</div>'
						+ '</div>'
						+ '</div>');
				}
				$('input[name=comment]').val('');
				$('input[name=nick]').val('');
			},
			error: function() {
				console.log(`TT`);
			}
		});
	};
	
	// image orign
	var imgCommonPreview = new Image();
	function image_orign(image) {
		if(image == "") { 
			alert('등록된 이미지가 없습니다.'); 
			return; 
		} 
		imgCommonPreview.src = image; 
		setTimeout("createPreviewWin(imgCommonPreview)", 100);
	}
	
	function createPreviewWin(imgCommonPreview) { 
		if(!imgCommonPreview.complete) { 
			setTimeout("createPreviewWin(imgCommonPreview)", 100); 
			return; 
		} 
		var scrollsize = 17; 
		var swidth = screen.width - 10; 
		var sheight = screen.height - 90; 
		var wsize = imgCommonPreview.width; 
		var hsize = imgCommonPreview.height; 
		
		if(wsize < 50) wsize = 50;	// 가로 최소 크기
		if(hsize < 50) hsize = 50;	// 세로 최소 크기 
		if(wsize > swidth) wsize = swidth;	// 가로 최대 크기 
		if(hsize > sheight) hsize = sheight;	// 세로 최대 크기 
		
		// 세로가 최대크기를 초과한경우 세로스크롤바 자리 확보 
		if((wsize < swidth-scrollsize) && hsize >= sheight) wsize += scrollsize; 
		
		// 가로가 최대크기를 초과한경우 가로스크롤바 자리 확보 
		if((hsize < sheight-scrollsize) && wsize >= swidth) hsize += scrollsize; 
		
		// IE 6,7 전용 : 가로세로 크기가 보통일때 세로 스크롤바 자리 확보 
		if((wsize < swidth-scrollsize) && hsize < sheight 
				&& (navigator.userAgent.indexOf("MSIE 6.0") > -1 
				|| navigator.userAgent.indexOf("MSIE 7.0") > -1)) wsize += scrollsize; 
		
		// 듀얼 모니터에서 팝업 가운데 정렬하기 
		var mtWidth = document.body.clientWidth;	// 현재 브라우저가 있는 모니터의 화면 폭 사이즈 
		var mtHeight = document.body.clientHeight;	// 현재 브라우저가 있는 모니터의 화면 높이 사이즈 
		var scX = window.screenLeft;	// 현재 브라우저의 x 좌표(모니터 두 대를 합한 총 위치 기준) 
		var scY = window.screenTop;	// 현재 브라우저의 y 좌표(모니터 두 대를 합한 총 위치 기준) 
		var popX = scX + (mtWidth - wsize) / 2 - 50;	// 팝업 창을 띄울 x 위치 지정(모니터 두 대를 합한 총 위치 기준) 
		var popY = scY + (mtHeight - hsize) / 2 - 50;	// 팝업 창을 띄울 y 위치 지정(모니터 두 대를 합한 총 위치 기준) 
		
		// window.open('주소', '이름(공란가능)', '속성'); 
		imageWin = window.open("", "", "top=" + popY + ",left=" + popX 
				+ ",width=" + wsize + ",height=" + hsize 
				+ ",scrollbars=yes,resizable=yes,status=no"); 
		
		imageWin.document.write("<html><title>Preview</title><body style='margin:0;cursor:pointer;' title='Close' onclick='window.close()'>"); 
		imageWin.document.write("<img src='" + imgCommonPreview.src + "'>"); 
		imageWin.document.write("</body></html>"); 
	}
</script>
<title>${ content.title }</title>
</head>
<body>
	<div class="wrap">
		<!-- loading bar -->
		<div class="loading_bar">
			<div class="spinner">
				<div class="dot1"></div>
				<div class="dot2"></div>
			</div>
		</div>
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
							<button class="right attached ui button"
								onclick="modal_signup();">signup</button>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="padding_10"></div>
			<div class="nav">
				<div class="two_per_one">
					<c:if
						test="${ member.level == 'A' || member.idx == content.m_idx }">
						<button class="ui button" onclick="modal_edit();">수정</button>
						<button class="ui button" onclick="modal_del();">삭제</button>
					</c:if>
				</div>
				<div class="two_per_two">
					<button class="ui button"
						onclick="location.href='${ pageContext.request.contextPath }/'">글목록</button>
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
			<div class="con_body" id="con_body">
				<%-- 				<p>${ content.content }</p>  --%>
				<%-- <%-- 				<img alt="" src="${ content.image }" style="width: 20%; height: auto;" onclick="image_orign('${ content.image }')"> --%>
				<%-- 				<img alt="" src="${ content.image }" width="20%" height="auto" onclick="image_orign('${ content.image }')"> --%>
			</div>
			<div class="padding_10"></div>
			<div class="sec_comment">
				<div class="ui minimal comments">
					<div class="ui form" id="comment_form">
						<input type="hidden" name="c_idx" value="${ content.idx }" />
						<div class="field">
							<input type="text" name="comment" id="comment"
								placeholder="악플 달지 맙시다." required="required">
							<c:if test="${ member == null }">
								<input type="text" name="nick" placeholder="닉네임"
									required="required" />
							</c:if>
							<button class="ui button" onclick="write_comment()">등록</button>
						</div>
					</div>
					<script id="comment_list" type="text/x-handlebars-template">
					<div class="comment">
						<div class="content">
							{{#comment_list}}
							<a class="author">{{_nick}}</a>
							<div class="metadata">
								<span class="date">{{format_date}}</span>
							</div>
							<div class="text">{{comment}}</div>
							{{/comment_list}}
						</div>
					</div>
					</script>
				</div>
			</div>
		</div>
		<!-- 글 수정 모달 -->
		<div class="ui modal small edit">
			<i class="close icon"></i>
			<div class="header">글 수정</div>
			<div class="content">
				<!-- 	  	<form> -->
				<!-- 	  		<div class="row"> -->
				<!-- 			    <input type="text" name="title" id="title" placeholder="제목을 입력하세요." required="required"> -->
				<!-- 	  		</div> -->
				<!-- 	  	</form> -->
				<form class="ui form" id="edit_form" method="post"
					action="${ pageContext.request.contextPath }/edit">
					<div class="field">
						<input type="text" name="title" id="title"
							placeholder="제목을 입력하세요." required="required"
							value=${ content.title }>
					</div>
					<div class="field">
						<input type="hidden" name="content" /> <input type="hidden"
							name="idx" value="${ content.idx }" />
						<div id="editor_container"></div>
						<!-- 		    <textarea rows="2" name="content" id="content" placeholder="내용을 입력하세요." required="required"></textarea> -->
					</div>
					<div class="field">
						<input type="text" id="tag" name="tag" placeholder="태그를 입력하세요."
							required="required" value="${ tag.tag }" disabled="disabled">
					</div>
				</form>
			</div>
			<div class="actions">
				<div class="ui black deny button" onclick="do_edit();">수정</div>
			</div>
		</div>
		<!-- 글 수정 모달 -->
		<!-- 로그인 모달 -->
		<div class="ui modal small login">
			<i class="close icon"></i>
			<div class="header">로그인</div>
			<div class="content">
				<form class="ui form" id="login_form" method="post"
					action="${ pageContext.request.contextPath }/login">
					<div class="field">
						<input type="text" name="id" placeholder="아이디를 입력하세요."
							required="required">
					</div>
					<div class="field">
						<input type="password" name="pw" placeholder="비밀번호를 입력하세요."
							required="required">
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
				<form class="ui form" id="signup_form" method="post"
					action="${ pageContext.request.contextPath }/signup">
					<div class="field">
						<input type="text" name="id" placeholder="아이디를 입력하세요."
							required="required">
					</div>
					<div class="field">
						<input type="text" name="pw" placeholder="비밀번호를 입력하세요."
							required="required">
					</div>
					<div class="field">
						<input type="text" name="nick" placeholder="닉네임을 입력하세요."
							required="required">
					</div>
				</form>
			</div>
			<div class="actions">
				<div class="ui black deny button" onclick="do_signup();">가입</div>
			</div>
		</div>
		<!-- 가입 모달 -->
		<!-- 로그아웃 모달 -->
		<!-- 		<div class="ui modal mini logout"> -->
		<!-- 		  <i class="close icon"></i> -->
		<!-- 		  <div class="header">로그아웃</div> -->
		<!-- 		  <div class="content"> -->
		<!-- 		  	정말 로그아웃 하시겠습니까 -->
		<!-- 		  </div> -->
		<!-- 		  <div class="actions"> -->
		<!-- 		    <div class="ui black deny button" onclick="do_logout();">네</div> -->
		<!-- 		  </div> -->
		<!-- 		</div> -->
		<!-- 로그아웃 모달 -->
		<!-- 글 삭제 모달 -->
		<div class="ui modal mini del">
			<i class="close icon"></i>
			<div class="header">글 삭제</div>
			<div class="content">정말 삭제 하시겠습니까</div>
			<div class="actions">
				<div class="ui black deny button"
					onclick="do_del(${ content.idx });">네</div>
			</div>
		</div>
		<!-- 글 삭제 모달 -->
	</div>
</body>
</html>