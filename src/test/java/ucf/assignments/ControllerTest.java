/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Alexander Williams
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    ObservableList<Event> list = FXCollections.observableArrayList();

    @Test
    public void addEvent() {
        Event event = new Event(LocalDate.now(), "Test1", "Incomplete");
        list.add(event);
        assertEquals(list.get(0).getDescription(), "Test1");
    }

    @Test
    void removeList() {
        Event event = new Event(LocalDate.now(), "Test1", "Incomplete");

        addEvent();
        list.remove(0);
        boolean bool = list.contains(event);
        assertFalse(bool);
    }

    @Test
    void changeStatus() {
        addEvent();
        list.get(0).setStatus("Complete");
        assertEquals(list.get(0).getStatus(), "Complete");
    }

    @Test
    void clearList() {
        addEvent();
        list.clear();
        boolean bool = list.isEmpty();
        assertTrue(bool);
    }

    @Test
    void showComplete() {
        ObservableList<Event> newList = FXCollections.observableArrayList();

        Event event = new Event(LocalDate.now(), "Test1", "Incomplete");
        Event event1 = new Event(LocalDate.now(), "Test2", "Complete");

        list.add(event);
        list.add(event1);

        for(Event eventTest : list)
        {
            if(eventTest.getStatus().equals("Complete"))
                newList.add(eventTest);
        }

        boolean bool = newList.contains(event);
        assertFalse(bool);
    }

    @Test
    void showIncomplete() {
        ObservableList<Event> newList = FXCollections.observableArrayList();

        Event event = new Event(LocalDate.now(), "Test1", "Incomplete");
        Event event1 = new Event(LocalDate.now(), "Test2", "Complete");

        list.add(event);
        list.add(event1);

        for(Event eventTest : list)
        {
            if(eventTest.getStatus().equals("Incomplete"))
                newList.add(eventTest);
        }

        boolean bool = newList.contains(event1);
        assertFalse(bool);
    }

    @Test
    void showAll() {
        ObservableList<Event> newList = FXCollections.observableArrayList();

        Event event = new Event(LocalDate.now(), "Test1", "Incomplete");
        Event event1 = new Event(LocalDate.now(), "Test2", "Complete");

        newList.add(event);
        newList.add(event1);

        boolean bool = newList.contains(event);
        boolean bool1 = newList.contains(event1);

        assertTrue(bool);
        assertTrue(bool1);
    }

    @Test
    void importFile() {
        String inputFile = "C:/Users/link2/IdeaProjects/williams-cop3330-assignment4part2/src/main/java/ucf/textFiles/sampleOut.txt";
        File file = new File(inputFile);

        assertTrue(file.exists());
    }

    @Test
    void exportFile() {
        String outputFile = "C:/Users/link2/IdeaProjects/williams-cop3330-assignment4part2/src/main/java/ucf/textFiles/sampleOut.txt";
        File file = new File(outputFile);

        assertTrue(file.exists());
    }

    @Test
    void editDescription() {
        addEvent();
        list.get(0).setDescription("Test2");
        assertEquals(list.get(0).getDescription(), "Test2");
    }

    @Test
    void editDueDate() {
        addEvent();
        list.get(0).setDate(LocalDate.of(2021, 11,14));
        assertEquals(list.get(0).getDate(), LocalDate.of(2021,11, 14));
    }

    void addToDoItem() {
        // this function is now obsolete
    }

    void removeToDoItem() {
        // this function is now obsolete
    }

    void editToDoItemDescription() {
        // this function is now obsolete
    }

    void editToDoItemDate() {
        // this function is now obsolete
    }

    void seeList() {
        // this function is now obsolete
    }
}