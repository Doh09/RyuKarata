/*
 * This class is used to administrate the time that has passed in a battle.
 */
package BE;

import java.util.ArrayList;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class StopWatch
{

    private int millisecond;
    private int second;
    private int minute;
    private int hour;   
    private ArrayList<String> timeList;

    public StopWatch()
    {
        timeList = new ArrayList<>();
        reset();
    }

    public void start(int time)
    {
        millisecond += 1 * time;
        if (millisecond >= (1000))
        {
            millisecond = 0;
            second++;
            if (second >= 60)
            {
                second = 0;
                minute++;
                if (minute >= 60)
                {
                    minute = 0;
                    hour++;
                }
            }
        }
    }

    public void newLap()
    {
        timeList.add(toString());
        reset();
    }

    public void remove(int i)
    {
        timeList.remove(i);
    }

    public void reset()
    {
        this.millisecond = 0;
        this.second = 0;
        this.minute = 0;
        this.hour = 0;
    }

    @Override
    public String toString()
    {
        return String.format("%2d : %2d : %2d : %4d", hour, minute, second, millisecond);
    }

    public ArrayList<String> getTimeList()
    {
        return timeList;
    }

    public void addLap()
    {
        timeList.add(toString());
    }

    public void removeLap()
    {
        if (!timeList.isEmpty())
        {
            timeList.remove(timeList.size() - 1);
        }
    }

    public int getMillisecond()
    {
        return millisecond;
    }

    public int getSecond()
    {
        return second;
    }

    public int getMinute()
    {
        return minute;
    }

    public int getHour()
    {
        return hour;
    }

}
