package org.xu.handler.imp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xu.common.Constant;
import org.xu.common.HttpClient;
import org.xu.handler.Handler;

import java.util.HashMap;
import java.util.Map;

public class IP138Handler extends Handler {

    public IP138Handler(int phoneZone) {
        super(phoneZone);
    }

    @Override
    public void process(String phone) {
        String resp = HttpClient.doGet(Constant.IP138_URL_PREFIX + phone, "utf-8");
        resp = resp.replaceAll("\r|\n", "");

        String record = parseDom(resp);
        result.add(record);

    }

    private String parseDom(String html) {
        Document doc = Jsoup.parse(html);
        Elements tableElements = doc.getElementsByTag("table");
        Element element = tableElements.first();

        Elements trElement = element.getElementsByTag("tr");
        Map<String, String> map = new HashMap<>();
        for (Element tr : trElement) {

            Elements tds = tr.getElementsByTag("td");
            if (tds.size() == 2) {
                map.put(tds.get(0).text(), tds.get(1).text());
            }

        }

        return String.join("\t", map.values());
    }

}
