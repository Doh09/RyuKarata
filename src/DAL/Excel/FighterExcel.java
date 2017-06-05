/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL.Excel;

import BE.ColLooker;
import BE.Fighter;
import BE.Tournaments.Abstract_Tournament;
import BE.Tournaments.Battles.Battle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class FighterExcel {

    public Fighter readFighter(String fileName, int lineStart, ColLooker colLooker) throws IOException {
        if (fileName.endsWith(".xls")) {
            return oldReadExcel(fileName, lineStart, colLooker);
        } else {
            return newReadExcel(fileName, lineStart, colLooker);
        }
    }

    private Fighter oldReadExcel(String fileName, int line, ColLooker colLooker) throws IOException {
        File myFile = new File(fileName);
        // Finds the workbook instance for XLS file
        try (FileInputStream fileInput = new FileInputStream(myFile)) {
            // Finds the workbook instance for XLS file
            HSSFWorkbook workBook = new HSSFWorkbook(fileInput);

            // Return first sheet from the XLS workbook, the fighters, to get Person, replace the 0 with 1
            HSSFSheet sheet = workBook.getSheetAt(0);

            CellReference ref = new CellReference("A" + line);
            Row r = sheet.getRow(ref.getRow());
            if (r != null) {
                String name = r.getCell(ref.getCol() + colLooker.getNameCol()).toString().trim();
                
                Fighter.Gender gender = r.getCell(ref.getCol() + colLooker.getGenderCol()).toString().toUpperCase().equals(("M")) ? Fighter.Gender.Male : Fighter.Gender.Female;

                int grade = 0;
                int age = 0;
                double weight = 0;
                double height = 0;
                boolean kata = false;
                boolean kumite = false;
                boolean kobudo = false;
                boolean eat = false;

                try {
                    grade = (int) Double.parseDouble(r.getCell(ref.getCol() + colLooker.getGradeCol()).toString());
                } catch (NumberFormatException ex) {

                }

                try {
                    age = (int) Double.parseDouble(r.getCell(ref.getCol() + colLooker.getAgeCol()).toString());
                } catch (NumberFormatException ex) {

                }

                try {
                    weight = Double.parseDouble(r.getCell(ref.getCol() + colLooker.getWeightCol()).toString());
                } catch (NumberFormatException ex) {

                }
                try {
                    height = Double.parseDouble(r.getCell(ref.getCol() + colLooker.getHeightCol()).toString());
                } catch (NumberFormatException ex) {

                }

                try {
                    kata = (int) Double.parseDouble((r.getCell(ref.getCol() + colLooker.getKataCol()).toString())) == 1;
                } catch (NumberFormatException ex) {

                }
                try {
                    kumite = (int) Double.parseDouble(r.getCell(ref.getCol() + colLooker.getKumiteCol()).toString()) == 1;
                } catch (NumberFormatException ex) {

                }
                try {
                    kobudo = (int) Double.parseDouble(r.getCell(ref.getCol() + colLooker.getKobudoCol()).toString()) == 1;
                } catch (NumberFormatException ex) {

                }
                try {
                    eat = (int) Double.parseDouble(r.getCell(ref.getCol() + colLooker.getEatCol()).toString()) == 1;
                } catch (NumberFormatException ex) {

                }

                if (!name.isEmpty()) {
                    fileInput.close();
                    return new Fighter(-1, name, gender, grade, age, weight, height, kata, kumite, kobudo, eat, -1, null);
                } else {
                    fileInput.close();
                    return null;
                }

            }
        }

        return null;
    }

    private Fighter newReadExcel(String fileName, int line, ColLooker colLooker) throws IOException {
        File myFile = new File(fileName);
        // Finds the workbook instance for XLSX file
        try (FileInputStream fis = new FileInputStream(myFile)) {
            // Finds the workbook instance for XLSX file
            XSSFWorkbook workBook = new XSSFWorkbook(fis);

            // Return first sheet from the XLSX workbook, the fighters, to get Person, replace the 0 with 1
            XSSFSheet sheet = workBook.getSheetAt(0);

            CellReference ref = new CellReference("A" + line);
            Row r = sheet.getRow(ref.getRow());
            if (r != null) {

                String name = r.getCell(ref.getCol() + colLooker.getNameCol()).toString().trim();
                Fighter.Gender gender = r.getCell(ref.getCol() + colLooker.getGenderCol()).toString().toUpperCase().equals(("M")) ? Fighter.Gender.Male : Fighter.Gender.Female;

                int grade = 0;
                int age = 0;
                double weight = 0;
                double height = 0;
                boolean kata = false;
                boolean kumite = false;
                boolean kobudo = false;
                boolean eat = false;

                try {
                    grade = (int) Double.parseDouble(r.getCell(ref.getCol() + colLooker.getGradeCol()).toString());
                } catch (NumberFormatException | NullPointerException ex) {

                }

                try {
                    age = (int) Double.parseDouble(r.getCell(ref.getCol() + colLooker.getAgeCol()).toString());
                } catch (NumberFormatException | NullPointerException ex) {

                }

                try {
                    weight = Double.parseDouble(r.getCell(ref.getCol() + colLooker.getWeightCol()).toString());
                } catch (NumberFormatException | NullPointerException ex) {

                }
                try {
                    height = Double.parseDouble(r.getCell(ref.getCol() + colLooker.getHeightCol()).toString());
                } catch (NumberFormatException | NullPointerException ex) {

                }

                try {
                    kata = (int) Double.parseDouble((r.getCell(ref.getCol() + colLooker.getKataCol()).toString())) == 1;
                } catch (NumberFormatException | NullPointerException ex) {

                }
                try {
                    kumite = (int) Double.parseDouble(r.getCell(ref.getCol() + colLooker.getKumiteCol()).toString()) == 1;
                } catch (NumberFormatException | NullPointerException ex) {

                }
                try {
                    kobudo = (int) Double.parseDouble(r.getCell(ref.getCol() + colLooker.getKobudoCol()).toString()) == 1;
                } catch (NumberFormatException | NullPointerException ex) {

                }
                try {
                    eat = (int) Double.parseDouble(r.getCell(ref.getCol() + colLooker.getEatCol()).toString()) == 1;
                } catch (NumberFormatException | NullPointerException ex) {

                }

                if (!name.isEmpty()) {
                    fis.close();
                    return new Fighter(-1, name, gender, grade, age, weight, height, kata, kumite, kobudo, eat, -1, null);
                } else {
                    fis.close();
                    return null;
                }

            }
        }
        return null;
    }

    public void writeFighter(String fileName, Fighter fighter, Abstract_Tournament at) throws IOException {
        if (fileName.endsWith(".xls")) {
            oldWriteExcel(fileName, fighter, at);
        } else {
            newWriteExcel(fileName, fighter, at);
        }
    }

    private void oldWriteExcel(String fileName, Fighter fighter, Abstract_Tournament at) throws FileNotFoundException, IOException {

        ArrayList<Battle> battleList = at.getBattleManager().getBattleFighterList(fighter);
        String tournamentName = at.getName();
        String tournamentPartOf = at.getPartOfLargerTournament();

        System.out.println("Writing on XLS file started, wait for it to finish before opening file...");
        FileOutputStream fis;
        try (HSSFWorkbook workBook = new HSSFWorkbook()) {
            File excel = new File(fileName);
            fis = new FileOutputStream(excel);
            workBook.write(fis);
            //Create cells
            //Create a sheet
            HSSFSheet fighterSheet = workBook.createSheet(fighter.getName());
            fighterSheet.setColumnWidth(0, 10000);

            Row row = fighterSheet.createRow((short) 0);
            Cell cell = row.createCell(0);
            String toPrint = "Turnering: " + tournamentName;
            cell.setCellValue(toPrint);

            row = fighterSheet.createRow((short) 1);
            cell = row.createCell(0);
            toPrint = "En del af: " + tournamentPartOf;
            cell.setCellValue(toPrint);

            row = fighterSheet.createRow((short) 2);
            cell = row.createCell(0);
            toPrint = "------------------------------";
            cell.setCellValue(toPrint);

            row = fighterSheet.createRow((short) 3);
            cell = row.createCell(0);
            cell.setCellValue("Information omkring ");
            cell = row.createCell(1);
            cell.setCellValue(fighter.getName());

            row = fighterSheet.createRow((short) 4);
            cell = row.createCell(0);
            cell.setCellValue("Køn");
            cell = row.createCell(1);
            String gender = "Mand";
            if (fighter.getGender() == Fighter.Gender.Female) {
                gender = "Kvinde";
            }
            cell.setCellValue(gender);

            row = fighterSheet.createRow((short) 5);
            cell = row.createCell(0);
            cell.setCellValue("Grad");
            cell = row.createCell(1);
            cell.setCellValue(fighter.getGrade());

            row = fighterSheet.createRow((short) 6);
            cell = row.createCell(0);
            cell.setCellValue("Alder");
            cell = row.createCell(1);
            cell.setCellValue(fighter.getAge());

            row = fighterSheet.createRow((short) 7);
            cell = row.createCell(0);
            cell.setCellValue("Vægt");
            cell = row.createCell(1);
            cell.setCellValue(fighter.getWeight());

            row = fighterSheet.createRow((short) 8);
            cell = row.createCell(0);
            cell.setCellValue("Højde");
            cell = row.createCell(1);
            cell.setCellValue(fighter.getHeight());

            row = fighterSheet.createRow((short) 9);
            cell = row.createCell(0);
            cell.setCellValue("Kata");
            cell = row.createCell(1);
            cell.setCellValue(fighter.isKata() ? "Ja" : "Nej");

            row = fighterSheet.createRow((short) 10);
            cell = row.createCell(0);
            cell.setCellValue("Kumite");
            cell = row.createCell(1);
            cell.setCellValue(fighter.isKumite() ? "Ja" : "Nej");

            row = fighterSheet.createRow((short) 11);
            cell = row.createCell(0);
            cell.setCellValue("Kobudo");
            cell = row.createCell(1);
            cell.setCellValue(fighter.isKobudo() ? "Ja" : "Nej");

            row = fighterSheet.createRow((short) 12);
            cell = row.createCell(0);
            cell.setCellValue("Fællesspisning");
            cell = row.createCell(1);
            cell.setCellValue(fighter.isCommunalMeal() ? "Ja" : "Nej");

            int startLineBattle = 14;

            int index = startLineBattle;

            for (Battle battle : battleList) {
                row = fighterSheet.createRow((short) index + 0);
                cell = row.createCell(0);
                cell.setCellValue("------------------------------");

                row = fighterSheet.createRow((short) index + 1);
                cell = row.createCell(0);
                cell.setCellValue("Kæmpere: ");
                cell = row.createCell(1);
                if (battle.getFighter1Red() != null && battle.getFighter2Blue() != null) {
                    cell.setCellValue(battle.getFighter1Red().getName() + " VS " + battle.getFighter2Blue().getName());
                } else if (battle.getFighter1Red() != null) {
                    cell.setCellValue(battle.getFighter1Red().getName() + " VS " + "Ingen modstander");
                } else if (battle.getFighter2Blue() != null) {
                    cell.setCellValue("Ingen modstander" + " VS " + battle.getFighter2Blue().getName());
                }

                row = fighterSheet.createRow((short) index + 2);
                cell = row.createCell(0);
                if (battle.getFighter1Red() != null) {
                    cell.setCellValue(battle.getFighter1Red().getName() + " point:");
                    cell = row.createCell(1);
                    cell.setCellValue(battle.getRedPoint());
                } else {
                    cell.setCellValue("Ingen modstander" + " point:");
                    cell = row.createCell(1);
                    cell.setCellValue(battle.getRedPoint());
                }

                row = fighterSheet.createRow((short) index + 3);
                cell = row.createCell(0);
                if (battle.getFighter2Blue() != null) {
                    cell.setCellValue(battle.getFighter2Blue().getName() + " point:");
                    cell = row.createCell(1);
                    cell.setCellValue(battle.getBluePoint());
                } else {
                    cell.setCellValue("Ingen modstander" + " point:");
                    cell = row.createCell(1);
                    cell.setCellValue(battle.getBluePoint());
                }

                row = fighterSheet.createRow((short) index + 4);
                cell = row.createCell(0);
                cell.setCellValue("Vinder:");
                cell = row.createCell(1);
                if (battle.getWinner() != null) {
                    cell.setCellValue(battle.getWinner().getName());
                } else {
                    cell.setCellValue("Vinder ikke fundet");
                }

                index += 6;
            }

            // open an OutputStream to save written data into XLSX file
            try (
                    FileOutputStream os = new FileOutputStream(excel)) {
                workBook.write(os);
                System.out.println("Writing on XLS file Finished ...");
            }
        }
        fis.close();
    }

    private void newWriteExcel(String fileName, Fighter fighter, Abstract_Tournament at) throws FileNotFoundException, IOException {

        ArrayList<Battle> battleList = at.getBattleManager().getBattleFighterList(fighter);
        String tournamentName = at.getName();
        String tournamentPartOf = at.getPartOfLargerTournament();
        FileOutputStream fis;
        try (XSSFWorkbook workBook = new XSSFWorkbook()) {
            File excel = new File(fileName);
            fis = new FileOutputStream(excel);
            workBook.write(fis);
            //Create cells
            //Create a sheet
            XSSFSheet fighterSheet = workBook.createSheet(fighter.getName());
            fighterSheet.setColumnWidth(0, 10000);

            Row row = fighterSheet.createRow((short) 0);
            Cell cell = row.createCell(0);
            String toPrint = "Turnering: " + tournamentName;
            cell.setCellValue(toPrint);

            row = fighterSheet.createRow((short) 1);
            cell = row.createCell(0);
            toPrint = "En del af: " + tournamentPartOf;
            cell.setCellValue(toPrint);

            row = fighterSheet.createRow((short) 2);
            cell = row.createCell(0);
            toPrint = "------------------------------";
            cell.setCellValue(toPrint);

            row = fighterSheet.createRow((short) 3);
            cell = row.createCell(0);
            cell.setCellValue("Information omkring ");
            cell = row.createCell(1);
            cell.setCellValue(fighter.getName());

            row = fighterSheet.createRow((short) 4);
            cell = row.createCell(0);
            cell.setCellValue("Køn");
            cell = row.createCell(1);
            String gender = "Mand";
            if (fighter.getGender() == Fighter.Gender.Female) {
                gender = "Kvinde";
            }
            cell.setCellValue(gender);

            row = fighterSheet.createRow((short) 5);
            cell = row.createCell(0);
            cell.setCellValue("Grad");
            cell = row.createCell(1);
            cell.setCellValue(fighter.getGrade());

            row = fighterSheet.createRow((short) 6);
            cell = row.createCell(0);
            cell.setCellValue("Alder");
            cell = row.createCell(1);
            cell.setCellValue(fighter.getAge());

            row = fighterSheet.createRow((short) 7);
            cell = row.createCell(0);
            cell.setCellValue("Vægt");
            cell = row.createCell(1);
            cell.setCellValue(fighter.getWeight());

            row = fighterSheet.createRow((short) 8);
            cell = row.createCell(0);
            cell.setCellValue("Højde");
            cell = row.createCell(1);
            cell.setCellValue(fighter.getHeight());

            row = fighterSheet.createRow((short) 9);
            cell = row.createCell(0);
            cell.setCellValue("Kata");
            cell = row.createCell(1);
            cell.setCellValue(fighter.isKata() ? "Ja" : "Nej");

            row = fighterSheet.createRow((short) 10);
            cell = row.createCell(0);
            cell.setCellValue("Kumite");
            cell = row.createCell(1);
            cell.setCellValue(fighter.isKumite() ? "Ja" : "Nej");

            row = fighterSheet.createRow((short) 11);
            cell = row.createCell(0);
            cell.setCellValue("Kobudo");
            cell = row.createCell(1);
            cell.setCellValue(fighter.isKobudo() ? "Ja" : "Nej");

            row = fighterSheet.createRow((short) 12);
            cell = row.createCell(0);
            cell.setCellValue("Fællesspisning");
            cell = row.createCell(1);
            cell.setCellValue(fighter.isCommunalMeal() ? "Ja" : "Nej");

            int startLineBattle = 14;

            int index = startLineBattle;

            for (Battle battle : battleList) {
                row = fighterSheet.createRow((short) index + 0);
                cell = row.createCell(0);
                cell.setCellValue("------------------------------");

                row = fighterSheet.createRow((short) index + 1);
                cell = row.createCell(0);
                cell.setCellValue("Kæmpere: ");
                cell = row.createCell(1);
                if (battle.getFighter1Red() != null && battle.getFighter2Blue() != null) {
                    cell.setCellValue(battle.getFighter1Red().getName() + " VS " + battle.getFighter2Blue().getName());
                } else if (battle.getFighter1Red() != null) {
                    cell.setCellValue(battle.getFighter1Red().getName() + " VS " + "Ingen modstander");
                } else if (battle.getFighter2Blue() != null) {
                    cell.setCellValue("Ingen modstander" + " VS " + battle.getFighter2Blue().getName());
                }

                row = fighterSheet.createRow((short) index + 2);
                cell = row.createCell(0);
                if (battle.getFighter1Red() != null) {
                    cell.setCellValue(battle.getFighter1Red().getName() + " point:");
                    cell = row.createCell(1);
                    cell.setCellValue(battle.getRedPoint());
                } else {
                    cell.setCellValue("Ingen modstander" + " point:");
                    cell = row.createCell(1);
                    cell.setCellValue(battle.getRedPoint());
                }

                row = fighterSheet.createRow((short) index + 3);
                cell = row.createCell(0);
                if (battle.getFighter2Blue() != null) {
                    cell.setCellValue(battle.getFighter2Blue().getName() + " point:");
                    cell = row.createCell(1);
                    cell.setCellValue(battle.getBluePoint());
                } else {
                    cell.setCellValue("Ingen modstander" + " point:");
                    cell = row.createCell(1);
                    cell.setCellValue(battle.getBluePoint());
                }

                row = fighterSheet.createRow((short) index + 4);
                cell = row.createCell(0);
                cell.setCellValue("Vinder:");
                cell = row.createCell(1);
                if (battle.getWinner() != null) {
                    cell.setCellValue(battle.getWinner().getName());
                } else {
                    cell.setCellValue("Vinder ikke fundet");
                }

                index += 6;
            }

            // open an OutputStream to save written data into XLSX file
            try (FileOutputStream os = new FileOutputStream(excel)) {
                workBook.write(os);
                System.out.println("Writing on XLSX file Finished ...");
            }
        }
        fis.close();
    }

}
