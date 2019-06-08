package com.t1708m.controller;

import com.t1708m.entity.Student;
import com.t1708m.model.StudentModel;
import com.t1708m.util.ApplicationConstant;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginController extends HttpServlet {

    private StudentModel model = new StudentModel();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hello Login");
        String message = (String) req.getAttribute("message");
        String message2 = (String) req.getAttribute("message2");
        System.out.println(message);
        System.out.println(message2);
//        Cookie[] cookies = req.getCookies();
//        if(cookies != null){
//            for (Cookie cookie :
//                    cookies) {
//                System.out.println(cookie.getName() + " - " + cookie.getValue() + " - " + cookie.getDomain());
//            }
//        }
//        HttpSession session =  req.getSession();
//        Student student = (Student) session.getAttribute("currentLoggedIn");
//        req.setAttribute("student", student);
        req.getRequestDispatcher("/member/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String inputPassword = req.getParameter("password");
        String inputUsername = req.getParameter("username");
        Student student = model.findByUsernameAndStatus(inputUsername, Student.Status.ACTIVE);
        if (student == null) {
            resp.setStatus(404);
            resp.getWriter().println("Tài khoản không tồn tại hoặc đã bị xoá.");
        } else {
            // mã hoá password với salt lấy ra từ database trước khi so sánh.
            if (inputPassword.equals(student.getPassword())) {
                HttpSession session = req.getSession();
                session.setAttribute(ApplicationConstant.CURRENT_LOGGED_IN, student);
                resp.setStatus(200);
                resp.getWriter().println("Login thành công");
            } else {
                resp.setStatus(401);
                resp.getWriter().println("Sai thông tin tài khoản.");
            }
        }
    }
}
