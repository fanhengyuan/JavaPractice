package com.servlet;

import com.test.HibernateTest;
import com.utils.Tools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Date;
import java.text.SimpleDateFormat;

@WebServlet("/demoServlet")
public class DemoServlet extends HttpServlet {
    int count = 0;

    synchronized void addCount() {
        count++;
    }

    public void init() throws ServletException {
        System.out.println("我被初始化了");
    }

    /**
     * 全局调用
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        addCount();

        PrintWriter out = response.getWriter();

        String a = request.getParameter("a");
        if( !"".equals(a) && a != null ) {
//            System.out.println(a);
            out.println(a);
        }

        //全路径
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        out.println(basePath);
        out.println("第 " + count + " 次访问");

        // ip 地址
        String ip = Tools.getIpAddress(request);
        out.println(ip);

        Date date = new Date();
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String name = time.format(date);
        out.println(time.format(date));

        // 测试
        HibernateTest.getInstance().test1(ip, name, count);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
