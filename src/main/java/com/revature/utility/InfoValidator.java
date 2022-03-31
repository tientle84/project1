package com.revature.utility;

public class InfoValidator {
    // validate id (should be an int number)
    public static int isValidId(String id) {
        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid id was provided.");
        }
    }

    // validate query parameter (should be a double number)
    public static double isValidParam(String param) {
        try {
            return Double.parseDouble(param);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid parameter was provided.");
        }
    }
}
