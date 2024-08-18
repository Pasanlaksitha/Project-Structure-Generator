import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DirectoryTree {

    public static void printDirectoryTree(File rootDir, String indent) {
        File[] items = rootDir.listFiles();
        if (items != null) {
            for (int i = 0; i < items.length; i++) {
                File item = items[i];
                boolean isLast = i == items.length - 1;

                if (item.isDirectory()) {
                    System.out.println(indent + "├── " + item.getName() + "/");
                    printDirectoryTree(item, indent + (isLast ? "    " : "│   "));
                } else {
                    System.out.println(indent + "├── " + item.getName());
                }
            }
        }
    }

    public static void writeDirectoryTree(File rootDir, FileWriter writer, String indent) throws IOException {
        File[] items = rootDir.listFiles();
        if (items != null) {
            for (int i = 0; i < items.length; i++) {
                File item = items[i];
                boolean isLast = i == items.length - 1;

                if (item.isDirectory()) {
                    writer.write(indent + "├── " + item.getName() + "/\n");
                    writeDirectoryTree(item, writer, indent + (isLast ? "    " : "│   "));
                } else {
                    writer.write(indent + "├── " + item.getName() + "\n");
                }
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java DirectoryTree <directory> <output_file>");
            return;
        }

        File projectRoot = new File(args[0]);
        String outputFileName = args[1];

        if (!projectRoot.isDirectory()) {
            System.out.println("The specified path is not a directory.");
            return;
        }

        System.out.println(projectRoot.getName() + "/");
        printDirectoryTree(projectRoot, "");

        try (FileWriter writer = new FileWriter(outputFileName)) {
            writer.write(projectRoot.getName() + "/\n");
            writeDirectoryTree(projectRoot, writer, "");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
