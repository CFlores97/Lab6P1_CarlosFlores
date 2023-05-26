package lab6p1_carlosflores;

import java.util.Scanner;
import java.util.Random;

public class Lab6P1_CarlosFlores {

    static Scanner sc = new Scanner(System.in);
    static Random ran = new Random();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("1. ¿Cuántos primos tienes?\n2. Frecuencia de letras.\n3. Salir");
            int op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.println("Ingrese un tamanyo: ");
                    int size = sc.nextInt();

                    // Validacion de tamanyo
                    while (size < 1) {
                        System.out.println("el Tamanyo debe ser mayor a 1!");
                        size = sc.nextInt();
                    }

                    System.out.println("Ingrese un limite inferior: ");
                    int min = sc.nextInt();

                    System.out.println("Igrese un limite superior: ");
                    int max = sc.nextInt();

                    // Validacion de limites
                    while (min > max) {
                        System.out.println("El limite superior debe ser mayor que el inferior");

                        System.out.println("Ingrese un limite inferior: ");
                        min = sc.nextInt();

                        System.out.println("Igrese un limite superior: ");
                        max = sc.nextInt();
                    }

                    // guardar arreglo temporal generado en un arreglo definido
                    int[] arreglo_gen = new int[size];
                    arreglo_gen = genRandArray(size, min, max);

                    // Imprime arreglo generado
                    System.out.print("Arreglo generado: ");
                    print(arreglo_gen);

                    System.out.println();

                    // Imprime arreglo de No. de factores primos
                    System.out.print("No. divisores primos: ");
                    print(getTotalPrimeCount(arreglo_gen));

                    System.out.println();

                    break;

                case 2:
                    System.out.println("Ingrese una cadena: ");
                    String word = sc.next();
                    
                    // Validacion
                    for (int i = 0; i < word.length(); i++) {
                        char letter = word.charAt(i);

                        while (letter < 97 || letter > 122 && letter != 65533) {
                            System.out.println("Solo es permitido letras minusculas, ‘á’ a la ‘ú’, ‘ü’ y la 'ñ'."
                                    + "\nIngrese una cadena nuevamente: ");
                            word = sc.next();
                            break;
                        }
                    }

                    
                    // contiene el arreglo de la frecuencia
                    int[] num_rep = extraerFrecuencias(word);
                    
                    
                    // Imprime frecuencia
                    System.out.printf("Frecuencias para la palabra '%s': ", word);
                    System.out.println();
                    print(num_rep);
                    break;

                case 3:
                    running = false;
                    break;

                default:
                    break;
            }
        }
    }

    public static int[] genRandArray(int size, int min, int max) {
        int[] temp = new int[size];

        for (int i = 0; i < temp.length; i++) {
            temp[i] = min + ran.nextInt(max);

        }
        return temp;
    }

    public static void print(int[] arreglo) {
        for (int i = 0; i < arreglo.length; i++) {
            System.out.print("[" + arreglo[i] + "]");
        }
        System.out.println();
    }

    public static boolean isPrime(int num) {
        boolean is_prime = false;
        int c = 0;
        int c1 = 1;

        while (c1 <= num) {
            if (num % c1 == 0) {
                c++;
            }
            c1++;
        }

        if (c == 2) {
            is_prime = true;
        }

        return is_prime;
    }

    public static int countPrimeFactors(int num) {
        int num_factors = 0;
        int n;

        for (int i = 1; i <= num; i++) {
            n = num % i;

            if (isPrime(i) && n == 0) {
                num_factors++;
            }
        }
        return num_factors;
    }

    public static int[] getTotalPrimeCount(int[] arreglo) {
        int[] temp = arreglo;

        for (int i = 0; i < arreglo.length; i++) {
            temp[i] = countPrimeFactors(arreglo[i]);
        }

        return temp;
    }

    public static int[] extraerFrecuencias(String word) {
        int[] temp = new int[27];

        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            int pos;

            if (letter >= 97 && letter <= 122) {
                pos = letter - 97;
                temp[pos] += 1;
            } else if (letter == 65533) {
                temp[26] += 1;
            }
        }

        return temp;
    }

}
