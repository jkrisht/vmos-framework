package com.vmos.utils;

import com.vmos.factories.BundleFile;
import org.apache.log4j.Logger;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.vmos.utils.TestConstants.FILE_SEPARATOR;
import static com.vmos.utils.TestConstants.PAGE_CONTENT_DIR;

public class PropertiesUtil {
    private final static Logger logger = Logger.getLogger(PropertiesUtil.class);

    synchronized public static ResourceBundle getBundle(BundleFile bundleFile) {
        String filePath = PAGE_CONTENT_DIR + FILE_SEPARATOR;
        ResourceBundle bundle = null;
        try {
            File file = new File(filePath);
            URL[] urls = {file.toURI().toURL()};
            ClassLoader loader = new URLClassLoader(urls);
            bundle = ResourceBundle.getBundle(bundleFile.getName(), Locale.getDefault(), loader);
        } catch (MalformedURLException e) {
            String message = String.format("Failed to read the %s properties file", bundleFile.getName());
            logger.error(message, e);
            throw new RuntimeException(message);
        }
        return bundle;
    }
}
