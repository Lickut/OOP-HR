package com.epam.xml.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.epam.hr.candidates.Candidate;
import com.epam.hr.candidates.Grade;
import com.epam.hr.candidates.OpenPosition;
import com.epam.xml.handlers.CandidatesXMLHandler;
import com.epam.xml.handlers.OpenPositionsXMLHandler;

public class XMLParser {
	public static int  parseRequiredPositions(
			String path, Map<Grade, OpenPosition> requiredPositions) throws SAXException, ParserConfigurationException,
			IOException {
		SAXParserFactory parserFactor = SAXParserFactory.newInstance();
		SAXParser parser = parserFactor.newSAXParser();
		OpenPositionsXMLHandler neededPositionsHandler = new OpenPositionsXMLHandler(requiredPositions);
		parser.parse(new FileInputStream(new File(path)),
				neededPositionsHandler);
		return neededPositionsHandler.getTotalAmountOfPositions();
	}

	public static List<Candidate> parseCandidates(String path,
			Set<Grade> positions) throws SAXException,
			ParserConfigurationException, IOException {
		SAXParserFactory parserFactor = SAXParserFactory.newInstance();
		SAXParser parser = parserFactor.newSAXParser();
		CandidatesXMLHandler jobStandardsHandler = new CandidatesXMLHandler(
				positions);
		parser.parse(new FileInputStream(new File(path)), jobStandardsHandler);
		return jobStandardsHandler.getJobSeekers();
	}
}
