package com.epam.xml.handlers;

import java.util.*;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.epam.hr.candidates.Grade;
import com.epam.hr.candidates.OpenPosition;

import static com.epam.xml.tags.CandidateTagNames.*;
import static com.epam.xml.tags.OpenPositionsTagNames.*;

public class OpenPositionsXMLHandler extends DefaultHandler {
	private Map<Grade, OpenPosition> requiredPositions;
	private OpenPosition position = null;
	private String content = null;
	private int totalAmountOfPositions;

	public OpenPositionsXMLHandler(Map<Grade, OpenPosition> requiredPositions){
		requiredPositions.clear();
		this.requiredPositions = requiredPositions;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		switch (qName) {
		case positionTag:
			position = new OpenPosition();
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
			switch (qName) {
			case positionTag:
				requiredPositions.put(position.getPositionLevel(), position);
				break;
			case positionTitleTag:
				position.setPositionLevel(Enum.valueOf(Grade.class, content));
				break;
			case priorityTag:
				position.setPriority(Integer.parseInt(content));
				break;
			case amountTag:
				position.setAmount(Integer.parseInt(content));
				break;
			case totalAmountTag:
				totalAmountOfPositions = Integer.parseInt(content);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		content = String.copyValueOf(ch, start, length).trim();
	}

//	public Map<Grade, OpenPosition> getRequiredPositions() {
//		return requiredPositions;
//	}

	public int getTotalAmountOfPositions() {
		return totalAmountOfPositions;
	}
	
}