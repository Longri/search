package de.longri.search;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringSplitExample {
    public static void main(String[] args) {
        String input = "suche \"suche2\" & und & \"auch und\" - minus -MINUS";

        // Definieren Sie das gewünschte Muster
        Pattern pattern = Pattern.compile("([&\\-]?)\\s*((?:\\w+|\"[^\"]*\")+)");

        StringBuilder OrSearchString = new StringBuilder();
        ArrayList<String> AndSearchStrings = new ArrayList<>();
        ArrayList<String> NotSearchStrings = new ArrayList<>();

        // Erstellen Sie einen Matcher mit dem Muster und dem Eingabestring
        Matcher matcher = pattern.matcher(input);

        // Iterieren Sie über Übereinstimmungen und geben Sie das Ergebnis aus
        while (matcher.find()) {
            String delimiter = matcher.group(1);
            String word = matcher.group(2);

            // Überprüfen Sie das Vorzeichen und fügen Sie es vor das Wort hinzu
            if (!delimiter.isEmpty()) {
                if (delimiter.equals("&")) {
                    AndSearchStrings.add(word.startsWith("\"") ? word : word + "~");
                } else {
                    NotSearchStrings.add(word.startsWith("\"") ? word : word + "~");
                }
                System.out.println(delimiter + word);
            } else {
                if (!word.startsWith("\""))
                    OrSearchString.append(word).append("~").append(" ");
                else
                    OrSearchString.append(word).append(" ");
                System.out.println(word);
            }
        }

        System.out.println("OR :" + OrSearchString.toString());
        System.out.println("AND:" + AndSearchStrings.toString());
        System.out.println("NOT:" + NotSearchStrings.toString());
    }
}
