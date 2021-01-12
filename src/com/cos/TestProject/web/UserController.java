package com.cos.TestProject.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.TestProject.domain.user.User;
import com.cos.TestProject.domain.user.dto.DeleteReqDto;
import com.cos.TestProject.domain.user.dto.JoinReqDto;
import com.cos.TestProject.domain.user.dto.LoginReqDto;
import com.cos.TestProject.service.UserService;
import com.cos.TestProject.util.Script;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cmd = request.getParameter("cmd");
		UserService userService = new UserService();

		if (cmd.equals("joinForm")) {
			response.sendRedirect("user/joinForm.jsp");

		} else if (cmd.equals("join")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String role = request.getParameter("role");

			JoinReqDto dto = new JoinReqDto();
			dto.setUsername(username);
			dto.setPassword(password);
			dto.setEmail(email);
			dto.setRole(role);
			System.out.println(dto);
			int result = userService.회원가입(dto);
			if (result == 1) {
				response.sendRedirect("index.jsp");
			} else {
				Script.back(response, "회원가입에 실패하였습니다.");
			}

		}

		else if (cmd.equals("loginForm")) {
			response.sendRedirect("user/loginForm.jsp");
		}

		else if (cmd.equals("login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			LoginReqDto dto = new LoginReqDto();
			dto.setUsername(username);
			dto.setPassword(password);
			User userEntity = userService.로그인(dto);
			if (userEntity != null) {
				HttpSession session = request.getSession();
				session.setAttribute("principal", userEntity); // 인증주체

				RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
				dis.forward(request, response);
				// response.sendRedirect("index.jsp");

			} else {
				Script.back(response, "로그인실패");
			}

		} else if (cmd.equals("list")) {
			List<User> users = userService.유저목록보기();
			System.out.println("users : "+users);
			request.setAttribute("users", users);
			RequestDispatcher dis = request.getRequestDispatcher("board/list.jsp");
			dis.forward(request, response);

		} else if (cmd.equals("logout")) {
			HttpSession session = request.getSession();
			session.invalidate();
			RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
			dis.forward(request, response);
		} else if(cmd.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("delete"+id);
			DeleteReqDto dto = new DeleteReqDto();
			dto.setId(id);
			int result = userService.글삭제(dto);
			if(result ==1) {
				PrintWriter out = response.getWriter();
				out.print("ok");
				out.flush();
			}
			
		}

	}

}
