import javax.swing.*;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        MyWindow window = new MyWindow();
        window.setVisible(true);
        window.setResizable(false);
        window.setLocation(500, 400);
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
