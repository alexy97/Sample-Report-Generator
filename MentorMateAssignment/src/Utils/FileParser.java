package Utils;

import Exceptions.ConfigurationFileException;
import Exceptions.DataFileFormatException;
import Model.EmployeePerformance;
import Model.ReportConfiguration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileParser {
    private static FileParser instance = null;

   private FileParser(){

   }


    public static FileParser getInstance()
    {
        if (instance == null)
            instance = new FileParser();

        return instance;
    }

    public ReportConfiguration parseConfigurationFile(String filePath) throws ConfigurationFileException {
        JSONParser jsonParser = new JSONParser();
        ReportConfiguration configuration = new ReportConfiguration(0,false, 0);
        try (FileReader reader = new FileReader(filePath))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONObject config = (JSONObject) obj;
            if(!config.containsKey("useExprienceMultiplier") || !config.containsKey("topPerformersThreshold") || !config.containsKey("periodLimit") ){
                throw new ConfigurationFileException("Invalid configuration file format.");
            }
            //Get employee first name
            configuration.setUseExprienceMultiplier ((boolean) config.get("useExprienceMultiplier"));
            configuration.setTopPerformersThreshold ((long) config.get("topPerformersThreshold"));
            configuration.setPeriodLimit ((long) config.get("periodLimit"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(0);

        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return configuration;
    }

    public List<EmployeePerformance> parseDataFile(String filePath){
        JSONParser jsonParser = new JSONParser();
        EmployeePerformance performance = new EmployeePerformance("",0,0,0);
        List<EmployeePerformance> performancesList =  new ArrayList<>();
        try (FileReader reader = new FileReader(filePath))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray performances = (JSONArray) obj;

            performances.forEach( emp -> {
                EmployeePerformance current = null;
                try {
                    current = parseEmployeePerformance((JSONObject) emp);
                } catch (DataFileFormatException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                    System.exit(0);
                }
                performancesList.add(current);
            });


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(0);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(0);

        }
        return performancesList;
    }

    private EmployeePerformance parseEmployeePerformance(JSONObject performance) throws DataFileFormatException {
       //Check file format
       if(!performance.containsKey("name") || !performance.containsKey("totalSales") || !performance.containsKey("salesPeriod") || !performance.containsKey("experienceMultiplier")){
            throw new DataFileFormatException("Invalid data file format");
       }
        return new EmployeePerformance((String) performance.get("name"),(long)performance.get("totalSales"),
                (long) performance.get("salesPeriod"),(double) performance.get("experienceMultiplier"));
    }
}
