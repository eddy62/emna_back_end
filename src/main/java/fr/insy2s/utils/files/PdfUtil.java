package fr.insy2s.utils.files;

import fr.insy2s.security.SecurityUtils;
import fr.insy2s.web.rest.vm.ContratPdfVm;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PdfUtil {

    private static final Logger LOG = LoggerFactory.getLogger(PdfUtil.class);

    private static final String URL_TEMPLATE_MACHIN="/templates/pdf/machin/machin.jasper";
    private static final String URL_TEMPLATE_CONTRAT="/templates/Pdf/Contrat/TemplateContrat.jasper";

    static {
        LOG.info("ELM static pdfUtils utility init...");
    }
/*

    */
/**
     * Methode permettant de générer un PDF Tache Hebdomadaire d'un Stagiaire à partir d'un objet WrapperPdfProgrammeHebdoTravail
     *
     * @param wrapper
     * @return PDF en byte array
     * @throws JRException
     * @author Peter Mollet
     *//*

    public static byte[] generateTacheHebdoStagiaireAsBytes(WrapperClientFournisseur wrapper) throws JRException {
        String userLogin = SecurityUtils.getCurrentUserLogin().get();
        Map<String, Object> params = new HashMap<>();
        params.put("user", userLogin);
        params.put("imageHeader", ResourcesUtil.BO_OPER_PREC_HEADER);
        byte[] bytes = PdfUtil.generatePdfReportAsBytes(
                URL_TEMPLATE_MACHIN,
                params,
                Arrays.asList(wrapper)
        );
        return bytes;
    }
*/
public static byte[] generatePDFContrat(ContratPdfVm wrapper) throws JRException {
    String userLogin = SecurityUtils.getCurrentUserLogin().get();
    Map<String, Object> params = new HashMap<>();
    params.put("user", userLogin);
    byte[] bytes = PdfUtil.generatePdfReportAsBytes(
        URL_TEMPLATE_CONTRAT,
        params,
        Arrays.asList(wrapper)
    );
    return bytes;
}

    /**
     * Methode generique permettant de charger un template pdf jaspersoft, et de lui donner les params et données dont il a besoin
     *
     * @param templateUrl : url du template que l'on veut utiliser
     * @param params : parametres dont le template a besoin, tel que des images/logo
     * @param objects : les données que l'on veut afficher dans le pdf (ex: les données de pointage venant de la BDD permettant de générer un pdf cra)
     * 					Peut être null, si le pdf n'a pas besoin de données venant de la BDD
     * @return le pdf en bytes array
     * @throws JRException
     * @author Peter Mollet
     */
    private static byte[] generatePdfReportAsBytes(String templateUrl, Map<String, Object> params, List<Object> objects) throws JRException {
        InputStream templateStream = PdfUtil.class.getResourceAsStream(templateUrl);
        JasperPrint jasperPrint;
        if(objects != null){
            System.err.println("--------------------------------------------------------------");
            System.err.println("templatesStream :");
            System.err.println(templateStream);
            System.err.println("params :");
            System.err.println(params);
            System.err.println("objects :");
            System.err.println(objects);
            System.err.println(new JRBeanCollectionDataSource(objects));
            System.err.println("--------------------------------------------------------------");
            jasperPrint = JasperFillManager.fillReport(templateStream, params, new JRBeanCollectionDataSource(objects));
        }
        else
            jasperPrint = JasperFillManager.fillReport(templateStream, params);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }


}
