package com.jyc.abc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class aaaaa {
    public static void main(String[] args) throws IOException {
        File file = new File("d:/test.txt");
        Writer w = new FileWriter(file);
        String str = "Hello World!";
        w.write(str);

    }
}
