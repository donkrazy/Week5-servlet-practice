<%@page import="com.estsoft.db.MySQLWebDBConnection"%>
<%@page import="com.estsoft.guestbook.dao.GuestbookListDAO"%>
<%@ page import="com.estsoft.guestbook.vo.GuestbookListVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 

<%
   request.setCharacterEncoding("UTF-8");
   String name = request.getParameter("name");
   String password = request.getParameter("password");
   String message = request.getParameter("message");

   GuestbookListVO vo = new GuestbookListVO();
   vo.setName(name);
   vo.setPassword(password);
   vo.setMessage(message);
   
   GuestbookListDAO dao = new GuestbookListDAO(new MySQLWebDBConnection());
   dao.insert(vo);
   response.sendRedirect("/");
%>