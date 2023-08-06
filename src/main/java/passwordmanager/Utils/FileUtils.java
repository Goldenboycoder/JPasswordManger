package src.main.java.passwordmanager.Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
}
