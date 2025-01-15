package exceptions;

import java.util.Scanner;

public class ExceptionTask {
    static int i=0;
    public static void main(String[] args) {
        getResult();
    }

    static void getResult(){
        Scanner sc = new Scanner(System.in);
        int[] ages = new int[5];
        while(true) {
            try {
                System.out.print("Enter age: ");
                String age = sc.nextLine();
                if(age.equals("exit") || age.equals("stop")) {
                    break;
                }

                int ageInt = Integer.parseInt(age);

                if(ageInt < 0) {
                    throw new IllegalArgumentException("Age cannot be negative");
                }
                if(ageInt > 200) {
                    throw new ArithmeticException("Age is too large");
                }

                ages[i++] = ageInt;

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
            }
        }
        //entered ages
        System.out.println("\n== FUNCTIONALITIES ==\n");
        System.out.print("Entered Ages: [");


        for (int j = 0; j < i; j++) {
            System.out.print(ages[j]);
            if(j != i - 1) {
                System.out.print(", ");
            }
            if(j == i - 1) {
                System.out.print("]");
            }
        }
        System.out.println();
        //details for age
        System.out.println("\nDetails for each age: ");


        for (int j = 0; j < i; j++) {
            try {
                long factorial = calculateFactorial(ages[j]);
                String evenOdd = isEven(ages[j]) ? "Even" : "Odd";
                System.out.println("- Age: " + ages[j] + " -> Factorial: " + factorial + ", " + evenOdd);
            } catch (ArithmeticException e) {
                System.out.println("- Age: " + ages[j] + " -> " + e.getMessage());
            }
        }


        //statistical summary
        System.out.println("\nStatistical Summary: ");
        getStatistics(ages, i);
    }

    static int factorial(int n)
    {
        if (n == 0 || n == 1) return 1;

        // Otherwise we do n*(n-1)!
        return n * factorial(n - 1);
    }

    //factorial
    public static long calculateFactorial(int age) throws ArithmeticException {
        long result = 1;
        for (int j = 1; j <= age; j++) {
            if(result > Long.MAX_VALUE / j) {
                throw new ArithmeticException("Factorial too large to calculate");
            }
            result *= j;
        }
        return result;
    }

    //even or odd
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    // get statistics
    public static void getStatistics(int[] ages, int count) {
        int totalPeople = i;
        int sum = 0;
        for (int j = 0; j < i; j++) {
            sum += ages[j];
        }
        double average = (double)sum / totalPeople;
        //The program should display the count of people in each age group.
        int childen = 0;
        int teenagers = 0;
        int adults = 0;
        int seniors = 0;
        for (int j = 0; j < totalPeople; j++) {
            if(ages[j] <= 12 && ages[j] >= 0) childen++;

            if(ages[j] > 12 && ages[j] <= 19) {
                teenagers++;
            }
            if(ages[j] > 19 && ages[j] <= 64) {
                adults++;
            }
            if(ages[j] > 64) {
                seniors++;
            }
        }
        System.out.println("- Total number of people: " + totalPeople);
        System.out.println("- Sum of ages: " + sum);
        System.out.println("- Average of ages: " + average);
        System.out.println("- Age Group Statistics: ");
        if(childen > 0)
            System.out.println("* Childen: " + childen);
        if(teenagers > 0)
            System.out.println("* Teenagers: " + teenagers);
        if(adults > 0)
            System.out.println("* Adults: " + adults);
        if(seniors > 0)
            System.out.println("* Seniors: " + seniors);
    }
}
