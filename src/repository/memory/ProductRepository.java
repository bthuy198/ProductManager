package repository.memory;

import model.Product;

import java.util.ArrayList;


public class ProductRepository extends MemoryContext<Product> {
    @Override
    public void init() {
        list = new ArrayList<>();
        list.add(new Product(111, "Milk", 15000, 5, "milk"));
        list.add(new Product(123, "Butter", 10000, 5,"Butter"));
        list.add(new Product(124, "Candy", 20000, 5, "Candy"));
        list.add(new Product(456, "Cookie", 5000, 5, "Cookie"));
        list.add(new Product(354, "Sugar", 15000, 5, "Sugar"));
    }
}
