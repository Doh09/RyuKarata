/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class ColLooker
{

    private int nameCol;
    private int eatCol;

    private int genderCol;
    private int gradeCol;
    private int ageCol;
    private int weightCol;
    private int heightCol;
    private int kataCol;
    private int kumiteCol;
    private int kobudoCol;

    private int judgeCol;
    private int officialCol;
    private int coachCol;

    public ColLooker(String name, String gender, String grade, String age, String weight, String height, String kata, String kumite, String kobudo, String eat)
    {
        if (!name.isEmpty() && !gender.isEmpty() && !grade.isEmpty() && !age.isEmpty() && !weight.isEmpty() && !height.isEmpty() && !kata.isEmpty() && !kumite.isEmpty() && !kobudo.isEmpty() && !eat.isEmpty())
        {
            nameCol = makeLetterToInt(name);
            genderCol = makeLetterToInt(gender);
            gradeCol = makeLetterToInt(grade);
            ageCol = makeLetterToInt(age);
            weightCol = makeLetterToInt(weight);
            heightCol = makeLetterToInt(height);
            kataCol = makeLetterToInt(kata);
            kumiteCol = makeLetterToInt(kumite);
            kobudoCol = makeLetterToInt(kobudo);
            eatCol = makeLetterToInt(eat);
        }
        else
        {
            nameCol = 0;
            genderCol = 1;
            gradeCol = 2;
            ageCol = 3;
            weightCol = 4;
            heightCol = 5;
            kataCol = 6;
            kumiteCol = 7;
            kobudoCol = 8;
            eatCol = 9;
        }
    }

    public ColLooker(String name, String judge, String official, String coach, String eat)
    {
        if (!name.isEmpty() && !judge.isEmpty() && !official.isEmpty() && !coach.isEmpty() && !eat.isEmpty())
        {
            nameCol = makeLetterToInt(name);
            judgeCol = makeLetterToInt(judge);
            officialCol = makeLetterToInt(official);
            coachCol = makeLetterToInt(coach);
            eatCol = makeLetterToInt(eat);
        }
        else
        {
            nameCol = 0;
            judgeCol = 1;
            officialCol = 2;
            coachCol = 3;
            eatCol = 4;
        }
    }

    private int makeLetterToInt(String text)
    {
        switch (text.toUpperCase().trim())
        {
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
            case "E":
                return 4;
            case "F":
                return 5;
            case "G":
                return 6;
            case "H":
                return 7;
            case "I":
                return 8;
            case "J":
                return 9;
            case "K":
                return 10;
            case "L":
                return 11;
            case "M":
                return 12;
            case "N":
                return 13;
            case "O":
                return 14;
            case "P":
                return 15;
            case "Q":
                return 16;
            case "R":
                return 17;
            case "S":
                return 18;
            case "T":
                return 19;
            case "U":
                return 20;
            case "V":
                return 21;
            case "W":
                return 22;
            case "X":
                return 23;
            case "Y":
                return 24;
            case "Z":
                return 25;
            case "AA":
                return 26;
            case "AB":
                return 27;
            case "AC":
                return 28;
            case "AD":
                return 29;
            case "AE":
                return 30;
            case "AF":
                return 31;
            case "AG":
                return 32;

        }
        return -1;
    }

    public int getNameCol()
    {
        return nameCol;
    }

    public int getGenderCol()
    {
        return genderCol;
    }

    public int getGradeCol()
    {
        return gradeCol;
    }

    public int getAgeCol()
    {
        return ageCol;
    }

    public int getWeightCol()
    {
        return weightCol;
    }

    public int getHeightCol()
    {
        return heightCol;
    }

    public int getKataCol()
    {
        return kataCol;
    }

    public int getKumiteCol()
    {
        return kumiteCol;
    }

    public int getKobudoCol()
    {
        return kobudoCol;
    }

    public int getEatCol()
    {
        return eatCol;
    }

    public int getJudgeCol()
    {
        return judgeCol;
    }

    public int getOfficialCol()
    {
        return officialCol;
    }

    public int getCoachCol()
    {
        return coachCol;
    }
    
    

}
