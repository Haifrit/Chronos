package jan.philip.wolter.chronos.jan.philip.wolter.database;

/**
 * Created by J.Wolter on 13.06.2016.
 */
public class MyEvent {

  private int hour;
  private int minute;
  private String eventText;

  public MyEvent(int hour, int minute) {
    this.hour = hour;
    this.minute = minute;
    this.eventText = "Nothing Yet";
  }

  public void setEventText(String eventText) {
    this.eventText = eventText;
  }

  public int getHour() {
    return hour;
  }

  public int getMinute() {
    return minute;
  }

  public String getEventText() {
    return eventText;
  }
}
