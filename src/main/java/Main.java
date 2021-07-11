import java.io.IOException;


public class Main {
    public ClassLoader classLoader = getClass().getClassLoader();
   public  static void main(String[] args) throws IOException {
       if (args.length > 0) {
           String str = args[0];
           ConfigParser config = new ConfigParser();
           config.parseFile(str);
           System.out.println(config.get("application.name"));
       }
       else{
           System.out.println("No parameter provided");

       }
   }
           }


