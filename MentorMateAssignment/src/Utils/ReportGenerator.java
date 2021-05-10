package Utils;

import Model.EmployeePerformance;
import Model.ReportConfiguration;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ReportGenerator {

    private static ReportGenerator instance = null;

    private ReportGenerator(){

    }

    public static ReportGenerator getInstance()
    {
        if (instance == null)
            instance = new ReportGenerator();

        return instance;
    }

    public void generateCSVReport(List<EmployeePerformance> performanceList, ReportConfiguration configuration){

        List<EmployeePerformance> reportPerformances = new ArrayList<>();
        boolean useMultiplier = configuration.getUseExprienceMultiplier();
        //Sort list based on scores
        performanceList.sort((performance, t1) -> {
            Double score1 = performance.getScore(useMultiplier);
            Double score2 = t1.getScore(useMultiplier);
            return score1.compareTo(score2);
        });


        double threshold = 1 - (double) configuration.getTopPerformersThreshold()/100;
        //Get the starting index of the top X percent of the sorted list
        int startIndex = (int)(performanceList.size()*threshold);
        for(EmployeePerformance performance: performanceList){
            //Check if the salesPeriod<= limit and that the employee is in the top X percent of the list
            if(performance.getSalesPeriod() <= configuration.getPeriodLimit() && performanceList.indexOf(performance)>=startIndex){
                reportPerformances.add(performance);
            }
        }
        generateCSVFile(reportPerformances,useMultiplier);
    }

    private void generateCSVFile(List<EmployeePerformance> performances, boolean useMultiplier){

        try {
            FileWriter csvWriter = new FileWriter("report.csv");
            csvWriter.append("Name");
            csvWriter.append(",");
            csvWriter.append("Score");
            csvWriter.append("\n");

            for (EmployeePerformance performance : performances) {
                csvWriter.append(performance.getName() );
                csvWriter.append(", " + performance.getScore(useMultiplier));
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
