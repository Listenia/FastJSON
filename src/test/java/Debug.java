import fun.listenia.fastjson.ReaderJSON;

public class Debug {


    public static void main(String[] args) {


        String json = "{    \"a\"  : \"b\"}    ";

        System.out.println(ReaderJSON.readRawObject(json));


    }

}
