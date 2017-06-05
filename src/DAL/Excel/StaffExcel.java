/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL.Excel;

import BE.ColLooker;
import BE.StaffPerson;
import BE.StaffPosition;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class StaffExcel
{

    public StaffPerson readStaff(String fileName, int lineStart, ColLooker colLooker) throws IOException
    {
        if (fileName.endsWith(".xls"))
        {
            return oldReadExcel(fileName, lineStart, colLooker);
        }
        else
        {
            return newReadExcel(fileName, lineStart, colLooker);
        }
    }

    private StaffPerson oldReadExcel(String fileName, int lineStart, ColLooker colLooker) throws IOException
    {
        File myFile = new File(fileName);
        // Finds the workbook instance for XLS file
        try (FileInputStream fis = new FileInputStream(myFile))
        {
            // Finds the workbook instance for XLS file
            HSSFWorkbook workBook = new HSSFWorkbook(fis);

            // Return first sheet from the XLS workbook, the fighters, to get StaffPerson, replace the 0 with 1
            HSSFSheet sheet = workBook.getSheetAt(0);

            CellReference ref = new CellReference("A" + lineStart);
            Row r = sheet.getRow(ref.getRow());
            if (r != null)
            {
                try
                {
                    String name = r.getCell(ref.getCol() + colLooker.getNameCol()).toString().trim();
                    boolean judge = Integer.parseInt(r.getCell(ref.getCol() + colLooker.getJudgeCol()).toString()) == 1;
                    boolean official = Integer.parseInt(r.getCell(ref.getCol() + colLooker.getOfficialCol()).toString()) == 1;
                    boolean coach = Integer.parseInt(r.getCell(ref.getCol() + colLooker.getCoachCol()).toString()) == 1;

                    StaffPosition position = judge
                            ? StaffPosition.Judge
                            : official
                                    ? StaffPosition.Official
                                    : coach
                                            ? StaffPosition.Coach
                                            : null;

                    boolean eat = Integer.parseInt(r.getCell(ref.getCol() + colLooker.getEatCol()).toString()) == 1;

                    if (!name.isEmpty())
                    {
                        fis.close();
                        return new StaffPerson(-1, name, position, eat, -1, "");
                    }
                }
                catch (NumberFormatException ex)
                {
                    return null;
                }
            }
        }

        return null;
    }

    private StaffPerson newReadExcel(String fileName, int lineStart, ColLooker colLooker) throws IOException
    {
        File myFile = new File(fileName);
        // Finds the workbook instance for XLS file
        try (FileInputStream fis = new FileInputStream(myFile))
        {
            // Finds the workbook instance for XLS file
            XSSFWorkbook workBook = new XSSFWorkbook(fis);

            // Return first sheet from the XLS workbook, the fighters, to get StaffPerson, replace the 0 with 1
            XSSFSheet sheet = workBook.getSheetAt(0);

            CellReference ref = new CellReference("A" + lineStart);
            Row r = sheet.getRow(ref.getRow());
            if (r != null)
            {
                try
                {
                    String name = r.getCell(ref.getCol() + colLooker.getNameCol()).toString().trim();
                    boolean judge = r.getCell(ref.getCol() + colLooker.getJudgeCol()).toString().trim().equals("1.0");
                    boolean official = r.getCell(ref.getCol() + colLooker.getOfficialCol()).toString().trim().equals("1.0");
                    boolean coach = r.getCell(ref.getCol() + colLooker.getCoachCol()).toString().trim().equals("1.0");

                    StaffPosition position = judge
                            ? StaffPosition.Judge
                            : official
                                    ? StaffPosition.Official
                                    : coach
                                            ? StaffPosition.Coach
                                            : null;

                    boolean eat = r.getCell(ref.getCol() + colLooker.getEatCol()).toString().trim().equals("1.0");

                    if (!name.isEmpty())
                    {
                        fis.close();
                        return new StaffPerson(-1, name, position, eat, -1, "");
                    }
                }
                catch (NullPointerException ex)
                {
                    return null;
                }
            }
        }

        return null;
    }

}
