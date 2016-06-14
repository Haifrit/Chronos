package jan.philip.wolter.chronos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import jan.philip.wolter.chronos.jan.philip.wolter.database.MyEvent;

/**
 * Created by J.Wolter on 09.06.2016.
 */
public class MyDate {
  private int year;
  private int month;
  private int dayOfMonth;
  private List<MyEvent> listOfEvents;
  private Calendar calendar = Calendar.getInstance();

  public MyDate(int year, int month, int dayOfMonth) {
    this.year = year;
    this.month = month;
    this.dayOfMonth = dayOfMonth;
    listOfEvents = new ArrayList<>();
    calendar.set(Calendar.YEAR,this.year);
    calendar.set(Calendar.MONTH,this.month);
    calendar.set(Calendar.DAY_OF_MONTH,this.dayOfMonth);
  }

  public int getYear() {
    return year;
  }

  public String getMonthAsString() {
    return calendar.getDisplayName(Calendar.MONTH,Calendar.LONG, Locale.getDefault());
  }

  public int getMonth() {
    return month;
  }

  public void addEvent (MyEvent myEvent) {
    listOfEvents.add(myEvent);
  }

  public int getDayOfMonth() {
    return dayOfMonth;
  }

  public int getDateAsInt() {
    String date = "" + year + month + dayOfMonth;
    return Integer.parseInt(date);
  }
}
