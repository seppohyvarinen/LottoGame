package fi.tuni.tamk.tiko.hyvarinenseppo;

import fi.tuni.tamk.tiko.hyvarinenseppo.util.Math;
import fi.tuni.tamk.tiko.hyvarinenseppo.util.Arrays;
import java.io.Console;
import fi.tuni.tamk.tiko.hyvarinenseppo.util.MyConsole;


/**
* A game that calculates how long it will take to win the Lotto jackpot.
*
* The game calculates and displays how long it takes to get certain amount of numbers right in lottery and only stops if the jackpot is won within lifetime (120 years).
* The game is played from command line interface, it is possible to input numbers from the cmd or then one by one after starting the application.
* The Lotto class has the main method that uses a variety different classes and methods in achieving it's functionality.
*
*@author Seppo Hyvarinen
 */



public class Lotto {

    private static int[] lotteryTicket = new int[7];
    private static int[] lotteryNumbers = new int [40];
    private static int[] lotto = new int[7];
    
    private static int weeks = 0;
    private static int years = 0;
    private static int correctNumbers = 1;

    private static boolean numbersAreUnique = false;
    private static boolean numbersEnteredFromCmd = false;
    private static boolean displayLotto = false;
    
    private static Console c = System.console();
    
    public static void main(String [] args) {
        
        fillTheLotto();

        if (checkArgs(args)) {
            numbersEnteredFromCmd = true;
        }  else {
            for (int i = 0; i < lotteryTicket.length; i++) {
                lotteryTicket[i] = 0;
            }
        }
        
        userInput();

        
        boolean wonInLifeTime = false;

        while (!wonInLifeTime) {
            lotto = calculateLotto();
            
            fillTheLotto();
            
            counter();

            if (Arrays.containsSameValues(lotteryTicket, lotto) == correctNumbers) {
                if (displayLotto) {
                    printLotto(lotteryTicket, "User lotto: ");
                    printLotto(lotto, "Random lotto: ");
                }
                System.out.println("You got " + correctNumbers + " right! Took only "+years+ " years!");
                correctNumbers++;
            }
            if (years <= 120 && Arrays.containsSameValues(lotteryTicket, lotto) == 7) {
                wonInLifeTime = true;
            }  else if (Arrays.containsSameValues(lotteryTicket, lotto) == 7) {
                System.out.println("Y O U  W O N .... It took more than a lifetime but let's try again!");
                System.out.println();
                years = 0;
                weeks = 0;
                correctNumbers = 1;
            }  
        }

        System.out.println("W O W ----- YOU ACTUALLY DID IT!");

    }

    /**
    * Fills the int [] array containing all the available lottery numbers.
    */

    private static void fillTheLotto() {
        for (int i = 0; i<lotteryNumbers.length; i++) {
            lotteryNumbers[i] = i+1;
        }
    }

    /**
    * Calculates and returns an int[] array containing weekly lottery numbers.
    *
    *@return returns an int[] array containing the weekly lottery numbers.
    */

    private static int[] calculateLotto() {
        int[] availableNumbers = lotteryNumbers; 
        int[] weeklyNumbers = new int[7];

        for (int i = 0; i<weeklyNumbers.length; i++) {
            int n = Math.getRandom(0, availableNumbers.length-1);
            weeklyNumbers[i] = availableNumbers[n];
            availableNumbers = Arrays.removeIndex(availableNumbers, n);
        }
        
        return weeklyNumbers;
    }

    /**
    * Prints the user interface and queries the desired int numbers for users lotteryticket.
    *
    * Uses readInt method from MyConsole class to make sure numbers are in the permitted range and that all the tickets numbers are unique.
    * Method also contains display settings.
    *
    */

    private static void userInput () {

        int numbersLeftToChoose = 7;
            
        if (!numbersEnteredFromCmd) {
            for (int i = 0; i<lotteryTicket.length; i++) {
            numbersAreUnique = false;
            System.out.println("Please enter a unique number between 1 and 40 (" + numbersLeftToChoose + " numbers left to choose)");         
            
                while (!numbersAreUnique) {
                    int n = MyConsole.readInt(1, 40, "Please enter a number without letters", "Please enter a numeric value between 1 and 40");
                    if (!(Arrays.contains(n, lotteryTicket))) {
                        lotteryTicket[i] = n;
                        numbersLeftToChoose--;
                        numbersAreUnique = true;                    
                    }  else {
                        System.out.println ("You already have number "+n+ " in your lottery ticket! Please enter a new number.");
                    }                 
                }                       
            }
        }
        
        
        
        boolean displayCheck = false;
        while (!displayCheck) {
            
            System.out.println("Do you wish to display the winning lines?(y/n)");
            String d = c.readLine();
            if (d.equals("y")) {
                displayLotto = true;
                displayCheck = true;
            }  else if (d.equals("n")) {
                displayCheck = true;
            }
        }

        
    }

    /**
    * Prints the wanted int[] array of lottery numbers separated with commas.
    *
    * This method uses the sort() method from Arrays class to sort the numbers in ascending order before printing.
    *
    *@param numbers is the int[] array that will be printed
    *@param u is the String attached to numbers providing user the information whether the displayed numbers are the users lottery ticket or weekly random numbers.
    */

    private static void printLotto (int[] numbers, String u) {
        Arrays.sort(numbers);

        String[] prefixedNumbers = new String[numbers.length];
        
        prefixedNumbers = Arrays.addZeroPrefix(numbers, 2);

        for (int i = 0; i<prefixedNumbers.length; i++) {
            if (i == 0) {
                System.out.print(u + "[");
            }

            if (numbers[i] < 10) {
                System.out.print(prefixedNumbers[i]);
            }  else {
                System.out.print(prefixedNumbers[i]);
            }

            if (!(i == prefixedNumbers.length-1)) {
                System.out.print(", ");
            } else {
                System.out.print("]");
            }           
        }
        System.out.println();
    }

    /**
    * Keeps track of the years and weeks that pass when playing the game.
    *
    */

    private static void counter() {
        weeks++;

        if (weeks == 52) {
            years++;
            weeks = 0;
        }
    }

    /**
    * Method that checks that the numbers given in the command line are int values and that the numbers fall in the appropriate range.
    *
    * The method handles NumberFormatException and also that the array containing the lottery numbers is the right size. 
    *
    * @param arguments is the String[] array containing the wanted lottery numbers.
    *
    * @return returns true or false depending if the numbers are input correctly.
    */

    private static boolean checkArgs (String[] arguments) {
        if (arguments.length == 0) {
            return false;
        }

        int number = 0;

        if (arguments.length == 7) {
            for (int i = 0; i < arguments.length; i++) {
                
                try {
                    number = Integer.parseInt(arguments[i]);

                    if (number == (int) number && number <= 40 && number > 0 && !(Arrays.contains(number, lotteryTicket))) {
                        lotteryTicket[i] = number;
                    }  else {
                        System.out.println("Something went wrong with cmd input, proceed to manual input one by one");
                        return false;
                    }

                }  catch(NumberFormatException e) {
                    System.out.println("Something went wrong with cmd input, proceed to manual input one by one");
                    return false;
                }
            }
            return true;
        }  else {
            System.out.println("Something went wrong with cmd input, proceed to manual input one by one");
            return false;
        }
        
    }
}
