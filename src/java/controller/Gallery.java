/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.DescriptionDAO;
import dal.GalleryDAO;
import dal.ImageDAO;
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
public class Gallery extends BaseController {

    private final GalleryDAO galleryDAO;
    private final ImageDAO imageDAO;

    public Gallery() {
        galleryDAO = new GalleryDAO();
        imageDAO = new ImageDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");

            String rawPage = request.getParameter("page") == null ? "1" : request.getParameter("page");
            int currentPage = 0, numberpage = 0;
            ArrayList<String> listImage = new ArrayList();
            currentPage = Integer.parseInt(rawPage);
            String rawGallery = request.getParameter("id");
            int galleryId = 0;
            galleryId = Integer.parseInt(rawGallery);

            //check gallery í exits in database
            if (!galleryDAO.checkExitsId(galleryId)) {
                request.setAttribute("error", "This gallery don't exits");
            }

            int numberImageInPage = 8;

            numberpage = imageDAO.getNumPage(numberImageInPage, galleryId);
            //If numberpage is greater than 0 then need to handle the current page. otherwise there is no need to handle it
            if (numberpage > 0) {
                //If the current page is larger than the number of pages, set the current page to be the maximum page
                if (currentPage > numberpage) {
                    currentPage = numberpage;
                    //If the current page is less than 0, set the current page to be the minimum page
                } else if (currentPage < 1) {
                    currentPage = 1;
                }

                listImage = imageDAO.getListImage(numberImageInPage, currentPage, galleryId);
            }

            GalleryInfo galleryInfo =  galleryDAO.getGalleryById(galleryId);

            String imageDisplay = request.getParameter("img");
            // set deafult image for gallery
            if (imageDisplay != null) {
                galleryInfo.setPicture(imageDisplay);
            }

            // set data for menu
            setMenuGallery(request);

            request.setAttribute("listImage", listImage);
            request.setAttribute("galleryInfo", galleryInfo);
            // for pagging
            request.setAttribute("numberPage", numberpage);
            request.setAttribute("currentPage", currentPage);

            request.getRequestDispatcher("gallery.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
