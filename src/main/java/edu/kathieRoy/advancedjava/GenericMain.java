package edu.kathieRoy.advancedjava;

public class GenericMain {
    public static void main(String[] args) {
        /*
         * the ArbitraryTypeClass will hold Integers
         */
        ArbType<Integer> arbTypeI = new ArbType<Integer>(1234);
        Integer retI = arbTypeI.getInputStr();
        System.out.println(retI);

        ArbType<String> arbTypeS = new ArbType<String>("Cool");
        String ret = arbTypeS.getInputStr();
        System.out.println(ret.toString());

    }
}
