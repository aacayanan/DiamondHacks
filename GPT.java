import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GPT {
    public static void main(String[] args) {
        System.out.println(integerVitaminC("apple"));
    }
    public static String chatGPT(String message) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "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";
        String model = "gpt-3.5-turbo";
        String primer = ": I gave you the name of a food item or meal and it is your job to return back to me how much Vitamin C is in that meal in mg.  If you do not know the number, output the estimate. Do not give a range. Your output should be: [vitamin C content]";
        try {
            // create the HTTP POST request
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setRequestProperty("Content-Type", "application/json");

            // build the request body
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + message + primer + "\"}]}";
            con.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // get the response
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            
            return extractContentFromResponse(response.toString()); // Returns the substring containing only the response.
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String extractContentFromResponse(String response) {
        int start = response.indexOf("content")+11;     // marker where content starts
        int end = response.indexOf("\"", start);        // marker where content ends
        return response.substring(start, end);              // returns the substring containing only the response.
    }

    public static int integerVitaminC(String foodItem) {
        String response = chatGPT(foodItem);
        System.out.println(response);
        // remove anything that is not a digit and make it an integer
        // String output = response.replaceAll("[^\\d]", "").replaceAll("(\\d+).*", "$1");
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(response);

        if (matcher.find()) {
            String output = matcher.group();
            System.out.println(output);
            return Integer.parseInt(output);
        }
        System.out.println(response);
        return 0;
    }
}
