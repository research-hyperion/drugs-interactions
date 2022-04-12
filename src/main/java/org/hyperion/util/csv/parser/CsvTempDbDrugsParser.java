package org.hyperion.util.csv.parser;

import com.opencsv.bean.CsvToBeanBuilder;
import org.hyperion.util.csv.pojo.DrugCsv;
import org.hyperion.util.csv.pojo.DrugCsvTemp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;

public class CsvTempDbDrugsParser<T> implements ICsvParser<T> {
    private String csvFile;

    private List<T> csvBeans=null;


    public CsvTempDbDrugsParser(String csvFile) {
        this.csvFile=csvFile;
    }

    @Override
    public void parseCsv() {
        try {
            csvBeans = new CsvToBeanBuilder(new FileReader(this.csvFile))
                    .withType(DrugCsvTemp.class)
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
