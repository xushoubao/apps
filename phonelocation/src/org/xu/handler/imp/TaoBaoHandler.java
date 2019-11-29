package org.xu.handler.imp;

import com.google.gson.Gson;
import org.xu.common.Constant;
import org.xu.common.HttpClient;
import org.xu.handler.Handler;

import java.util.Map;

public class TaoBaoHandler extends Handler {

    private Gson gson;

    public TaoBaoHandler(int phoneZone) {
        super(phoneZone);
        this.gson = new Gson();
    }

    @Override
    public void process(String phone) {
        String resp = HttpClient.doGet(Constant.TAO_BAO_URL_PREFIX + phone, "gbk");
        resp = resp.replaceAll("_| |\r|\n|\t", "");
        resp = resp.replace("GetZoneResult=", "");

        Map map = gson.fromJson(resp, Map.class);
        String record = String.join("\t", map.values());
        result.add(record);
    }
}
