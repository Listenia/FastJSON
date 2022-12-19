package exemple;

import fun.listenia.fastjson.JSONSerializable;
import fun.listenia.fastjson.Serializer;

public class Hello extends JSONSerializable {

    private final int state;
    private final String message;

    private SubHello subHello;

    public Hello (int state, String message) {
        this.state = state;
        this.message = message;
    }

    public void setSubHello (SubHello subHello) {
        this.subHello = subHello;
    }


    @Override
    public void serialize (Serializer serializer) {
        serializer.put("state", state);
        serializer.put("message", message);
        serializer.put("subHello", subHello);
    }
}
