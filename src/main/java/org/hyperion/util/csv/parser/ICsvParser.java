package org.hyperion.util.csv.parser;

import java.util.List;
import java.util.Optional;

public interface ICsvParser<T> {
    public void parseCsv();
    public Optional<List<T>> getCsvBeans();
}
