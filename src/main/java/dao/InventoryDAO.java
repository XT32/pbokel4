import java.sql.*;
import java.util.*;

public class InventoryDAO {

    private Connection connection;

    // Konstruktor untuk koneksi ke database
    public InventoryDAO() {
        try {
            String url = "jdbc:mysql://localhost:3306/your_database_name"; // Ganti dengan database yang sesuai
            String user = "your_database_user"; // Ganti dengan username
            String password = "your_database_password"; // Ganti dengan password
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Menambahkan item ke inventory
    public void addInventoryItem(InventoryItem item) {
        String query = "INSERT INTO inventory (name, type, stock, status, date_added) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, item.getName());
            stmt.setString(2, item.getType());
            stmt.setInt(3, item.getStock());
            stmt.setString(4, item.getStatus());
            stmt.setString(5, item.getDateAdded());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Mengambil semua item dari inventory
    public List<InventoryItem> getAllInventoryItems() {
        List<InventoryItem> items = new ArrayList<>();
        String query = "SELECT * FROM inventory";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                int stock = rs.getInt("stock");
                String status = rs.getString("status");
                String dateAdded = rs.getString("date_added");
                items.add(new InventoryItem(id, name, type, stock, status, dateAdded));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    // Menutup koneksi
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
