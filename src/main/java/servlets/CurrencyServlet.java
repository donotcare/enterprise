package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyServlet extends HttpServlet {
    private final static String CURRENCY_URL = "http://www.cbr.ru/scripts/XML_daily.asp";
    private final static String XSL_FILENAME = "/currency.xsl";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StreamSource styleSource = new StreamSource(this.getClass().getResourceAsStream(XSL_FILENAME));
        StreamSource xmlSource = getCurrencyXml();
        resp.setContentType("text/html; charset=utf-8");
        try (PrintWriter pw = resp.getWriter()) {
            Transformer transformer = TransformerFactory.newInstance().newTransformer(styleSource);
            transformer.transform(xmlSource, new StreamResult(pw));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private StreamSource getCurrencyXml() throws IOException {
        URL url = new URL(CURRENCY_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        StreamSource xmlsource = new StreamSource(connection.getInputStream());
        return xmlsource;
    }
}
