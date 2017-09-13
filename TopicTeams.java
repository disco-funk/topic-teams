package com.philhudson.topicteams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class TopicTeams {

    public static void main(String[] args) {

        String fileName = "/Users/phud/IdeaProjects/topic-teams/src/com/philhudson/topicteams/data.csv";

        Set<Record> names = new HashSet<>();
        Set<String> projects = new HashSet<>();
        ArrayList<Set<Record>> tables = new ArrayList<>();
        ArrayList<Record> topicTeamLead = new ArrayList<>();

        for (int i = 0; i <= 8; i++) {
            tables.add(new HashSet<>());
        }

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.forEach(line -> {
                String[] arr = line.split(",");
                Record r = new Record(arr[0], arr[1], arr[2]);

                if (!arr[2].startsWith("TTL-")) {
                    names.add(r);
                    projects.add(arr[2]);
                } else {
                    topicTeamLead.add(r);
                }
            });


            ArrayList<Record> cloneNames = new ArrayList<>();
            cloneNames.addAll(names);

            while (cloneNames.size() > 0) {
                Record n = cloneNames.remove(ThreadLocalRandom.current().nextInt(0, cloneNames.size()));

                int tableNo = 0;
                boolean allocated = false;
                do {
                    if (n.isAllowedOnTable(tables.get(tableNo))) {
                        tables.get(tableNo).add(n);
                        allocated = true;
                    } else {
                        tableNo++;
                    }
                } while (tableNo < 9 && !allocated);

            }

            System.out.println("--------------- Tables");
            for (int i = 0; i < 8; i++) {
                Record ttl = topicTeamLead.get(i);
                System.out.println(i + "..." + ttl.firstname + " " + ttl.surname);
                for (Record name : tables.get(i)) {
                    System.out.println("\t"  + name.firstname + " " + name.surname + " (" + name.project + ")");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
