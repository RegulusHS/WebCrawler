package Excel.crawler;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataToExcelByJxl {

    public static void writeInfoListToExcel(String filePath, String sheetName, List<PostModel> datalist) throws IOException, WriteException {
        File xlsFile = new File(filePath);
        WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);   //创建工作簿
        WritableSheet sheet = workbook.createSheet(sheetName, 0);   //创建一个工作表
        sheet.addCell(new Label(0, 0, "post_id"));  //添加表头
        sheet.addCell(new Label(1, 0, "post_title"));   //添加表头
        //添加内容
        for (int i = 0; i < datalist.size(); i++) {
            sheet.addCell(new Label(0, i + 1, datalist.get(i).getPost_id()));
            sheet.addCell(new Label(1, i + 1, datalist.get(i).getPost_title()));
        }
        workbook.write();
        workbook.close();
        System.out.println(">>>>>>>>>数据写入完成!<<<<<<<<<");
    }
}
