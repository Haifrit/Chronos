package jan.philip.wolter.chronos;

/**
 * Created by J.Wolter on 09.06.2016.
 */
public class MyDate {
  private int year;
  private int month;
  private int dayOfWeek;

  public MyDate(int year, int month, int dayOfWeek) {
    this.year = year;
    this.month = month;
    this.dayOfWeek = dayOfWeek;
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
}
