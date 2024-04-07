import java.util.ArrayList;

public class IntakeLog {
    private ArrayList<IntakeEntry> entries;

    public IntakeLog() {
        entries = new ArrayList<>();
    }
    
    public void addEntry(String foodItem, int vitaminAmount) {
        IntakeEntry entry = new IntakeEntry(foodItem, vitaminAmount);
        entries.add(entry);
    }

    public String getEntries() {
        StringBuilder sb = new StringBuilder();
        for (IntakeEntry entry : entries) {
            sb.append(entry.getTimestamp())
              .append(" : ")
              .append(entry.getFoodItem())
              .append(" : ")
              .append(entry.getVitaminAmount())
              .append("\n");
        }
        return sb.toString();
    }
}