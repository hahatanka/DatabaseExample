
import products.Product;
import products.ProductController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ProductController productController = new ProductController();

//        System.out.println(productController.createProduct(new Product("Rice",19.52f, 20)));
//        System.out.println(productController.createProduct(new Product("Milk",5.56f, 30)));
//        System.out.println(productController.createProduct(new Product("Bread",1.55f, 70)));

        System.out.println(productController.getAllProducts());

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nenter the name of the product you want to find:");

        String product_name = scanner.nextLine();
        System.out.println( productController.findProducts(product_name));

        Scanner scanner2 = new Scanner(System.in);

        System.out.println("\nenter the ID of the product you want to find:");

        int product_id = scanner2.nextInt();
        System.out.println( productController.findProduct(product_id));


        System.out.println("\nenter the ID of the product you want to change:");

        int product_id2 = scanner2.nextInt();
      Product product = productController.findProduct(product_id2);
      product.name = "wine";
      productController.updateProduct(product);

        System.out.println("\nenter the ID of the product you want t delete:");

        int product_id3 = scanner2.nextInt();
        productController.deleteProd(product_id3);




    }

}

