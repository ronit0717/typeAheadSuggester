package com.rccode.service;

import com.rccode.constant.AppConstants;
import com.rccode.exception.ProcessException;
import com.rccode.model.Dictionary;
import com.rccode.model.Node;

import java.util.List;


public class SuggestorService {

    /**
     * This method takes the input string and makes a new entry in the dictionary.
     * Assumption: The dictionary accepts only small case letter between a-z, space character and numeric characters between 0-9
     * If the string contains any capital case characters, they are converted to small case characters
     * If the string encounters any other character, then exception is thrown
     * @param dictionary
     * @param str
     */
    public void registerString(Dictionary dictionary, String str) {
        String cleanString = getCleanString(str) + AppConstants.EOL;

        Node prevNode = null;
        Node node = null;
        for (int i = 0; i < cleanString.length(); i++) {
            char c = cleanString.charAt(i);
            int index = getIndexOfCharacter(c);

            Node[] nodes = (i == 0) ? dictionary.getNodes() : prevNode.getNextNodes();
            if (nodes[index] == null) {
                node = new Node(c);
                nodes[index] = node;
                if (i == 0) {
                    dictionary.setNodes(nodes);
                } else {
                    prevNode.setNextNodes(nodes);
                }
            }
            prevNode = nodes[index];
        }
    }

    public void typeAheadSuggest(Dictionary dictionary, String str) {
        String cleanString = getCleanString(str);
        Node node = null;
        for (int i = 0; i < cleanString.length(); i++) {
            char c = cleanString.charAt(i);
            int index = getIndexOfCharacter(c);

            Node[] nodes = (i == 0) ? dictionary.getNodes() : node.getNextNodes();
            if (nodes[index] != null) {
                node = nodes[index];
            } else {
                break;
            }
        }

        List<String> suggestions = (node == null) ? null : node.getNextNodeSuggestions();
        if (suggestions == null || suggestions.size() == 0) {
            System.out.println("No suggestions...");
        } else {
            System.out.println("Printing suggestions...");
            for (String suggestion : suggestions) {
                System.out.println(cleanString + suggestion);
            }
        }
    }

    private String getCleanString(String str) {
        StringBuilder sb = new StringBuilder();
        str = str.toLowerCase().trim();
        if (str.length() == 0) {
            throw new ProcessException("Register String", "Empty String");
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                //code to remove redundant space in middle
                while (i < str.length() - 1) {
                    if (str.charAt(i + 1) == ' ') {
                        i++;
                    } else {
                        break;
                    }
                }
                sb.append(str.charAt(i));
            } else if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z') {
                sb.append(str.charAt(i));
            } else {
                //invalid string
                throw new ProcessException("Register String", "String contains non alphabetic characters");
            }
        }
        return sb.toString();
    }

    private int getIndexOfCharacter(char c) {
        int index;
        if (c == AppConstants.SPACE) {
            index = AppConstants.SPACE_INDEX;
        } else if (c == AppConstants.EOL) {
            index = AppConstants.EOL_INDEX;
        } else if (c >= '0' && c <= '9') {
            index = AppConstants.SPACE_INDEX + 1 + c - '0';
        } else {
            index = c - 'a';
        }
        return index;
    }

}
