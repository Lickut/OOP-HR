package com.epam.xml.io;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.sun.xml.txw2.output.IndentingXMLStreamWriter;

public class XMLWriter {
	public static <T extends XMLWriteble> void writeToXML(String filePath,
			Collection<T> candidates, String rootTag)
			throws XMLStreamException, IOException {
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		try {

			XMLStreamWriter writer = new IndentingXMLStreamWriter(
					factory.createXMLStreamWriter(new FileWriter(filePath)));
			try {
				writer.writeStartDocument();
				writer.writeStartElement(rootTag);
				for (T c : candidates) {
					c.writeToXml(writer);
				}
				writer.writeEndElement();
				writer.writeEndDocument();
				writer.flush();
			} finally {
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
