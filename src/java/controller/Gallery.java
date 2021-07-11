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
            Description description = null;
            DescriptionDAO descriptionDAO = new DescriptionDAO();
            try {
                description = descriptionDAO.getDescription();
            } catch (Exception e) {
                request.setAttribute("error", "Some errors have occurred, some information cannot be displayed");
            }
            request.setAttribute("description", description);

            String rawPage = request.getParameter("page") == null ? "1" : request.getParameter("page");
            int currentPage = 0, numberpage = 0;
            ArrayList<String> list = new ArrayList();

            try {
                currentPage = Integer.parseInt(rawPage);
            } catch (NumberFormatException e) {
                currentPage = 1;
                request.setAttribute("error", "Some errors have occurred, some information cannot be displayed");
            }

            String rawGallery = request.getParameter("id");
            int gallery = 0;
            try {
                gallery = Integer.parseInt(rawGallery);
            } catch (Exception e) {
                request.setAttribute("error", "Some errors have occurred, some information cannot be displayed");
            }

            try {
                //check gallery í exits in database
                if (!galleryDAO.checkExitsId(gallery)) {
                    request.setAttribute("error", "This gallery don't exits");
                }
            } catch (Exception e) {
                request.setAttribute("error", "Some errors have occurred, some information cannot be displayed");
            }

            int numberImageInPage = 8;
            try {
                numberpage = imageDAO.getNumPage(numberImageInPage, gallery);
                //If numberpage is greater than 0 then need to handle the current page. otherwise there is no need to handle it
                if (numberpage > 0) {
                    //If the current page is larger than the number of pages, set the current page to be the maximum page
                    if (currentPage > numberpage) {
                        currentPage = numberpage;
                        //If the current page is less than 0, set the current page to be the minimum page
                    } else if (currentPage < 1) {
                        currentPage = 1;
                    }

                    list = imageDAO.getListImage(numberImageInPage, currentPage, gallery);
                }
            } catch (Exception e) {
                request.setAttribute("error", "Some errors have occurred, some information cannot be displayed");
            }

            ArrayList<String> up = new ArrayList();
            ArrayList<String> down = new ArrayList();

            int elementInTopAndBottom = numberImageInPage / 2;
            for (int i = 0; i < elementInTopAndBottom && i < list.size(); i++) {
                up.add(list.get(i));
            }
            for (int i = elementInTopAndBottom; i < numberImageInPage && i < list.size(); i++) {
                down.add(list.get(i));
            }

            GalleryInfo galleryInfo = null;
            try {
                galleryInfo = galleryDAO.getGalleryById(gallery);
            } catch (Exception e) {
                request.setAttribute("error", "Some errors have occurred, some information cannot be displayed");
            }
            String imageDisplay = request.getParameter("img");
            //if yser don't chose any image before, set deafult image for gallery
            if (imageDisplay != null) {
                galleryInfo.setPicture(imageDisplay);
            }
            try {
                setMenuGallery(request);
            } catch (Exception e) {
                request.setAttribute("error", "Some errors have occurred, some information cannot be displayed");
            }

            request.setAttribute("gallery", gallery);
            request.setAttribute("up", up);
            request.setAttribute("down", down);
            request.setAttribute("galleryInfo", galleryInfo);
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
