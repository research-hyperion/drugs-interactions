package org.hyperion.util.csv.pojo;

import com.opencsv.bean.CsvBindByPosition;

import java.util.Arrays;
import java.util.List;

public class DrugCsvTemp {
    @CsvBindByPosition(position = 0)
    private String dbId1;

    @CsvBindByPosition(position = 1)
    private String rxCui1;

    @CsvBindByPosition(position = 2)
    private String dbId2;

    @CsvBindByPosition(position = 3)
    private String rxCui2;


    public String getDbId1() {
        return dbId1;
    }

    public void setDbId1(String dbId1) {
        this.dbId1 = dbId1;
    }

    public String getRxCui1() {
        return rxCui1;
    }

    public void setRxCui1(String rxCui1) {
        this.rxCui1 = rxCui1;
    }

    public String getDbId2() {
        return dbId2;
    }

    public void setDbId2(String dbId2) {
        this.dbId2 = dbId2;
    }

    public String getRxCui2() {
        return rxCui2;
    }

    public void setRxCui2(String rxCui2) {
        this.rxCui2 = rxCui2;
    }
}



