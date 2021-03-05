package utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidator {

    private String dateFormat = "dd.MM.yyyy";

    private Pattern pattern;
    private Matcher matcher;

    private static final String DATE_PATTERN =
            "(0?[1-9]|[12][0-9]|3[01]).(0?[1-9]|1[012]).((19|20)\\d\\d)";

    public DateValidator() {
        pattern = Pattern.compile(DATE_PATTERN);
    }


    public Date convert(String timestamp) {
        Date date = null;

        try {
            DateFormat dateFormat = new SimpleDateFormat(this.dateFormat);
            java.util.Date dateTemp = dateFormat.parse(timestamp);
            date = new Date(dateTemp.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date currentDate() {
        Long current = System.currentTimeMillis();
        java.util.Date date = new Date(current);
        return new Date(date.getTime());

    }

    public boolean isDatesValid(final String checkinStr, final String checkoutStr) {
        Date checkinDate = null;
        Date checkoutDate = null;
        if(isDateValid(checkinStr) && isDateValid(checkoutStr)) {
            checkinDate = convert(checkinStr);
            checkoutDate = convert(checkoutStr);
            if(checkoutDate.after(checkinDate)) {
                return true;
            }
        }
        return false;
    }

    public boolean isDateValid(final String date){

        matcher = pattern.matcher(date);

        if(matcher.matches()){
            matcher.reset();

            if(matcher.find()){
                String day = matcher.group(1);
                String month = matcher.group(2);
                int year = Integer.parseInt(matcher.group(3));

                if (day.equals("31") &&
                        (month.equals("4") || month .equals("6") || month.equals("9") ||
                                month.equals("11") || month.equals("04") || month .equals("06") ||
                                month.equals("09"))) {
                    return false; // only 1,3,5,7,8,10,12 has 31 days
                }

                else if (month.equals("2") || month.equals("02")) {
                    //leap year
                    if(year % 4==0){
                        if(day.equals("30") || day.equals("31")){
                            return false;
                        }
                        else{
                            return true;
                        }
                    }
                    else{

                        if(day.equals("29")||day.equals("30")||day.equals("31")){
                            return false;
                        }
                        else{
                            return true;
                        }
                    }
                }

                else{
                    return true;
                }
            }

            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

}
