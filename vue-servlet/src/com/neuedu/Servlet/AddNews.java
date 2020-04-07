package com.neuedu.Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuedu.pojo.News;
import com.neuedu.pojo.ResponseBean;
import com.neuedu.pojo.SysRole;
import com.neuedu.service.INewsservice;
import com.neuedu.service.IRoleService;
import com.neuedu.service.impl.NewsServiceImpl;
import com.neuedu.service.impl.RoleServiceImpl;
import com.neuedu.utils.UUIDUtil;

/**
 * Servlet implementation class AddNews
 */
@WebServlet("/addNews")
public class AddNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNews() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newsname=request.getParameter("newsName");
		String newscontent=request.getParameter("newscontent");
		String visible=request.getParameter("visible");
		String ordernum=request.getParameter("orderNum");
		
		//У��ǰ̨����������Ƿ��õ�
		System.out.println("��������Ϊ"+newsname);
		System.out.println("��������"+newscontent);
		System.out.println("����"+visible);
		System.out.println("��ʾ����"+ordernum);
		
		
		
		
		News news=new News();
		news.setNewsname(newsname);
		news.setNewscontent(newscontent);
		news.setVisible(visible);
		news.setOrdernum(ordernum);
		int i=0;
		INewsservice ns=new NewsServiceImpl();
		try {
			i=ns.add(news);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ResponseBean resp=new ResponseBean();
		
		if (i != 0) {
			resp.setCode("0");
			resp.setMsg("�޸ĳɹ�");
		} else {
			resp.setCode("-1");
			resp.setMsg("�޸�ʧ��");
			
		}
		System.out.println(resp);
		ObjectMapper om=new ObjectMapper();
		String json=om.writeValueAsString(resp);
		System.out.println(json);
		response.getWriter().print(json);
	    
	    
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
