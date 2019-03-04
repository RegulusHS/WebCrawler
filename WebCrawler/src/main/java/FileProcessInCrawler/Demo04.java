package FileProcessInCrawler;

import java.io.*;

public class Demo04 {
    public static void main(String[] args) throws IOException {
        /******   文件读取第一种方式   ******/
        File file = new File("F:\\learn\\WebCrawler\\src\\main\\java\\FileProcessInCrawler\\data/3.txt");
        //FileReader读取文件
        FileReader fileReader = new FileReader(file);
        //根据FileReader创建缓冲流
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String s = null;
        //按行读取
        while ((s = bufferedReader.readLine()) != null) {
            System.out.println(s);
        }
        //流关闭
        bufferedReader.close();
        fileReader.close();

        /******   文件读取第二种方式   ******/
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("F:\\learn\\WebCrawler\\src\\main\\java\\FileProcessInCrawler\\data/3.txt")), "utf-8"));
        String s1 = null;
        while ((s1 = reader.readLine()) != null) {
            System.out.println(s1);
        }
        //流关闭
        reader.close();
    }
}
