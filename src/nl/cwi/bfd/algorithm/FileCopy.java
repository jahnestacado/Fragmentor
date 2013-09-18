package nl.cwi.bfd.algorithm;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


public class FileCopy {
    public static void copyTo(String fromPath) {
        String source = fromPath;
        String [] temp = source.split("/");
        String fileName = temp[temp.length-1];
        String fileType = temp[temp.length-3];
        String target = "/home/jahn/Desktop/corpus/onlytext"+"/"+fileName;
        FileCopy.copyFile(source, target);
    }

    public static void copyFile(String source, String target) {
        try {
            Files.copy(new File(source).toPath(), new File(target).toPath(),
                StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
