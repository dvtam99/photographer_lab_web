/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import entity.Description;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author TamDV
 */
public class DescriptionDAO extends DBContext {

    private final String patch = "gallery/";

    // description
    public Description getDescription() throws Exception {
        Description description = null;
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String querry = "SELECT DescriptionText, ItalicText, DescriptionPicture FROM Description";
            cnn = getConnection();
            ps = cnn.prepareStatement(querry);
            rs = ps.executeQuery();
            while (rs.next()) {
                description = new Description(rs.getString("DescriptionText"), rs.getString("ItalicText"), patch + rs.getString("DescriptionPicture"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(rs, ps, cnn);
        }
        return description;
    }

    // get count to show the number of visits page
    public int getCount() throws Exception {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String querry = "SELECT [Count] FROM Description";
            cnn = getConnection();
            ps = cnn.prepareStatement(querry);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("Count");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(rs, ps, cnn);
        }
        return 0;
    }

    //update count
    public void updateCount(int amount) throws Exception {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String querry = "UPDATE Description SET [Count] = ?";
            cnn = getConnection();
            ps = cnn.prepareStatement(querry);
            ps.setInt(1, amount);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(rs, ps, cnn);
        }
    }
}
