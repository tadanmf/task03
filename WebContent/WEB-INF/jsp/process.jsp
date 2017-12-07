<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.onload = function() {
		var url = '${ pageContext.request.contextPath }/';
		
// 		if('${ type } == login') {
// 		} else if('${ type } == logout') {
// 			alert('로그아웃 되었습니다');
// 		} else if('${ type } == signup') {
			
// 		} else if('${ type } == write') {
			
// 		} else if('${ type } == edit') {
			
// 		} else if('${ type } == delete') {
			
// 		}

		alert('${ type } ${ result }');
		location.href = url;
	}
</script>
</head>
<body>

</body>
</html>