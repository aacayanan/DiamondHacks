import java.sql.Date;

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
        this.timestamp = new Date(System.currentTimeMillis());
    }

}
