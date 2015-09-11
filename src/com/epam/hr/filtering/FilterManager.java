package com.epam.hr.filtering;

import static com.epam.xml.io.XMLParser.parseRequiredPositions;
import static com.epam.xml.tags.CandidateTagNames.candidateRootTag;

import java.io.IOException;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.SAXException;

import com.epam.hr.candidates.ProcessedCandidate;
import com.epam.hr.candidates.Grade;
import com.epam.hr.candidates.OpenPosition;
import com.epam.xml.io.XMLWriter;

public class FilterManager {
	public static void applyHRFilter(String openPositionsPath,
			String candidatesPath, String positionRequirementsPath,
			String approvedCandidatesPath, String filteredCandidatesPath) throws ParserConfigurationException,IOException, SAXException, XMLStreamException {
		int amountOfNeededEmployees;
		Map<Grade, OpenPosition> requiredPositions = new EnumMap<>(Grade.class);

		amountOfNeededEmployees = parseRequiredPositions(openPositionsPath,
				requiredPositions);
		HRFilter hrFilter = new HRFilter(requiredPositions,
				amountOfNeededEmployees, candidatesPath,
				positionRequirementsPath);

		List<ProcessedCandidate> processedCandidates = hrFilter.filter();
		List<ProcessedCandidate> approvedCandidates = hrFilter
				.getAcceptedCandidates();

		XMLWriter.writeToXML(approvedCandidatesPath, approvedCandidates,
				candidateRootTag);
		XMLWriter.writeToXML(filteredCandidatesPath, processedCandidates,
				candidateRootTag);
	}
}
