import java.util.Objects;

public class word_frequency implements Comparable<word_frequency>
{
    private final String word;
    private int count;

    public word_frequency(String word)
    {
        this.word = word;
        this.count = 0;
    }

    public String getWord()
    {
        return word;
    }

    public int getCount()
    {
        return count;
    }

    public void incrementCount()
    {
        count++;
    }

    public int compareTo(word_frequency other)
    {
        return Integer.compare(other.count, this.count);
    }

}
