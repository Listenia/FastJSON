import fun.listenia.fastjson.ReaderJSON;
import fun.listenia.fastjson.WriterJSON;
import writing.WritingTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Debug {


    public static void main(String[] args) {


        Map<String, Object> sub = new HashMap<>();
        sub.put("key", "value");

        List<Object> list = new ArrayList<>();
        list.add(15);
        list.add(0.1D);
        list.add("Test");
        list.add("\\\"Test");

        Map<String, Object> map = new HashMap<>();
        map.put("str", "hello");
        map.put("double", 0.8D);
        map.put("boolean", true);
        map.put("obj", sub);
        map.put("array", list);

        String json = WriterJSON.writeObject(map);

        System.out.println(ReaderJSON.readRawObject(json));


    }

}
