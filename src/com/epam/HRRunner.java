package com.epam;

import static com.epam.hr.filtering.FilterManager.applyHRFilter;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.SAXException;

public class HRRunner {

	public static void main(String[] args) {
		try{
		applyHRFilter("resources\\xml\\openPositions1.xml",
				"resources\\xml\\candidates.xml",
				"resources\\xml\\positionRequirements.xml",
				"resources\\xml\\outcome\\approvedCandidates1.xml",
				"resources\\xml\\outcome\\processedCandidates1.xml");

		applyHRFilter("resources\\xml\\openPositions2.xml",
				"resources\\xml\\candidates.xml",
				"resources\\xml\\positionRequirements.xml",
				"resources\\xml\\outcome\\approvedCandidates2.xml",
				"resources\\xml\\outcome\\processedCandidates2.xml");

		applyHRFilter("resources\\xml\\openPositions3.xml",
				"resources\\xml\\candidates.xml",
				"resources\\xml\\positionRequirements.xml",
				"resources\\xml\\outcome\\approvedCandidates3.xml",
				"resources\\xml\\outcome\\processedCandidates3.xml");
		}
		catch(IOException|SAXException|XMLStreamException|ParserConfigurationException e){
			e.printStackTrace();
		}
	}
}
