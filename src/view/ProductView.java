package view;

import comparator.SortByPriceASC;
import model.Product;
import service.ProductDataService;
import service.ProductService;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ProductView {
    private ProductService productService;
    Scanner scn = new Scanner(System.in);
    private ProductDataService productDataService;

    public ProductView() {
        productService = new ProductService();
        productDataService = new ProductDataService();
    }

    public void renderMenu() {
        System.out.println("-----CHƯƠNG TRÌNH QUẢN LÍ SẢN PHẨM-----");
        System.out.println("Chọn chức năng theo số để tiếp tục");
        System.out.println("1. Xem danh sách");
        System.out.println("2. Thêm mới");
        System.out.println("3. Cập nhật");
        System.out.println("4. Xóa");
        System.out.println("5. Sắp xếp");
        System.out.println("6. Tìm kiếm sản phẩm có giá trị đắt nhất");
        System.out.println("7. Đọc từ file");
        System.out.println("8. Ghi từ file");
        System.out.println("9. Thoát");
    }

    public void launcher() {
        boolean checkAction = false;
        do {
            renderMenu();
            int actionMenuProduct = Integer.parseInt(scn.nextLine());
            switch (actionMenuProduct) {
                case 1:
                    showProductList(productService.getAllProduct());
                    break;
                case 2:
                    addNewProduct();
                    break;
                case 3:
                    updateProductById();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    menuSortByPrice();
                    break;
                case 6:
                    findHighestPriceProduct();
                    break;
                case 7:
                    readFile();
                    break;
                case 8:
                    writeFile();
                    break;
                case 9:
                    System.exit(0);
            }
            checkAction = checkActionContinue();
        } while (checkAction);
    }

    public void readFile() {
        showProductList(productDataService.getAllProduct());
    }

    public void writeFile() {
        System.out.println("Dữ liệu trong file sẽ mất sau khi bạn tiếp tục chức năng này");
        System.out.println("Nhấn Y để tiếp tục, nhấn bất kì phím nào để thoát chức năng này");
        boolean checkWrite = checkContinue();
        System.out.println(checkWrite);
        if (checkWrite) {
            List<Product> products = productService.getAllProduct();
            System.out.println(products);
            try {
                FileWriter writer = new FileWriter("./data/product.csv");
                PrintWriter printWriter = new PrintWriter(writer);
                for (Product product : products) {
                    printWriter.println(product.toString());
                }

                printWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void menuSortByPrice() {
        boolean checkAction = false;
        do {
            System.out.println("1. Hiển thị danh sách sản phẩm theo giá tăng dần");
            System.out.println("2. Hiển thị danh sách sản phẩm theo giá giảm dần");
            System.out.println("3. Quay về menu chính");
            System.out.println("Hãy nhập lựa chọn");
            int actionMenuProduct = Integer.parseInt(scn.nextLine());
            switch (actionMenuProduct) {
                case 1:
                    sortAscendingByPrice();
                    break;
                case 2:
                    sortDescendingByPrice();
                    break;
                case 3:
                    launcher();
                    break;
            }
            checkAction = checkActionContinue();
        } while (checkAction);
    }

    public void sortAscendingByPrice() {
        List<Product> list = productService.getAllProduct();

        Comparator<Product> myComparator;
        myComparator = new SortByPriceASC();
        list.sort(myComparator);
        showProductList(list);
    }

    public void sortDescendingByPrice() {
        List<Product> list = productService.getAllProduct();
        Comparator<Product> myComparator;
        myComparator = new SortByPriceASC();
        Collections.sort(list, myComparator);
        Collections.reverse(list);
        showProductList(list);
    }

    public void findHighestPriceProduct() {
        showProductView(productService.findHighestPriceProduct());
    }

    public void deleteProduct() {
        System.out.println("Enter ID's product you want to delete");
        long id = Long.parseLong(scn.nextLine());
        if (productService.findProductById(id) == null) {
            System.out.println("Khng tìm được sản phẩm với mã sản phẩm trên");
            return;
        }
        showProductView(productService.findProductById(id));
        if (checkContinueDelete()) {
            productService.deleteProductById(id);
            showProductList(productService.getAllProduct());
        }
    }

    public boolean checkContinue() {
        String choose = scn.nextLine();
        return choose.equals("Y");
    }

    public boolean checkContinueDelete() {
        System.out.println("Bạn có muốn xóa sản phẩm này? Nhấn Y để xóa, nhấn một phím bất kì để hủy");
        String choose = scn.nextLine();
        switch (choose) {
            case "Y":
                return true;
            default:
                return false;
        }
    }

    public void updateProductById() {
        showProductList(productService.getAllProduct());
        System.out.println("ID's product you want to edit");
        long idProduct = Long.parseLong(scn.nextLine());
        if (productService.findProductById(idProduct) == null) {
            System.out.println("Khng tìm được sản phẩm với mã sản phẩm trên");
            return;
        }
        editProduct(productService.findProductById(idProduct));
    }

    public void addNewProduct() {
        long idProduct = System.currentTimeMillis() % 1000;
        System.out.println("Nhập tên sản phẩm");
        String name = scn.nextLine();
        System.out.println("Nhập giá sản phẩm");
        double price = Double.parseDouble(scn.nextLine());
        System.out.println("Nhập số lượng sản phẩm");
        int quantity = Integer.parseInt(scn.nextLine());
        System.out.println("Nhập mô tả sản phẩm");
        String desc = scn.nextLine();
        Product newProduct = new Product(idProduct, name, price, quantity, desc);
        System.out.println("Hãy xem lại thông tin sản phẩm");
        System.out.println(newProduct.viewProduct());
        System.out.println("Lưu sản phẩm này? Y/N");
        String choice = scn.nextLine();
        choice = choice.trim().toUpperCase();
        switch (choice) {
            case "Y":
                productService.addProduct(newProduct);
                break;
            case "N":
                System.out.println("Edit(1) or cancel(2)? ");
                int edit = Integer.parseInt(scn.nextLine());
                if (edit == 1) {
                    editProduct(newProduct);
                    productService.addProduct(newProduct);
                } else break;
            default:
                break;
        }
    }

    public void editProduct(Product product) {
        System.out.println("You want to edit what?");
        System.out.println("1. Tên sản phẩm");
        System.out.println("2. Giá");
        System.out.println("3. Số lượng");
        System.out.println("4. Mô tả");
        System.out.println("Hãy chọn");
        int choice = Integer.parseInt(scn.nextLine());
        switch (choice) {
            case 1:
                System.out.println("Nhâập tên");
                product.setProductName(scn.nextLine());
                showProductView(product);
                break;
            case 2:
                System.out.println("Nhập giá");
                product.setPrice(Double.parseDouble(scn.nextLine()));
                showProductView(product);
                break;
            case 3:
                System.out.println("Nhập số lượng");
                product.setQuantity(Integer.parseInt(scn.nextLine()));
                showProductView(product);
                break;
            case 4:
                System.out.println("Nhập mô tả");
                product.setDesc(scn.nextLine());
                showProductView(product);
                break;
            default:
                break;
        }
    }

    public boolean checkActionContinue() {
        boolean checkActionContinue = false;
        do {
            System.out.println("Continue? Y/N");
            String choice = scn.nextLine().trim().toUpperCase();
            switch (choice) {
                case "Y":
                    return true;
                case "N":
                    return false;
                default:
                    checkActionContinue = true;
            }
        } while (checkActionContinue);
        return false;
    }

    public void showProductList(List<Product> productList) {
        System.out.printf("%5s|%15s|%10s|%10s|%-10s", "ID", "Product name", "Price", "Quantity", "Description");
        System.out.println();
        for (Product product : productList) {
            System.out.println(product.viewProduct());
        }
    }

    public void showProductView(Product product) {
        System.out.println(product.viewProduct());
    }
}
