package FileProcessInCrawler;

import java.io.*;

public class Demo02 {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream(new File("F:\\learn\\WebCrawler\\src\\main\\java\\FileProcessInCrawler\\data/1.txt"));
        FileOutputStream outputStream = new FileOutputStream("F:\\learn\\WebCrawler\\src\\main\\java\\FileProcessInCrawler\\data/out.txt");
        int temp;
        while((temp = inputStream.read()) != -1) {
            System.out.println((char)temp);
            outputStream.write(temp);
        }
        outputStream.close();
        inputStream.close();
    }
}
