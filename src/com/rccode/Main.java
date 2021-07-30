package com.rccode;

import com.rccode.constant.AppConstants;
import com.rccode.model.Dictionary;
import com.rccode.service.SuggestorService;

import java.util.Scanner;

public class Main {
    private static final Dictionary dictionary = new Dictionary();

    private static final SuggestorService service = new SuggestorService();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type Ahead Suggester Application Started, please enter command\n");

        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            System.out.println();
            if (command.equals(AppConstants.EXIT_COMMAND)) {
                break;
            }
            processCommand(command);
            System.out.println();
        }

        System.out.println("Terminating Type Ahead Suggester Application");
    }

    private static void processCommand(String command) {
        try {
            if (command.indexOf(AppConstants.REGISTER_COMMAND) == 0) {
                service.registerString(dictionary, command.substring(AppConstants.REGISTER_COMMAND.length()));
            } else if (command.indexOf(AppConstants.SUGGEST_COMMAND) == 0) {
                service.typeAheadSuggest(dictionary, command.substring(AppConstants.SUGGEST_COMMAND.length()));
            } else {
                System.out.println(AppConstants.INVALID_COMMAND_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(AppConstants.INVALID_COMMAND_MESSAGE);
        }
    }
}
