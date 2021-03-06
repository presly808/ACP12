package week3.downloadUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gavri on 05.03.2016.
 */
public class MyDownloader {

    public static void load(URL url, String dest) throws IOException {

        try (InputStream is = url.openStream();
             OutputStream os = new FileOutputStream(new File(dest))) {

            byte[] buff = new byte[1024];
            int count = 0;

            while ((count = is.read(buff)) != -1) {
                os.write(buff, 0, count);
                os.flush();
            }
        }

    }

    public static List<URL> searchFiles(URL url) throws ParserConfigurationException, IOException {
        List<URL> arrayList = new ArrayList<>();
        String urlStart = "http://www.ex.ua";
        Document document = Jsoup.parse(url, 1000);
        Element body = document.body();
        Elements elements = body.getElementsByTag("a");
        int innerCount = 0;
        for (Element element : elements) {
            if (element.attr("href").contains("/load")) {
                if (arrayList.size() == 0) {
                    arrayList.add(new URL(urlStart + element.attr("href")));

                } else {

                    if (!element.attr("href").contains(arrayList.get(innerCount).getPath())) {
                        arrayList.add(new URL(urlStart + element.attr("href")));
                        ++innerCount;
                    }


                }

            }
        }

        return arrayList;
    }

    public static void groupDownload(URL url, String destinationFolder, String fileFormat) throws IOException, ParserConfigurationException {
        List<URL> list = searchFiles(url);
        int id = 0;
        for (URL url1 : list) {
            id++;
            load(url1, destinationFolder + id + "." + fileFormat);
        }

    }

    public static List<String> searchFilesWithCss(URL url) throws IOException {
        List<String> arrayList = new ArrayList<>();
        String urlStart = "http://www.ex.ua";
        Document doc = Jsoup.parse(url, 1000);
        Element element = doc.body();

            String select = "a[href^=/load]";

            Elements elements = Selector.select(select,element);

        for (Element element1 : elements) {
            arrayList.add(element1.attr("href"));
        }
        return arrayList;
    }
}
