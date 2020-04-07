package com.neuedu.Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuedu.pojo.ResponseBean;
import com.neuedu.pojo.SysRole;
import com.neuedu.service.IRoleService;
import com.neuedu.service.impl.RoleServiceImpl;
import com.neuedu.utils.UUIDUtil;

/**
 * Servlet implementation class AddRole
 */
@WebServlet("/addRole")
public class AddRole extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRole() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String roleName=request.getParameter("roleName");
		String roleSort=request.getParameter("roleSort");
		String status=request.getParameter("status");
		String menuids=request.getParameter("menuids");
		String[] menuArray=menuids.split(",");
		String roleId=UUIDUtil.generateUUID();
		
		System.out.println("id数组为"+menuids);
		System.out.println("id为"+roleId);
		System.out.println("角色名称"+roleName);
		System.out.println("显示排序"+roleSort);
		System.out.println("显示状态"+status);
		
		
		
		
		SysRole role=new SysRole();
		role.setRoleId(roleId);
		role.setRoleName(roleName);
		role.setRoleSort(roleSort);
		role.setStatus(status);
		role.setDelFlag("0");
		
		IRoleService rs=new RoleServiceImpl();
		ResponseBean rb = null;
	    try {
			rb = rs.add(role,menuArray);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
		ObjectMapper om=new ObjectMapper();
		String json=om.writeValueAsString(rb);
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
