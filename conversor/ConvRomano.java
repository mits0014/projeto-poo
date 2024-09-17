package conversor;

public class ConvRomano {

    public static String toRoman(int entrada) {
        int[] vaNum = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String resultado = "";
        String[] vaRom = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        int numero = entrada;
        if (numero == 0) {
            return "não exite zero em romano";
        }
        int i = 0;
        while (numero > 0) {
            if (numero >= vaNum[i]) {
                resultado += vaRom[i];
                numero -= vaNum[i];
            } else {
                i++;
            }
        }
        return resultado;
    }

}
