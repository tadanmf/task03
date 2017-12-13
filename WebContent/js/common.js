	var quill = null;

	$(document).ready(function() {
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
	
// 	function write_comment() {
// // 		$('#comment_form').submit();
// 		var params = $('#comment_form').serialize();
		
// 		$.ajax({
// 			url: '${ pageContext.request.contextPath }/writeComment',
// 			type: 'POST',
// 			data: params,
// 			success: function(result) {
// 				alert(result);
// 				console.log(result);
// 			},
// 			error: function() {
// 				console.log(`TT`);
// 			}
// 		});
// 	};
