package com.estsoft.guestbook.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.guestbook.dao.GuestbookListDAO;
import com.estsoft.guestbook.vo.GuestbookListVO;

@WebServlet("/")
public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GuestbookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding( "UTF-8" );
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청분석
		String actionName = request.getParameter( "a" );
		if( "add".equals( actionName ) ) {
			String name = request.getParameter( "name" );
			String password = request.getParameter( "password" );
			String message = request.getParameter("message");
			GuestbookListVO vo = new GuestbookListVO();
			vo.setName(name);
			vo.setPassword(password);
			vo.setMessage(message);
			GuestbookListDAO dao = new GuestbookListDAO( new MySQLWebDBConnection() );
			dao.insert(vo);
			response.sendRedirect( "/" );
		} else if( "deleteform".equals( actionName ) ) {
			String id = request.getParameter("id");
			request.setAttribute("id", id);
			RequestDispatcher rd = request.getRequestDispatcher(  "/WEB-INF/views/deleteform.jsp" );
			rd.forward( request, response );
		} else if( "delete".equals( actionName ) ) {
			String id = request.getParameter("id");
			request.setAttribute("id", id);
			RequestDispatcher rd = request.getRequestDispatcher(  "/WEB-INF/views/delete.jsp" );
			rd.forward( request, response );	
		} else {
			// default action ( list, index )
			GuestbookListDAO dao = new GuestbookListDAO( new MySQLWebDBConnection() );
			List<GuestbookListVO> list = dao.getList();
			
			// 포워딩전에 JSP로 보낼 데이터를 request범위(scope)에 저장한다.
			request.setAttribute( "list", list );
			
			// forwarding (request 확장, request dispatcher )
			RequestDispatcher rd = request.getRequestDispatcher(  "/WEB-INF/views/index.jsp"  );
			rd.forward( request, response );
		}
	}

}
