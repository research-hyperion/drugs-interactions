package org.hyperion.util.csv.parser;

public interface ICsvParserCreator {
    public  ICsvParser createParser(String csvFile);
}
