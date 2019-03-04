package FileProcessInCrawler;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Demo03 {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("F:\\learn\\WebCrawler\\src\\main\\java\\FileProcessInCrawler\\data/3.txt");
        FileWriter fileWriter = new FileWriter("F:\\learn\\WebCrawler\\src\\main\\java\\FileProcessInCrawler\\data/outtest.txt");
        int temp;
        while ((temp = fileReader.read()) != -1) {
            System.out.println((char)temp);
            fileWriter.write((char)temp);
        }
        fileWriter.close();
        fileReader.close();
    }
}
