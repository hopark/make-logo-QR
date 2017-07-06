<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>현재 시간</title>
</head>
<body>
	<form action="viewResult.jsp" method="post"
		enctype="Multipart/form-data">
		<label for="filepath">업로드</label> <br>
		<input type="file" name="filepath"/> <br>
		<label
			for="info">정보</label> <input type="text" name="info"> <input
			type="submit" value="전송">
	</form>
</body>
</html>