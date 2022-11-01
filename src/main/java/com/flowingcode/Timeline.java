package com.flowingcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.shared.Registration;

@SuppressWarnings("serial")
@StyleSheet("/webjars/vis/4.21.0/dist/vis-timeline-graph2d.min.css")
public class Timeline extends Div {

	
	private List<Item> items;

	public Timeline(Item ... items) {
    	setId("visualization");
    	this.items = Arrays.asList(items);
    	String initFunction = createInitFunction();
		UI.getCurrent().getPage().executeJs(initFunction , this);
		UI.getCurrent().getPage().addJavaScript("/webjars/vis/4.21.0/dist/vis.js");
	}
	
	public void focus(Item item) {
		UI.getCurrent().getPage().executeJs("$0.timeline.focus($1)", this,item.getId());
	}

	@SuppressWarnings("serial")
	@DomEvent("click")
	static public class ItemClickEvent extends ComponentEvent<Timeline> {
		private Item item;
	    public ItemClickEvent(Timeline source, boolean fromClient, @EventData("element.timeline.getEventProperties(event).item") int id) {
	        super(source, fromClient);
	        source.items.stream().filter(item->item.getId().equals(id)).findFirst().ifPresent(founditem->this.item=founditem);
	    }
		public Item getItem() {
			return item;
		}
	}

	public Registration addItemClickListener(ComponentEventListener<ItemClickEvent> listener) {
		return addListener(ItemClickEvent.class, listener);
	}

	private String createInitFunction() {
		String function = "  // Create a DataSet (allows two way data-binding)\n" + 
				"  var items = new vis.DataSet([\n" + 
				items.stream().map(item->item.toJSON()).collect(Collectors.joining(",")) +
				"  ]);\n" + 
				"\n" + 
				"  // Configuration for the Timeline\n" + 
				"  var options = {};\n" + 
				"\n" + 
				"  // Create a Timeline\n" + 
				"  var timeline = new vis.Timeline($0, items, options);" + 
				"  $0.timeline = timeline";
		return function;
	}

}
