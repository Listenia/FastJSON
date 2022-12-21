package writing;

import fun.listenia.fastjson.extra.Serializer;
import org.json.JSONObject;

public class HardwriteBench {

    public static void main(String[] args) {
        int iters = 1_000_000;

        long start = System.currentTimeMillis();
        for (int i = 0; i < iters; i++) {
            legacy();
        }
        long end = System.currentTimeMillis();
        System.out.println("legacy: " + (end-start));



        long start2 = System.currentTimeMillis();
        for (int i = 0; i < iters; i++) {
            evol();
        }
        long end2 = System.currentTimeMillis();
        System.out.println("Evol: " + (end2-start2));

        double diff = ((double) end-start) / (end2-start2);
        System.out.println("Diff: " + diff);

    }

    public static String evol () {
        Serializer serializer = Serializer.createSerializer();
        serializer.put("str", "hello");
        serializer.put("double", 0.8D);
        serializer.put("boolean", true);
        return serializer.toString();
    }

    public static String legacy () {
        JSONObject obj = new JSONObject();
        obj.put("str", "hello");
        obj.put("double", 0.8D);
        obj.put("boolean", true);
        return obj.toString();
    }

}
