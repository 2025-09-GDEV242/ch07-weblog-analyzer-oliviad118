/**
 * Enhanced LogEntry that includes a status code field for Exercise 7.20.
 */
public class EnhancedLogEntry
{
    private int[] dataValues;
    private static final int YEAR = 0, MONTH = 1, DAY = 2,
                             HOUR = 3, MINUTE = 4, STATUS_CODE = 5;
    private static final int NUMBER_OF_FIELDS = 6;
                      
    public EnhancedLogEntry(String logline)
    {
        dataValues = new int[NUMBER_OF_FIELDS];
        LoglineTokenizer tokenizer = new LoglineTokenizer();
        tokenizer.tokenize(logline, dataValues);
    }
    
    public EnhancedLogEntry(int year, int month, int day, int hour, int minute, int statusCode)
    {
        dataValues = new int[NUMBER_OF_FIELDS];
        dataValues[YEAR] = year;
        dataValues[MONTH] = month;
        dataValues[DAY] = day;
        dataValues[HOUR] = hour;
        dataValues[MINUTE] = minute;
        dataValues[STATUS_CODE] = statusCode;
    }
    
    public int getHour() { return dataValues[HOUR]; }
    public int getMinute() { return dataValues[MINUTE]; }
    public int getYear() { return dataValues[YEAR]; }
    public int getMonth() { return dataValues[MONTH]; }
    public int getDay() { return dataValues[DAY]; }
    public int getStatusCode() { return dataValues[STATUS_CODE]; }
    
    public boolean isSuccessful() { return dataValues[STATUS_CODE] == 200; }
    public boolean isForbidden() { return dataValues[STATUS_CODE] == 403; }
    public boolean isNotFound() { return dataValues[STATUS_CODE] == 404; }
    
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        for(int value : dataValues) {
            if(value < 10 && value != dataValues[STATUS_CODE]) {
                buffer.append('0');
            }
            buffer.append(value);
            buffer.append(' ');
        }
        return buffer.toString().trim();
    }
}
