package org.hyperion.util.csv.parser;

import org.hyperion.util.csv.pojo.DrugCsv;

public class DbDrugsCsvParserCreator implements ICsvParserCreator{


    @Override
    public ICsvParser createParser(String csvFile) {
        return new CsvDbDrugsParser<DrugCsv>(csvFile);
    }
}
