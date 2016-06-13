package jan.philip.wolter.database;

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
    this.eventText = "Nothing yet";
  }

  public void setEventText(String eventText) {
    this.eventText = eventText;
  }
}
