package com.loginexcel.loginexcel.helper;

import com.loginexcel.loginexcel.model.userDetails;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class excelHelper {
    public static boolean checkExcelFormat(MultipartFile file){
       String contentType= file.getContentType();

        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
           return true;
       else
           return false;
    }

    public static List<userDetails> convertExcelToList (InputStream is)
    {
        List<userDetails> list = new ArrayList<>();
        try
        {

            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheet("data");
            int rowNumber =0;
            Iterator<Row> iterator = sheet.iterator();
            
            while(iterator.hasNext()){
                Row row = iterator.next();
                if(rowNumber == 0){
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cells = row.iterator();
                int cid =0;
                userDetails u = new userDetails();
                while(cells.hasNext()){
                   Cell cell = cells.next();

                   switch (cid){
                       case 0:
                           u.setId((int)cell.getNumericCellValue());
                          break;
                       case 1:
                           u.setUsername(cell.getStringCellValue());
                          break;
                       case 2:
                           u.setPassword(cell.getStringCellValue());
                          break;
                       default:
                           break;
                   }
                   cid++;
                }
                list.add(u);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

}
