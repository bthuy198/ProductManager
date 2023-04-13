package service;

import model.Product;
import repository.file.ProductRepository;

import java.util.List;

public class ProductDataService {
    private ProductRepository productRepository;
    private ProductService productService;

    public ProductDataService() {
        productRepository = new ProductRepository();
        productService = new ProductService();
    }

    public List<Product> getAllProduct() {
        return productRepository.getAll();
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
    public void saveData(List<Product> list) {
        productRepository.addList(list);
    }
}
