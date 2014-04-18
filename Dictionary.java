import java.io.*;
import java.util.*;

public class Dictionary {
    private ArrayList<String> russian;
    private ArrayList<String> english;
    private ArrayList<String> bRussian;
    private ArrayList<String> bEnglish;

    Dictionary(String input) throws IOException, InterruptedException {
        FileInputStream fstream1 = new FileInputStream(input);
        DataInputStream in = new DataInputStream(fstream1);
        BufferedReader br = new BufferedReader(new InputStreamReader(in, "windows-1251"));
        bRussian = new ArrayList<String>();
        bEnglish = new ArrayList<String>();
        russian = new ArrayList<String>();
        english = new ArrayList<String>();
        while (br.ready()) {
            String s = br.readLine();
            String[] parts = s.split(" - ");
            bEnglish.add(parts[0]);
            bRussian.add(parts[1]);
            english.add(parts[0]);
            russian.add(parts[1]);
        }
    }

    public void copyArray() {
        for (String item: bRussian) {
            String buff = item;
            russian.add(buff);
        }
        for (String item: bEnglish) {
            String buff = item;
            english.add(buff);
        }
    }

    public int randomInt() {
        if (russian.isEmpty()) {
            copyArray();
            return -1;
        }
        Random source = new Random();
        return source.nextInt(russian.size());
    }

    /*public int randomInt() {
        if (russian.isEmpty()) {
            copyArray();
        }
        Random source = new Random();
        return source.nextInt(russian.size());
    }*/

    public String getRussian(int i) {
        String buff = russian.get(i);
        russian.remove(i);
        return buff;
    }

    public String getEnglish(int i) {
        String buff = english.get(i);
        english.remove(i);
        return buff;
    }
}
