/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.DescriptionDAO;
import dal.GalleryDAO;
import entity.Description;
import entity.GalleryInfo;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TamDV
 */
public class Home extends BaseController {

    private final DescriptionDAO descriptionDAO;
    private final GalleryDAO galleryDAO;

    public Home() {
        descriptionDAO = new DescriptionDAO();
        galleryDAO = new GalleryDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            Description description = descriptionDAO.getDescription();
            
            String rawPage = request.getParameter("page") == null ? "1" : request.getParameter("page");
            int currentPage = 0, numberpage = 0;

            ArrayList<GalleryInfo> list = new ArrayList();

            currentPage = Integer.parseInt(rawPage);

            int numberGalleryInPage = 3;

            numberpage = galleryDAO.getNumPage(numberGalleryInPage);
            //If numberpage is greater than 0 then need to handle the current page. otherwise there is no need to handle it
            if (numberpage > 0) {
                //If the current page is larger than the number of pages, set the current page to be the maximum page
                if (currentPage > numberpage) {
                    currentPage = numberpage;
                    //If the current page is less than 0, set the current page to be the minimum page
                } else if (currentPage < 1) {
                    currentPage = 1;
                }

                list = galleryDAO.getListGallery(numberGalleryInPage, currentPage);
            }

            
            request.setAttribute("list", list);
           // set data for menu
            setMenuGallery(request);
            // set description
            request.setAttribute("description", description);
            // for pagging
            request.setAttribute("numberPage", numberpage);
            request.setAttribute("currentPage", currentPage);
            

            request.getRequestDispatcher("home.jsp").forward(request, response);
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
