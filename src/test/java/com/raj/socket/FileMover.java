package com.raj.socket;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileMover {
    static List<Path> regularFile = new ArrayList<>();
    static Set<Path> allreadyTracked = new HashSet<>();

    public static void main(String[] args) throws IOException {
        FileMover fileMover = new FileMover();
        Path vidDestPath = Paths.get("D:\\Downloads\\Videos");
        Path imgDestPath = Paths.get("D:\\Downloads\\Images");
        Path docDestPath = Paths.get("D:\\Downloads\\Documents");
        Path ebookDestPath = Paths.get("D:\\Downloads\\Documents");
        Path softDestPath = Paths.get("D:\\Software");
        Path regDestPath = Paths.get("D:\\Downloads");
        fileMover.listAllFiles();
        regularFile.forEach(System.out::println);
        regularFile.forEach(file -> {
            if (file.toString().endsWith(".mp4") || file.toString().endsWith(".ts") || file.toString().endsWith(".webm")) {
                fileMover.moveToDdrive(file, vidDestPath);
            } else if (file.toString().endsWith(".jpg") || file.toString().endsWith(".jpeg") || file.toString().endsWith(".png") || file.toString().endsWith(".gif")) {
                fileMover.moveToDdrive(file, imgDestPath);
            } else if (file.toString().endsWith(".exe") || file.toString().endsWith(".jar") || file.toString().endsWith(".msi")) {
                fileMover.moveToDdrive(file, softDestPath);
            } else if (file.toString().endsWith(".pdf") || file.toString().endsWith(".epub")) {
                fileMover.moveToDdrive(file, docDestPath);
            } else {
                fileMover.moveToDdrive(file, regDestPath);
            }
        });
    }

    private void moveToDdrive(Path file, Path destPath) {
        System.out.println("\n\nMoving file-> " + file + "\nTo this folder-> " + destPath);
        Path toPath = Paths.get(destPath.toString() + "\\" + file.getFileName());
        try {
            Files.move(file, toPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
//            e.printStackTrace();
        }
    }

    public void listAllFiles() throws IOException {
        Files.walk(Paths.get("D:\\Downloads")).forEach(path -> {
            if (Files.isRegularFile(path)) {
                regularFile.add(path);
            }
        });
    }
}
