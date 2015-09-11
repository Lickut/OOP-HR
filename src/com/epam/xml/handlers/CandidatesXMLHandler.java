package com.epam.xml.handlers;

import java.util.*;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.epam.hr.candidates.Candidate;
import com.epam.hr.candidates.Grade;
import com.epam.hr.candidates.Skill;

import static com.epam.xml.tags.CandidateTagNames.*;

public class CandidatesXMLHandler extends DefaultHandler {
	private List<Candidate> jobSeekers = new ArrayList<>();
	private Candidate seeker = null;
	private String content = null;
	private Set<Grade> requiredPositions;
	private boolean currentPositionRequired;

	public CandidatesXMLHandler(Set<Grade> requiredPositions) {
		super();
		this.requiredPositions = requiredPositions;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		switch (qName) {
		case candidateTag:
			seeker = new Candidate();
			currentPositionRequired = true;
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equals("positionLevel")) {
			if (!requiredPositions.contains(Enum.valueOf(Grade.class, content)))
				currentPositionRequired = false;
			else
				seeker.setPositionLevel(Enum.valueOf(Grade.class, content));
		} else if (currentPositionRequired) {
			switch (qName) {
			case candidateTag:
				jobSeekers.add(seeker);
				break;
			case positionTitleTag:
				seeker.setPositionLevel(Enum.valueOf(Grade.class, content));
			case fullNameTag:
				seeker.setFullName(content);
				break;
			case experienceTag:
				seeker.setExperience(Integer.parseInt(content));
				break;
			case yearOfStudyTag:
				seeker.setYearOfStudy(Integer.parseInt(content));
				break;
			case skillTag:
				seeker.getSkills().add(Enum.valueOf(Skill.class, content));
				break;
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		content = String.copyValueOf(ch, start, length).trim();
	}

	public List<Candidate> getJobSeekers() {
		return jobSeekers;
	}

}
