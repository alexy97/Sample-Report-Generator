import Exceptions.ConfigurationFileException;
import Model.EmployeePerformance;
import Model.ReportConfiguration;
import Utils.FileParser;
import Utils.ReportGenerator;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        if(args.length != 2)
        {
            System.out.println("Two command line arguments expected. Use 'java dataFilePath configurationFilePath' to run the program.");
            System.exit(0);
        }

        String dataFilePath = args[0];
        String configurationFilePath = args[1];

        FileParser fileParser = FileParser.getInstance();
        ReportGenerator generator = ReportGenerator.getInstance();
        try {
            ReportConfiguration configuration = fileParser.parseConfigurationFile(configurationFilePath);
            List<EmployeePerformance> performances = fileParser.parseDataFile(dataFilePath);
            generator.generateCSVReport(performances,configuration);
        }
        catch (ConfigurationFileException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }


    }
}
