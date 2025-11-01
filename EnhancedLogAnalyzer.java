/**
 * Enhanced LogAnalyzer for Exercise 7.20 that can analyze HTTP status codes.
 */
public class EnhancedLogAnalyzer
{
    private int[] hourCounts;
    private int successfulAccesses;
    private int forbiddenAccesses;
    private int notFoundAccesses;
    private int otherAccesses;
    private LogfileReader reader;
    private String filename;

    public EnhancedLogAnalyzer(String filename)
    { 
        this.filename = filename;
        hourCounts = new int[24];
        successfulAccesses = 0;
        forbiddenAccesses = 0;
        notFoundAccesses = 0;
        otherAccesses = 0;
        reader = new LogfileReader(filename);
    }

    public void analyzeData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }
    
    public void analyzeEnhancedData()
    {
        successfulAccesses = 0;
        forbiddenAccesses = 0;
        notFoundAccesses = 0;
        otherAccesses = 0;
        
        System.out.println("Enhanced analysis would require:");
        System.out.println("1. Modified LogfileReader to handle 6-field format");
        System.out.println("2. Log files with format: year month day hour minute statusCode");
        System.out.println("3. Enhanced tokenization to handle the extra field");
    }
    
    public int getSuccessfulAccesses() { return successfulAccesses; }
    public int getUnsuccessfulAccesses() { return forbiddenAccesses + notFoundAccesses + otherAccesses; }
    public int getForbiddenAccesses() { return forbiddenAccesses; }
    public int getNotFoundAccesses() { return notFoundAccesses; }
    
    public void printStatusSummary()
    {
        int total = successfulAccesses + forbiddenAccesses + notFoundAccesses + otherAccesses;
        System.out.println("=== Access Status Summary ===");
        System.out.println("Total accesses: " + total);
        System.out.println("Successful (200): " + successfulAccesses);
        System.out.println("Forbidden (403): " + forbiddenAccesses);
        System.out.println("Not Found (404): " + notFoundAccesses);
        System.out.println("Other status codes: " + otherAccesses);
        
        if(total > 0) {
            double successRate = (double) successfulAccesses / total * 100;
            System.out.printf("Success rate: %.1f%%\n", successRate);
        }
    }
    
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
}
