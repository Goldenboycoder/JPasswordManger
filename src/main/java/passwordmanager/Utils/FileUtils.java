package src.main.java.passwordmanager.Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    public static byte[] readFileBytes(String path) throws IOException{
        Path file = Paths.get(path);
        return Files.readAllBytes(file);
    }

    public static void writeToFile(String path,String content) throws IOException{
        FileWriter writer = new FileWriter(path,Charset.forName("utf-8"));
        writer.write(content);
        //BufferedWriter bufferedWriter = new BufferedWriter(writer);
        //bufferedWriter.write(content);
        writer.close();
    }

    public static void writeToFile(String path,byte[] content) throws IOException{
        Path file = Paths.get(path); 
        Files.write(file, content);
        //BufferedWriter bufferedWriter = new BufferedWriter(writer);
        //bufferedWriter.write(content);
    }
}
