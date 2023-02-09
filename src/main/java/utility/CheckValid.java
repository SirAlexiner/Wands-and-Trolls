package utility;

/**
 * CheckValid class part of the package Utility.
 * Contains methods to check user input, to minimize user errors.
 *
 * @author Arthur Borger Thorkildsen
 * @version 23-10-2022
 */

public class CheckValid
{
    private CheckValid() {}

    /**
     * A method to check user String input. The string cannot be empty.
     *
     * @param   stringToCheck checks a String to see if it is valid.
     * @return  True if it is a validVariable.
     * */
    public static boolean checkString(String stringToCheck)
    {
        return (stringToCheck != null && !stringToCheck.isEmpty());
    }
}