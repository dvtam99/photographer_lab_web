/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.DescriptionDAO;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TamDV
 */
public class NewFilter implements Filter {

    private FilterConfig filterConfig = null;
    private String link;
    private int count;
    private int add;

    public NewFilter() {
        DescriptionDAO descriptionDAO = new DescriptionDAO();
        link = "http://localhost:8080/J3.L.P0017/";
        try {
            count = descriptionDAO.getCount();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            count = 0;
        }
        add = 0;
    }

    protected void count(HttpServletRequest request) throws Exception {
        try {
            DescriptionDAO descriptionDAO = new DescriptionDAO();

            int amount = count + add;
            String countConvert = String.format("%06d", amount);
            String reverseRaw = new StringBuffer(countConvert).reverse().toString();
            String reverse[] = reverseRaw.split("");
            request.setAttribute("count", reverse);

            add++;
            if (add > 5) {
                descriptionDAO.addCount(amount + add);
                add = 0;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (filterConfig == null) {
            return;
        }
        String servletPath = req.getServletPath();
        //if user access to jsp file send it to correct servlet if system have that servlet, if can't, send it to home
        if (servletPath.endsWith(".jsp")) {
             if (servletPath.endsWith("home.jsp")) {
                res.sendRedirect(link + "home");
            } 
            if (servletPath.endsWith("contact.jsp")) {
                res.sendRedirect(link + "contact");
            } else if (servletPath.endsWith("gallery.jsp")) {
                res.sendRedirect(link + "gallery?id=1");
            } else {
                res.sendRedirect(link + "home");
            }
        } else {
            try {
                count(req);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
