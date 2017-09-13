package com.philhudson.topicteams;

import java.util.Set;

public class Record {

    public String surname;
    public String firstname;
    public String project;

    public Record(String surname, String firstname, String project) {
        this.surname = surname;
        this.firstname = firstname;
        this.project = project;
    }

    public boolean isAllowedOnTable(Set<Record> records) {
        boolean isAllowed = true;

        if(records.size() > 8) {
            return false;
        }

        int noOnSameProject = 0;

        for (Record r: records) {
            if (r.project.equals(this.project)) {
                noOnSameProject ++;
            }
        }

        return noOnSameProject <= 2;
    }
}
