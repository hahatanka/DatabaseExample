package products;

import database.DBHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductRepository {
    DBHandler dbHandler = new DBHandler();

    public void create(Product product) throws SQLException {
        String query = "INSERT INTO products(name, price, quantity) VALUES(?,?,?)";
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
        preparedStatement.setString(1,product.name);
        preparedStatement.setFloat(2,product.price);
        preparedStatement.setInt(3,product.quantity);

        preparedStatement.execute();
        preparedStatement.close();
    }

    public ArrayList<Product> getAll() throws SQLException{
        String query = "SELECT * FROM products";
        Statement statement = dbHandler.getConnection().createStatement();
        ResultSet results = statement.executeQuery(query);

        ArrayList<Product> products = new ArrayList<>();

        while(results.next()){
            int id = results.getInt("id");
            String name = results.getString("name");
            Float price = results.getFloat("price");
            int quantity = results.getInt("quantity");

            Product product = new Product(name, price, quantity);
            products.add(product);

        }
        return products;

    }

    public ArrayList<Product> getProductByName(String name) throws SQLException{
        String query = "SELECT* FROM products WHERE name=?";

        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.execute();

        ResultSet results = preparedStatement.getResultSet();

        ArrayList<Product> products = new ArrayList<>();

        while(results.next()){ //by calling results.next, java checks the db table if there's any info available
            String product_name = results.getString("name");
            float price = results.getFloat("price");
            int quantity = results.getInt("quantity");

            Product product = new Product(product_name,price,quantity);
            products.add(product);
        }

        preparedStatement.close();
        return products;// after the while loop ends it returns the arraylist products
    }
    public Product findProductById(int id) throws SQLException{
        String query = "SELECT * FROM products WHERE id = " + id;

        Statement statement = dbHandler.getConnection().createStatement();
        ResultSet result = statement.executeQuery(query);

        if(result.next()) {
            int product_id = result.getInt("id");
            String product_name = result.getString("name");
            float price = result.getFloat("price");
            int quantity = result.getInt("quantity");

            Product product = new Product(product_name,price,quantity);
            product.id = product_id;
            statement.close();
            return product;
        }else{
            return null;
        }

    }

    public void update(Product product) throws SQLException{
        String query = "UPDATE products SET name=?, price =?, quantity =? WHERE id=? ";
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(query);
        preparedStatement.setString(1, product.name);
        preparedStatement.setFloat(2, product.price);
        preparedStatement.setInt(3, product.quantity);
        preparedStatement.setInt(4, product.id);

        preparedStatement.execute();
        preparedStatement.close();

    }

    public void delete(int id) throws  SQLException{
        String query = "DELETE FROM products WHERE id=?";

        PreparedStatement preparedStatement= dbHandler.getConnection().prepareStatement(query);
        preparedStatement.setInt(1,id);

        preparedStatement.execute();
        preparedStatement.close();
    }
}
