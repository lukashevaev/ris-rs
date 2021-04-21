package com.ols.record;


import com.ols.z3950.record.Record;
import org.w3c.dom.Document;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;


@Singleton(name = "ris")
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionManagement(TransactionManagementType.BEAN)
@Remote(BeanSchema.class)
@EJB(name = "java:global/ruslan/recordSchema/ris", beanInterface = BeanSchema.class)
public class RISRecordSchema implements BeanSchema {
    private static final Logger log = Logger.getLogger(RISRecordSchema.class
            .getName());
    private static final TransformerFactory transformerFactory = TransformerFactory
            .newInstance();
    private static Templates templates;

    @EJB(lookup = "java:global/ruslan/recordSchema/ruslan", beanInterface = RecordSchema.class)
    private RecordSchema ruslanRecordSchema;

    @PostConstruct
    public void init() {
        log.fine("Preparing XSL templates");
        log.fine(Objects.requireNonNull(getClass().getClassLoader().getResource("RUSMARC2RIS.xsl")).toString());
        try {
            templates = transformerFactory.newTemplates(new StreamSource(
                    getClass().getClassLoader().getResourceAsStream(
                            "RUSMARC2RIS.xsl")));

        } catch (TransformerConfigurationException e) {
            log.severe("Unable to initialise templates: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public byte[] getTransformedRecord(byte[] record, String encoding) throws Exception {
        /*Document src = ruslanRecordSchema.toDocument(record, encoding);
        RISBuilder builder = getBuilder(src);
        return builder.buildRIS().getBytes(encoding);*/
        return null;

    }

    @Override
    public String getMimeType() {
        return "text/plain";
    }

    RISBuilder getBuilder(Document src) throws TransformerException {
        Transformer transformer = templates.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        DOMResult result = new DOMResult();
        transformer.transform(new DOMSource(src), result);
        //получаем поля из схемы для составления формата
        Map<String, String> fields = XmlParser.parse((Document) result.getNode());
        return new RISBuilder(fields);
    }

    @Override
    public String toString(Record record, String encoding) throws Exception {
        return ruslanRecordSchema.toString(record, encoding);
    }

    @Override
    public Record normalize(Record record, String encoding) {
        return ruslanRecordSchema.normalize(record, encoding);
    }

    @Override
    public Record denormalize(Record record, String encoding) {
        return ruslanRecordSchema.denormalize(record, encoding);	}


}
