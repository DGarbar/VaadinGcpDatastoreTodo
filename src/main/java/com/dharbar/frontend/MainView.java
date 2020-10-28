package com.dharbar.frontend;

import com.dharbar.service.GcpDatastoreService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
@CssImport("./styles/shared-styles.css")
public class MainView extends VerticalLayout {

    @Autowired
    GcpDatastoreService gcpDatastoreService;

    public MainView() {
        VerticalLayout todosList = new VerticalLayout();
        TextField taskField = new TextField();

        Button addButton = createAddButton(todosList, taskField);
        Button refreshButton = createRefreshButton(todosList);

        add(
                new H1("Tasks"),
                todosList,
                new HorizontalLayout(
                        taskField,
                        addButton,
                        refreshButton
                )
        );
    }

    private Button createAddButton(VerticalLayout todosList, TextField taskField) {
        Button addButton = new Button("Add");
        addButton.addClickShortcut(Key.ENTER);
        addButton.addClickListener(click -> {
            final String taskDesk = taskField.getValue();
            gcpDatastoreService.addTask(taskDesk);
            Checkbox checkbox = new Checkbox(taskDesk);
            todosList.add(checkbox);
        });

        return addButton;
    }

    private Button createRefreshButton(VerticalLayout todosList) {
        Button refreshButton = new Button("Refresh");
        refreshButton.addClickShortcut(Key.KEY_R);
        refreshButton.addClickListener(click -> {
            gcpDatastoreService.getAllTasks().stream()
                    .map(Checkbox::new)
                    .forEach(todosList::add);
        });

        return refreshButton;
    }

}
