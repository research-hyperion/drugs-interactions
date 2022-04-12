package org.hyperion.util.csv.parser;

import com.opencsv.bean.CsvToBeanBuilder;
import org.hyperion.util.csv.pojo.DrugCsv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;

public class CsvDbDrugsParser<T> implements ICsvParser<T>{
    private String csvFile;

    private List<T> csvBeans=null;


    public CsvDbDrugsParser(String csvFile) {
        this.csvFile=csvFile;
    }

    @Override
    public void parseCsv() {
        try {
            csvBeans = new CsvToBeanBuilder(new FileReader(this.csvFile))
                    .withType(DrugCsv.class)
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<List<T>> getCsvBeans() {
        return Optional.ofNullable(this.csvBeans);
    }
}
