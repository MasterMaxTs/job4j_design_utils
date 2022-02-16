package ru.job4j.ood.srp.reports;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

public class XmlReport extends ReportEngine {

    public XmlReport(Store store) {
        super(store);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String xml = "";
        List<Employee> employees = this.getStore().findBy(filter);
        try {
            JAXBContext context = JAXBContext.newInstance(Pair.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            try (StringWriter sw = new StringWriter()) {
                marshaller.marshal(new Pair(employees), sw);
                xml = sw.getBuffer().toString();
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return xml;
    }
}
