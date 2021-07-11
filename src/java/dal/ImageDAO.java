/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author TamDV
 */
public class ImageDAO extends DBContext {

    private final String patch = "gallery/";

    public ArrayList<String> getListImage(int numberGalleryInPage, int pageCurrent, int gallery) throws Exception {
        String querry = "SELECT Name FROM (\n"
                + "SELECT ROW_NUMBER() OVER(ORDER BY id) as Number, [Name] FROM [Image] WHERE Gallery = ?\n"
                + ") as DataSearch where Number between ? and ?";
        ArrayList<String> list = new ArrayList<>();
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            int to = pageCurrent * numberGalleryInPage;
            int from = to - numberGalleryInPage + 1;
            cnn = getConnection();
            ps = cnn.prepareStatement(querry);
            ps.setInt(1, gallery);
            ps.setInt(2, from);
            ps.setInt(3, to);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(patch + rs.getString("Name"));
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

    public int getNumPage(int numberGalleryInPage, int gallery) throws Exception {
        String querry = "SELECT COUNT(id) as num FROM [Image] WHERE Gallery = ?";
        int numpage = 0;
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            ps = cnn.prepareStatement(querry);
            ps.setInt(1, gallery);
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
}
