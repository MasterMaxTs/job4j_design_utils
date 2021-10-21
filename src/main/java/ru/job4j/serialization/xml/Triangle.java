package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "triangle")
@XmlAccessorType(XmlAccessType.FIELD)
public class Triangle {

    @XmlAttribute
    private boolean isosceles;

    @XmlAttribute
    private float height;

    @XmlAttribute
    private String triangleType;

    @XmlElementWrapper
    @XmlElement(name = "point")
    private Point[] points = new Point[3];

    public Triangle() {

    }

    public Triangle(boolean isosceles, float height,
                    String triangleType, Point[] points) {
        this.isosceles = isosceles;
        this.height = height;
        this.triangleType = triangleType;
        this.points = points;
    }

    @Override
    public String toString() {
        return "Triangle{"
                + "isosceles=" + isosceles
                + ", height=" + height
                + ", triangleType='" + triangleType
                + '\''
                + ", points=" + Arrays.toString(points)
                + '}';
    }

    private Marshaller createMarshaller() throws JAXBException {
        /* Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(Triangle.class);
        /* Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();
        /* Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        return marshaller;
    }

    private Unmarshaller createUnMarshaller() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Triangle.class);
        /* Создаем десериализатор */
        return context.createUnmarshaller();
    }

    private String convertToXml() throws JAXBException {
        String xml = "";
        try (StringWriter stringWriter = new StringWriter()) {
            /* Сериализуем объект и записываем в буфер */
            createMarshaller().marshal(this, stringWriter);
            /* Достаем xml из буфера */
            xml = stringWriter.getBuffer().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xml;
    }

    private void convertToXmlFile(String filePath) throws JAXBException {
        File out = new File(filePath);
        /* Сериализуем объект и записываем в выходной файл */
        createMarshaller().marshal(this, out);
        System.out.println("XML has been written in file "
                + out.getPath());
    }

    private Triangle convertXmlToObject(String string) throws JAXBException {
        Triangle result = null;
        /* Проверяем, является ли входной параметр xml-строкой, а не filePath */
        if (string.startsWith("<?xml") || string.contains("</")) {
            try (StringReader stringReader = new StringReader(string)) {
                /* Десериализуем объект */
                return (Triangle) createUnMarshaller().unmarshal(stringReader);
            }
        }
        File in = new File(string);
        /* Десериализуем объект */
        result = (Triangle) createUnMarshaller().unmarshal(in);
        return result;
    }

    public static void main(String[] args) throws IOException, JAXBException {
        Triangle triangle = new Triangle(
                false,
                3,
                "acute-angled",
                new Point[]{new Point(0, 0), new Point(1, 0), new Point(3, 0)}
        );
        System.out.println(triangle);
        System.out.println(triangle.convertToXml());
        String filePathOut = "./src/main/java/ru/job4j/serialization/xml"
                + "/TriangleToXML.xml";
        triangle.convertToXmlFile(filePathOut);
        String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                        + "<!-- XML данные объекта треугольник -->\n"
                        + "<triangle isosceles=\"false\" height=\"3\" triangleType=\"acute-angled\">\n"
                        + "    <points>\n"
                        + "    <point x=\"1\" y=\"1\"/>\n"
                        + "    <point x=\"3\" y=\"2\"/>\n"
                        + "    <point x=\"0\" y=\"4\"/>\n"
                        + "    </points>\n"
                        + "</triangle>";
        System.out.println(triangle.convertXmlToObject(xml));
        System.out.println(triangle.convertXmlToObject(filePathOut));
    }
}
