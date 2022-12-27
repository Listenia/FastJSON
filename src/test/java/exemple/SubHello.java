package exemple;

import fun.listenia.fastjson.extra.deserialize.Deserializer;
import fun.listenia.fastjson.extra.serialize.JSONSerializable;
import fun.listenia.fastjson.extra.serialize.ObjectSerializer;

public class SubHello extends JSONSerializable {

    private final String content;

    public SubHello (String content) {
        this.content = content;
    }

    @Override
    public void serialize (ObjectSerializer serializer) {
        serializer.put("content", content);
    }

    @Override
    public void deserialize(Deserializer deserializer) {
        /*content = deserializer.getString("content");*/
    }
}
