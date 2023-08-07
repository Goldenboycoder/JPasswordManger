package src.main.java.passwordmanager.Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
   
    public static String readFileContent(String path) throws IOException{
        String result ="";
        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder contentString = new StringBuilder();
        String line="";
        while((line = reader.readLine()) != null){
            contentString.append(line);
        }
        reader.close();
        return contentString.toString();
    }

    public static void writeToFile(String path,String content) throws IOException{
        FileWriter writer = new FileWriter(path);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(content);
        writer.close();
    }
}
