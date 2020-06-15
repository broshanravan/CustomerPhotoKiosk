package dl;

import bl.beens.FilmProcessingOrder;
import bl.beens.SystemProperties;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class SystemAndNetworkProperties {

    final static Logger logger = Logger.getLogger(SystemAndNetworkProperties.class.getName());
    String refJsonFileName = "./resources/system_properties.json";

    private String labelPrinterAddress ="";
    private String receiptrinterAddress ="";
    private String dbFileLocation ="";

    private static SystemAndNetworkProperties systemAndNetworkProperties = null;

    private SystemAndNetworkProperties(){
        readPropertiesFile();
    }

    private void readPropertiesFile() {
        Map<String,String> propertiesMap = new HashMap<String,String>();
        File jsonFile = new File(refJsonFileName);
        try {

            Gson gson = new Gson();
            String propertiesJson;

            SystemProperties systemProperties = gson.fromJson(new FileReader("resources/system_properties.json"), SystemProperties.class);



            labelPrinterAddress = systemProperties.getLablePrinterSpec();
            receiptrinterAddress = systemProperties.getReceiptPrinterSpec();
            dbFileLocation = systemProperties.getdBLocation();



        } catch(FileNotFoundException nfe) {
            logger.error("The Inventory file is missing");

        } catch (IOException ioe){
            logger.error("The Inventory file is corrupted or not present");
            logger.error(ioe.getStackTrace());

        }
    }


    public String getLablePrinterAddress(){
        return labelPrinterAddress;
    }

    public String getReceiptPrinterAddress(){
        return receiptrinterAddress;
    }

    public String getDBFileLocation(){
        return dbFileLocation;
    }

    public static SystemAndNetworkProperties getInstance(){
        if (systemAndNetworkProperties == null){
            systemAndNetworkProperties = new SystemAndNetworkProperties();
        }

        return systemAndNetworkProperties;
    }


    public static void main(String[] rgs) {

        SystemAndNetworkProperties systemAndNetworkProperties = SystemAndNetworkProperties.getInstance();

        String dbFileAddress = systemAndNetworkProperties.getDBFileLocation();
        System.out.println(" DB file location is: " + dbFileAddress);

        String labelPrinterAddress = systemAndNetworkProperties.labelPrinterAddress;
        System.out.println(" Label printer location is: " + labelPrinterAddress);

        String receiptPrinterAddress = systemAndNetworkProperties.receiptrinterAddress;
        System.out.println(" Receipt Printer location is: " + receiptPrinterAddress);

    }
}
