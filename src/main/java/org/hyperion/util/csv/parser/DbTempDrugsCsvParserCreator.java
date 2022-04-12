package org.hyperion.util.csv.parser;

import org.hyperion.util.csv.pojo.DrugCsvTemp;

public class DbTempDrugsCsvParserCreator implements ICsvParserCreator{

    @Override
    public ICsvParser createParser(String csvFile) {
        return new CsvTempDbDrugsParser<DrugCsvTemp>(csvFile);
    }
}
