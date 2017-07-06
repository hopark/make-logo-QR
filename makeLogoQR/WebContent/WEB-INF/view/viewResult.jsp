<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.LogoQR.*"%>
<%@ page import="java.io.File"%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<%
	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		try {
			MultipartRequest mr = new MultipartRequest(request, this.getServletContext().getRealPath(""),
					1024 * 1024 * 1024, "utf-8", new DefaultFileRenamePolicy());
			File file = mr.getFile("filepath");
			String fileName = file.getName();
			long fileSize = file.length();
			if (fileName == null) {
				System.out.print("파일 업로드 실패");
			} else {
				System.out.println("파일명:" + fileName + "<br/>");
				System.out.println("파일크기:" + fileSize + "<br/>");
				String information = mr.getParameter("info");

				Logo lg = new Logo(file);
				QRcode qr = new QRcode(lg.getSize(), information);
				LogoQR.combineImage(qr, lg);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	%>
</body>
</html>