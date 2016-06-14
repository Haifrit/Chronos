package jan.philip.wolter.chronos;

import android.app.Application;
import jan.philip.wolter.chronos.jan.philip.wolter.database.ChronosDataSource;

/**
 * Created by J.Wolter on 29.04.2016.
 */
public class GlobalDatabase extends Application {


  private ChronosDataSource chronosDataSource = new ChronosDataSource(this);

  public ChronosDataSource getChronosDataSource() {
    return chronosDataSource;
  }



  @Override
  public void onCreate() {
    super.onCreate();
    chronosDataSource.open();
  }

  @Override
  public void onTerminate() {
    super.onTerminate();
    chronosDataSource.close();
  }
}
