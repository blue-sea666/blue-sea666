package cn.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dao.IUser;
import cn.daoimpl.UserImpl;
import cn.domain.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
		IUser iu=new UserImpl();
       @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	   req.setCharacterEncoding("UTF-8");
    		String comm=req.getParameter("comm");
    		if("del".equals(comm)) {
    			delete(req,resp);
    		}else if("upd".equals(comm)) {
    			update(req,resp);
    		}else if("add".equals(comm)) {
    			insert(req,resp);
    		}
    		else{
    			getlist(req,resp);
    		}
    	}
    private void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
    	String name=req.getParameter("username");
		String password=req.getParameter("password");
		User u=new User();
		u.setName(name);
		u.setPassword(password);
		iu.inset(u);
		resp.sendRedirect("UserServlet");
	
		
	}
	private void update(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
		String id=req.getParameter("id");
		String name=req.getParameter("username");
		String password=req.getParameter("password");
		int ids=Integer.valueOf(id);
		User u=new User();
		u.setId(ids);
		u.setName(name);
		u.setPassword(password);
		iu.update(u);
		resp.sendRedirect("UserServlet");
	}
	private void getlist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<User> list=new ArrayList<User>();
		list=iu.getlist();
		req.setAttribute("ulist", list);
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	//删除
	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("id");
		int ids=Integer.valueOf(id);
		iu.delete(ids);
		resp.sendRedirect("UserServlet");
	}
}
