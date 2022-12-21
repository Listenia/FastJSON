package reading;

import fun.listenia.fastjson.ReaderJSON;
import writing.WritingTest;

import java.util.Map;

public class ReadingTest {

    public static void main (String[] args) {


        String json = WritingTest.value();

        Map<String, Object> map = ReaderJSON.readRawObject(json);
        System.out.println(map);




    }

}
