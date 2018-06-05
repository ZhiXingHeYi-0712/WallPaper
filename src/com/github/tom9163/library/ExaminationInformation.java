package com.github.tom9163.library;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author tom9163
 * Get Examination from exam.xml
 */
public final class ExaminationInformation {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    private static final String XMLPOS = "src/res/exam.xml";

    public static List<Examination> getExaminations() {
        return getExaminations(XMLPOS);
    }

    /**
     * @return an instance of Examination
     */
    private static List<Examination> getExaminations(String fileName) {
        List<Examination> examinations = new ArrayList<>();
        try {
            DocumentBuilder domBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            File input = new File(fileName);
            Document doc = domBuilder.parse(input);

            Element root = doc.getDocumentElement();
            NodeList senior3 = root.getChildNodes();
            if (senior3 != null) {
                for (int i = 0, size = senior3.getLength(); i < size; i++) {
                    Node exam = senior3.item(i);
                    if (exam.getNodeType() == Node.ELEMENT_NODE) {
                        String name = exam.getAttributes().getNamedItem("name").getNodeValue();
                        String time = exam.getAttributes().getNamedItem("time").getNodeValue();
                        Examination addingExam = new Examination(name,time);
                        try {
                            // 不计过期的考试
                            if (new Date().compareTo(format.parse(addingExam.S_time)) <= 0) {
                                examinations.add(addingExam);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        Collections.sort(examinations);
        return examinations;
    }
}