package com.t1708m.filter;

import com.t1708m.entity.Student;
import com.t1708m.util.ApplicationConstant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VipMemberFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Student student = (Student) request.getAttribute(ApplicationConstant.CURRENT_LOGGED_IN); // hơi trùng với việc lấy từ session.
        if (student != null
                && student.isVipMember()) {
            filterChain.doFilter(request, response);
        } else {
            request.setAttribute("code", HttpServletResponse.SC_FORBIDDEN);
            request.setAttribute("message", "Permission deny.");
            request.setAttribute("content", "Bạn không có quyền truy cập trang này. Vui lòng đóng tiền.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
