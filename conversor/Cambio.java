package conversor;
import java.util.Arrays;
import java.text.NumberFormat;
import java.util.Locale;

public class Cambio {

    static String[] moedas = { "USD", "EUR", "JPY", "GBP", "BRL" };
  static double[] taxaDolar = { 1.00, 0.85, 110.0, 0.75, 5.4 };

  public static String convert(double amount, String fromCurrency, String toCurrency) {
    try {
      fromCurrency = fromCurrency.toUpperCase();
      toCurrency = toCurrency.toUpperCase();

      int fromCurrencyIndex = findCurrencyIndex(fromCurrency);
      int toCurrencyIndex = findCurrencyIndex(toCurrency);

      if (fromCurrencyIndex == -1 || toCurrencyIndex == -1) {
        return "Moeda não suportada para converção.";
      }

      double amoutToDolar = amount / taxaDolar[fromCurrencyIndex];
      double amountToConvert = amoutToDolar * taxaDolar[toCurrencyIndex];

      String amoutToConvertFormatted = formatCurrency(amountToConvert, toCurrency);

      String formattedConversion = String.format("%.2f %s em %s: %s \n", amount, fromCurrency, toCurrency,                              amoutToConvertFormatted);

      return formattedConversion;
    } catch (IllegalArgumentException e) {
      String error = String.format("Erro: " + e.getMessage());
      System.out.println(error);
      return error;
    }
  }

  public static int findCurrencyIndex(String currency) {
    int index = Arrays.asList(moedas).indexOf(currency);

    if (index != -1) {
      return index;
    }
    System.out.println("A moeda " + currency + " não foi encontrada.");
    return -1;
  }

  public static String formatCurrency(double amount, String toCurrency) {
    Locale locale;
    if (toCurrency.equals("USD")) {
      locale = new Locale("en", "US");
    } else if (toCurrency.equals("EUR")) {
      locale = Locale.FRANCE;
    } else if (toCurrency.equals("JPY")) {
      locale = new Locale("ja", "JP");
    } else if (toCurrency.equals("GBP")) {
      locale = new Locale("en", "GB");
    } else if (toCurrency.equals("BRL")) {
      locale = new Locale("pt", "BR");
    } else {
      throw new IllegalArgumentException("Moeda desconhecida: " + toCurrency);
    }
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

    return currencyFormatter.format(amount);
  }
}
