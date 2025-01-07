package dao;

import model.InventoryItem;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {

    public void addInventoryItem(InventoryItem item) throws SQLException {
        String query = "INSERT INTO inventory (name, type, stock, status, date_added) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.connectDB();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, item.getName());
            stmt.setString(2, item.getType());
            stmt.setInt(3, item.getStock());
            stmt.setString(4, item.getStatus());
            stmt.setDate(5, Date.valueOf(item.getDateAdded()));
            stmt.executeUpdate();
        }
    }

    public List<InventoryItem> getAllInventoryItems() throws SQLException {
        String query = "SELECT * FROM inventory";
        List<InventoryItem> items = new ArrayList<>();
        try (Connection connection = DatabaseConnection.connectDB();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                InventoryItem item = new InventoryItem(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getInt("stock"),
                        rs.getString("status"),
                        rs.getDate("date_added").toString()
                );
                items.add(item);
            }
        }
        return items;
    }

    public void deleteInventoryItem(int id) throws SQLException {
        String query = "DELETE FROM inventory WHERE id = ?";
        try (Connection connection = DatabaseConnection.connectDB();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void updateInventoryItem(InventoryItem item) throws SQLException {
        String query = "UPDATE inventory SET name = ?, type = ?, stock = ?, status = ?, date_added = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.connectDB();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, item.getName());
            stmt.setString(2, item.getType());
            stmt.setInt(3, item.getStock());
            stmt.setString(4, item.getStatus());
            stmt.setDate(5, Date.valueOf(item.getDateAdded()));
            stmt.setInt(6, item.getId());
            stmt.executeUpdate();
        }
    }
}
