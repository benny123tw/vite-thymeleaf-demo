package io.github.benny123tw.vitethymeleafdemo.processor;

import io.github.benny123tw.vitethymeleafdemo.config.ViteProperties;
import org.springframework.context.ApplicationContext;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

public class ViteEntryAttributeProcessor extends AbstractAttributeTagProcessor {

    private static final String ATTR_NAME = "entry";
    private static final int PRECEDENCE = 10000;

    private final ViteProperties viteProperties;

    public ViteEntryAttributeProcessor(final String dialectPrefix,  ApplicationContext applicationContext) {
        super(
                TemplateMode.HTML, // This processor will apply only to HTML mode
                dialectPrefix,     // Prefix to be applied to name for matching
                "script",              // No tag name: match any tag name
                false,             // No prefix to be applied to tag name
                ATTR_NAME,         // Name of the attribute that will be matched
                true,              // Apply dialect prefix to attribute name
                PRECEDENCE,        // Precedence (inside dialect's own precedence)
                true);             // Remove the matched attribute afterward
        this.viteProperties = applicationContext.getBean(ViteProperties.class);
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName, String attributeValue,
            IElementTagStructureHandler structureHandler) {
        final IEngineConfiguration configuration = context.getConfiguration();

        /*
         * Obtain the Thymeleaf Standard Expression parser
         */
        final IStandardExpressionParser parser =
                StandardExpressions.getExpressionParser(configuration);

        /*
         * Parse the attribute value as a Thymeleaf Standard Expression
         */
        final IStandardExpression expression =
                parser.parseExpression(context, attributeValue);

        /*
         * Execute the expression just parsed
         */
        final String entry = (String) expression.execute(context);

        boolean isDebug = viteProperties.isDebug();
        String manifestPath = viteProperties.getManifestPath();
        String localServerUrl = viteProperties.getLocalServerUrl();
        String resourcePath = viteProperties.getResourcePath();

        structureHandler.setAttribute("type", "module");
        if (isDebug) {
            structureHandler.setAttribute("src", localServerUrl + "/@vite/client");
        } else {
            structureHandler.setAttribute("src", resourcePath + "/assets/" + entry);
        }
    }

}
