package dl;

import bl.beens.Customer;
import com.google.gson.*;
import com.google.gson.stream.JsonWriter;

import java.io.FileWriter;
import java.io.IOException;

public class CustomerInventoryImpl implements CustomerInventory {

    public Customer findCustomer(String email){
        Customer customer = new Customer();


        return customer;
    }

    public boolean customerExists(String email){

        boolean exists = false;

        return exists;

    }

    public void saveCustomer(Customer customer){

        try {

            Gson customerGson =  new GsonBuilder()
                .setPrettyPrinting()
                .create();
            CustomerJSON customerJSONObj = new CustomerJSON(customer.getEmail(),customer);
            System.out.println(customerJSONObj);

            FileWriter fileWriter = new FileWriter("C:\\Users\\Behrooz\\Mainworkspace\\CustomerPhotoKiosk\\src\\main\\resources\\CustomerRecords.json", true);

            JsonWriter jw = new JsonWriter(fileWriter);

            customerGson.toJson(customer,customer.getClass(), jw);


            fileWriter.flush();
            fileWriter.close();



            //System.out.println("JSON string write to a file successfully");

            /*
            String customerJSONString = customerGson.toJson(customerJSONObj);
            //Map jsonMap = new HashMap();
            //jsonMap.put(customer.getEmail(),customer);
            //fileWriter.write(jsonMap);

            JsonWriter writer = new JsonWriter(fileWriter);
            writer.append(customerJSONString);


            */

            //System.out.println(customerJSONString);



        }catch(IOException ioe){

        }
    }


    public static void main(String [] args) {
        CustomerInventory customerInventoryImpl = new CustomerInventoryImpl();
        Customer Bruce = new Customer("Bruce","07861671879", "broshanravan@hotmail.com");
        Customer Laleh = new Customer("Laleh","078422223", "lahehn@hotmail.com");
        Customer Areya = new Customer("Areya","0786467865", "aroshan@gmail.com");
        Customer Atoosa = new Customer("Atoosa","44445555", "atoohanravan@outlook.com");
        customerInventoryImpl.saveCustomer(Bruce);
        customerInventoryImpl.saveCustomer(Laleh);
        customerInventoryImpl.saveCustomer(Areya);
        customerInventoryImpl.saveCustomer(Atoosa);



    }
}


