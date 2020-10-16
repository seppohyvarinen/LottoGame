package fi.tuni.tamk.tiko.hyvarinenseppo.util;

/**
* The Class Arrays contains several methods for maintaining and editing different types of Arrays as necessary.
*
*@author Seppo Hyvarinen
 */

public class Arrays {

    /**
    * Compiles and returns an int[] array from String[] array that is used as argument.
    *
    *@param array is the String[] array that is expected to be changed into int[] array
    *
    *@return returns an int[] array that is compiled from the String[] array.
    */

    public static int [] toIntArray(String [] array) {
        int [] intArray = new int [array.length];
        
        for (int i = 0; i<array.length; i++) {
            intArray[i] = Integer.parseInt(array[i]);
        }

        return intArray;
    }

    /**
    * This boolean method compares the inserted int value to the int values in the inserted int[] array and returns true if the
    * value is found in the array.
    *
    *@param value is the int value the method searches from an int[] array
    *@param array is the int[] array where the method searches the int value
    *
    *@return returns true if the value is found from the array, returns false if the value is not found from the arrays
    */

    public static boolean contains(int value, int [] array) {
        for (int i = 0; i<array.length; i++) {
            if (value == array[i]) {
                return true;
            }
        }
        return false;
    }

    /** 
    * Compares the int values in two int[] arrays and returns the amount of same values the arrays share
    *
    * Note: The method excpects that both int[] arrays contain only unique values!
    *
    *@param array1 is the first of the int arrays used for comparing values.
    *@param array2 is the second of the int arrays used for comparing values.
    *
    *@return returns int value that is the sum of same int values found in the two arrays
    */

    public static int containsSameValues(int [] array1, int [] array2) { 
        int sameValues = 0;
        
        for (int i = 0; i<array1.length; i++) {
            for (int j = 0; j<array2.length; j++) {
                if (array1[i] == array2[j]) {
                    sameValues++;
                }
            }
        }

        return sameValues;
    }

    /**
    * Removes a wanted index from an int[] array.
    *
    * The method creates a new int[] array containing all the values from the original array except the index that is wanted to be removed.
    *
    *@param original is the original int[] array we wish to have an index removed.
    *@param index is the index we want to remove from the int[] array
    *
    *@return returns the new int[] array which has the desired index removed.
    */
    
    public static int[] removeIndex(int [] original, int index) {
        int [] newArray = new int [original.length-1];
        original[index] = 0;
        int i = 0;
        int j = 0;
        while (j < original.length) {
            if (original[j] > 0) {
                newArray[i] = original[j];
                i++;
            }
            j++;
        }

        return newArray;
    }

    /**
    * Sorts an int[] array using selection sort to sort the array into ascending order.
    *
    *@param array is the array that will be sorted
    *
    *@return returns the sorted int[] array.
    */

    public static int[] sort (int[] array) {
        for (int i = 0; i < array.length-1; i++) {
            int minI = i;
            for (int j = i+1; j < array.length; j++) {
                if (array[j] < array[minI]) {
                    minI = j;
                }
            }

            int temp = array[minI];
            array[minI] = array[i];
            array[i] = temp;
        }

        return array;
    }

    /**
    * Converts an int[] array to String[] array and adds a desired amount of zeros as prefix to numbers.
    *
    * Parameter zeros amount determine the length of each element in the array.
    * For example with the parameter zeros = 2, the output will be 02, 03, 04, 12, 15. With zeros = 3 output will be 001, 004, 012, 032, 134 etc.
    *
    *@param array is the int[] array we wish to convert to String[] array and add the prefixes to.
    *@param zeros determines the length of each element in the array ie. how many zero prefixes is desired to values under the amount.
    *
    *@return returns the prefixed String[] array.
    */

    public static String[] addZeroPrefix(int [] array, int zeros) {
        String[] prefixedArray = new String[array.length];

        for (int i = 0; i < prefixedArray.length; i++) {
            prefixedArray[i] = "";
        }
        
        
        for (int i = 0; i < array.length; i++) {
            if (Integer.toString(array[i]).length() < zeros) {
                for (int j = 0; j < zeros - Integer.toString(array[i]).length(); j++) {                   
                    prefixedArray[i] += "0";
                }
                prefixedArray[i] += Integer.toString(array[i]);
            }  else {
                prefixedArray[i] = Integer.toString(array[i]);
            }
        }
        return prefixedArray;        
    }


}
