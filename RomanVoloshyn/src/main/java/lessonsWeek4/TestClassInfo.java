package lessonsWeek4;

import myScanner.MyScanner;

import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by serhii on 04.03.16.
 */
public class TestClassInfo {


    public static void main(String[] args){

        MyScanner myScanner;
        try {
            myScanner = new MyScanner("hjksahdjksdfhi");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String myScannerString = RTTIUtils.toString(myScanner);
        System.out.println(myScannerString);

        RTTIUtils.fromString(myScannerString);
        //OutputStreamWriter osw = new OutputStreamWriter(myScannerString);


    }
}
