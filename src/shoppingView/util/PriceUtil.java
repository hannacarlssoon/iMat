package shoppingView.util;

public class PriceUtil {

    public static String toPriceFormat(double price) {
        int priceInEars = (int) (price * 100);
        int crowns = priceInEars / 100;
        int ears = priceInEars % 100;
        String extraZero = (ears < 10) ? "0" : "";
        return crowns + ":" + ears + extraZero + " kr";
    }
}
