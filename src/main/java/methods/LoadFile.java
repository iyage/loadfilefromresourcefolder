package methods;

import java.io.File;

public class LoadFile {
    LoadFile loadFile = new LoadFile();
 public File loadFile(String fileName){
        ClassLoader classLoader = getClass().getClassLoader();
        File file  = new File(classLoader.getResource(fileName).getFile());

        return  file;
    }
}
