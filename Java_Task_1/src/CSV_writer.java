import java.io.*;
import java.util.List;
import java.util.Locale;

public class CSV_writer
{
    public static void writeToCSV(List<word_frequency> wordList, String outputFile)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile)))
        {
            writer.write("Слово,Частота,Частота (%)\n");

            int totalWords = wordList.stream().mapToInt(word_frequency::getCount).sum();

            for (word_frequency wf : wordList)
            {
                double percentage = ((double) wf.getCount() / totalWords) * 100;
                percentage = Math.round(percentage * 100.0) / 100.0;
                writer.write(String.format(Locale.US, "%s,%d,%.2f\n", wf.getWord(), wf.getCount(), percentage));
            }

            System.out.println("Результат сохранён в " + outputFile);
        }
        catch (IOException e) {
            System.err.println("Ошибка при записи CSV: " + e.getLocalizedMessage());
        }
    }
}
