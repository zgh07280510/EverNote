package com.lanou.evernote.search;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by dllo on 16/7/21.
 */
public class HistoryBean {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    int id;
    @Column("etContent")
    String etContent;

    public HistoryBean(String etContent) {
        this.etContent = etContent;
    }

    public String getEtContent() {
        return etContent;
    }

    public void setEtContent(String etContent) {
        this.etContent = etContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
