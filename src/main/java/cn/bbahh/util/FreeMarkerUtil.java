package cn.bbahh.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class FreeMarkerUtil {

    public static void generateWord(Map<String, Object> dataMap, String templateName, OutputStream outputStream) throws Exception {
        Configuration configuration = new Configuration(new Version("2.3.28"));
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(FreeMarkerUtil.class, "/template");
        // 设置FreeMarker生成Word文档所需要的模板
        Template tem = configuration.getTemplate(templateName, "UTF-8");
        Writer out = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        tem.process(dataMap, out);
        out.flush();
        out.close();
    }

    public static String generateHtmlToPdf(Map<String, Object> dataMap, String templateName) throws IOException, TemplateException {
        Writer out =new StringWriter();
        Configuration configuration = new Configuration(new Version("2.3.28"));
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(FreeMarkerUtil.class, "/template");
        Template template = configuration.getTemplate(templateName, "UTF-8");
        template.process(dataMap, out);
        return out.toString();
    }
}
