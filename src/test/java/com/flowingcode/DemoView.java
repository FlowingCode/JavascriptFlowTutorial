package com.flowingcode;

import java.time.LocalDate;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;

@Route("")
public class DemoView extends Div {

    public DemoView() {
    	Item[] items = new Item[] {
    			new Item(1,LocalDate.now(), LocalDate.now().plusDays(2), "Task 1"),
    			new Item(2,LocalDate.now().minusDays(1), LocalDate.now().plusDays(1), "Task 2"),
    			new Item(3,LocalDate.now().plusDays(2), LocalDate.now().plusDays(3), "Task 3")};
    	Timeline tl = new Timeline(items);
    	tl.addItemClickListener(ev->Notification.show("Clicked on item: " + ev.getItem().getContent()));
    	tl.setWidth("100%");
        add(tl);
        Button b1 = new Button("Focus task 1");
        b1.addClickListener(ev->tl.focus(items[0]));
        Button b2 = new Button("Focus task 2");
        b2.addClickListener(ev->tl.focus(items[1]));
        Button b3 = new Button("Focus task 3");
        b3.addClickListener(ev->tl.focus(items[2]));
        add(b1,b2,b3);
    }
    
}
