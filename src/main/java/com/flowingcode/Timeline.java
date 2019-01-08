package com.flowingcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;

@JavaScript("/webjars/vis/4.21.0/dist/vis.js")
@StyleSheet("/webjars/vis/4.21.0/dist/vis-timeline-graph2d.min.css")
public class Timeline extends Div {

	
	private List<Item> items;

	public Timeline(Item ... items) {
    	setId("visualization");
    	this.items = Arrays.asList(items);
    	String initFunction = createInitFunction();
		UI.getCurrent().getPage().executeJavaScript(initFunction , this);	
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
