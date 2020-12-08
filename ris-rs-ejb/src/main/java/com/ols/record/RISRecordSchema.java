package com.ols.record;


import com.ols.z3950.record.Record;
import org.w3c.dom.Document;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringWriter;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;


@Singleton(name = "ris")
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionManagement(TransactionManagementType.BEAN)
@Remote(RecordSchema.class)
@EJB(name = "java:global/ruslan/recordSchema/bibtex", beanInterface = RecordSchema.class)
public class RISRecordSchema implements RecordSchema {
    private static final String URI = "bibtex";
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
    public String getURI() {
        return URI;
    }

    @Override
    public String toString(Record record, String encoding) throws Exception {
        return ruslanRecordSchema.toString(record, encoding);
    }

   //@Override
   //public Document toDocument(Record record, String encoding) throws Exception {
   //    return transformSchema(ruslanRecordSchema.toDocument(record, encoding));
   //}

    //@Override
    public String transformSchema(Document src) throws TransformerException {
        Transformer transformer = templates.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

        //getting XML (String)
        StreamResult result = new StreamResult(new StringWriter());
        //DOMResult result = new DOMResult();
        transformer.transform(new DOMSource(src), result);
        //return (Document) result.getNode();

        PatternFactory patternFactory = new PatternFactory(result.getWriter().toString());
        PatternFactory.createPatternsForType();
        Map<String, String> patterns = patternFactory.getPatternForTags();
        RISBuilder builder = new RISBuilder(patterns);
        return builder.build();
    }



    @Override
    public Record normalize(Record record, String encoding) {
        return ruslanRecordSchema.normalize(record, encoding);
    }

    @Override
    public Record denormalize(Record record, String encoding) {
        return ruslanRecordSchema.denormalize(record, encoding);	}


}
