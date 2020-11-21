package segura.taylor.ui;

import java.io.PrintStream;
import java.util.Scanner;

public class UI {
    private PrintStream output = new PrintStream(System.out);
    private Scanner input = new Scanner(System.in);

    public void imprimir(String mensaje) {
        System.out.print(mensaje);
    }

    public void imprimirLinea(String mensaje) {
        System.out.println(mensaje);
    }

    public String leerLinea() {
        return input.next();
    }
    public int leerEntero() {
        return input.nextInt();
    }
    public double leerDouble() {
        return input.nextDouble();
    }
}
