package Model;

public class EmployeePerformance {
    private String name;
    private long totalSales;
    private long salesPeriod;
    private double expereinceMultiplier;

    public EmployeePerformance(String name, long totalSales, long salesPeriod, double expereinceMultiplier){
        this.name = name;
        this.totalSales = totalSales;
        this.salesPeriod = salesPeriod;
        this.expereinceMultiplier = expereinceMultiplier;
    }

    public String getName() {
        return name;
    }

    public long getTotalSales() {
        return totalSales;
    }

    public long getSalesPeriod() {
        return salesPeriod;
    }

    public double getExpereinceMultiplier() {
        return expereinceMultiplier;
    }

    public void setTotalSales(long totalSales) {
        this.totalSales = totalSales;
    }

    public void setSalesPeriod(long salesPeriod) {
        this.salesPeriod = salesPeriod;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExpereinceMultiplier(double expereinceMultiplier) {
        this.expereinceMultiplier = expereinceMultiplier;
    }

    public double getScore(boolean useMultiplier){
        if(useMultiplier){
            return Math.round(((double)totalSales/salesPeriod)*expereinceMultiplier*100.0)/100.0;
        }
        else{
            return  Math.round(((double) totalSales/salesPeriod)*100.0)/100.0;
        }
    }
}
