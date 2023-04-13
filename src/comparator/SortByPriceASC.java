package comparator;

import model.Product;

import java.util.Comparator;

public class SortByPriceASC implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
//        if(o1.getPrice() > o2.getPrice()){
//            return 1;
//        } else{
//            if(o1.getPrice() < o2.getPrice()){
//                return -1;
//            }
//        }
//        return 0;
        double result = o1.getPrice() - o2.getPrice();
        if (result == 0)
            return 0;
        return result > 0 ? 1 : -1;
    }
}
