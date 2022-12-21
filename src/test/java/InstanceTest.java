import exemple.Hello;
import exemple.SubHello;

public class InstanceTest {

    public static void main(String[] args) {

        Hello hello = new Hello(200, "OK");

        SubHello subHello = new SubHello("Image");
        hello.setSubHello(subHello);


        System.out.println(hello);


    }

}
