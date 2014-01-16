package de.seliger.webcontrol;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import de.seliger.webcontrol.io.ResourceLoader;

public class HttpHelperTest {

    private final ResourceLoader loader = new ResourceLoader(HttpHelperTest.class);

    private final String afterLoginPageFile = "seite-nach-login.html";

    private final HttpHelper helper = new HttpHelper();

    @Test
    public void test() throws Exception {
        File afterLoginPage = loader.loadResourceAsFile(afterLoginPageFile);
        WebClient client = new WebClient();

        HtmlPage page = client.getPage(afterLoginPage.toURL());

        assertThat(helper.getCsrfToken(page), is(""));
        fail("Not yet implemented");
    }

}
