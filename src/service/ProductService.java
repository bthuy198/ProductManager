package service;

import model.Product;
import repository.memory.ProductRepository;

import java.util.List;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService() {
        productRepository = new ProductRepository();
    }

    public List<Product> getAllProduct() {
        return productRepository.getAll();
    }
    public void updateFile(List<Product> list){

    }

    public List<Product> searchProductByName(String name) {
        return productRepository.searchByName(name);
    }

    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }

    public Product findProductById(long id) {
        return productRepository.findById(id);
    }

    public Product findProductByName(String name) {
        return productRepository.findByName(name);
    }

    public void addProduct(Product newProduct) {
        productRepository.add(newProduct);
    }

    public Product findHighestPriceProduct() {
        List<Product> list = getAllProduct();
        double highest = list.get(0).getPrice();
        Product highestPrice = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPrice() > highest) {
                highest = list.get(i).getPrice();
                highestPrice = list.get(i);
            }
        }
        return highestPrice;
    }
}
