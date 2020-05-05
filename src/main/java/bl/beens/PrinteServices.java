package bl.beens;

import bl.enums.OrderType;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.print.*;

public class PrinteServices {

    private OrderType orderType;
    private StringBuilder pintableText;
    private StringBuilder pintReceiptText;
    private Customer customer = null;
    private OtherOrderTypes order = null;
    private FilmProcessingOrder filmProcessingOrder = null;

    public PrinteServices(){

    }

    public PrinteServices(OrderType orderType, Customer customer, FilmProcessingOrder filmProcessingOrder) {
        this.orderType = orderType;
        this.customer = customer;
        this.filmProcessingOrder = filmProcessingOrder;
    }

    public PrinteServices(OrderType orderType, Customer customer, OtherOrderTypes order) {
        this.orderType = orderType;
        this.customer = customer;
        this.order = order;
    }


    public  String getLableContentsText(){

        if(orderType.equals(OrderType.FilmProcessing)){

        } else{

        }

        String newline = System.getProperty("line.separator");
        pintableText = new StringBuilder();
       // pintableText.append(newline);
        pintableText.append(String.format("Order Number: 234 ",newline));
        //pintableText.append(newline);
        pintableText.append(String.format("Date ",newline));
        //pintableText.append(newline);
        pintableText.append(String.format("Bruce Roshanravan " ,newline));
       // pintableText.append(newline);
        pintableText.append(String.format("broshanravan@hotmail.com ",newline));

        String text =  pintableText.toString();
        System.out.println(text);
        return text;
    }

    public String getReceiptContentsText(){

        if(orderType.equals(OrderType.FilmProcessing)){

        } else{

        }

        String newline = System.getProperty("line.separator");
        pintReceiptText = new StringBuilder();
        // pintableText.append(newline);
        pintReceiptText.append(String.format("Order Number:234 ",newline));
        //pintableText.append(newline);
        pintReceiptText.append(String.format("Date ",newline));
        //pintableText.append(newline);
        pintReceiptText.append(String.format("Bruce Roshanravan ",newline));
        // pintableText.append(newline);
        pintReceiptText.append(String.format("broshanravan@hotmail.com ",newline));
        // pintableText.append(newline);
        pintReceiptText.append(String.format("07861671869 ",newline));
        // pintableText.append(newline);
        pintReceiptText.append(String.format("total ",newline));
        // pintableText.append(newline);
        pintReceiptText.append(String.format("VAT ",newline));
        // pintableText.append(newline);
        pintReceiptText.append(String.format("Deposit ",newline));
        // pintableText.append(newline);
        pintReceiptText.append(String.format("Balance",newline));



        String text =  pintReceiptText.toString();
        System.out.println(text);
        return text;
    }

    public void printLable() {

        PrinterJob pj = PrinterJob.getPrinterJob();
        if (pj.printDialog()) {
            PageFormat pf = pj.defaultPage();
            Paper paper = pf.getPaper();
            double width = fromCMToPPI(10);
            double height = fromCMToPPI(5);
            paper.setSize(width, height);
            paper.setImageableArea(
                    fromCMToPPI(0.25),
                    fromCMToPPI(0.5),
                    width - fromCMToPPI(0.35),
                    height - fromCMToPPI(1));

            System.out.println("Before- " + dump(paper));
            pf.setOrientation(PageFormat.PORTRAIT);
            pf.setPaper(paper);
            //System.out.println("After- " + dump(paper));
            //System.out.println("After- " + dump(pf));
            dump(pf);
            PageFormat validatePage = pj.validatePage(pf);
            System.out.println("Valid- " + dump(validatePage));
            Book book = new Book();
            book.append(new MyPrintable(), pf);
            pj.setPageable(book);
            pj.setPrintable(new MyPrintable(), pf);
            try {
                pj.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void printReceipt() {

        PrinterJob pj = PrinterJob.getPrinterJob();
        if (pj.printDialog()) {
            PageFormat pf = pj.defaultPage();
            Paper paper = pf.getPaper();
            double width = fromCMToPPI(40);
            
            double height = fromCMToPPI(30);
            paper.setSize(width, height);
            paper.setImageableArea(
                    fromCMToPPI(0.25),
                    fromCMToPPI(0.5),
                    width - fromCMToPPI(0.35),
                    height - fromCMToPPI(1));
            System.out.println("Before- " + dump(paper));
            pf.setOrientation(PageFormat.PORTRAIT);
            pf.setPaper(paper);
            //System.out.println("After- " + dump(paper));
            //System.out.println("After- " + dump(pf));
            dump(pf);
            PageFormat validatePage = pj.validatePage(pf);
            System.out.println("Valid- " + dump(validatePage));
            Book book = new Book();
            book.append(new MyPrintable(), pf);
            pj.setPageable(book);
            pj.setPrintable(new MyPrintable(), pf);
            try {
                pj.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
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


    public StringBuilder getPintableText() {
        return pintableText;
    }

    public void setPintableText(StringBuilder pintableText) {
        this.pintableText = pintableText;
    }

    public StringBuilder getPintReceiptText() {
        return pintReceiptText;
    }

    public void setPintReceiptText(StringBuilder pintReceiptText) {
        this.pintReceiptText = pintReceiptText;
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

    public void setFilmProcessingOrder(FilmProcessingOrder filmProcessingOrder) {
        this.filmProcessingOrder = filmProcessingOrder;
    }

    /**************************************************************************************Printable Class ******************************************************************************************/

    public  class MyPrintable implements Printable {

        @Override
        public int print(Graphics graphics, PageFormat pageFormat,
                         int pageIndex) throws PrinterException {
            System.out.println(pageIndex);
            int result = NO_SUCH_PAGE;
            if (pageIndex < 1 ) {
                Graphics2D g2d = (Graphics2D) graphics;
                System.out.println("[Print Page Format:  ] " + dump(pageFormat));
                double width = pageFormat.getImageableWidth();
                double height = pageFormat.getImageableHeight();
                g2d.translate((int) pageFormat.getImageableX(),
                        (int) pageFormat.getImageableY());
                g2d.draw(new Rectangle2D.Double(1, 1, width - 1, height - 1));
                FontMetrics fm = g2d.getFontMetrics();



                g2d.drawString(getLableContentsText(), 0, fm.getAscent());
                result = PAGE_EXISTS;
            }
            return result;
        }
    }


    public static void main (String[] args){

        PrinteServices printeServices = new PrinteServices();

        printeServices.printLable();
    }

}
