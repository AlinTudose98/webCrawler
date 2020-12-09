import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Integer;


public class Config {

    public int numThreads;
    public int delay;
    public String rootDir;
    public int logLevel;
    public int ignoreRobots;
    public String logFilename;
    public int maxDepth;
    public long dSizeLimit;
    public String[] dTypes = new String[20];

    private static Config ConfigInstance;

    final int numParams = 9;
    final String delims = "[= ]+";
    String[] tokens = new String[21];

    private Config(String filename){
        try{
            File configFile = new File(filename);
            if (!configFile.exists()) {
                throw new FileException("Error with config file", ErrorCode.ERR);
            }
            Scanner reader = new Scanner(configFile);

            for (int i=0;i<numParams;i++){
                String data = reader.nextLine();
                tokens = data.split(delims);

                switch(tokens[0]){
                    case "numThreads":
                        numThreads = Integer.parseInt(tokens[1]);
                        break;
                    case "delay":
                        delay = Integer.parseInt(tokens[1]);
                        break;
                    case "rootDir":
                        rootDir = tokens[1];
                        break;
                    case "logLevel":
                        logLevel = Integer.parseInt(tokens[1]);
                        break;
                    case "ignoreRobots":
                        ignoreRobots = Integer.parseInt(tokens[1]);
                        break;
                    case "logFilename":
                        logFilename = tokens[1];
                        break;
                    case "maxDepth":
                        maxDepth = Integer.parseInt(tokens[1]);
                        break;
                    case "dSizeLimit":
                        dSizeLimit = Integer.parseInt(tokens[1]);
                        break;
                    case "dTypes":
                        for (int j=1;j< tokens.length;j++){
                            dTypes[j] = tokens[j];
                        }
                        break;
                }
            }
            reader.close();
        } catch (FileException | FileNotFoundException e){
            System.out.println(e.getMessage());
        }


    }

    public static Config getInstance(String filename)
    {
        if(ConfigInstance == null) {
            ConfigInstance = new Config(filename);
        }
        return ConfigInstance;
    }

    public void help(){
        System.out.println("Fisierul de configurare are urmatoarea structura:\n");
        System.out.println(("numThreads = <nr>\n" +
                            "delay = <nr>\n" +
                            "rootDir = <path>\n" +
                            "logLevel = <nr>\n" +
                            "ignoreRobots = <1/0>\n" +
                            "logFilename = <path>\n" +
                            "maxDepth = <nr>\n" +
                            "dSizeLimit = <nr>\n" +
                            "dTypes = <string> <string> <string> "));
    }
}
