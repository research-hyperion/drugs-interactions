package org.hyperion.util.csv.writer;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvDrugsWriter {
    private String csvFile;
    public CsvDrugsWriter(String csvFile) {
        this.csvFile=csvFile;
    }

    public void write(List<String[]> rows){
        try (CSVWriter writer = new CSVWriter((new FileWriter(this.csvFile)))) {
            writer.writeAll(rows);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
