package cn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.dao.IUser;
import cn.daoimpl.UserImpl;
import cn.domain.User;

/**
 * Servlet implementation class LoginServlets
 */
@WebServlet("/LoginServlets")
public class LoginServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   @Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   req.setCharacterEncoding("UTF-8");
	   resp.setContentType("text/html;charset=utf-8");
	   String name=req.getParameter("username");
	   String password=req.getParameter("password");
	   IUser iu=new UserImpl();
	   User u=new User();
	  
	   u=iu.login(name,password);
	   PrintWriter out=resp.getWriter();
	   HttpSession session=req.getSession();
	   if(u!=null) {
		   if(name.equals(u.getName()) &&password.equals(u.getPassword())) {
			   ArrayList<User> list=new ArrayList<User>();
				list=iu.getlist();
				req.setAttribute("ulist", list);
				session.setAttribute("user", u);
				//session.setMaxInactiveInterval(3*60);
			  req.getRequestDispatcher("index.jsp").forward(req, resp);
		   }else {
			 //  System.out.println("密码或者账号错误");
		   }
	   }else {
		   out.write("<script>");
		   out.write("charset=\"UTF-8\";");
		   out.write("alert('用户名或密码错误');");
		   out.write("location.href=\"login.jsp\";");
		   out.write("</script>");
	   }
	   out.close();
	   
}

}
