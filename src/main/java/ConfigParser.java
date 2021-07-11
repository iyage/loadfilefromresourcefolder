import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConfigParser {
static

    private Map<String,String> stringMap = new HashMap<>();
  public String get(String configKey) {
        String value = stringMap.get(configKey);
      System.out.println(stringMap);
        return value;
    }
  private File loadFile(String fileName){
       ClassLoader classLoader = getClass().getClassLoader();
       File file  = new File(classLoader.getResource(fileName).getFile());

  return  file;
   }
  private String readFileContesnt(File file) throws IOException {
       String str  = "";
      FileReader fileReader =  new FileReader(file);
       BufferedReader br = new BufferedReader(fileReader);
       try {
           String line;
           while ((line = br.readLine()) != null) {
               if(line.length() ==0 ) str += " ";
               else  str += line+" ";
           }
       } finally {
           br.close();
       }

   return  str.trim();
   }
 private ArrayList keepFileContentInArray(String fileContent){
       ArrayList<String> stringArrayList = new ArrayList<>();
       String[]  contentInArry = fileContent.split("  ");
       for (String e:contentInArry) {
           String str = "";
           String[] str1 = e.split(" ");
           for(int i = 0; i< str1.length;i++){
               if(str1[i].contains("[")){
                   str =  str1[i].substring(1,str1[i].length()-1);
                   str +=".";
               }
               else {
                   String str2 = str+str1[i];
                   stringArrayList.add(str2);
               }
           }
       }
   return  stringArrayList;
   }
   public void storeValueInMap(ArrayList<String> arrayList){
       for (String content:arrayList) {
           String key = content.substring(0,content.indexOf("="));
           String value = content.substring(content.indexOf("=")+1);
           if(!stringMap.containsKey(key)){
               stringMap.put(key,value);
           }
       }
   }
    public void parseFile(String string) throws IOException {
       String file = "";
        String fileContent ="";
        ArrayList<String> arrayListOfContent = new ArrayList<>();
        switch(string.toLowerCase()){
            case "development":
             file = "config.txt.dev";
            File fileLoaded  =    loadFile(file);
            fileContent = readFileContesnt(fileLoaded);
             arrayListOfContent = keepFileContentInArray(fileContent);
              storeValueInMap(arrayListOfContent);
                break;
            case "production":
             file = "config.txt";
             File fileLoaded1 = loadFile(file);
             fileContent = readFileContesnt(fileLoaded1);
                arrayListOfContent = keepFileContentInArray(fileContent);
                //arrayListOfContent.remove(0);
                storeValueInMap(arrayListOfContent);
                break;
            case "staging":
                file = "config.txt.staging";
                File fileLoaded2 = loadFile(file);
                fileContent = readFileContesnt(fileLoaded2);
                arrayListOfContent = keepFileContentInArray(fileContent);
                //arrayListOfContent.remove(0);
                  storeValueInMap(arrayListOfContent);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + string.toLowerCase());
        }
   }



}
