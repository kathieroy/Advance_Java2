package edu.kathieRoy.advancedjava;

/* package whatever; // don't place package name! */

/* Name of the class has to be "Main" only if the class is public. */
class GenericMethod {
    public static void main(String[] args) throws java.lang.Exception {
        String[] input = {"1", "2", "3", "4"};
        String[] ret = GenericMethod.<String>swap(input, 2, 3);
        System.out.println("returned array is " + ret[0] + ret[1] + ret[2] + ret[3]);
    }

    public static <T> T[] swap(T[] input, int posa, int posb) {
        T holda = input[posa];
        input[posa] = input[posb];
        input[posb] = holda;
        return input;
    }
}