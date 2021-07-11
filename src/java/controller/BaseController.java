/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.GalleryDAO;
import entity.GalleryInfo;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author TamDV
 */
public abstract class BaseController extends HttpServlet {

    public void setMenuGallery(HttpServletRequest request) throws Exception {
        GalleryDAO galleryDAO = new GalleryDAO();
        ArrayList<GalleryInfo> menuGallery = new ArrayList<>();
        menuGallery = galleryDAO.getTop3Gallery();
        request.setAttribute("menuGallery", menuGallery);
    }
}