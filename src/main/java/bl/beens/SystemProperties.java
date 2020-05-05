package bl.beens;

public class SystemProperties {

   private String LablePrinterSpec;
   private String ReceiptPrinterSpec;
   private String DBLocation;

    public String getLablePrinterSpec() {
        return LablePrinterSpec;
    }

    public void setLablePrinterSpec(String LablePrinterSpec) {
        this.LablePrinterSpec = LablePrinterSpec;
    }

    public String getReceiptPrinterSpec() {
        return ReceiptPrinterSpec;
    }

    public void setReceiptPrinterSpec(String receiptPrinterSpec) {
        ReceiptPrinterSpec = receiptPrinterSpec;
    }

    public String getDBLocation() {
        return DBLocation;
    }

    public void setDBLocation(String DBLocation) {
        this.DBLocation = DBLocation;
    }
}
