# Type ahead suggester

Typeahead suggestions enable users to search for known and frequently searched terms. As the user types into the search box, it tries to predict the query based on the characters the user has entered and gives a list of suggestions to complete the query. Typeahead suggestions help the user to articulate their search queries better. It’s not about speeding up the search process but rather about guiding the users and lending them a helping hand in constructing their search query. 

[Documentation](https://docs.google.com/document/d/1NGVs2QgiqgrNyRPAmKuCFoYq-UaVKFarkPE85Cdn4VA/edit?usp=sharing)

## Example:
**Hello** World
**Hello** Ronit
**Hello** Robot
In the above strings, there is a common part *"Hello"*. 

**Expectation**:  When the user enters the text *"Hello"*, our application should return back all three suggestions, ie. "**Hello** World", "**Hello** Ronit", "**Hello** Robot".

## Features
- The application has the feature to add new strings, which will be used as reference to suggest future suggestions.
- The application should return a list of suggestions when a particular string in entered

## Assumptions
- The application supports only alphanumeric characters and spaces.
- The application is case insensitive

## Approach
The application uses the **Trie** data-structure. 

### Data structure: Trie (Dictionary)

```
'h' -> 'e' -> 'l' -> 'l' -> 'o' -> ' ' -> 'w' -> 'o' -> 'r' -> 'l' -> 'd' -> End 
                                     \
                                      \-> 'r' -> 'o' -> 'n' -> 'i' -> 't' -> End
                                                   \
                                                    \-> 'b' -> 'o' -> 't' -> End
```

### Time complexity
The time-complexity of the program is in the order of length of the string O(Length of String).

## Commands
1. **register** < some string for reference >
			This command enters a new string which can be used as reference to give suggestions. The string should have only alphanumeric characters and spaces, else exception in thrown

2. **suggest** < some string >
			This prints a list of suggestions matching the text <*some string*>
3. **exit**
			Exits the command line application

### example

#### Commands
register hello world  
register hello ronit  
register hello robot  
register how are you  
register how are you doing  
suggest hello  
suggest how  
exit

#### Output

Registration successful  
Registration successful  
Registration successful  
Registration successful  
Registration successful    
Printing suggestions...  
hello robot  
hello ronit  
hello world  
Printing suggestions...  
how are you doing  
how are you  
Terminating Type Ahead Suggester Application