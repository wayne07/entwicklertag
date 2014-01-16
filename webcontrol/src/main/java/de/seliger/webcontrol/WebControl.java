package de.seliger.webcontrol;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

public class WebControl {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebControl.class);

    private static final String site = "http://htmlunit.sourceforge.net";
    private static final String server = "https://www.regelleistung.net/test-ip/action/index";
    private static final String github = "https://github.com";
    private static final String githublogin = "https://github.com/login";
    private static final String idosmail = "http://mail:5108/index.html";


    public static void main(String[] args) {
        System.out.println("WebControl.main()");
        doWork();
        System.out.println("WebControl.main() ende");
    }


    private static void doWork() {
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);
        webClient.addRequestHeader("Accept-Encoding", "deflate");

        HtmlPage page = null;
        try {
            page = doIdosWebmailLogin(webClient);
        } catch (Exception e) {
            e.printStackTrace();
        }

        debugPageText(page);

        webClient.closeAllWindows();
    }

    private static HtmlPage doIdosWebmailLogin(WebClient webClient) {
        try {
            HtmlPage page = webClient.getPage(idosmail);
            debugPageText(page);
            HtmlForm form = page.getFormByName("mainform");
            HtmlInput inputUsername = form.getInputByName("username");
            HtmlPasswordInput inputPassword = form.getInputByName("password1");
            HtmlImageInput logonButton = form.getInputByName("submitbtn");
            inputUsername.setValueAttribute("js");
            inputPassword.setValueAttribute("xxxx");

            HtmlPage afterLogin = (HtmlPage)logonButton.click();
            debugPageText(afterLogin);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    private static HtmlPage doGitHubLogin(WebClient webClient) {
        try {
            webClient.addRequestHeader("Accept-Encoding", "deflate");

            HtmlPage page = webClient.getPage(github);
            debugPageText(page);

            DomElement elementByName = page.getElementByName("button signin");
            HtmlSubmitInput input = (HtmlSubmitInput)elementByName;
            HtmlPage loginPage = input.click();
            debugPageText(loginPage);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static void debugPageText(HtmlPage page) {
        if (page != null) {
            LOGGER.debug(page.asText());
        }
    }
}
