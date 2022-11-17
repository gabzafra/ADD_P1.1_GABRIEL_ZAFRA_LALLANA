package dam2.add.p11;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
  private File loggerFile;

  public Logger(String loggerPath) {
    this.loggerFile = new File(loggerPath);
  }

  public boolean addLog(String name, boolean isValid) {

    try (BufferedWriter input = new BufferedWriter(new FileWriter(loggerFile, true))) {
      Date now = new Date();
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

      String formatedRow = dateFormat.format(now) + ";" + name + ";" + (isValid ? "OK" : "ERROR");

      input.write(formatedRow);
      input.newLine();
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
