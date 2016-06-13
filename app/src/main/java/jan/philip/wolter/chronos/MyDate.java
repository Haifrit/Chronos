package jan.philip.wolter.chronos;

import java.util.ArrayList;
import java.util.List;

import jan.philip.wolter.chronos.jan.philip.wolter.database.MyEvent;

/**
 * Created by J.Wolter on 09.06.2016.
 */
public class MyDate {
  private int year;
  private int month;
  private int dayOfWeek;
  private List<MyEvent> listOfEvents;

  public MyDate(int year, int month, int dayOfWeek) {
    this.year = year;
    this.month = month;
    this.dayOfWeek = dayOfWeek;
    listOfEvents = new ArrayList<>();
  }

  public int getYear() {
    return year;
  }

  public int getMonth() {
    return month;
  }

  public int getDayOfWeek() {
    return dayOfWeek;
  }

  public void addEvent (MyEvent myEvent) {
    listOfEvents.add(myEvent);
  }

  public int getDateAsInt() {
    String date = "" + year + month + dayOfWeek;
    return Integer.parseInt(date);
  }
}
