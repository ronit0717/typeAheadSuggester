package com.rccode.model;


import com.rccode.constant.AppConstants;

import java.util.LinkedList;
import java.util.List;

public class Node {
    private char character;
    private Node[] nextNodes;

    public Node(char character) {
        this.character = character;
        this.nextNodes = new Node[38];
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public Node[] getNextNodes() {
        return nextNodes;
    }

    public void setNextNodes(Node[] nextNodes) {
        this.nextNodes = nextNodes;
    }

    public List<String> getSuggestions() {
        List<String> suggestions = new LinkedList<>();
        boolean leaf = true; //leaf node
        boolean eolChildNodeExists = false;
        for (int i = 0; i < nextNodes.length; i++) {
            if (nextNodes[i] == null) {
                continue;
            }

            if (i == AppConstants.EOL_INDEX) {
                eolChildNodeExists = true;
            }

            List<String> subSuggestions = nextNodes[i].getSuggestions();
            if (subSuggestions.size() != 0) {
                leaf = false;
            }
            for (String str : subSuggestions) {
                suggestions.add(character + str);
            }
        }

        if (eolChildNodeExists || (leaf && character != AppConstants.EOL)) {
            suggestions.add(Character.toString(character));
        }
        return suggestions;
    }

    public List<String> getNextNodeSuggestions() {
        List<String> suggestions = new LinkedList<>();
        for (int i = 0; i < nextNodes.length; i++) {
            if (nextNodes[i] == null) {
                continue;
            }
            suggestions.addAll(nextNodes[i].getSuggestions());
        }
        return suggestions;
    }
}
