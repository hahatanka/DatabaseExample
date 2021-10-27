package products;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductController {
    ProductRepository productRepository = new ProductRepository();

    public String createProduct(Product product){
        try {
            productRepository.create(product);
            return "Product created successfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return "error inserting product in database";
        }

    }

    public ArrayList<Product>getAllProducts(){

        try {
            return productRepository.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Product> findProducts(String name){
        try{
            return productRepository.getProductByName(name);
        }catch (SQLException e){
            System.out.println("cannot find product from database" );
            e.printStackTrace();
            return null;
        }
    }
    public Product findProduct(int id){
        try{
            return productRepository.findProductById(id);
        }catch (SQLException e){
            System.out.println("cannot find product from database" );
            e.printStackTrace();
            return null;
        }
    }

    public void updateProduct(Product product){
        try{
            productRepository.update(product);
            System.out.println("updated successfully");
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    public void deleteProd(int id){
        try {
            productRepository.delete(id);
            System.out.println("deleted successfully");
        }catch(SQLException e){
            System.out.println(e);
        }
    }

}
