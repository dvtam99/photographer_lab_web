/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import entity.GalleryInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author TamDV
 */
public class GalleryDAO extends DBContext {

    private final String patch = "gallery/";

    // lay 3 gallery dau tien
    public ArrayList<GalleryInfo> getTop3Gallery() throws Exception {
        ArrayList<GalleryInfo> galleryInfos = new ArrayList<>();
        String querry = "SELECT Top 3 Id, [Name] FROM Gallery";
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            ps = cnn.prepareStatement(querry);
            rs = ps.executeQuery();
            while (rs.next()) {
                GalleryInfo gallery = new GalleryInfo();
                gallery.setId(rs.getInt("Id"));
                gallery.setName(rs.getString("Name"));
                galleryInfos.add(gallery);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                closeConnection(rs, ps, cnn);
            } catch (Exception e) {
                throw e;
            }
        }
        return galleryInfos;
    }

    // find gallery by id
    public GalleryInfo getGalleryById(int gallery) throws Exception {
        String querry = "SELECT Name, DescriptionText, DescriptionPicture FROM Gallery WHERE id=?";
        GalleryInfo galleryInfo = null;
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            ps = cnn.prepareStatement(querry);
            ps.setInt(1, gallery);
            rs = ps.executeQuery();
            while (rs.next()) {
                galleryInfo = new GalleryInfo(gallery, rs.getString("Name"), rs.getString("DescriptionText"), patch + rs.getString("DescriptionPicture"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                closeConnection(rs, ps, cnn);
            } catch (Exception e) {
                throw e;
            }
        }
        return galleryInfo;
    }

    // lay list gallery theo pagging
    public ArrayList<GalleryInfo> getListGallery(int numberGalleryInPage, int pageCurrent) throws Exception {
        String querry = "SELECT * FROM (\n"
                + "SELECT ROW_NUMBER() OVER(ORDER BY id) as Number, Id, [Name], DescriptionText, DescriptionPicture FROM Gallery\n"
                + ") as DataSearch where Number between ? and ?";
        ArrayList<GalleryInfo> list = new ArrayList<>();
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            int to = pageCurrent * numberGalleryInPage;
            int from = to - numberGalleryInPage + 1;
            cnn = getConnection();
            ps = cnn.prepareStatement(querry);
            ps.setInt(1, from);
            ps.setInt(2, to);
            rs = ps.executeQuery();
            while (rs.next()) {
                GalleryInfo gallery = new GalleryInfo(rs.getInt("Id"), rs.getString("Name"), rs.getString("DescriptionText"), patch + rs.getString("DescriptionPicture"));
                list.add(gallery);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                closeConnection(rs, ps, cnn);
            } catch (Exception e) {
                throw e;
            }
        }
        return list;
    }

    // dem tong so gallery 
    public int getNumPage(int numberGalleryInPage) throws Exception {
        String querry = "SELECT COUNT(id) as num FROM Gallery";
        int numpage = 0;
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            ps = cnn.prepareStatement(querry);
            rs = ps.executeQuery();
            while (rs.next()) {
                int num = rs.getInt("num");
                if (num % numberGalleryInPage == 0) {
                    numpage = num / numberGalleryInPage;
                } else {
                    numpage = num / numberGalleryInPage + 1;
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                closeConnection(rs, ps, cnn);
            } catch (Exception e) {
                throw e;
            }
        }
        return numpage;
    }

    // kiem tra xem gallery co ton tai ko
    public boolean checkExitsId(int id) throws Exception {
        String querry = "SELECT * FROM Gallery WHERE id=?";
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            ps = cnn.prepareStatement(querry);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                closeConnection(rs, ps, cnn);
            } catch (Exception e) {
                throw e;
            }
        }
        return false;
    }
}
