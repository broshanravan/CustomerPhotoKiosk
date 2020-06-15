package bl.beens;

import bl.enums.OrderType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dl.CustomerInventory;
import dl.CustomerInventoryImpl;
import dl.FilmProceccingOrderInventory;
import dl.FilmProceccingOrderInventoryImpl;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.print.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import java.util.Date;
import java.util.List;



public class PrinterServices {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
    private OrderType orderType = OrderType.FilmProcessing;
    private StringBuilder printableText;
    private StringBuilder receiptText;
    private Customer customer = null;
    private OtherOrderTypes order = null;
    private FilmProcessingOrder filmProcessingOrder = null;
    static GsonBuilder builder = new GsonBuilder();
    static Gson gson = null;
    static SystemProperties systemProperties;

    static{
        builder.setDateFormat("dd/mm/yyyy");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("resources/system_properties.json"));
            Gson gson = builder.create();
            systemProperties = gson. fromJson(bufferedReader, SystemProperties. class);

        }catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }

    }


    public PrinterServices(){
        builder.setDateFormat("mmm/dd/yyyy");

    }

    public PrinterServices(OrderType orderType, Customer customer, FilmProcessingOrder filmProcessingOrder) {
        this.orderType = orderType;
        this.customer = customer;
        this.filmProcessingOrder = filmProcessingOrder;

    }

    public PrinterServices(OrderType orderType, Customer customer, OtherOrderTypes order) {
        this.orderType = orderType;
        this.customer = customer;
        this.order = order;

    }



    public  List<String> getLabelContentsText(){


        String strDate= formatter.format(new Date());
        long orderNumber =order.getJobNumber();

        String name =customer.getName();
        String email = customer.getEmail();

        List<String> labelList = Arrays.asList("Order No: " + orderNumber, String.format(strDate),name,email);

        String text =  printableText.toString();
        System.out.println(text);
        return labelList;
    }


    public String getReceiptContentsJson(){
        String orderJSON = "";

        builder.setPrettyPrinting();

        gson = builder.create();
        if(OrderType.FilmProcessing.equals(orderType)){
            orderJSON = gson.toJson(filmProcessingOrder);
        } else{
            orderJSON = gson.toJson(order);
        }

        System.out.println(orderJSON);
        return orderJSON;
    }


    public void printLabel() {

        PrinterJob printerJob = PrinterJob.getPrinterJob();

                PageFormat pageFormat = printerJob.defaultPage();
                Paper paper = pageFormat.getPaper();
                double width = fromCMToPPI(10);
                double height = fromCMToPPI(5);
                paper.setSize(width, height);
                paper.setImageableArea(
                        fromCMToPPI(0.25),
                        fromCMToPPI(0.5),
                        width - fromCMToPPI(0.35),
                        height - fromCMToPPI(1));

                System.out.println("Before- " + dump(paper));
                pageFormat.setOrientation(PageFormat.PORTRAIT);
                pageFormat.setPaper(paper);;
                dump(pageFormat);
                PageFormat validatePage = printerJob.validatePage(pageFormat);
                System.out.println("Valid- " + dump(validatePage));
                Book book = new Book();
                book.append(new LabelPrintable(), pageFormat);
                printerJob.setPageable(book);
                printerJob.setPrintable(new LabelPrintable(), pageFormat);
                try {
                    printerJob.print();
                } catch (PrinterException ex) {
                    ex.printStackTrace();
                }

    }


    public void printReceipt() {

        System.out.println("label Printer: " + systemProperties.getLablePrinterSpec());
        System.out.println("Receipt Printer: " + systemProperties.getReceiptPrinterSpec());
        System.out.println("Database location: " + systemProperties.getdBLocation());

        PrinterJob printerJob = PrinterJob.getPrinterJob();

        //printerJob.set

        PageFormat pageFormat = printerJob.defaultPage();
        Paper paper = pageFormat.getPaper();
        double width = fromCMToPPI(40);

        double height = fromCMToPPI(30);
        paper.setSize(width, height);
        paper.setImageableArea(
                fromCMToPPI(0.25),
                fromCMToPPI(0.5),
                width - fromCMToPPI(0.35),
                height - fromCMToPPI(1));
        System.out.println("Before- " + dump(paper));
        pageFormat.setOrientation(PageFormat.PORTRAIT);
        pageFormat.setPaper(paper);
        dump(pageFormat);
        PageFormat validatePage = printerJob.validatePage(pageFormat);
        System.out.println("Valid- " + dump(validatePage));
        Book book = new Book();
        ReceiptPrintable receiptPrintable = new ReceiptPrintable();
        book.append(receiptPrintable, pageFormat);
        printerJob.setPageable(book);
        printerJob.setPrintable(receiptPrintable, pageFormat);
        try {
            printerJob.print();
        } catch (PrinterException ex) {
                ex.printStackTrace();
        }

    }


    protected static double fromCMToPPI(double cm) {
        return toPPI(cm * 0.393700787);
    }

    protected static double toPPI(double inch) {
        return inch * 72d;
    }

    protected static String dump(Paper paper) {
        StringBuilder sb = new StringBuilder(64);
        sb.append(paper.getWidth()).append("x").append(paper.getHeight())
                .append("/").append(paper.getImageableX()).append("x").
                append(paper.getImageableY()).append(" - ").append(paper
                .getImageableWidth()).append("x").append(paper.getImageableHeight());
        return sb.toString();
    }

    protected static String dump(PageFormat pf) {
        Paper paper = pf.getPaper();
        return dump(paper);
    }


    public StringBuilder getPrintableText() {
        return printableText;
    }

    public void setPrintableText(StringBuilder pintableText) {
        this.printableText = printableText;
    }

    public StringBuilder getReceiptText() {
        return receiptText;
    }

    public void setReceiptText(StringBuilder receiptText) {
        this.receiptText = receiptText;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public OtherOrderTypes getOrder() {
        return order;
    }

    public void setOrder(OtherOrderTypes order) {
        this.order = order;
    }

    public FilmProcessingOrder getFilmProcessingOrder() {
        return filmProcessingOrder;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public void setFilmProcessingOrder(FilmProcessingOrder filmProcessingOrder) {
        this.filmProcessingOrder = filmProcessingOrder;
    }

 /************************************************************************* Printable Class ********************************************************************************/

    public  class LabelPrintable implements Printable {

        @Override
        public int print(Graphics graphics, PageFormat pageFormat,
                         int pageIndex) throws PrinterException {
            System.out.println(pageIndex);
            int result = NO_SUCH_PAGE;
            if (pageIndex < 1 ) {
                Graphics2D g2d = (Graphics2D) graphics;
                g2d.setColor( new Color(2, 100, 10) );
                System.out.println("[Print Page Format:] " + dump(pageFormat));
                double width = pageFormat.getImageableWidth();
                double height = pageFormat.getImageableHeight();
                g2d.translate((int) pageFormat.getImageableX(),
                        (int) pageFormat.getImageableY());
                g2d.draw(new Rectangle2D.Double(1, 1, width + 1, height));
                FontMetrics fm = g2d.getFontMetrics();

                List<String> labelList= getLabelContentsText();

                int y=20;
                for(String value :labelList){
                    g2d.drawString(value, 15, y);
                    y= y + 20;
                }

                result = PAGE_EXISTS;
            }
            return result;
        }
    }

    public  class ReceiptPrintable implements Printable {

        @Override
        public int print(Graphics graphics, PageFormat pageFormat,
                         int pageIndex) throws PrinterException {

            int result = NO_SUCH_PAGE;
            if (pageIndex < 1 ) {
                Graphics2D g2d = (Graphics2D) graphics;
                g2d.setColor( new Color(2, 100, 10) );
                System.out.println("[Print Page Format:] " + dump(pageFormat));
                double width = pageFormat.getImageableWidth();
                double height = pageFormat.getImageableHeight();
                g2d.translate((int) pageFormat.getImageableX(),
                        (int) pageFormat.getImageableY());
                g2d.draw(new Rectangle2D.Double(1, 1, width + 1, height));

                //FontMetrics fm = g2d.getFontMetrics();

                //List<String> labelList= getLabelContentsText();

                String orderJSON = getReceiptContentsJson();
                //GsonBuilder builder = new GsonBuilder();
                builder.setPrettyPrinting();

                //Gson gson = builder.create();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
                if(OrderType.FilmProcessing.equals(orderType)){

                    FilmProcessingOrder filmProcessingorder =
                    gson.fromJson(orderJSON, FilmProcessingOrder.class);

                    g2d.drawString(String.valueOf("Order No:" +  filmProcessingorder.getOrderNum()), 15, 20);
                    g2d.drawString(formatter.format(filmProcessingorder.getOrderDate()), 15, 40);
                    g2d.drawString(customer.getName(), 15, 60);
                    g2d.drawString(filmProcessingorder.getCustomerEmail(), 15, 80);
                    g2d.drawString(customer.getMobileNum(), 15, 100);
                    /********************Receipt Headers***********/
                    g2d.drawString("Job Type ", 50, 140);
                    g2d.drawString("Prices", 230, 140);

                    /********************Receipt Contents***********/
                    g2d.drawString(orderType.toString(), 15, 160);


                    double vat = Math.floor(filmProcessingorder.getTotalPrice() * 17.5) / 100;
                    double totalWithVAT = filmProcessingorder.getTotalPrice() + vat ;


                    g2d.drawString("sub-total:   "+ String.valueOf(filmProcessingorder.getTotalPrice()), 220, 180);
                    g2d.drawString("VAT:         " + vat, 220, 200);
                    g2d.drawString("Total:       " + String.valueOf(totalWithVAT), 220, 220);
                    g2d.drawString("Deposit:     " + String.valueOf(filmProcessingorder.getDeposit()), 220, 240);
                    g2d.drawString("Balance due: " + String.valueOf(filmProcessingorder.getBalance()), 220, 260);


                } else{
                    OtherOrderTypes otherOrderTypesOrder = gson.fromJson(orderJSON, OtherOrderTypes.class);

                    g2d.drawString(String.valueOf("Order No:" +  otherOrderTypesOrder.getJobNumber()), 15, 20);
                    g2d.drawString(formatter.format(otherOrderTypesOrder.getOrderDate()), 15, 40);
                    g2d.drawString(customer.getName(), 15, 60);
                    g2d.drawString(otherOrderTypesOrder.getEmail(), 15, 80);
                    g2d.drawString(customer.getMobileNum(), 15, 100);
                    /********************Receipt Headers***********/
                    g2d.drawString("Job Type ", 50, 140);
                    g2d.drawString("Prices", 230, 140);

                    /********************Receipt Contents***********/
                    g2d.drawString(orderType.toString(), 15, 160);


                    double vat = Math.floor(otherOrderTypesOrder.getTotalPrice() * 17.5) / 100;
                    double totalWithVAT = otherOrderTypesOrder.getTotalPrice() + vat ;


                    g2d.drawString("sub-total:   "+ String.valueOf(otherOrderTypesOrder.getTotalPrice()), 220, 180);
                    g2d.drawString("VAT:         " + vat, 220, 200);
                    g2d.drawString("Total:       " + String.valueOf(totalWithVAT), 220, 220);
                    g2d.drawString("Deposit:     " + String.valueOf(otherOrderTypesOrder.getDeposit()), 220, 240);
                    g2d.drawString("Balance due: " + String.valueOf(otherOrderTypesOrder.getBalance()), 220, 260);


                }


                result = PAGE_EXISTS;
            }
            return result;
        }
    }

    private class LongDateTypeAdapter extends TypeAdapter<Date> {
        @Override public void write(JsonWriter out, Date value) throws IOException {
            if (value == null) {
                out.nullValue();
            } else {
                out.value(String.valueOf(value.getTime()));
            }
        }
        @Override public Date read(JsonReader in) throws IOException {
            switch (in.peek()) {
                case NULL: return null;
                case STRING:
                    try {
                        return new Date(Long.parseLong(in.nextString()));
                    } catch (NumberFormatException e) {
                        throw new JsonSyntaxException(e);
                    }
                default: throw new JsonSyntaxException("invalid date" + in.getPath());
            }
        }
    }

    public static void main (String[] args){

        FilmProceccingOrderInventory filmProceccingOrderInventory = new FilmProceccingOrderInventoryImpl();
        FilmProcessingOrder filmProcessingOrder = filmProceccingOrderInventory.retrieveProcessingOrder(1);

        CustomerInventory customerInventory = new CustomerInventoryImpl();
        Customer customer =  customerInventory.findCustomer(filmProcessingOrder.getCustomerEmail());

        PrinterServices printerServices = new  PrinterServices(OrderType.FilmProcessing,  customer,  filmProcessingOrder);

        //String receiptContents = printerServices.getReceiptContentsJson();
        //System.out.println(printerServices.receiptText);
        //System.out.println(receiptContents);
        printerServices.printReceipt();
        //printerServices.printLabel();

    }

}
