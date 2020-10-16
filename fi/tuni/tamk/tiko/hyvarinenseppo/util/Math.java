package fi.tuni.tamk.tiko.hyvarinenseppo.util;
/**
* The Class Math contains several methods for performing basic mathematical functions.
*
*
*@author Seppo Hyvarinen
 */

public class Math {

    /**
    *Returns a random int value between the minimum and maximum int value determined by user.
    *
    *The method uses Math.random() method to acquire a pseudorandom double value
    *and then transforms it into int value.
    *@param min sets the minimum int value.
    *@param max sets the maximum int value.
    *
    *@return random int value.
    */
    public static int getRandom(int min, int max) {
        return min + (int) (java.lang.Math.random() * ((max - min) + 1));
    }
}
