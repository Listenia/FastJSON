package exemple;

import fun.listenia.fastjson.JSONSerializable;
import fun.listenia.fastjson.extra.Serializer;

public class SubHello extends JSONSerializable {

    private final String content;

    public SubHello (String content) {
        this.content = content;
    }

    @Override
    public void serialize (Serializer serializer) {
        serializer.put("content", content);
    }
}
