package org.hyperion.util.rxcui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class IdGroup {
    private List<String> rxnormId = new ArrayList<String>();
    public List<String> getRxnormId() {
        return rxnormId;
    }
    public String getRxnormIdString() {
        return rxnormId.size()!=0?rxnormId.get(0):"";
    }
    public void setRxnormId(List<String> rxnormId) {
        this.rxnormId = rxnormId;
    }
}

