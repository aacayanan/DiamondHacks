import java.util.ArrayList;

public class IntakeLog {
    private ArrayList<IntakeEntry> entries;
    
    public void addEntry(String foodItem, int vitaminAmount) {
        IntakeEntry entry = new IntakeEntry(foodItem, vitaminAmount);

        // check if the entries is empty, if so create a new arraylist
        if (entries == null) {
            entries = new ArrayList<>();
        }
        entries.add(entry);
    }

    public String getEntries() {
        StringBuilder sb = new StringBuilder();
        for (IntakeEntry entry : entries) {
            sb.append(entry.getEntryId())
              .append(" ")
              .append(entry.getFoodItem())
              .append(" ")
              .append(entry.getVitaminAmount())
              .append(" ")
              .append(entry.getTimestamp())
              .append("\n");
        }
        return sb.toString();
    }
}
