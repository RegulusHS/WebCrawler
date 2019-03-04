package Excel.crawler;

import jxl.write.WriteException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


public class DataToExcelByPoi {

    public static void writeInfoListToExcel(String filePath, String sheetName, List<PostModel> datalist) throws IOException, WriteException {
        //文件名称
        File file = new File(filePath);
        OutputStream outputStream = new FileOutputStream(file);
        Workbook workbook = getWorkBook(file);
        Sheet sheet = workbook.createSheet(sheetName);
        //添加表头
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("post_id");
        row.createCell(1).setCellValue("post_title");
        for (int i = 0; i < datalist.size(); i++) {
            Row everyRow = sheet.createRow(i + 1);
            everyRow.createCell(0).setCellValue(datalist.get(i).getPost_id());
            everyRow.createCell(1).setCellValue(datalist.get(i).getPost_title());
        }
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
        System.out.println(">>>>>>>>>数据写入完成!<<<<<<<<<");
    }

    public static Workbook getWorkBook(File file) {
        Workbook workbook = null;
        //Excel 2003
        if(file.getName().endsWith("xls")) {
            workbook = new HSSFWorkbook();
        }else if (file.getName().endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        }
        return workbook;
    }
}
