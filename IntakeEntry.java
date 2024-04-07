import java.util.Date;
import java.text.SimpleDateFormat;

public class IntakeEntry {
    private static int lastEntryId = 0;
    private int entryId;
    private String foodItem;
    private int vitaminAmount;
    private Date timestamp;

    public IntakeEntry(String foodItem, int vitaminAmount) {
        this.entryId = ++lastEntryId;
        this.foodItem = foodItem;
        this.vitaminAmount = vitaminAmount;
        this.timestamp = new Date();
    }

    public int getEntryId() {
        return entryId;
    }
    
    public String getFoodItem() {
        return foodItem;
    }

    public int getVitaminAmount() {
        return vitaminAmount;
    }

    public String getTimestamp() {
        SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return date.format(this.timestamp);
    }
}
