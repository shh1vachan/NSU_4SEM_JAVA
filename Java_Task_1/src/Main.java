import java.io.File;


public class Main
{
    public static void main(String[] args)
    {
        if (args.length != 1)
        {
            System.out.println("Usage: java Main <file_name.txt>");
            return;
        }

        String filename = args[0];
        File file = new File(filename);
        if (!file.exists() || !file.isFile())
        {
            System.out.println("File Not Found: " + filename);
            return;
        }
        file_processor processor = new file_processor();
        processor.process(filename);
    }
}
