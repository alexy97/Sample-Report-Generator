package Model;


public class ReportConfiguration {
    private long topPerformersThreshold;
    private boolean useExprienceMultiplier;
    private long periodLimit;

    public ReportConfiguration(long topPerformersThreshold, boolean useExprienceMultiplier, long periodLimit){
        this.topPerformersThreshold = topPerformersThreshold;
        this.useExprienceMultiplier = useExprienceMultiplier;
        this.periodLimit = periodLimit;
    }

    public long getTopPerformersThreshold() {
        return topPerformersThreshold;
    }

    public long getPeriodLimit() {
        return periodLimit;
    }
    public boolean getUseExprienceMultiplier(){
        return useExprienceMultiplier;
    }

    public void setUseExprienceMultiplier(boolean useExprienceMultiplier) {
        this.useExprienceMultiplier = useExprienceMultiplier;
    }

    public void setTopPerformersThreshold(long topPerformersThreshold) {
        this.topPerformersThreshold = topPerformersThreshold;
    }

    public void setPeriodLimit(long periodLimit) {
        this.periodLimit = periodLimit;
    }
}

