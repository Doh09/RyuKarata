/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.Fighter;
import BE.Tournaments.Abstract_Tournament;
import BLL.TournamentTypes.SuperTournament;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class TournamentRequirementChecker
{
    ArrayList<Fighter> list = new ArrayList<>();
public TournamentRequirementChecker()
{

}
public void newGruop(Abstract_Tournament tournament,SuperTournament superTournament)
    {
      //add fighter til groups
         ArrayList<Fighter> ListOFAllFighters = superTournament.getFighterManager().getAllAsArrayList();  
               try
        {    
           
    double grade= tournament.getRequirementsValue().get("grade");
    double age= tournament.getRequirementsValue().get("age");
    double weight= tournament.getRequirementsValue().get("weight");
    double height= tournament.getRequirementsValue().get("height");
    boolean kata = tournament.getRequirementsBooleans().get("kata");
    boolean kumite= tournament.getRequirementsBooleans().get("kumite");
    boolean kobudo=tournament.getRequirementsBooleans().get("kobudo");
 
    double gradeT= tournament.getRequirementsValue().get("gradeT");
    double ageT= tournament.getRequirementsValue().get("ageT");
    double weightT= tournament.getRequirementsValue().get("weightT");
    double heightT= tournament.getRequirementsValue().get("heightT");
    double genderT= tournament.getRequirementsValue().get("genderT");
    // these boobleans are to check if the fighter over hold the requaments
    boolean ageW=false;
    boolean gradeW=false;
    boolean weightW=false;
    boolean heightW=false;
    boolean kataW=false;
    boolean kumiteW=false;
    boolean kobudoW=false;
    boolean genderW=false;
        //if (){}
      for (Fighter fighter : ListOFAllFighters) {
     ageW=false;
     gradeW=false;
     weightW=false;
     heightW=false;
     kataW=false;
     kumiteW=false;
     kobudoW=false;
     genderW=false;
          if     (fighter.isInGroup() == false )
                  {
          //age          
          if (0.00==(ageT))//Lige meget
          {ageW=true;}else
          if (1.00==(ageT))//equal
          {if (fighter.getAge() == age){ageW=true;}}
          if (2.00==(ageT))//not equal
          {if (fighter.getAge() != age){ageW=true;}}
          if (3.00==(ageT))//bigger
          {if (fighter.getAge() >= age){ageW=true;}}
          if (4.00==(ageT))//smaller
          {if (fighter.getAge() <= age){ageW=true;}}
                    //Grade            
          if (0.00==(gradeT))
          {gradeW=true;}else
          if (1.00==(gradeT))
          {if (fighter.getGrade() == grade){gradeW=true;}}
          if (2.00==(gradeT))
          {if (fighter.getGrade() != grade){gradeW=true;}}
          if (3.00==(gradeT))
          {if (fighter.getGrade() >= grade){gradeW=true;}}
          if (4.00==(gradeT))
          {if (fighter.getGrade() <= grade){gradeW=true;}}
                    //weightW            
          if (0.00==(weightT))
          {weightW=true;}else
          if (1.00==(weightT))
          {if (fighter.getWeight() == weight){weightW=true;}}
          if (2.00==(weightT))
          {if (fighter.getWeight() != weight){weightW=true;}}
          if (3.00==(weightT))
          {if (fighter.getWeight() >= weight){weightW=true;}}
          if (4.00==(weightT))
          {if (fighter.getWeight() <= weight){weightW=true;}}
                    //heightW            
          if (0.00==(heightT))
          {heightW=true;}else
          if (1.00==(heightT))
          {if (fighter.getHeight() == height){heightW=true;}}
          if (2.00==(heightT))
          {if (fighter.getHeight() != height){heightW=true;}}
          if (3.00==(heightT))
          {if (fighter.getHeight() >= height){heightW=true;}}
          if (4.00==(heightT))
          {if (fighter.getHeight() <= height){heightW=true;}}
          //kata kodubo kumite
          if (fighter.isKata() == kata){kataW=true;}
          if (fighter.isKobudo() == kobudo){kobudoW=true;}
          if (fighter.isKumite() == kumite){kumiteW=true;}
          //gender the reson men is first is because they most offen have men tournements
          if (1.00==(genderT)) //both
          {genderW=true;}else
          if (0.00==(genderT)) //male
          {if (fighter.getGender() == Fighter.Gender.Male){genderW=true;}}
          if (2.00==(genderT)) //female
          {if (fighter.getGender() == Fighter.Gender.Female){genderW=true;}}
          //checks if the recuramets are true
          if      ((ageW==true)&&
                  (gradeW==true)&&
                  (heightW==true)&&
                  (weightW==true)&&
                  (kataW==true)&&
                  (kobudoW==true)&&
                  (kumiteW==true)&&
                  (genderW==true)
                  )
                  {
                  //så add til gruppen
                  list.add(fighter);
                  tournament.getFighterManager().setFighters(list);
                  fighter.setInGroup(true);
                  }
                  }
                  }
                }
        catch (Exception e)
        {
        JOptionPane.showMessageDialog(null,"venligst kun brug tal ved grade alder wægt og højte");
        }
}}
