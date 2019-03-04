package FileProcessInCrawler;

import java.io.File;

public class Demo01 {
    public static void main(String[] args) {
        File root = new File("F:\\learn\\WebCrawler\\src\\main\\java\\FileProcessInCrawler\\data");
        Boolean isDirectory = root.isDirectory();
        System.out.println(root.isDirectory());
        if(isDirectory) {
            File[] files = root.listFiles();
            for (File file : files) {
                System.out.println("文件名称为:" + file.getName());
                System.out.println("文件可读否:" + file.canRead());
                System.out.println("绝对路径:" + file.getAbsolutePath());
                System.out.println("文件的长度为:" + file.length());
            }
        }
    }
}
