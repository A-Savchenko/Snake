import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 *	PromptFinal.java - Uses BufferedReader.
 *	Provides utilities for user input.  This enhances the BufferedReader
 *	class so our programs can recover from "bad" input, and also provides
 *	a way to limit numerical input to a range of values.
 *
 *	The advantages of BufferedReader are speed, synchronization, and piping
 *	data in Linux.
 *
 *	@author	Mr Greenstein
 *	@since	8/10/2015
 *	@version 8/8/2018
 */

public class Prompt
{
    // BufferedReader variables
    private static InputStreamReader streamReader = new InputStreamReader(System.in);
    private static BufferedReader bufReader = new BufferedReader(streamReader);

    /**
     *	Prompts user for string of characters and returns the string.
     *	@param ask  The prompt line
     *	@return  The string input
     */
    public static String getString (String ask)
    {
        System.out.print(ask + " -> ");
        String input = "";
        try {
            input = bufReader.readLine();
        } catch (IOException e) {
            System.err.println("ERROR: BufferedReader could not read line");
        }
        return input;
    }

    /**
     *	Prompts the user for a character and returns the character.
     *	@param ask  The prompt line
     *	@return  The character input
     */
    public static char getChar (String ask)
    {
        String input = new String("");
        char ch = '+';
        do
        {
            input = getString(ask);
        }
        while(input.length() != 1);
        ch = input.charAt(0);
        return ch;
    }

    /**
     *	Prompts the user for an integer and returns the integer.
     *	@param ask  The prompt line
     *	@return  The integer input
     */
    public static int getInt (String ask)
    {
        boolean badinput = false;
        String input = new String("");
        int value = 0;
        do
        {
            badinput = false;
            input = getString(ask);
            try
            {
                value = Integer.parseInt(input);
            }
            catch(NumberFormatException e)
            {
                badinput = true;
            }

        }
        while(badinput);
        return value;
    }

    /**
     *	Prompts the user for an integer using a range of min to max,
     *	and returns the integer.
     *	@param ask  The prompt line
     *	@param min  The minimum integer accepted
     *	@param max  The maximum integer accepted
     *	@return  The integer input
     */
    public static int getInt (String ask, int min, int max)
    {
        int value = 0;
        do
        {
            value = getInt(ask + " (" + min + " - " + max + ")");
        }
        while(value < min || value > max);
        return value;
    }

    /**
     *	Prompts the user for a double and returns the double.
     *	@param ask  The prompt line
     *	@return  The double input
     */
    public static double getDouble (String ask)
    {
        boolean badinput = false;
        String input = new String("");
        double value = 0.0;
        do
        {
            badinput = false;
            input = getString(ask);
            try
            {
                value = Double.parseDouble(input);
            }
            catch(NumberFormatException e)
            {
                badinput = true;
            }

        }
        while(badinput);
        return value;
    }

    /**
     *	Prompts the user for a double and returns the double.
     *	@param ask  The prompt line
     *	@param min  The minimum double accepted
     *	@param max  The maximum double accepted
     *	@return  The double input
     */
    public static double getDouble (String ask, double min, double max)
    {
        double value = 0;
        String stringmin = String.format("%.2f", min);
        String stringmax = String.format("%.2f", max);
        do
        {
            value = getDouble(ask + " (" + stringmin + " - " + stringmax + ")");
        }
        while(value < min || value > max);
        return value;
    }
}