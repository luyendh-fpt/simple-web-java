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
        String referrer = req.getHeader("referer");
        System.out.println("Redirect từ trang: " + referrer);
        req.setAttribute("referer", referrer);
        req.getRequestDispatcher("/member/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String inputPassword = req.getParameter("password");
        String inputUsername = req.getParameter("username");
        String referer = req.getParameter("referer");
        Student student = model.findByUsernameAndStatus(inputUsername, Student.Status.ACTIVE);
        if (student == null) {
            req.setAttribute("code", HttpServletResponse.SC_NOT_FOUND);
            req.setAttribute("message", "Tài khoản không tồn tại hoặc đã bị xoá.");
            req.setAttribute("content", "Vui lòng liên hệ ban quản trị để biết thêm chi tiết");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        } else {
            // mã hoá password với salt lấy ra từ database trước khi so sánh.
            if (inputPassword.equals(student.getPassword())) {
                HttpSession session = req.getSession();
                session.setAttribute(ApplicationConstant.CURRENT_LOGGED_IN, student);
                resp.sendRedirect(referer);
            } else {
                req.setAttribute("code", HttpServletResponse.SC_UNAUTHORIZED);
                req.setAttribute("message", "Sai thông tin tài khoản.");
                req.setAttribute("content", "Vui lòng liên hệ ban quản trị để biết thêm chi tiết");
                req.getRequestDispatcher("/error.jsp").forward(req, resp);
            }
        }


    }
}
