package checks;

import org.apache.commons.lang.time.DateUtils;
import java.util.Calendar;
import java.util.Date;

import static org.apache.commons.lang.time.DateUtils.truncate;

class DateUtilsTruncateCheck_java7 {
  public void foo(Date date, Calendar calendar, Object object, int field) {
    DateUtils.truncate(date, field);
    DateUtils.truncate(calendar, field);
    DateUtils.truncate(object, field);
    truncate(date, field);
    truncate(calendar, field);
    truncate(object, field);
  }
}
