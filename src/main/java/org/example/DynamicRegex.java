package org.example;

import java.util.regex.*;

public class DynamicRegex {
    public static void main(String[] args) {
        String sentence = "In this supermarket you will be paying $100, in the normal market you will be paying $30.";

        // Generate the regex dynamically
        String dynamicRegex = generateRegex(sentence);
        System.out.println("Generated Regex: " + dynamicRegex);

        // Test the regex on text with random newlines
        String newRegex = "^In[\\s\\n]*this[\\s\\n]*supermarket[\\s\\n]*you[\\s\\n]*will[\\s\\n]*be[\\s\\n]*paying[\\s\\n]*\\$\\d+,[\\s\\n]*in[\\s\\n]*the[\\s\\n]*normal[\\s\\n]*market[\\s\\n]*you[\\s\\n]*will[\\s\\n]*be[\\s\\n]*paying[\\s\\n]*\\$30\\.$";
        String text = "In this \nsupermarket \nyou will be paying \n$100, in the normal market \nyou will be paying $30.";
        Pattern pattern = Pattern.compile(dynamicRegex);
        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()) {
            System.out.println("✅ The text matches the expected format.");
        } else {
            System.out.println("❌ ERROR: The text does not follow the expected structure!");
        }
    }

    public static String generateRegex(String sentence) {
        // Escape special characters except for spaces, commas, and dollar signs
        String[] words = sentence.split(" ");
        StringBuilder regex = new StringBuilder("^");

        for (int i = 0; i < words.length; i++) {
            // Escape special characters (except spaces, commas, and dollar signs)
            String word = words[i].replaceAll("([.^$+?(){}|])", "\\\\$1");
            regex.append(word);

            // Add [\\s\\n]* except after the last word
            if (i < words.length - 1) {
                regex.append("[\\s\\n]*");
            }
        }

        regex.append("$");
        return regex.toString();
    }
}
