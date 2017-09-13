package com.philhudson.topicteams;

import java.util.ArrayList;
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

    public boolean isAllowedOnTable(Set<Record> records, int rotation, int currentTableNo, ArrayList<ArrayList<Set<Record>>> tableRotation) {
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

        if( noOnSameProject > 2) {
            return false;
        }


        if(rotation >= 1) {
            for(int x = 0; x < rotation; x++ ) {

                ArrayList<Set<Record>> lastTableSetup = tableRotation.get(x);

                // which table did I sit at last time?
                int lastTableISatAt =-1;

                for (int i = 0; i < 7; i++) {
                    for (Record r: lastTableSetup.get(i)) {
                        if(r.surname.equals(this.surname) && r.firstname.equals(this.firstname)) {
                            lastTableISatAt = i;
                        }
                    }
                }

                if(currentTableNo == lastTableISatAt) {
                    return false;
                }
            }

        }

        return true;
    }
}
