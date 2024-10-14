package moedas.conversor.challenge;

public record Pair(String result, String baseCode, String targetCode, double conversionRate) {

    public String getConversionMessage(double amount){
        double amountConverted = this.conversionRate * amount;
        String amountConvertedString = String.format("%,.2f", amountConverted);
        return ("--> " + amount + " " + this.baseCode + " equivale a "
                + amountConvertedString + " " + this.targetCode);
    }
}