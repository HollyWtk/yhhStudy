package study.skr.yhh.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class JobXmlUtil {

    private JobXmlUtil() {

    }

    public static String setSetScriptAndGetString(String fileName, String scriptFile) {
        try (InputStream ins = FileUtil.getClassAbsolutePath(fileName)) {
            if (ins == null) {
                throw new RuntimeException("没有在classpath下找到对应文件：" + fileName);
            }
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(ins);
            Node element = doc.getElementsByTagName("script").item(0);
            element.setTextContent(asStringFrom(scriptFile));
            DOMSource source = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            Result result = new StreamResult(writer);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            return writer.getBuffer().toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static String asStringFrom(String filePath) throws Exception {
        try (InputStream ins = FileUtil.getClassAbsolutePath(filePath)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            throw e;
        }
    }
}
