package Excel.file;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;

/**
 * POI数据写入
 */
public class PoiExcelWrite {

    public static void main(String[] args) throws IOException {
        //创建文件输出流
        File file = new File("F:\\learn\\WebCrawler\\src\\main\\java\\Excel\\data/b.xls");
        OutputStream outputStream = new FileOutputStream(file);
        //创建工作簿及工作表
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet1");
        //添加表头
        HSSFRow row = sheet.createRow(0);   //创建某行
        row.createCell(0).setCellValue("post_id");
        row.createCell(1).setCellValue("post_title");
        //添加内容
        for (int i = 0; i < 2; i++) {
            HSSFRow everyRow = sheet.createRow(i + 1);
            everyRow.createCell(0).setCellValue("帖子id为:0" + i);
            everyRow.createCell(1).setCellValue("帖子内容为:" + i);
        }
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

}
