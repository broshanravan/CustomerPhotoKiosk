package dl;

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
            BufferedReader bufferReader = new BufferedReader(new FileReader(jsonFile));
            Gson gson = new Gson();

            List<SystemProperties> propertiesItemList = gson.fromJson(bufferReader,new TypeToken<List<SystemProperties>>(){}.getType());
            System.out.println("The length of Items list Is: " + propertiesItemList.size());
            //barcodeList = new ArrayList<String>();

            SystemProperties property =propertiesItemList.get(0);
            labelPrinterAddress = property.getLablePrinterSpec();
            receiptrinterAddress = property.getReceiptPrinterSpec();
            dbFileLocation = property.getDBLocation();


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
