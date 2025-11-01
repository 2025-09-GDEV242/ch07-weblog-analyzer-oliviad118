/**
 * Test class to demonstrate the LogAnalyzer functionality.
 * 
 * @author Student
 * @version 2025.10.29
 */
public class LogAnalyzerTest
{
    public static void main(String[] args)
    {
        // Create a multi-year log file from 2018 to 2025
        LogfileCreator creator = new LogfileCreator();
        String multiYearFilename = "multiyear.log";
        
        System.out.println("Creating multi-year log file...");
        boolean success = creator.createMultiYearFile(multiYearFilename, 1000, 2018, 2025);
        
        if(success) {
            System.out.println("Multi-year log file created successfully.");
            
            // Test LogAnalyzer with the new constructor
            LogAnalyzer analyzer = new LogAnalyzer(multiYearFilename);
            
            // Analyze the data
            analyzer.analyzeHourlyData();
            
            // Test all the required methods
            System.out.println("\n=== Log Analysis Results ===");
            
            // 7.14 - Number of accesses
            System.out.println("Total number of accesses: " + analyzer.numberOfAccesses());
            
            // 7.15 - Busiest hour
            int busiest = analyzer.busiestHour();
            System.out.println("Busiest hour: " + busiest + ":00");
            
            // 7.16 - Quietest hour
            int quietest = analyzer.quietestHour();
            System.out.println("Quietest hour: " + quietest + ":00");
            
            // 7.18 - Busiest two-hour period
            int busiestTwo = analyzer.busiestTwoHour();
            int endHour = (busiestTwo + 1) % 24;
            System.out.println("Busiest two-hour period: " + busiestTwo + ":00 - " + endHour + ":59");
            
            // Extra credit methods (7.19)
            System.out.println("\n=== Extra Credit Analysis ===");
            
            // Quietest and busiest day
            System.out.println("Quietest day of month: " + analyzer.quietestDay());
            System.out.println("Busiest day of month: " + analyzer.busiestDay());
            
            // Monthly analysis
            int[] monthlyTotals = analyzer.totalAccessesPerMonth();
            System.out.println("Total accesses per month:");
            String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                                   "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
            for(int i = 0; i < 12; i++) {
                System.out.println("  " + monthNames[i] + ": " + monthlyTotals[i]);
            }
            
            System.out.println("Quietest month: " + monthNames[analyzer.quietestMonth() - 1]);
            System.out.println("Busiest month: " + monthNames[analyzer.busiestMonth() - 1]);
            System.out.println("Average accesses per month: " + 
                              String.format("%.2f", analyzer.averageAccessesPerMonth()));
            
            // Print hourly distribution
            System.out.println("\n=== Hourly Distribution ===");
            analyzer.printHourlyCounts();
            
        } else {
            System.out.println("Failed to create multi-year log file.");
            
            // Test with default constructor and existing demo.log
            LogAnalyzer analyzer = new LogAnalyzer();
            analyzer.analyzeHourlyData();
            
            System.out.println("Testing with default file:");
            System.out.println("Total accesses: " + analyzer.numberOfAccesses());
            System.out.println("Busiest hour: " + analyzer.busiestHour());
            System.out.println("Quietest hour: " + analyzer.quietestHour());
            analyzer.printHourlyCounts();
        }
    }
}