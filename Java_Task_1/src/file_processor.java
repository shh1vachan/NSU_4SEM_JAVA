import java.io.*;
import java.util.*;

public class file_processor
{
    public void process(String filename)
    {
        Map<String, word_frequency> wordMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename))))
        {
            StringBuilder word_builder = new StringBuilder();
            int ch;

            while ((ch = reader.read()) != -1)
            {
                if (Character.isLetterOrDigit(ch) || ch == '-' || ch == '_')
                    word_builder.append((char) ch);

                else if (word_builder.length() > 0)
                {
                    add_word(wordMap, word_builder.toString().toLowerCase());
                    word_builder.setLength(0);
                }
            }

            if (word_builder.length() > 0)
                add_word(wordMap, word_builder.toString().toLowerCase());
        }

        catch (IOException e)
        {
            System.err.println("Error reading File: " + e.getLocalizedMessage());
        }

        List<word_frequency> wordList = new ArrayList<>(wordMap.values());
        Collections.sort(wordList);

        CSV_writer.writeToCSV(wordList, "output.csv");
    }

    private void add_word(Map<String, word_frequency> wordMap, String word)
    {
        wordMap.putIfAbsent(word, new word_frequency(word));
        wordMap.get(word).incrementCount();
    }
}
