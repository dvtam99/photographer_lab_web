/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.DescriptionDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CounterFilter implements Filter {

    private FilterConfig filterConfig = null;
    private String link;
    private int count = 0;
    DescriptionDAO descriptionDAO = new DescriptionDAO();

    protected void formatCouter(HttpServletRequest request, int newCount) throws Exception {
        try {

            String countConvert = String.format("%06d", newCount); //change to 000000
            String reverseRaw = new StringBuffer(countConvert).reverse().toString();
            String reverse[] = reverseRaw.split("");
            request.setAttribute("count", reverse);

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            if (filterConfig == null) {
                return;
            }
            String servletPath = req.getServletPath();
            int newCount = 0;
            if (servletPath.equals("/home")) {
                // neu vao home thi tang count
                newCount = count++;
                descriptionDAO.updateCount(newCount);

            } else {
                newCount = count;
            }
            formatCouter(req, newCount);

            chain.doFilter(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CounterFilter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            this.filterConfig = filterConfig;
            count = descriptionDAO.getCount();
        } catch (Exception ex) {
            Logger.getLogger(CounterFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
