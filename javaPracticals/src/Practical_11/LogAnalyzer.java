package Practical_11;

import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.*;
import java.util.*;

public class LogAnalyzer {
    public static void main(String[] args) {

        String keyword = "ERROR";
        int totalLines = 0;
        int totalMatches = 0;

        try {
            // Create sample log files
            Files.write(Paths.get("log1.txt"), Arrays.asList(
                    "INFO: Program started",
                    "ERROR: File not found",
                    "INFO: Program running"
            ));

            Files.write(Paths.get("log2.txt"), Arrays.asList(
                    "ERROR: Connection failed",
                    "INFO: User logged in",
                    "ERROR: Invalid password"
            ));

            Files.write(Paths.get("log3.txt"), Arrays.asList(
                    "INFO: System started",
                    "WARNING: Low memory",
                    "ERROR: System crashed"
            ));

            String[] files = {
                    "log1.txt", "log2.txt", "log3.txt"
            };
            for (String file : files) {
                Path path = Paths.get(file);

                int lines = 0;
                int matches = 0;

                try (BufferedReader reader =
                             Files.newBufferedReader(path)) {

                    String line;

                    while ((line = reader.readLine()) != null) {
                        lines++;

                        if (line.contains(keyword)) {
                            matches++;
                        }
                    }
                }

                BasicFileAttributes attr =
                        Files.readAttributes(
                                path, BasicFileAttributes.class);

                System.out.println("File: " + file);
                System.out.println("Size: " + attr.size()
                        + " bytes");
                System.out.println("Total Lines: " + lines);
                System.out.println("Keyword Matches: " + matches);
                System.out.println("----------------------");

                totalLines += lines;
                totalMatches += matches;
            }

            System.out.println("FINAL SUMMARY");
            System.out.println("Total Lines: " + totalLines);
            System.out.println("Total Keyword Matches: "
                    + totalMatches);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


