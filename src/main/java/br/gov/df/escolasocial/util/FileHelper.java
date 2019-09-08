package br.gov.df.escolasocial.util;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public final class FileHelper {

    private FileHelper() {

    }

    public static String curdir() {
        return System.getProperty("user.dir");
    }

    public static boolean fileExistsInCurDir(String filename) {
        return fileExists(curdir()+ File.separator+filename);
    }

    public static boolean fileExists(String filename) {
        Path path = FileSystems.getDefault().getPath(filename);
        return Files.exists(path);
    }
}
