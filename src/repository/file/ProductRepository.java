package repository.file;

import model.Product;

public class ProductRepository extends FileContext<Product> {
    public ProductRepository() {
        filePath = "./data/product.csv";
        tClass = Product.class;
    }
}
