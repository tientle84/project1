package com.revature.utility;

import com.revature.model.Reimbursement;
import com.revature.model.User;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class InfoValidator {
//    // validate client information
//    public static void validateClientInformation(Client client) {
//        client.setFirstName(client.getFirstName().trim());
//        client.setLastName(client.getLastName().trim());
//        client.setAddress(client.getAddress().trim());
//        client.setPhone(client.getPhone().trim());
//
//        if(!client.getFirstName().matches("[a-zA-Z]+")) {
//            throw new IllegalArgumentException("First name must only have alphabetical characters.");
//        }
//
//        if(!client.getLastName().matches("[a-zA-Z]+")) {
//            throw new IllegalArgumentException("Last name must only have alphabetical characters.");
//        }
//
//        if(!client.getAddress().matches("[\\w\\s,]+")) {    // /w: short for [a-zA-Z_0-9], \s: whitespace
//            throw new IllegalArgumentException("Please enter a valid address.");
//        }
//
//        if(!client.getPhone().matches("[0-9]{10}")) {
//            throw new IllegalArgumentException("Please enter a valid phone number with 10 digits.");
//        }
//    }

//    // validate account information
//    public static void validateAccountInformation(Account account) {
//        account.setAccountNumber(account.getAccountNumber().trim());
//        account.setRoutingNumber(account.getRoutingNumber().trim());
//        account.setOpenDate(account.getOpenDate().trim());
//
//        if(!account.getAccountNumber().matches("[0-9]{10}")) {
//            throw new IllegalArgumentException("Please enter a valid account number with 10 digits number.");
//        }
//
//        if(!account.getRoutingNumber().matches("[0-9]{9}")) {
//            throw new IllegalArgumentException("Please enter a valid routing number with 9 digits number.");
//        }
//
//        if(account.getCurrentBalance() < 0) {
//            throw new IllegalArgumentException("Please enter a valid balance number that must be >= 0.");
//        }
//
//        try {
//            LocalDate.parse(account.getOpenDate());
//
//            /*
//                Using custom date format
//                default format: (yyyy-MM-dd)
//                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy").withResolverStyle(ResolverStyle.STRICT);
//                LocalDate.parse(dateStr, dateFormatter);
//             */
//
//        } catch (DateTimeParseException e) {
//            throw new IllegalArgumentException("Please enter a valid date following the format YYYY-MM-DD.");
//        }
//    }

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
