package servlets;

import com.google.gson.Gson;
import model.NewsContent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CrawlerServlet extends HttpServlet {

    private static final String NEWS_URL = "https://www.rbc.ru/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        Document document = Jsoup.connect(NEWS_URL).get();
        Element container = document.getElementsByClass("news-feed__list").first();
        Elements newsElements = container.getElementsByTag("a");
        int i = 0;
        List<NewsContent> newsList = new ArrayList<>();
        for (Element news : newsElements) {
            if(i++ > 5)
                break;
            Elements title = news.getElementsByClass("news-feed__item__title");
            newsList.add(new NewsContent(news.attr("href"), title.text()));
        }
        try (PrintWriter pw = resp.getWriter()) {
            Gson gson = new Gson();
            pw.println(gson.toJson(newsList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
