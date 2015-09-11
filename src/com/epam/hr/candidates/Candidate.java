package com.epam.hr.candidates;

import static com.epam.xml.tags.CandidateTagNames.*;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.epam.xml.io.XMLWriteble;

public class Candidate implements XMLWriteble{

	protected String fullName;
	protected Grade positionTitle;
	protected Set<Skill> skills;
	protected int yearOfStudy;
	protected int experience;

	public Candidate() {
		yearOfStudy = Integer.MAX_VALUE; // MAX_VALUE means "not a student"
		skills = EnumSet.noneOf(Skill.class);
	}

	public Candidate(Candidate j) {
		this.fullName = j.fullName;
		this.positionTitle = j.positionTitle;
		this.skills = j.skills;
		this.yearOfStudy = j.yearOfStudy;
		this.experience = j.experience;
	}

	public boolean isStudent() {
		return (yearOfStudy != Integer.MAX_VALUE);
	}

	public boolean fitsModelCandidate(Candidate a) {
		return (a.getPositionLevel() == positionTitle
				&& yearOfStudy >= a.getYearOfStudy()
				&& experience >= a.getExperience() && skills.containsAll(a
				.getSkills()));
	}

	public String weakPointsCompareTo(Candidate a) {// return "" if there are no
		String refusalCause = ""; // weak points
		if (!fitsModelCandidate(a)) {
			List<String> causes = new ArrayList<>();
			if (yearOfStudy < a.getYearOfStudy())
				if (a.getYearOfStudy() == Integer.MAX_VALUE)
					causes.add("students are not allowed for this position");
				else
					causes.add("got:" + yearOfStudy + " yearOfStudy, needed:"
							+ a.getYearOfStudy());
			if (experience < a.getExperience())
				causes.add("got:" + experience + " experience, needed:"
						+ a.getExperience());
			if (!skills.containsAll(a.getSkills())) {
				Set<Skill> temp = EnumSet.copyOf(a.getSkills());
				temp.removeAll(skills);
				for (Skill s : temp)
					causes.add("no " + s + " skill");
			}
			refusalCause += causes.toString();
		}
		return refusalCause;

	}

	@Override
	public String toString() {
		String info = "fullName=" + fullName + ", positionLevel="
				+ positionTitle + ", skills=" + skills;
		if (this.isStudent())
			info += ", yearOfStudy=" + yearOfStudy;
		return info += ", experience=" + experience;
	}

	public String toStringAsRequirements() {
		String info = "positionLevel=" + positionTitle + ", minSkills="
				+ skills;
		if (this.isStudent())
			info += ", minYearOfStudy=" + yearOfStudy;
		return info += ", minExperience=" + experience;
	}

	public void writeToXml(XMLStreamWriter writer) throws XMLStreamException {
		writeBeginCandidateToXml(writer);
		writeEndCandidateXML(writer);
	}
	
	protected void writeBeginCandidateToXml(XMLStreamWriter writer) throws XMLStreamException {
		writer.writeStartElement(candidateTag);
		writer.writeStartElement(fullNameTag);
		writer.writeCharacters(fullName);
		writer.writeEndElement();

		writer.writeStartElement(positionTitleTag);
		writer.writeCharacters(positionTitle.toString());
		writer.writeEndElement();

		writer.writeStartElement(experienceTag);
		writer.writeCharacters(experience + "");
		writer.writeEndElement();
		
		if (yearOfStudy!=Integer.MAX_VALUE){
		writer.writeStartElement(yearOfStudyTag);
		writer.writeCharacters(yearOfStudy + "");
		writer.writeEndElement();
		}

		writer.writeStartElement(skillsTag);
		for (Skill s : skills) {
			writer.writeStartElement(skillTag);
			writer.writeCharacters(s.toString());
			writer.writeEndElement();
		}
		writer.writeEndElement();
	}

	protected void writeEndCandidateXML(XMLStreamWriter writer)
			throws XMLStreamException {
		writer.writeEndElement();
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getYearOfStudy() {
		return yearOfStudy;
	}

	public void setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public Grade getPositionLevel() {
		return positionTitle;
	}

	public void setPositionLevel(Grade positionLevel) {
		this.positionTitle = positionLevel;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

}
