package com.epam.hr.candidates;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import static com.epam.xml.tags.CandidateTagNames.*;

public class ProcessedCandidate extends Candidate {

	public static final String DEFAULT_REFUSE = "There is no free position any more";
	private boolean fit;
	private String refusalCause;

	public ProcessedCandidate(Candidate candidate) {
		super(candidate);
	}

	public void accept() {
		fit = true;
		refusalCause = "";
	}

	public void refuse(String cause) {
		fit = false;
		refusalCause = cause;
	}

	public boolean isFit() {
		return fit;
	}

	public String getRefusalCause() {
		return refusalCause;
	}

	@Override
	public String toString() {
		String info =  super.toString()+", fit=" + fit;
		if (!fit)
			info += ", refusalCause="
						+ refusalCause;
		return info;
	}
	
	@Override
	public void writeToXml(XMLStreamWriter writer) throws XMLStreamException {
		super.writeBeginCandidateToXml(writer);
		
		writer.writeStartElement(fitTag);
		writer.writeCharacters(fit+"");
		writer.writeEndElement();

		if (!fit){
			writer.writeStartElement(refusalCauseTag);
			writer.writeCharacters(refusalCause);
			writer.writeEndElement();
		}
		
		super.writeEndCandidateXML(writer);
	}
}
