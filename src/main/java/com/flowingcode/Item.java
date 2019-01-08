
package com.flowingcode;

import java.time.LocalDate;

import elemental.json.Json;
import elemental.json.JsonObject;

public class Item {

	private Integer id;
	private LocalDate start;
	private LocalDate end;
	private String content;

	public Item(Integer id, LocalDate start, LocalDate end, String content) {
		super();
		this.setId(id);
		this.setStart(start);
		this.setEnd(end);
		this.setContent(content);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	protected String toJSON() {
		JsonObject js = Json.createObject();
		if (getId() != null) js.put("id", getId());
		if (getContent() != null) js.put("content", getContent());
		if (getStart() != null) js.put("start", getStart().toString());
		if (getEnd() != null) js.put("end", getEnd().toString());

		return js.toJson();
	}

}
