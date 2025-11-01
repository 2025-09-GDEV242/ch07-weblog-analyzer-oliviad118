/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;
    // Store the filename for recreating readers
    private String filename;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        this("demo.log");
    }
    
    /**
     * Create an object to analyze hourly web accesses.
     * @param filename The name of the log file to analyze.
     */
    public LogAnalyzer(String filename)
    { 
        this.filename = filename;
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(filename);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /**
     * Return the number of accesses recorded in the log file.
     * @return The number of accesses.
     */
    public int numberOfAccesses()
    {
        int total = 0;
        // Add the value in each element of hourCounts to total.
        for(int i = 0; i < hourCounts.length; i++) {
            total += hourCounts[i];
        }
        return total;
    }
    
    /**
     * Find the busiest hour of the day.
     * @return The hour with the most accesses.
     */
    public int busiestHour()
    {
        int busiestHour = 0;
        int maxCount = hourCounts[0];
        
        for(int hour = 1; hour < hourCounts.length; hour++) {
            if(hourCounts[hour] > maxCount) {
                maxCount = hourCounts[hour];
                busiestHour = hour;
            }
        }
        return busiestHour;
    }
    
    /**
     * Find the quietest hour of the day.
     * @return The hour with the fewest accesses.
     */
    public int quietestHour()
    {
        int quietestHour = 0;
        int minCount = hourCounts[0];
        
        for(int hour = 1; hour < hourCounts.length; hour++) {
            if(hourCounts[hour] < minCount) {
                minCount = hourCounts[hour];
                quietestHour = hour;
            }
        }
        return quietestHour;
    }
    
    /**
     * Find the busiest two-hour period of the day.
     * @return The starting hour of the busiest two-hour period.
     */
    public int busiestTwoHour()
    {
        int busiestStart = 0;
        int maxTwoHourCount = hourCounts[0] + hourCounts[1];
        
        for(int hour = 1; hour < hourCounts.length; hour++) {
            int nextHour = (hour + 1) % 24; // Handle wrap-around from 23 to 0
            int twoHourCount = hourCounts[hour] + hourCounts[nextHour];
            
            if(twoHourCount > maxTwoHourCount) {
                maxTwoHourCount = twoHourCount;
                busiestStart = hour;
            }
        }
        return busiestStart;
    }
    
    /**
     * Find the quietest day of the month.
     * Assumes all months have 28 days.
     * @return The day with the fewest accesses.
     */
    public int quietestDay()
    {
        int[] dayCounts = new int[29]; // Days 1-28
        
        // Reset the reader and count accesses by day
        reader = new LogfileReader(filename);
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int day = entry.getDay();
            if(day <= 28) { // Only consider days 1-28
                dayCounts[day]++;
            }
        }
        
        int quietestDay = 1;
        int minCount = dayCounts[1];
        
        for(int day = 2; day <= 28; day++) {
            if(dayCounts[day] < minCount) {
                minCount = dayCounts[day];
                quietestDay = day;
            }
        }
        return quietestDay;
    }
    
    /**
     * Find the busiest day of the month.
     * Assumes all months have 28 days.
     * @return The day with the most accesses.
     */
    public int busiestDay()
    {
        int[] dayCounts = new int[29]; // Days 1-28
        
        // Reset the reader and count accesses by day
        reader = new LogfileReader(filename);
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int day = entry.getDay();
            if(day <= 28) { // Only consider days 1-28
                dayCounts[day]++;
            }
        }
        
        int busiestDay = 1;
        int maxCount = dayCounts[1];
        
        for(int day = 2; day <= 28; day++) {
            if(dayCounts[day] > maxCount) {
                maxCount = dayCounts[day];
                busiestDay = day;
            }
        }
        return busiestDay;
    }
    
    /**
     * Return an array of total accesses per month.
     * Index 0 = January, Index 11 = December.
     * @return Array of monthly access counts.
     */
    public int[] totalAccessesPerMonth()
    {
        int[] monthCounts = new int[12]; // Months 1-12 mapped to 0-11
        
        // Reset the reader and count accesses by month
        reader = new LogfileReader(filename);
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int month = entry.getMonth();
            if(month >= 1 && month <= 12) {
                monthCounts[month - 1]++; // Convert to 0-based index
            }
        }
        
        return monthCounts;
    }
    
    /**
     * Find the quietest month over all years of data.
     * @return The month (1-12) with the fewest accesses.
     */
    public int quietestMonth()
    {
        int[] monthCounts = totalAccessesPerMonth();
        
        int quietestMonth = 1;
        int minCount = monthCounts[0];
        
        for(int month = 1; month < 12; month++) {
            if(monthCounts[month] < minCount) {
                minCount = monthCounts[month];
                quietestMonth = month + 1; // Convert back to 1-based
            }
        }
        return quietestMonth;
    }
    
    /**
     * Find the busiest month over all years of data.
     * @return The month (1-12) with the most accesses.
     */
    public int busiestMonth()
    {
        int[] monthCounts = totalAccessesPerMonth();
        
        int busiestMonth = 1;
        int maxCount = monthCounts[0];
        
        for(int month = 1; month < 12; month++) {
            if(monthCounts[month] > maxCount) {
                maxCount = monthCounts[month];
                busiestMonth = month + 1; // Convert back to 1-based
            }
        }
        return busiestMonth;
    }
    
    /**
     * Calculate the average accesses per month over all years of data.
     * @return The average number of accesses per month.
     */
    public double averageAccessesPerMonth()
    {
        int[] monthCounts = totalAccessesPerMonth();
        int total = 0;
        
        for(int count : monthCounts) {
            total += count;
        }
        
        return (double) total / 12.0;
    }
}
