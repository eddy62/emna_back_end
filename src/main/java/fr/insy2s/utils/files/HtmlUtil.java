package fr.insy2s.utils.files;

import fr.insy2s.security.SecurityUtils;
import fr.insy2s.utils.wrapper.WrapperPdfDpae;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HtmlUtil {

    private static final Logger LOG = LoggerFactory.getLogger(HtmlUtil.class);

    private static final String URL_TEMPLATE_DPAE = "/templates/pdf/dpae/TemplateDpae.jasper";


    static {
        LOG.info("EMNA static htmlUtils utility init...");
    }

    /**
     * Méthode permettant de générer un code html d'une DPAE à partir d'un objet WrapperPdfDpae
     *
     * @param wrapper the WrapperPdfDpae to map in the the template TemplateDpae.jasper
     * @return a html code string based on the template TemplateDpae.jasper
     * @throws JRException the exception throwned
     * @author Peter Mollet adapted by Erik DUNAIS
     */
    public static String generateDpaeAsStringHtml(WrapperPdfDpae wrapper) throws JRException {
        String userLogin = SecurityUtils.getCurrentUserLogin().get();
        Map<String, Object> params = new HashMap<>();
        params.put("user", userLogin);

        return HtmlUtil.generateHtmlReportAsString(
                URL_TEMPLATE_DPAE,
                params,
                Arrays.asList(wrapper)
        );
    }
    /**
     * Methode générique permettant de charger un template jaspersoft, et de lui donner les params et données dont il a besoin
     *
     * @param templateUrl : url du template que l'on veut utiliser
     * @param params      : parametres dont le template a besoin, tel que des images/logo
     * @param objects     : les données que l'on veut afficher dans le html
     *                    Peut être null, si le html n'a pas besoin de données venant de la BDD
     * @return le html en String
     * @throws JRException the exception
     * @author Peter Mollet adapted by Erik DUNAIS
     */
    private static String generateHtmlReportAsString(String templateUrl, Map<String, Object> params, List<Object> objects) throws JRException {
        InputStream templateStream = HtmlUtil.class.getResourceAsStream(templateUrl);
        JasperPrint jasperPrint;
        String codeHtmlReportFile = "./fichiers/" + objects.get(0).toString() + ".html";
        if (!objects.isEmpty()) {
            jasperPrint = JasperFillManager.fillReport(templateStream, params, new JRBeanCollectionDataSource(objects));
        } else
            jasperPrint = JasperFillManager.fillReport(templateStream, params);
        JasperExportManager.exportReportToHtmlFile(jasperPrint,codeHtmlReportFile);
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(codeHtmlReportFile, StandardCharsets.UTF_8));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            in.close();
        } catch (IOException e) {
        }
        String dpaeHtmlCode = contentBuilder.toString();
        File file = new File(codeHtmlReportFile);
        file.delete();
        return dpaeHtmlCode;
    }
}
