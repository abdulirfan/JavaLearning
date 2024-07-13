package PrimeNumber;

import java.util.Scanner;

public class PrimeNumber {

    static void findIfGivenNumberIsPrime() {
        System.out.print("Enter a number: ");
        int givenNumber = new Scanner(System.in).nextInt();

        if (givenNumber <= 1) {
            System.out.println(givenNumber + " is not a prime number");
        } else {

            for (int i = 2; i <= givenNumber / 2; i++) {
                if (givenNumber % i == 0) {
                    System.out.println(givenNumber + " is not a prime number");
                    return;
                }
            }
            System.out.println(givenNumber + " is prime number");
        }
    }
    public static void main(String[] args) {
        findIfGivenNumberIsPrime();
    }
}
