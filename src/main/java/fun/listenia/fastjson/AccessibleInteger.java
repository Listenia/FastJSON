package fun.listenia.fastjson;

public class AccessibleInteger {

    private int value;

    public AccessibleInteger (int value) {
        this.value = value;
    }

    public int get () {
        return value;
    }

    public void set (int value) {
        this.value = value;
    }

    public int incrementAndGet () {
        return ++value;
    }

    public int getAndIncrement () {
        return value++;
    }

    public int addAndGet (int value) {
        this.value += value;
        return this.value;
    }

}
