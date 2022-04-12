package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hyperion.core.ApiUrls;
import org.hyperion.core.IdExtracter;
import org.hyperion.core.InteractionChecker;
import org.hyperion.util.csv.pojo.DrugCsvTemp;
import org.hyperion.util.csv.writer.CsvDrugsWriter;
import org.hyperion.util.interaction.FullInteractionType;
import org.hyperion.util.interaction.FullInteractionTypeGroup;
import org.hyperion.util.interaction.InteractionPair;
import org.hyperion.util.rxcui.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Consumer implements Runnable{
    private final DataQueue dataQueue;
    private final String outputFile;
    private final CsvDrugsWriter writer;
    public Consumer(DataQueue dataQueue, String outputFile) {
        this.dataQueue=dataQueue;
        this.outputFile=outputFile;
        writer = new CsvDrugsWriter(outputFile);
    }
    @Override
    public void run() {
        consume();
    }

    private void consume() {
        List<String[]> contentList = new ArrayList<>();
        while(true) {
            Message message;
            if (dataQueue.isEmpty()) {
                try {
                    dataQueue.waitOnEmpty();
                } catch (InterruptedException e) {
                    break;
                }
            }
            message = dataQueue.remove();
            if (message.isDone()) {
                dataQueue.add(message);
                break;
            }
            String[] ids = processPair(message);
            String interaction = processInteractions(ids[1],ids[3]);
            contentList.add(new String[]{ids[0],ids[1],ids[2],ids[3],interaction});
            dataQueue.notifyAllForFull();

        }
        writer.write(contentList);
    }



    private String[] processPair(Message message) {
        String drug1DbId = message.getDrug1DbId();
        String drug2DbId = message.getDrug2DbId();
        IdExtracter extracter = new IdExtracter();
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("Extracting id for: "+drug1DbId + " and "+ drug2DbId);
        Optional<String> rxCuiId = extracter.extractRxCuiForDrug(drug1DbId, ApiUrls.DATABASE);
        String [] row = null;
        if (rxCuiId.isPresent()) {
                Optional<String> rxCuiIdInteraction = extracter.extractRxCuiForDrug(drug2DbId, ApiUrls.DATABASE);
                if (rxCuiIdInteraction.isPresent()) {
                    Root idGroup1 = null;
                    Root idGroup2 = null;
                    try {
                        idGroup1 = objectMapper.readValue(rxCuiId.get(), Root.class);
                        String rxNormId1 = idGroup1.getIdGroup().getRxnormIdString();
                        idGroup2 = objectMapper.readValue(rxCuiIdInteraction.get(), Root.class);
                        String rxNormId2 = idGroup2.getIdGroup().getRxnormIdString();
                        row = new String[]{drug1DbId, rxNormId1, drug2DbId, rxNormId2};


                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }

                }
            }

        return row;

    }

    public String processInteractions(String drug1Rxcui, String drug2Rxcui) {
        InteractionChecker interactionChecker = new InteractionChecker();
        ObjectMapper mapper = new ObjectMapper();
        Optional<String> interaction = interactionChecker.getInteraction(drug1Rxcui, drug2Rxcui, ApiUrls.DATABASE);
        String interactionSeverity = "";
        if (interaction.isPresent()) {
            org.hyperion.util.interaction.Root parsedInteraction = null;
            try {
                parsedInteraction= mapper.readValue(interaction.get(), org.hyperion.util.interaction.Root.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            if (parsedInteraction != null) {
                ArrayList<FullInteractionTypeGroup> fullInteractionTypeGroup = parsedInteraction.fullInteractionTypeGroup;
                if (fullInteractionTypeGroup != null) {
                    ArrayList<FullInteractionType> fullInteractionType = parsedInteraction.fullInteractionTypeGroup.get(0).fullInteractionType;
                    if (fullInteractionType != null) {
                        InteractionPair interactionPair = fullInteractionType.get(0).interactionPair.get(0);
                        interactionSeverity = interactionPair.severity;


                    }
                }
            }
        }
        return interactionSeverity;
    }

}
