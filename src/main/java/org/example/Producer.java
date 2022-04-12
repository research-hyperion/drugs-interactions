package org.example;

import org.hyperion.util.csv.parser.CsvDbDrugsParser;
import org.hyperion.util.csv.parser.DbDrugsCsvParserCreator;
import org.hyperion.util.csv.pojo.DrugCsv;

import java.util.List;
import java.util.Optional;

public class Producer implements Runnable{
    private final DataQueue dataQueue;
    private final String inputFile;
    private Optional<List<DrugCsv>> csvContent;
    public Producer(DataQueue dataQueue, String inputFile) {
        this.dataQueue = dataQueue;
        this.inputFile = inputFile;


        CsvDbDrugsParser csvDbDrugsParser = (CsvDbDrugsParser) new DbDrugsCsvParserCreator().createParser(inputFile);
        csvDbDrugsParser.parseCsv();
        csvContent = csvDbDrugsParser.getCsvBeans();
    }

    @Override
    public void run() {
        produce();
    }

    private void produce() {
        if (csvContent.isPresent()) {
            List<DrugCsv> drugCsvs = csvContent.get();
            for (DrugCsv drug:drugCsvs) {
                String drug1DbId = drug.getDbId();
                List<String> drugInteractions = drug.getDbInteractionsSplitted();
                for (String drugDbId :drugInteractions) {
                    Message message = new Message(drug1DbId,drugDbId,false);
                    while (dataQueue.isFull()){
                        try {
                            dataQueue.waitOnFull();
                        } catch (InterruptedException e) {
                            break;
                        }
                    }

                    dataQueue.add(message);
                    dataQueue.notifyAllForEmpty();
                }
            }
        }
        dataQueue.add(new Message("","",true));




    }


}
