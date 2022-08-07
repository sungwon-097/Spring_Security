package com.example.jwt_demo.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter1 implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Token : id, pw 가 정상적으로 들어와서 로그인이 완료 되면 토큰을 만들어주고 응답을 해줌
        // 요청 할 떄마다 header 에 Authorization 에 value 값으로 토큰을 가지고 옴
        // 내가 맞는 토큰이 맞는지 검증함
        if(req.getMethod().equals("POST")) {
            System.out.println("POST");
            String headerAuth = req.getHeader("Authorization");
            System.out.println(headerAuth);
            System.out.println("Filter 1");
            if (headerAuth.equals("Basic Og==")) { // Authorization 값이 이와 같을 때 체인에 등록, 아니면 메시지 출력
                chain.doFilter(req, res);
            } else {
                PrintWriter out = res.getWriter();
                out.println("not authorized");
            }
        }
    }
}