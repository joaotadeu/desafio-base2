package br.com.joaotadeu.mantis.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestReport {

    private BufferedWriter writer;

    public TestReport(String filePath) {
        try {
            writer = new BufferedWriter(new FileWriter(filePath, true));
            writer.write("<html><head><title>Test Report</title></head><body>");
            writer.write("<h1>Test Report</h1>");
            writer.write("<table border='1'>");
            writer.write("<tr><th>Test Name</th><th>Result</th><th>Message</th><th>Timestamp</th></tr>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logTestResult(String testName, String result, String message) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timestamp = LocalDateTime.now().format(formatter);
            writer.write("<tr>");
            writer.write("<td>" + testName + "</td>");
            writer.write("<td>" + result + "</td>");
            writer.write("<td>" + message + "</td>");
            writer.write("<td>" + timestamp + "</td>");
            writer.write("</tr>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (writer != null) {
                writer.write("</table></body></html>");
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
