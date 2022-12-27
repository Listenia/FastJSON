package fun.listenia.fastjson.extra.deserialize;

import java.io.InputStream;

public class SourceInput {

    private final Object source;
    private int index;

    private char last;
    private char future;

    public SourceInput (String source) {
        this.source = source;
    }

    public SourceInput (char[] source) {
        this.source = source;
    }

    public SourceInput (byte[] source) {
        this.source = source;
    }

    public char next () {
        if (future != 0) {
            last = future;
            future = 0;
            return last;
        }

        if (source instanceof String) {
            return last = ((String) source).charAt(index++);
        } else if (source instanceof char[]) {
            return last = ((char[]) source)[index++];
        } else if (source instanceof byte[]) {
            // convert byte(s) to char, UTF-8
            char c = (char) _readByte();
            if (c >= 0x80) {
                int i = 0;
                int b = c;
                while ((b & 0x80) != 0) {
                    b <<= 1;
                    i++;
                }
                i--;
                while (i > 0) {
                    c <<= 8;
                    c |= _readByte();
                    i--;
                }
            }
            return last = c;
        }
        return (char) -1;
    }

    private byte _readByte () {
        if (source instanceof byte[]) {
            return ((byte[]) source)[index++];
        } else if (source instanceof InputStream) {
            try {
                return (byte) ((InputStream) source).read();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (byte) -1;
    }


    public char get () {
        return last;
    }

    public char future () {
        future = 0;
        future = next();
        return future;
    }

    public void skip (int n) {
        for (int i = 0; i < n; i++) {
            next();
        }
    }


}
