/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL.Excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class CreateExcelTemplate
{

    public void create(String name) throws FileNotFoundException, IOException
    {
        if (name.endsWith(".xls"))
        {
            oldExcel(name);
        }
        else
        {
            newExcel(name);
        }
    }

    private void newExcel(String name) throws FileNotFoundException, IOException
    {

        FileOutputStream fileOut;
        try (XSSFWorkbook workBook = new XSSFWorkbook())
        {
            File excel = new File(name);
            fileOut = new FileOutputStream(excel);
            workBook.write(fileOut);
            //Create cells
            CreationHelper createHelper = workBook.getCreationHelper();

            //Create a sheet
            XSSFSheet sheet = workBook.createSheet("FighterTemplate");
            sheet.setColumnWidth(0, 10000);
                     //Writes into the cells
            Row row = sheet.createRow((short) 0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Navn");
            cell = row.createCell(1);
            cell.setCellValue("Køn (M/F)");
            cell = row.createCell(2);
            cell.setCellValue("Grad");
            cell = row.createCell(3);
            cell.setCellValue("Alder");
            cell = row.createCell(4);
            cell.setCellValue("Vægt (kg)");
            cell = row.createCell(5);
            cell.setCellValue("Højde (m)");
            cell = row.createCell(6);
            cell.setCellValue("Kata");
            cell = row.createCell(7);
            cell.setCellValue("Kumite");
            cell = row.createCell(8);
            cell.setCellValue("Kobudo");
            cell = row.createCell(9);
            cell.setCellValue("Fælles Spisning");
            //Create a second sheet
//            HSSFSheet personSheet = workBook.createSheet("PersonTemplate");
//            personSheet.setColumnWidth(0, 10000);
//            row = personSheet.createRow((short) 0);
            sheet.setColumnWidth(11, 10000);
            cell = row.createCell(11);
            cell.setCellValue("Navn");
            cell = row.createCell(12);
            cell.setCellValue("Dommer");
            cell = row.createCell(13);
            cell.setCellValue("Official");
            cell = row.createCell(14);
            cell.setCellValue("Coach");
            cell = row.createCell(15);
            cell.setCellValue("Fælles Spisning");
            // open an OutputStream to save written data into XLSX file
            FileOutputStream os = new FileOutputStream(excel);
            workBook.write(os);
            System.out.println("Writing on XLSX file Finished ...");
            os.close();
        }
        fileOut.close();
    }

    private void oldExcel(String name) throws FileNotFoundException, IOException
    {
        //Starts an output stream
        FileOutputStream fileOutput;
        try (HSSFWorkbook workBook = new HSSFWorkbook())
        {
            //Uses the parameter to make a file
            File excel = new File(name);
            //Inputs the file into the file output stream
            fileOutput = new FileOutputStream(excel);
            //Tells it to write in this stream
            workBook.write(fileOutput);
            //Create cells
            CreationHelper createHelper = workBook.getCreationHelper();

            //Create a sheet
            HSSFSheet sheet = workBook.createSheet("FighterTemplate");
            //Sets the first column (0) to have a with of 10000
            sheet.setColumnWidth(0, 10000);

            //Writes into the cells
            Row row = sheet.createRow((short) 0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Navn");
            cell = row.createCell(1);
            cell.setCellValue("Køn (M/F)");
            cell = row.createCell(2);
            cell.setCellValue("Grad");
            cell = row.createCell(3);
            cell.setCellValue("Alder");
            cell = row.createCell(4);
            cell.setCellValue("Vægt (kg)");
            cell = row.createCell(5);
            cell.setCellValue("Højde (m)");
            cell = row.createCell(6);
            cell.setCellValue("Kata");
            cell = row.createCell(7);
            cell.setCellValue("Kumite");
            cell = row.createCell(8);
            cell.setCellValue("Kobudo");
            cell = row.createCell(9);
            cell.setCellValue("Fælles Spisning");
            //Create a second sheet
//            HSSFSheet personSheet = workBook.createSheet("PersonTemplate");
//            personSheet.setColumnWidth(0, 10000);
//            row = personSheet.createRow((short) 0);
            sheet.setColumnWidth(11, 10000);
            cell = row.createCell(11);
            cell.setCellValue("Navn");
            cell = row.createCell(12);
            cell.setCellValue("Dommer");
            cell = row.createCell(13);
            cell.setCellValue("Official");
            cell = row.createCell(14);
            cell.setCellValue("Coach");
            cell = row.createCell(15);
            cell.setCellValue("Fælles Spisning");
            // open an OutputStream to save written data into XLSX file
            FileOutputStream os = new FileOutputStream(excel);
            workBook.write(os);
            System.out.println("Writing on XLS file Finished ...");
            os.close();
        }
        fileOutput.close();
    }
}
