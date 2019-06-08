package com.t1708m.filter;

import com.t1708m.entity.Student;
import com.t1708m.util.ApplicationConstant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // xử lý lấy thông tin người dùng trong trường hợp viết api.
        // chỗ này ko chỉ đơn giản là lấy từ session, mà còn có thể phải kiểm tra token từ database.
        HttpSession session = request.getSession();
        Student currentLoggedIn = (Student) session.getAttribute(ApplicationConstant.CURRENT_LOGGED_IN);
        if (currentLoggedIn != null) {
            System.out.println("Authenticated request. Logged in user.");
            request.setAttribute(ApplicationConstant.CURRENT_LOGGED_IN, currentLoggedIn);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
