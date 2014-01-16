package de.seliger.webcontrol.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.log4j.Logger;

public class ResourceLoader {

    private static final Logger LOGGER = Logger.getLogger(ResourceLoader.class);

    private final Class<?> classForLoadingResource;

    public ResourceLoader(Class<?> classForLoadingResource) {
        this.classForLoadingResource = classForLoadingResource;
    }

    public File loadResourceAsFile(String resourceName) throws URISyntaxException {
        return loadResourceAsFile(null, resourceName);
    }

    public File loadResourceAsFile(String resourcePrefix, String resourceName) throws URISyntaxException {
        String relativeResourcePath = "";
        if (resourcePrefix != null && resourcePrefix.length() > 0) {
            //                !StringUtils.isNullOrEmpty(resourcePrefix)) {
            relativeResourcePath += resourcePrefix + File.separator;
        }
        relativeResourcePath += resourceName;
        URL resource = classForLoadingResource.getResource(relativeResourcePath);
        if (resource == null) {
            throw new IllegalArgumentException("Datei '" + relativeResourcePath + "' konnte NICHT als Resource geladen werden.");
        }
        return new File(resource.toURI());
    }

    public URL loadResourceAsUrl(String resourceName) {
        URL resource = classForLoadingResource.getResource(resourceName);
        return resource;
    }

    public StringBuffer loadResourceAsStringBuffer(String resourceName) throws IOException {
        return readInputStreamAsStringBuffer(loadResourceAsStream(resourceName), null);
    }

    public StringBuffer loadResourceAsStringBuffer(String resourceName, String charsetEncoding) throws IOException {
        return readInputStreamAsStringBuffer(loadResourceAsStream(resourceName), charsetEncoding);
    }

    private InputStream loadResourceAsStream(String resourceName) {
        return classForLoadingResource.getResourceAsStream(resourceName);
    }

    /**
     * Liest den �bergebenen InputStream mit dem Charset Encoding 'ISO-8859-1'. Es werden nur Streams die einen Text repr�sentieren
     * unterst�tzt.
     */
    public static StringBuffer readInputStreamAsStringBufferIn_ISO_8859_1(InputStream inputStream) throws IOException {
        return readInputStreamAsStringBuffer(inputStream, "ISO-8859-1");
    }

    /**
     * Liest den �bergebenen InputStream mit dem gew�nschten CharsetEncoding ein. Es werden nur Streams die einen Text repr�sentieren
     * unterst�tzt.
     */
    public static StringBuffer readInputStreamAsStringBuffer(InputStream inputStream, String charsetEncoding) throws IOException {
        StringBuffer streamAsString = new StringBuffer();
        BufferedReader reader = null;
        try {
            if (inputStream != null) {
                if (charsetEncoding != null) {
                    reader = new BufferedReader(new InputStreamReader(inputStream, charsetEncoding));
                } else {
                    reader = new BufferedReader(new InputStreamReader(inputStream));
                }
                String line = reader.readLine();
                while (line != null) {
                    streamAsString.append(line);
                    line = reader.readLine();
                    if (line != null) {
                        streamAsString.append("\n");
                    }
                }
            } else {
                LOGGER.warn("InputStream ist  null  und kann NICHT gelesen werden !");
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return streamAsString;
    }

}
