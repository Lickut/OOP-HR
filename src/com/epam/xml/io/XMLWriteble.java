package com.epam.xml.io;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public interface XMLWriteble {
	public void writeToXml(XMLStreamWriter writer) throws XMLStreamException;
}
