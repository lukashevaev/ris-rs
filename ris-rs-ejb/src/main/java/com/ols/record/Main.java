package com.ols.record;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.naming.NamingException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


public class Main {

    public static void main(String[] args) throws TransformerException, IOException, SAXException, ParserConfigurationException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, NamingException {
        //preparing rusmarc.xml for reading
        File file = new File("C:\\Users\\Daniel\\Desktop\\ris-rs\\ris-rs-ejb\\src\\main\\resources\\RUSMARC.xml");
        FileReader fileReader = new FileReader(file);
        InputSource inputSourceFile = new InputSource(fileReader);

        //creating document for transformed(by xsl) rusmarc.xml
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
        Document document = docBuilder.parse(inputSourceFile);

        RISRecordSchema risRecordSchema = new RISRecordSchema();
        RISRecordSchema.class.getDeclaredMethod("init", new Class[]{}).invoke(risRecordSchema);
        System.out.println(risRecordSchema.transformSchema(document));
    }
}
