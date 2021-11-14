/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Alexander Williams
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public TableView<Event> tableView;
    public TableColumn<Event, String> descriptionList;
    public TableColumn<Event, LocalDate> dateColumn;
    public TableColumn<Event, String> statusColumn;

    @FXML
    TextField descriptionTextField;
    @FXML
    DatePicker datePicker;
    @FXML
    Button addButton;
    @FXML
    TextField exportTextField;
    @FXML
    TextField importTextField;

    ObservableList<Event> list = FXCollections.observableArrayList(

    );

    // initialize the GUI
    public void initialize(URL location, ResourceBundle resources)
    {
        tableView.setEditable(true);

        descriptionList.setCellValueFactory(new PropertyValueFactory<>("Description"));
        descriptionList.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionList.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Event, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Event, String> event) {
                        ((Event) event.getTableView().getItems().get(event.getTablePosition().getRow())).setDescription(event.getNewValue());
                    }
                }
        );

        statusColumn.setCellValueFactory(new PropertyValueFactory<>("Status"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        datePicker.setValue(LocalDate.now());

        //tableView.setItems(list);
    };

    // adds to do list
    public void addEvent(ActionEvent actionEvent)
    {
        // create event
        Event event = new Event(datePicker.getValue(), descriptionTextField.getText(), "Incomplete");

        // add event to list and update tableView
        list.add(event);
        tableView.getItems().add(event);
        refresh();
    }

    // set date picker back to current date
    private void refresh()
    {
        // set the date picker to today's date and the description field to empty
        datePicker.setValue(LocalDate.now());
        descriptionTextField.setText(null);
    }

    // removes a to-do item from the list
    public void removeList(ActionEvent actionEvent) {
        // remove the item from the observable list
        list.remove(tableView.getSelectionModel().getSelectedItem());

        // update tableView
        tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItem());
    }

    // changes the status of a to-do item
    public void changeStatus(ActionEvent actionEvent) {
        Event event = tableView.getSelectionModel().getSelectedItem();

        // change the status to the opposite of what it is currently set on
        if(event.getStatus() == "Incomplete") {
            event.setStatus("Complete");
        }else{
            event.setStatus("Incomplete");
        }

        // update the table view
        tableView.getItems().set(tableView.getSelectionModel().getFocusedIndex(), event);
    }

    // clear the to-do list
    public void clearList(ActionEvent actionEvent) {
        // clear both the tableView and the list
        list.clear();
        tableView.getItems().clear();
    }

    // shows the complete to-do items
    public void showComplete(ActionEvent actionEvent) {
        ObservableList<Event> newList = FXCollections.observableArrayList();

        // traverse the list and if the event is complete, add it to the temp list
        for(Event event : list)
        {
            if(event.getStatus().equals("Complete"))
                newList.add(event);
        }

        // set tableView to the temp list
        tableView.setItems(newList);
    }

    // shows the incomplete to-do items
    public void showIncomplete(ActionEvent actionEvent) {
        ObservableList<Event> newList = FXCollections.observableArrayList();

        // traverse the list and if the event is incomplete, add it to the temp list
        for(Event event : list)
        {
            if(event.getStatus().equals("Incomplete"))
                newList.add(event);
        }

        // set tableView to the temp list
        tableView.setItems(newList);
    }

    // shows all the to-do list items
    public void showAll(ActionEvent actionEvent) {
        // clear the tableView
        tableView.getItems().clear();

        // set the tableView to the list
        for(int i = 0; i < list.size(); i++)
            tableView.getItems().add(list.get(i));
    }

    // updates tableView based on imported file
    public void importFile(ActionEvent actionEvent) throws IOException {
        // create a buffered reader and get the file path
        String inputFile = importTextField.getText();
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        String splitter = ", ";
        String line = "";

        ObservableList<Event> inputList = FXCollections.observableArrayList();

        // read through the text file using commas as the splitter
        while((line = reader.readLine()) != null)
        {
            // add the text file information to events and add the events to the temp list
            String[] tempArray = line.split(splitter);
            LocalDate date = LocalDate.parse(tempArray[1]);

            Event tempEvent = new Event(date, tempArray[0], tempArray[2]);

            inputList.add(tempEvent);
        }

        reader.close();

        // update the tableView to the temp list
        tableView.setItems(inputList);
    }

    // exports a current to-do list to a text file
    public void exportFile(ActionEvent actionEvent) throws IOException {
        // create a buffered writer and get the file path
        String outputFile = exportTextField.getText();
        File tempFile = new File(outputFile);

        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        // traverse the tableView and copy the information to the text file
        // separate by commas and create a new line for each event
        for(Event event: list)
        {
            writer.write(event.getDescription());
            writer.write(", ");
            writer.write(event.getDate().toString());
            writer.write(", ");
            writer.write(event.getStatus());
            writer.write("\n");
        }

        writer.close();
    }

    public void editDescription(ActionEvent actionEvent) {
        // this function is now obsolete as it can be done within the initialization function
    }

    // edit the due date of a to-do list item
    public void editDueDate(ActionEvent actionEvent) {
        // get the information of the row to be edited and edit the event
        Event event = tableView.getSelectionModel().getSelectedItem();
        event.setDate(datePicker.getValue());

        // refresh the tableView
        tableView.getItems().set(tableView.getSelectionModel().getFocusedIndex(), event);
        refresh();
    }

    // adds to-do item in to-do list
    public void addToDoItem(ActionEvent actionEvent){
        // this function is now obsolete
    }

    // removes a to-do item in a to-do list
    public void removeToDoItem(ActionEvent actionEvent){
        // this function is now obsolete
    }

    // edits a to-do item in a to-do list
    public void editToDoItemDescription(ActionEvent actionEvent){
        // this function is now obsolete
    }

    // edits a to-do item date in to-do list
    public void editToDoItemDate(ActionEvent actionEvent){
        // this function is now obsolete
    }

    // brings a user to the contents of the to-do list
    public void seeList(ActionEvent actionEvent){
        // this function is now obsolete
    }
}
