import java.io.*;

public class FileHandling {
    String filename;
    
    FileHandling() {
        filename = "DB.json";
        if(!(new File(filename).exists())) {
            createJSONFile();
        }  
    }

    public static void writeStringToFile(String fileName, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        }
    }

    public static String readFileToString(String fileName) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line.trim());
            }
        }

        return stringBuilder.toString();
    }

    public static void createJSONFile() {
        String str = "{ \"students\": [] }";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DB.json"))) {
            writer.write(str);
        } catch (Exception e) {
            Log.info(e.getMessage());
        }
    }
}
