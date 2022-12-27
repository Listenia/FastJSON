package fun.listenia.fastjson.extra.serialize;

import java.io.OutputStream;

public class SourceOutput {

    Object source;

    int index;

    public SourceOutput (StringBuilder source) {
        this.source = source;
    }


    public SourceOutput (OutputStream source) {
        this.source = source;
    }

    private void _writeByte (byte b) {
        if (source instanceof OutputStream) {
            try {
                ((OutputStream) source).write(b);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void write (char c) {
        if (source instanceof StringBuilder) {
            ((StringBuilder) source).append(c);
        } else if (source instanceof OutputStream) {
            // convert char to byte(s), UTF-8
            if (c < 0x80) {
                _writeByte((byte) c);
            } else {
                int i = 0;
                int b = c;
                while (b > 0) {
                    b >>= 8;
                    i++;
                }
                while (i > 0) {
                    _writeByte((byte) (c >> (8 * (i - 1))));
                    i--;
                }
            }
        }
    }

    @Override
    public String toString() {
        if (source instanceof StringBuilder) {
            return ((StringBuilder) source).toString();
        }
        return super.toString();
    }
}
