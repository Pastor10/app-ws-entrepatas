package app.ws.entrepatas.service.impl;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.StringWriter;
import java.util.Map;

@Component
@AllArgsConstructor
public class FreemakerTemplateService {

    private final Configuration freemarkerConfiguration;

    public String buildTemplatePreset(Map<String, Object> model, String template) {
        StringBuffer content = new StringBuffer();
        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfiguration.getTemplate(template), model));
            return content.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String buildTemplate(Map<String, Object> model, String template) {
        StringWriter writer = new StringWriter();
        try {

            Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
            StringTemplateLoader loader = new StringTemplateLoader();
            loader.putTemplate("template",template);
            cfg.setTemplateLoader(loader);
            cfg.setDefaultEncoding("UTF-8");

            Template t = cfg.getTemplate("template");

            t.process(model,writer);
            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
