package com.test.recyclerviewenhanced;

/**
 * ====================== 数据实体 ========================
 *
 * @author SGamble
 */
public class RowModel {
    String mainText, subText;

    public RowModel(String mainText, String subText) {
        this.mainText = mainText;
        this.subText = subText;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public String getSubText() {
        return subText;
    }

    public void setSubText(String subText) {
        this.subText = subText;
    }
}
