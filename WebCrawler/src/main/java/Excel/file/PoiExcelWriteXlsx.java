package Excel.file;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class PoiExcelWriteXlsx {

    public static void main(String[] args) throws IOException {
        //创建文件输出流
        File file = new File("F:\\learn\\WebCrawler\\src\\main\\java\\Excel\\data/b.xlsx");
        OutputStream outputStream = new FileOutputStream(file);
        //创建工作簿及工作表
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("sheet1");
        //添加表头
        XSSFRow row = sheet.createRow(0);   //创建某行
        row.createCell(0).setCellValue("post_id");
        row.createCell(1).setCellValue("post_title");
        //添加内容
        for (int i = 0; i < 2; i++) {
            XSSFRow everyRow = sheet.createRow(i + 1);
            everyRow.createCell(0).setCellValue("帖子id为:0" + i);
            everyRow.createCell(1).setCellValue("帖子内容为:" + i);
        }
        //数据写入工作目录,并释放资源
        workbook.write(outputStream);
        outputStream.close();
    }
}
