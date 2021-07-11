/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.ContactDAO;
import entity.ContactInfo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TamDV
 */
public class Contact extends BaseController {

    private final ContactDAO contactDAO;

    public Contact() {
        contactDAO = new ContactDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            ContactInfo contact = null;
            try {
                contact = contactDAO.getContact();
            } catch (Exception e) {
                request.setAttribute("error", "Some errors have occurred, some information cannot be displayed");
            }
            int curretItemMenu = 2;
            request.setAttribute("curretItemMenu", curretItemMenu);
            request.setAttribute("contact", contact);
            try {
                setMenuGallery(request);
            } catch (Exception e) {
                request.setAttribute("error", "Some errors have occurred, some information cannot be displayed");
            }
            request.getRequestDispatcher("contact.jsp").forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher("error/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
