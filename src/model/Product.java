package model;

import repository.IModel;
import repository.ISearch;

import java.util.Date;

public class Product implements IModel<Product>, ISearch<Product> {
    protected long id;
    protected String productName;
    protected double price;
    protected int quantity;
    protected String desc;

    public Product() {
    }

    public Product(long id, String productName, double price, int quantity, String desc) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.desc = desc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void updateProduct(Product product) {
        this.setProductName(product.getProductName());
        this.setPrice(product.getPrice());
        this.setQuantity(product.getQuantity());
        this.setDesc(product.getDesc());
    }

    public Product parseData(String line) {
        Product product = new Product();

        String[] productInfo = line.split(",");
        //long idProduct, String productName, double price, int quantity, string desc
        long idProduct = Long.parseLong(productInfo[0]);
        String productName = productInfo[1];
        double price = Double.parseDouble(productInfo[2]);
        int quantity = Integer.parseInt(productInfo[3]);
        String desc = productInfo[4];

        product.setId(idProduct);
        product.setProductName(productName);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setDesc(desc);

        return product;
    }

    public String viewProduct() {
        return String.format("%5s|%15s|%10s|%10s|%-10s", id, productName, price, quantity, desc);
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s", id, productName, price, quantity, desc);
    }
}
