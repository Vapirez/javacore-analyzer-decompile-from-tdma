package com.ibm.jinwoo;

import java.io.*;
import java.util.zip.GZIPInputStream;

public class Analyzer {
    public static InputStream getInputStream(File file) throws IOException {
        if (file.getName().endsWith(".gz"))
            try {
                return new GZIPInputStream(new FileInputStream(file));
            } catch (FileNotFoundException ex) {
                throw ex;
            } catch (IOException e) {
                return new FileInputStream(file);
            }
        else
            return new FileInputStream(file);
    }
}
