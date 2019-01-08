package com.flowingcode;

import java.time.LocalDate;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route("")
public class DemoView extends Div {

    public DemoView() {
        add(new Timeline(new Item(1,LocalDate.now(), LocalDate.now().plusDays(2), "Task 1")));
    }
    
}
