package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import org.hyperion.core.ApiUrls;
import org.hyperion.core.IdExtracter;
import org.hyperion.core.InteractionChecker;
import org.hyperion.util.csv.parser.CsvDbDrugsParser;
import org.hyperion.util.csv.parser.CsvTempDbDrugsParser;
import org.hyperion.util.csv.parser.DbDrugsCsvParserCreator;
import org.hyperion.util.csv.parser.DbTempDrugsCsvParserCreator;
import org.hyperion.util.csv.pojo.DrugCsv;
import org.hyperion.util.csv.pojo.DrugCsvTemp;
import org.hyperion.util.csv.writer.CsvDrugsWriter;
import org.hyperion.util.interaction.FullInteractionType;
import org.hyperion.util.interaction.FullInteractionTypeGroup;
import org.hyperion.util.interaction.InteractionPair;
import org.hyperion.util.rxcui.Root;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
From https://www.twilio.com/blog/5-ways-to-make-http-requests-in-java
 */
public class Main {

//     private static void processCsvRows(List<DrugCsv> rows){
//        int index = 0;
//        IdExtracter extracter = new IdExtracter();
//        List<String[]> tempCsv = new ArrayList<>();
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        for (DrugCsv bean:rows) {
//            System.out.println("Checking for drug:"+bean.getDrugName());
//            String drugId = bean.getDbId();
//
//            Optional<String> rxCuiId = extracter.extractRxCuiForDrug(drugId,ApiUrls.DATABASE);
//            if (rxCuiId.isPresent()) {
//
//                List<String> dbInteractionsSplitted = bean.getDbInteractionsSplitted();
//                for (String drug:dbInteractionsSplitted) {
//                    if (tempCsv.size() >= 100) {
//                        index +=1;
//
//                        String file = "temp_"+index+".csv";
//                        CsvDrugsWriter writer = new CsvDrugsWriter(file);
//                        writer.write(tempCsv);
//                        tempCsv.clear();
//                    }
//                    System.out.println("\tChecking interaction id:" + drug);
//                    Optional<String> rxCuiIdInteraction = extracter.extractRxCuiForDrug(drug, ApiUrls.DATABASE);
//                    if (rxCuiIdInteraction.isPresent()) {
//                        Root idGroup1 = null;
//                        Root idGroup2 = null;
//                        try {
//                            idGroup1 = objectMapper.readValue(rxCuiId.get(), Root.class);
//                            String rxNormId1 = idGroup1.getIdGroup().getRxnormIdString();
//                            idGroup2 = objectMapper.readValue(rxCuiIdInteraction.get(), Root.class);
//                            String rxNormId2 = idGroup2.getIdGroup().getRxnormIdString();
//                            String[] row = {drugId, rxNormId1, drug, rxNormId2};
//                            tempCsv.add(row);
//
//                        } catch (JsonProcessingException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }
//            }
//
//        }
//    }
//
//    public static void processInteractions(List<DrugCsvTemp> rows,String outputFile) {
//        InteractionChecker interactionChecker = new InteractionChecker();
//        ObjectMapper mapper = new ObjectMapper();
//        List<String[]> finalCsvInteractions = new ArrayList<>();
//        for(DrugCsvTemp row : rows) {
//
//            String drug1DbId = row.getDbId1();
//            String drug2DbId = row.getDbId2();
//            String drug1Rxcui = row.getRxCui1();
//            String drug2Rxcui = row.getRxCui2();
//            Optional<String> interaction = interactionChecker.getInteraction(drug1Rxcui, drug2Rxcui, ApiUrls.DATABASE);
//            if (interaction.isPresent()) {
//                org.hyperion.util.interaction.Root parsedInteraction = null;
//                try {
//                     parsedInteraction= mapper.readValue(interaction.get(), org.hyperion.util.interaction.Root.class);
//                } catch (JsonProcessingException e) {
//                    e.printStackTrace();
//                }
//                if (parsedInteraction != null) {
//                    ArrayList<FullInteractionTypeGroup> fullInteractionTypeGroup = parsedInteraction.fullInteractionTypeGroup;
//                    if (fullInteractionTypeGroup != null) {
//                        ArrayList<FullInteractionType> fullInteractionType = parsedInteraction.fullInteractionTypeGroup.get(0).fullInteractionType;
//                        if (fullInteractionType != null) {
//                            InteractionPair interactionPair = fullInteractionType.get(0).interactionPair.get(0);
//                            String drug1 = interactionPair.interactionConcept.get(0).minConceptItem.rxcui;
//                            String drug2 = interactionPair.interactionConcept.get(1).minConceptItem.rxcui;
//                            String interactionSeverity = interactionPair.severity;
//                            finalCsvInteractions.add(new String[]{drug1DbId, drug1Rxcui, drug2DbId, drug2Rxcui, interactionSeverity});
//
//                        }
//                    }
//                }
//            }
//
//        }
//        CsvDrugsWriter writer = new CsvDrugsWriter(outputFile);
//        writer.write(finalCsvInteractions);
//
//    }

    public static void main(String[] args) {

//        CsvDbDrugsParser csvDbDrugsParser = (CsvDbDrugsParser) new DbDrugsCsvParserCreator().createParser(args[0]);
//
//        csvDbDrugsParser.parseCsv();
//        Optional<List<DrugCsv>> csvContent = csvDbDrugsParser.getCsvBeans();
//        if (csvContent.isPresent()) {
//            processCsvRows(csvContent.get());
//        }
//        int startPosition = Integer.parseInt(args[0]);
//        int offset = Integer.parseInt(args[1]);
//        int endPosition = startPosition+offset;
//        for (int i=startPosition;i<=endPosition;i++) {
//            String input_csv = "temp_"+i+".csv";
//            String output_csv = "output_csv/final_"+i+".csv";
//            System.out.println("Checking "+input_csv);
//            CsvTempDbDrugsParser<DrugCsvTemp> csvTempDrugsParser = (CsvTempDbDrugsParser<DrugCsvTemp>) new DbTempDrugsCsvParserCreator().createParser(input_csv);
//            csvTempDrugsParser.parseCsv();
//            Optional<List<DrugCsvTemp>> csvTempContent = csvTempDrugsParser.getCsvBeans();
//
//            if (csvTempContent.isPresent()) {
//                processInteractions(csvTempContent.get(),output_csv);
//            }
//
//        }

        DataQueue dataQueue = new DataQueue(100);
        int numOfThreads = 10;
        Thread producer = new Thread(new Producer(dataQueue,args[0]));
        producer.start();
        List<Thread> threads = new ArrayList<>();
        int finishedTHreads = 0;
        for (int i=0;i<numOfThreads;i++) {
            threads.add(new Thread(new Consumer(dataQueue,"file_"+i+".csv")));
            threads.get(i).start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            producer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
