package icd0019types.src.types;

public class StackTrace {

    public static void main(String[] args) {
        Double price = calculatePrice();
        System.out.println("Final price: " + price);
    }

    public static Double calculatePrice() {
        Double basePrice = calculateBasePrice();
        return basePrice * (1 + 0.2);
    }

    public static Double calculateBasePrice() {
        Double netCost = 100D;
        Integer profitConstant = readProfitConstant();

        if (profitConstant == null) {
            profitConstant = 1; // default value to prevent NullPointerException
        }

        return netCost + (0.1 * profitConstant * netCost);
    }

    public static Integer readProfitConstant() {
        // Original code returns null
        Integer result = null;
        return result; //k
    }

}
