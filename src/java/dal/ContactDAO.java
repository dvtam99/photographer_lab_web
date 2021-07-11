/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import entity.ContactInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author TamDV
 */
public class ContactDAO extends DBContext {

    public ContactInfo getContact() throws Exception {
        ContactInfo contact = null;
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String querry = "SELECT [Address], City, Country, Telephone, Email, Map FROM Contact";
            cnn = getConnection();
            ps = cnn.prepareStatement(querry);
            rs = ps.executeQuery();
            while (rs.next()) {
                contact = new ContactInfo(rs.getString("Address"), rs.getString("City"), rs.getString("Country"),
                        rs.getString("Telephone"), rs.getString("Email"), rs.getString("Map"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(rs, ps, cnn);
        }
        return contact;
    }
}
