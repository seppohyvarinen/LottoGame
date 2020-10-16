package fi.tuni.tamk.tiko.hyvarinenseppo.util;

import java.io.Console;

/**
* The class MyConsole contains methods that involve user input via Console.
*
* The methods in this class use the Java Console in achieving the functionality needed.
*
*@author Seppo Hyvarinen
 */

public class MyConsole {
    
    /**
    * This method checks if user's Console input is an int value. If not - the method displays an error message. If it is, the method returns the int value
    * that was inputted.
    *
    *@param errorMessage is the custom error message that is displayed in the case user doesn't input an int value.
    *
    *@return returns the int value that was inputted.
    */
    
    public static int readInt(String errorMessage) {
        Console con = System.console();
        boolean itsANumber = false;
        int number = 0;

        while (!itsANumber) {
            try {
                number = Integer.parseInt(con.readLine());

                if (number == (int) number) {
                    itsANumber = true;
                }

            }  catch(NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }

        return number;
        
    }

    /**
    * This method checks if user's Console input is an int value between the desired minimum and maximum values.
    * 
    * The method uses readInt(String errorMessage) methods functionality in taking the user input.
    *
    *@param min is the minimum accepted int value
    *@param max is the maximum accepted int value
    *@param errorMessageNonNumeric is the custom error message that is displayed in the case user doesn't input an int value.
    *@param errorMessageNonMinAndMax is the custom error message that is displayed if the inputted int value doesn't fall between minimum and maximum values
    *
    *@return returns the accepted int value that was inputted.
    */


    public static int readInt(int min, int max, String errorMessageNonNumeric, String errorMessageNonMinAndMax) {
        
        boolean numberIsCorrect = false;
        int number = 0;
        
        while (!numberIsCorrect) {
            number = readInt(errorMessageNonNumeric);
            
            if (number < min || number > max) {
                System.out.println(errorMessageNonMinAndMax);
            }  else {
                numberIsCorrect = true;
            }
        }
        
        return number;
    }

}
