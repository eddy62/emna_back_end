package fr.insy2s.utils.files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class ResourcesUtil {

    public static Image BO_OPER_PREC_HEADER = loadImageByResourcePath("/reports/bo_header.png");

    private static final Logger LOG = LoggerFactory.getLogger(ResourcesUtil.class);

    static {
        LOG.info("ELM static resources utility init ...");
    }

    private static Image loadImageByResourcePath(String resourcesPath) {
        try {
            return ImageIO.read(ResourcesUtil.class.getResourceAsStream(resourcesPath));
        } catch (IOException ex) {
            LOG.error(ex.getMessage());
            //TODO: return image vide  pour eviter les null pointer exceptions
            return null;
        }
    }

    private ResourcesUtil() {}

}