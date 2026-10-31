package util;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class ReportGenerator {

    public static void generate(Path logFolder, Path reportFile)
            throws IOException {

        long totalDeposits = 0;
        long totalWithdrawals = 0;

        if (Files.exists(logFolder)) {
            try (DirectoryStream<Path> files =
                         Files.newDirectoryStream(logFolder, "*.log")) {

                for (Path file : files) {
                    try (BufferedReader reader =
                                 Files.newBufferedReader(file)) {

                        String line;

                        while ((line = reader.readLine()) != null) {
                            String[] parts = line.split(",");

                            if (parts.length >= 2) {
                                try {
                                    long amount =
                                            Long.parseLong(parts[1].trim());

                                    if (parts[0].trim().equalsIgnoreCase("DEPOSIT")) {
                                        totalDeposits += amount;
                                    } else if (parts[0].trim()
                                            .equalsIgnoreCase("WITHDRAW")) {
                                        totalWithdrawals += amount;
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println(
                                            "Skipping invalid log line: " + line);
                                }
                            }
                        }
                    }

                    BasicFileAttributes attributes =
                            Files.readAttributes(
                                    file, BasicFileAttributes.class);

                    System.out.println("Log file: " + file.getFileName());
                    System.out.println("Size: " + attributes.size() + " bytes");
                    System.out.println("Modified: "
                            + attributes.lastModifiedTime());
                }
            }
        }

        String report =
                "----- MiniBank Daily Report -----\n"
                        + "Total Deposits: " + totalDeposits + "\n"
                        + "Total Withdrawals: " + totalWithdrawals + "\n";

        Path parent = reportFile.getParent();

        if (parent != null) {
            Files.createDirectories(parent);
        }

        Files.writeString(reportFile, report);
        System.out.println(report);
        System.out.println("Report saved to: " + reportFile);
    }
}
