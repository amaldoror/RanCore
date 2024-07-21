package org.rancore.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <p><u><b>Class name</b></u></p>
 * <p>LineCounter</p>
 * <p><u><b>Description</b></u></p>
 * <p>The LineCounter class counts the total number of files, code lines, blank lines, and comment lines
 * in a specified directory and its subdirectories. It supports printing the directory structure
 * and saving the results to a file.</p>
 * <p><u><b>Usage</b></u></p>
 * <p>java LineCounter &lt;directory&gt; [--print-structure | -p] [--save-to-file | -s &lt;output-path&gt;]</p>
 */
public class LineCounter {

    /**
     * <p><u><b>Method name</b></u></p>
     * <p>main</p>
     * <p><u><b>Description</b></u></p>
     * <p>The entry point of the program. Analyzes the arguments, checks the directory validity, and starts the
     * process of counting lines in the files.</p>
     * <p><u><b>Parameters</b></u></p>
     * <ul>
     * <li>args - Command line arguments: the directory, optional parameters for printing the structure and saving
     * the results to a file.</li>
     * </ul>
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please specify the directory.");
            System.exit(1);
        }

        String directoryPath = null;
        boolean printStructure = false;
        boolean saveToFile = false;
        String outputFilePath = null;
        StringBuilder outputFileName = new StringBuilder("project-info");
        String outputFileFormat = "txt";

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--print-structure") || args[i].equals("-p")) {
                printStructure = true;
            } else if (args[i].equals("--save-to-file") || args[i].equals("-s")) {
                saveToFile = true;
                if (i + 1 < args.length) {
                    outputFilePath = args[i + 1];
                    outputFileName.append(FileHelper.generateTimestampForFilename()).append(".").append(outputFileFormat);
                    outputFilePath = outputFilePath.endsWith(File.separator) ? outputFilePath + outputFileName : outputFilePath + File.separator + outputFileName;
                    i++; // skip the next argument (file path)
                } else {
                    System.out.println("Please specify the file path.");
                    System.exit(1);
                }
            } else {
                directoryPath = args[i];
            }
        }

        if (directoryPath == null) {
            System.out.println("Please specify the directory.");
            System.exit(1);
        }

        File directory = new File(directoryPath);

        if (!directory.isDirectory()) {
            System.out.println("The specified path is not a directory.");
            System.exit(1);
        }

        Result result = countLinesInDirectory(directory, "", printStructure, saveToFile, outputFilePath);

        String summary = String.format("""

                        Gesamtanzahl der Dateien:\t\t\t%d
                        Gesamtanzahl der Zeilen:\t\t\t%d
                        Gesamtanzahl der Leerzeilen:\t\t%d
                        Gesamtanzahl der Kommentarzeilen:\t%d
                        Gesamtanzahl der Codezeilen:\t\t%d""",
                result.totalFiles,
                result.totalLines,
                result.blankLines,
                result.commentLines,
                (result.totalLines - result.blankLines - result.commentLines));

        System.out.println(summary);
        if (saveToFile) {
            writeToFile(summary, outputFilePath);
        }
    }

    /**
     * <p><u><b>Method name</b></u></p>
     * <p>countLinesInDirectory</p>
     * <p><u><b>Description</b></u></p>
     * <p>Recursively searches a directory and counts the total number of files, code lines, blank lines, and comment lines.
     * Optionally prints the directory structure and saves the results to a file.</p>
     * @param directory The directory to search
     * @param indent A string used for formatting the output
     * @param printStructure Boolean indicating whether to print the directory structure
     * @param saveToFile Boolean indicating whether to save the results to a file
     * @param outputFilePath The path of the output file of saveToFile is true
     * @return Result object containing the total number of files, code lines, blank lines and comment lines
     */
    private static Result countLinesInDirectory(File directory,
                                                String indent,
                                                boolean printStructure,
                                                boolean saveToFile,
                                                String outputFilePath) {
        Result result = new Result();
        String subIndent = indent + "│   ";

        File[] files = directory.listFiles();
        if (files != null) {
            int fileCount = 0;
            for (File file : files) {
                fileCount++;
                boolean isLast = (fileCount == files.length);
                String fileIndent = isLast ? "└── " : "├── ";

                if (file.isDirectory()) {
                    if (printStructure) {
                        String output = indent + fileIndent + file.getName() + "/";
                        System.out.println(output);
                        if (saveToFile) {
                            writeToFile(output, outputFilePath);
                        }
                    }
                    Result subdirResult = countLinesInDirectory(file, subIndent, printStructure, saveToFile, outputFilePath);
                    result.add(subdirResult);
                } else if (file.isFile() && (file.getName().endsWith(".java") || file.getName().endsWith(".py") || file.getName().endsWith(".js") || file.getName().endsWith(".html") || file.getName().endsWith(".css"))) {
                    if (printStructure) {
                        String output = indent + fileIndent + file.getName();
                        System.out.println(output);
                        if (saveToFile) {
                            writeToFile(output, outputFilePath);
                        }
                    }
                    result.totalFiles++;
                    Result fileResult = countLinesInFile(file);
                    result.add(fileResult);
                }
            }
        }

        return result;
    }

    /**
     * <p><u><b>Method name</b></u></p>
     * <p>countLinesInFile</p>
     * <p><u><b>Description</b></u></p>
     * <p>Counts the total number of lines, blank lines, and comment lines in a file.</p>
     * @param file The file to analyze
     * @return Result object containing the total number of lines, blank lines, and comment lines in the file
     */
    private static Result countLinesInFile(File file) {
        Result result = new Result();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.totalLines++;
                line = line.trim();
                if (line.isEmpty()) {
                    result.blankLines++;
                } else if (line.startsWith("//") || line.startsWith("#") || line.startsWith("/*") || line.startsWith("*") || line.startsWith("*/")) {
                    result.commentLines++;
                }
            }
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen der Datei " + file.getPath() + ": " + e.getMessage());
        }

        return result;
    }

    /**
     * <p><u><b>Method name</b></u></p>
     * <p>writeToFile</p>
     * <p><u><b>Description</b></u></p>
     * <p>Writes a given string content to a file.</p>
     * @param content The content to write
     * @param filePath The path of the file to write the content to
     */
    private static void writeToFile(String content, String filePath) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(content);
            writer.write("\n");
        } catch (IOException e) {
            System.err.println("Error while writing to file:\n" + e.getMessage());
        }
    }

    private static class Result {
        int totalFiles = 0;
        int totalLines = 0;
        int blankLines = 0;
        int commentLines = 0;

        void add(Result other) {
            this.totalFiles += other.totalFiles;
            this.totalLines += other.totalLines;
            this.blankLines += other.blankLines;
            this.commentLines += other.commentLines;
        }
    }
}
