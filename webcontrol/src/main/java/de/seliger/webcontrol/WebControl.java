package de.seliger.webcontrol;


import java.io.IOException;
import java.net.MalformedURLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class WebControl {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebControl.class);

    private static final String site = "http://htmlunit.sourceforge.net";
    private static final String server = "https://www.regelleistung.net/test-ip/action/index";

    private static String basicUser = "testanbieter";
    private static String basicPwd = "Sogase43";
    private static String siteUserParam = "ipar_u";
    private static String sitePwdParam = "ipar_p";

    public static void main(String[] args) {
        System.out.println("WebControl.main()");
        doWork();
        System.out.println("WebControl.main() ende");
    }


    private static void doWork() {
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);

        HtmlPage page = null;
        try {
            page = callRegelleistungNet(webClient);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (page != null) {
            LOGGER.debug(page.asText());
        }

        webClient.closeAllWindows();
    }


    private static HtmlPage callRegelleistungNet(WebClient webClient) throws IOException, MalformedURLException {
        DefaultCredentialsProvider credentialsProvider = (DefaultCredentialsProvider)webClient.getCredentialsProvider();
        credentialsProvider.addCredentials(basicUser, basicPwd);


        HtmlPage page = webClient.getPage(server);
        return page;
    }

}
