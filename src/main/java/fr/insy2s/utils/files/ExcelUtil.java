package fr.insy2s.utils.files;

import fr.insy2s.security.SecurityUtils;
import fr.insy2s.utils.wrapper.WrapperClientFournisseur;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    private static final Logger LOG = LoggerFactory.getLogger(ExcelUtil.class);

    private static final String URL_TEMPLATE_MACHIN = "/templates/pdf/machin/machin.jasper";

    static {
        LOG.info("ELM static ExcelUtils utility init...");
    }

/*
    *//**
     * Méthode permettant de générer un xls de Tache Hebdomadaire d'un Stagiaire à partir d'un WrapperPdfProgrammeHebdoTravail
     *
     * @param wrapper un wrapper de tâche hebdomadaire contenant toutes les données à afficher dans le xls
     * @return xls en byte array
     * @throws JRException
     * @author Peter Mollet
     *//*
    public static byte[] generateExcelTacheHebdoStagiaire(WrapperClientFournisseur wrapper) throws JRException {
        String userLogin = SecurityUtils.getCurrentUserLogin().get();
        Map<String, Object> params = new HashMap<>();
        params.put("user", userLogin);
        return ExcelUtil.generateExcel(
                URL_TEMPLATE_MACHIN,
                params,
                Arrays.asList(wrapper)
        );
    }
    */

    /**
     * Methode générique permettant de générer un xls à partir d'un template JasperSoft
     *
     * @param templateUrl URL d'où se trouve le template que l'on veut utiliser
     * @param params les paramêtres du fichier
     * @param objects les données à utiliser/afficher dans l'excel
     * @return L'xls en byte array
     * @throws JRException
     * @author Peter Mollet
     */
    private static byte[] generateExcel(String templateUrl, Map<String, Object> params, List<Object> objects) throws JRException {
        InputStream templateStream = ExcelUtil.class.getResourceAsStream(templateUrl);
        JasperPrint jasperPrint = objects != null ?
                JasperFillManager.fillReport(templateStream, params, new JRBeanCollectionDataSource(objects))
                : JasperFillManager.fillReport(templateStream, params);

        JRXlsExporter xlsExporter = new JRXlsExporter();
        /*Configure Input with the JasperPrint*/
        xlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        /*Configure Export as a ByteArrayOutputStream so we can get the Excel in Byte Array, instead of save it locally*/
        ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
        xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));
        /*Configuration of the file, add others below if necessary*/
        SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
        configuration.setOnePagePerSheet(true);
        configuration.setRemoveEmptySpaceBetweenRows(false);
        configuration.setDetectCellType(false);
        configuration.setWhitePageBackground(true);
        xlsExporter.setConfiguration(configuration);
        /*Export Report (as ByteArrayOutputStream, as configured above) before returning the ByteArray*/
        xlsExporter.exportReport();
        return xlsReport.toByteArray();
    }

}
