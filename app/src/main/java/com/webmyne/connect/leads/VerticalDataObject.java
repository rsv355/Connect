package com.webmyne.connect.leads;

/**
 * Created by priyasindkar on 08-03-2016.
 */
public class VerticalDataObject {
    public String verticalName, verticalShortName;
    public int verticalIndexNo, verticalId, verticalColorIndex;

    public VerticalDataObject() {
    }

    public VerticalDataObject(String verticalName, String verticalShortName, int verticalIndexNo, int verticalId, int verticalColorIndex) {
        this.verticalName = verticalName;
        this.verticalShortName = verticalShortName;
        this.verticalIndexNo = verticalIndexNo;
        this.verticalId = verticalId;
        this.verticalColorIndex = verticalColorIndex;
    }

    public String getVerticalName() {
        return verticalName;
    }

    public void setVerticalName(String verticalName) {
        this.verticalName = verticalName;
    }

    public String getVerticalShortName() {
        return verticalShortName;
    }

    public void setVerticalShortName(String verticalShortName) {
        this.verticalShortName = verticalShortName;
    }

    public int getVerticalIndexNo() {
        return verticalIndexNo;
    }

    public void setVerticalIndexNo(int verticalIndexNo) {
        this.verticalIndexNo = verticalIndexNo;
    }

    public int getVerticalId() {
        return verticalId;
    }

    public void setVerticalId(int verticalId) {
        this.verticalId = verticalId;
    }

    public int getVerticalColorIndex() {
        return verticalColorIndex;
    }

    public void setVerticalColorIndex(int verticalColorIndex) {
        this.verticalColorIndex = verticalColorIndex;
    }
}
