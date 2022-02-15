package dante.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class DateUtil {

  public String localDateToString(LocalDate localDate){

      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

      return simpleDateFormat.format(localDate);
  }
}
