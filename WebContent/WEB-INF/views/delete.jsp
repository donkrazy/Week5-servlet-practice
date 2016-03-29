<%@ page import="com.estsoft.db.MySQLWebDBConnection"%>
<%@ page import="com.estsoft.guestbook.dao.GuestbookListDAO"%>
<%@ page import="com.estsoft.guestbook.vo.GuestbookListVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	GuestbookListDAO dao = new GuestbookListDAO(new MySQLWebDBConnection());
	if (dao.delete(id, password)) {
%>
	<script type='text/javascript'>
		alert("삭제 성공");
		window.location.assign("/");
	</script>
<%
	} else {
%>
	<script type='text/javascript'>
		alert("삭제 실패");
		window.location.assign("/");
	</script>
<%
	}
%>


<a href="/">목록으로 돌아가기</a>