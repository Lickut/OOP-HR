package com.epam.hr.filtering;

import static com.epam.xml.io.XMLParser.parseCandidates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.epam.hr.candidates.Candidate;
import com.epam.hr.candidates.ProcessedCandidate;
import com.epam.hr.candidates.Grade;
import com.epam.hr.candidates.OpenPosition;
import com.epam.hr.candidates.PositionRequirement;

public class HRFilter{

	private String candidatesPath;
	private String requirementsPath;
	private Map<Grade, OpenPosition> neededPositions;
	private SortedSet<PositionRequirement> requirementsForPositions;
	private Set<Grade> positions;
	private List<Candidate> candidates;
	private List<ProcessedCandidate> filteredCandidates;
	private int totalRequiredAmount;
	private int totalFoundAmount = 0;
	private int positionMaxAmount;
	private int positionFoundAmount;

	public HRFilter(Map<Grade, OpenPosition> neededPositions,
			int totalRequiredAmount, String candidatesPath,
			String requirementsPath) {
		this.neededPositions = neededPositions;
		this.candidatesPath = candidatesPath;
		this.requirementsPath = requirementsPath;
		this.totalRequiredAmount = totalRequiredAmount;
		requirementsForPositions = new TreeSet<>();
		positions = neededPositions.keySet();
	}

	public List<ProcessedCandidate> filter() throws SAXException,
			ParserConfigurationException, IOException {
		calulateStandardsForPositions();
		prepareCandidates();
		processAllCandidates();
		return filteredCandidates;
	}

	public List<ProcessedCandidate> getAcceptedCandidates() {
		List<ProcessedCandidate> acceptedCandidates = new ArrayList<>();
		for (ProcessedCandidate c : filteredCandidates)
			if (c.isFit())
				acceptedCandidates.add(c);
		return acceptedCandidates;
	}

	private void calulateStandardsForPositions() throws SAXException,
			ParserConfigurationException, IOException {
		List<Candidate> requirementsForAllPositions = parseCandidates(
				requirementsPath, positions);
		for (Candidate seeker : requirementsForAllPositions) {
			OpenPosition position = neededPositions.get(seeker
					.getPositionLevel());
			requirementsForPositions.add(new PositionRequirement(seeker,
					position.getPriority(), position.getAmount()));
		}
	}

	private void prepareCandidates() throws SAXException,
			ParserConfigurationException, IOException {
		candidates = parseCandidates(candidatesPath, positions);
	}

	private void processAllCandidates() {
		Candidate modelCandidate;
		filteredCandidates = new ArrayList<>();
		List<Candidate> positionCandidates;
		Iterator<PositionRequirement> reqIter = requirementsForPositions
				.iterator();

		while (reqIter.hasNext()) {
			PositionRequirement requirement = reqIter.next();
			positionMaxAmount = requirement.getAmount();
			positionFoundAmount = 0;
			modelCandidate = requirement.getModelCandidate();
			Grade jobPosition = modelCandidate.getPositionLevel();
			positionCandidates = candidates.stream()
					.filter(p -> p.getPositionLevel() == jobPosition)
					.collect(Collectors.toList());
			for (Candidate seeker : positionCandidates) {
				processOneCandidate(seeker, modelCandidate);
			}
		}

		positionCandidates = candidates.stream()
				.filter(p -> !(positions.contains(p.getPositionLevel())))
				.collect(Collectors.toList());
		for (Candidate seeker : positionCandidates) {
			defaultCandidateRefuse(seeker);
		}
	}

	private void processOneCandidate(Candidate seeker, Candidate modelCandidate) {
		ProcessedCandidate candidate = new ProcessedCandidate(seeker);
		if (seeker.fitsModelCandidate(modelCandidate)
				&& totalFoundAmount < totalRequiredAmount
				&& positionFoundAmount < positionMaxAmount) {
			candidate.accept();
			totalFoundAmount++;
			positionFoundAmount++;
		} else {
			if (seeker.fitsModelCandidate(modelCandidate))
				candidate.refuse(ProcessedCandidate.DEFAULT_REFUSE);
			else
				candidate.refuse(seeker.weakPointsCompareTo(modelCandidate));
		}
		filteredCandidates.add(candidate);
	}

	private void defaultCandidateRefuse(Candidate seeker) {
		ProcessedCandidate candidate = new ProcessedCandidate(seeker);
		candidate.refuse(ProcessedCandidate.DEFAULT_REFUSE);
		filteredCandidates.add(candidate);
	}

	public Map<Grade, OpenPosition> getRequired() {
		return neededPositions;
	}

	public List<Candidate> getCandidates() {
		return candidates;
	}

	public SortedSet<PositionRequirement> getRequirements() {
		return requirementsForPositions;
	}

	public Set<Grade> getPositions() {
		return positions;
	}
}