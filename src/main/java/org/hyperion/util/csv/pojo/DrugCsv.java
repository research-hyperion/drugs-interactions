package org.hyperion.util.csv.pojo;

import com.opencsv.bean.CsvBindByPosition;

import java.util.Arrays;
import java.util.List;

public class DrugCsv {

    @CsvBindByPosition(position = 0)
    private String dbId;

    @CsvBindByPosition(position = 1)
    private String drugName;

    @CsvBindByPosition(position = 2)
    private String drugApproved;

    @CsvBindByPosition(position = 3)
    private String dbInteractions;


    public String getDbId() {
        return dbId;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugApproved() {
        return drugApproved;
    }

    public void setDrugApproved(String drugApproved) {
        this.drugApproved = drugApproved;
    }

    public String getDbInteractions() {
        return dbInteractions;
    }

    public List<String> getDbInteractionsSplitted() {
        return Arrays.stream(dbInteractions.split(";")).toList();
    }

    public void setDbInteractions(String dbInteractions) {
        this.dbInteractions = dbInteractions;
    }
}


