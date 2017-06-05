/*
 * This class represents a karate fighter.
 */
package BE;

import BE.Users.Clubs.Abstract_ClubMember;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class Fighter extends Abstract_ClubMember
{

    /**
     * Variables
     */
    private final int fighterId;
    private String name = "Ikke navngivet";
    private Gender gender;
    private int grade;
    private int age;
    private int point = 0;
    private double weight;
    private double height;
    private boolean kata;
    private boolean kumite;
    private boolean kobudo;
    private boolean communalMeal;
    private boolean inGroup;

    private BattleAbility battleAbility;
    private RuleUnderstanding ruleUnderstanding;
    private Speed speed;
    private Statue statue;
    private Toughness toughness;

    /**
     * Constructor
     * @param id
     * @param name
     * @param gender
     * @param grade
     * @param age
     * @param weight
     * @param height
     * @param kata
     * @param kumite
     * @param kobudo
     * @param communalMeal
     * @param clubID
     * @param clubName 
     */
    public Fighter(int id, String name,
            Gender gender, int grade, int age, double weight,
            double height, boolean kata, boolean kumite, boolean kobudo, boolean communalMeal,
            int clubID, String clubName)
    {
        super(clubID, id, clubName);
        this.fighterId = id;
        this.name = name;
        this.communalMeal = communalMeal;
        this.gender = gender;
        this.grade = grade;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.kata = kata;
        this.kumite = kumite;
        this.kobudo = kobudo;

        this.battleAbility = BattleAbility.Bad;
        this.ruleUnderstanding = RuleUnderstanding.Bad;
        this.speed = Speed.Slow;
        this.statue = Statue.Sprawny;
        this.toughness = Toughness.Soft;

        this.point = 0;
        this.inGroup = false;
    }

    /**
     * @return the fighter ID of the fighter, unique to each tournament. 
     */
    public int getFighterId()
    {
        return fighterId;
    }

    /**
     * 
     * @return the name of the fighter.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Set the name of the fighter.
     * @param name 
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * 
     * @return whether or not the fighter is signed up to eat at the tournament.
     */
    public boolean isCommunalMeal()
    {
        return communalMeal;
    }

    /**
     * Set whether or not the fighter is signed up to eat at the tournament.
     * @param communalMeal 
     */
    public void setCommunalMeal(boolean communalMeal)
    {
        this.communalMeal = communalMeal;
    }

    /**
     * 
     * @return the gender of the fighter.
     */
    public Gender getGender()
    {
        return gender;
    }

    /**
     * Set the gender of the fighter.
     * @param gender 
     */
    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    /**
     * 
     * @return the grade of the fighter.
     */
    public int getGrade()
    {
        return grade;
    }

    /**
     * Set the grade of the fighter.
     * @param grade 
     */
    public void setGrade(int grade)
    {
        this.grade = grade;
    }

    /**
     * 
     * @return the age of the fighter.
     */
    public int getAge()
    {
        return age;
    }

    /**
     * Set the age of the fighter.
     * @param age 
     */
    public void setAge(int age)
    {
        this.age = age;
    }

    /**
     * 
     * @return the weight of the fighter.
     */
    public double getWeight()
    {
        return weight;
    }

    /**
     * Set the weight of the fighter.
     * @param weight 
     */
    public void setWeight(double weight)
    {
        this.weight = weight;
    }

    /**
     * 
     * @return the height of the fighter.
     */
    public double getHeight()
    {
        return height;
    }

    /**
     * Set the height of the fighter.
     * @param height 
     */
    public void setHeight(double height)
    {
        this.height = height;
    }

    public int getPoint()
    {
        return point;
    }

    public void setPoint(int point)
    {
        this.point = point;
    }

    /**
     * 
     * @return whether or not the fighter fights in kata battles.
     */
    public boolean isKata()
    {
        return kata;
    }

    /**
     * Set whether or not the fighter fights in kata battles.
     * @param kata 
     */
    public void setKata(boolean kata)
    {
        this.kata = kata;
    }

    /**
     * 
     * @return whether or not the fighter fights in kumite battles.
     */
    public boolean isKumite()
    {
        return kumite;
    }

    /**
     * Set whether or not the fighter fights in kumite battles.
     * @param kumite 
     */
    public void setKumite(boolean kumite)
    {
        this.kumite = kumite;
    }

    /**
     * 
     * @return whether or not the fighter fights in kobudo battles.
     */
    public boolean isKobudo()
    {
        return kobudo;
    }

    /**
     * Set whether or not the fighter fights in kobudo battles.
     * @param kobudo 
     */
    public void setKobudo(boolean kobudo)
    {
        this.kobudo = kobudo;
    }

    public BattleAbility getBattleAbility()
    {
        return battleAbility;
    }

    public RuleUnderstanding getRuleUnderstanding()
    {
        return ruleUnderstanding;
    }

    public Speed getSpeed()
    {
        return speed;
    }

    public Statue getStatue()
    {
        return statue;
    }

    public Toughness getToughness()
    {
        return toughness;
    }

    public void setBattleAbility(BattleAbility battleAbility)
    {
        this.battleAbility = battleAbility;
    }

    public void setRuleUnderstanding(RuleUnderstanding ruleUnderstanding)
    {
        this.ruleUnderstanding = ruleUnderstanding;
    }

    public void setSpeed(Speed speed)
    {
        this.speed = speed;
    }

    public void setStatue(Statue statue)
    {
        this.statue = statue;
    }

    public void setToughness(Toughness toughness)
    {
        this.toughness = toughness;

    }

    public enum Gender
    {
        Male, Female
    }

    public enum Statue
    {

        Sprawny, Averge, Heavy
    }

    public enum Speed
    {

        Slow, Averge, Fast
    }

    public enum Toughness
    {

        Soft, Averge, Hard
    }

    public enum BattleAbility
    {

        Bad, Averge, Good
    }

    public enum RuleUnderstanding
    {

        Bad, Average, Good
    }
    
        public boolean isInGroup()
    {
        return inGroup;
    }

    public void setInGroup(boolean inGroup)
    {
        this.inGroup = inGroup;
    }
    
    @Override
    public String toString()
    {
        return name;
    }
}
