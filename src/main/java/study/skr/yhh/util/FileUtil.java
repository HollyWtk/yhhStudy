package study.skr.yhh.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil
{

    private static final String SUFFIX_PATTERN = "\\.[^\\.]+";
    
    public static String getSuffix(String fileName)
    {
        //May be error implements,TODO
        Pattern pattern = Pattern.compile(SUFFIX_PATTERN);
        Matcher matcher = pattern.matcher(fileName);
        String g = "";
        while (matcher.find())
        {
            g = matcher.group();
        }
        if(g.length() > 0) {
            return g.substring(1, g.length());
        }
        return null;
    }
    
    public static InputStream getClassAbsolutePath(String fileName)
    {
        InputStream ins = FileUtil.class.getClassLoader().getResourceAsStream(fileName);
        return ins;
    }

    public static String asStringFrom(String fileName) throws Exception {
        try (InputStream ins = getClassAbsolutePath(fileName); StringWriter sw = new StringWriter();) {
            BufferedReader br = new BufferedReader(new InputStreamReader(ins, Charset.forName("utf-8")));
            char[] carr = new char[1024];
            int len;
            while ((len = br.read(carr)) > 0) {
                sw.write(carr, 0, len);
            }
            return sw.getBuffer().toString();
        } catch (Exception e) {
            throw e;
        }
    }
}
