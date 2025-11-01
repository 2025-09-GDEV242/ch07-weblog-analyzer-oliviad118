/**
 * Simple test to demonstrate LogAnalyzer with a specific filename.
 */
public class SimpleTest
{
    public static void main(String[] args)
    {
        // Test the new constructor that takes a filename
        LogAnalyzer analyzer = new LogAnalyzer("demo.log");
        
        // Analyze the data
        analyzer.analyzeHourlyData();
        
        // Display results
        System.out.println("=== Analysis of demo.log ===");
        System.out.println("Total number of accesses: " + analyzer.numberOfAccesses());
        System.out.println("Busiest hour: " + analyzer.busiestHour() + ":00");
        System.out.println("Quietest hour: " + analyzer.quietestHour() + ":00");
        
        int busiestTwoStart = analyzer.busiestTwoHour();
        int busiestTwoEnd = (busiestTwoStart + 1) % 24;
        System.out.println("Busiest two-hour period: " + busiestTwoStart + ":00 - " + busiestTwoEnd + ":59");
        
        System.out.println("Quietest day: " + analyzer.quietestDay());
        System.out.println("Busiest day: " + analyzer.busiestDay());
        
        System.out.println("\nHourly counts:");
        analyzer.printHourlyCounts();
    }
}