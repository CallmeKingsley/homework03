/**
 * Created by Kingsley on 10/31/17.
 */
/**
 * Created by Kingsley on 10/31/17.
 */
import org.apache.commons.cli.Option;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import java.util.Arrays;

public class Main {
    private CommandLine cmd;
    private Integer[] intArray;
    private String[] StringArray;
    private Integer int_key;
    private String Str_key;
    private boolean flag;





    boolean check(){
        return flag;
    }
    Integer[] getint_Array(){
        return intArray;
    }
    String[] getstr_key(){
        return StringArray;
    }


    boolean setupOptions(String[] args){

        Options option = new Options();

        CommandLineParser parser = new DefaultParser();

        option.addOption("key", true, "please insert current key");
        option.addOption("type", true, "insert the type of items");
        Option list = new Option("list", true, "insert the list of items");
        list.setArgs(Option.UNLIMITED_VALUES);
        option.addOption(list);

        try {
            cmd = parser.parse(option,args);

        }catch(ParseException e){

            System.out.println("Error occur");
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    protected void getResultOption(){
        String[] arrays = null;
        String key = null;
        String type = null;


        if(cmd.hasOption("type")){
            type = cmd.getOptionValue("type");
            setType(type);

        }
        if(cmd.hasOption("key")){
            key = cmd.getOptionValue("key");
            setKey(key);

        }
        if(cmd.hasOption("list")){


             arrays = cmd.getOptionValues("list");
            
        }
        if(flag){
            //intArray = Arrays.stream(cmd.getOptionValues("list")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);

            Integer[] intArrays =  new Integer[arrays.length];

            for(int i = 0; i < arrays.length; i++){
                intArrays[i] = Integer.parseInt(arrays[i]);
            }
               this.intArray = intArrays;
        }else{
            StringArray = cmd.getOptionValues("list");
        }



    }
    void setKey(String key){

        if (flag){
            int_key = new Integer(key);}
        else {
            Str_key = key;}

    }
    void setType(String type){

        if(type.equals("i")){flag = true;}
        else if (type.equals("s")){flag = false;}
        else {
            System.out.println("insert i or s please");
            System.exit(0);
        }

    }
    public int binSearch(Comparable[] aList, Comparable key, int count){

        int startPoint = 0;
        int endPoint = count - 1;



        while(startPoint <= endPoint){

            int midPoint = (startPoint + endPoint) / 2;

            if(aList[midPoint].compareTo(key) == 0){

                return midPoint;
            }
            if(key.compareTo(aList[midPoint]) < 0){

                endPoint = midPoint - 1;
            }else{
                 startPoint = midPoint + 1;
            }





        }

        return 0;
    }

    public boolean checkForExistenint(Comparable[] array, Comparable key, int lenght){

        for(int i  = 0; i < lenght; i++){

            if(array[i].compareTo(key) == 0){

                return true;
            }

        }

        return false;
    }
    public static void main(String[] args){
        Main hw = new Main();
        hw.setupOptions(args);
        hw.getResultOption();

        int result;







        if(hw.check()){
            if(hw.checkForExistenint(hw.intArray,hw.int_key,hw.intArray.length) == true){

                result = hw.binSearch(hw.intArray, hw.int_key, hw.intArray.length);
                System.out.print("the key was found at index " + result);
            }else{
                System.out.print("integer key not found");

            }

        }else{
            if(hw.checkForExistenint(hw.StringArray, hw.Str_key, hw.StringArray.length) == true){
                result = hw.binSearch(hw.StringArray, hw.Str_key, hw.StringArray.length);
                System.out.print("the key was found at index " + result);
            }else{
                System.out.print("string key not found");
            }

        }


    }


}


