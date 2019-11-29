package org.xu.handler;

import org.apache.commons.io.FileUtils;
import org.xu.common.Constant;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 发送http请求,并解析结果
 *
 * todo 添加请求失败,重新请求次数
 */
public abstract class Handler extends Thread{

    private int phoneZone;
    protected Set<String> result;

    protected Handler(int phoneZone){
        this.phoneZone = phoneZone;
        this.result = new HashSet<>();
    }

    @Override
    public void run() {

        for (int i=0; i<100; i++){
            String phone = generPhone(i);
            process(phone);
        }

        if (result.size() > 0) {
            writeResult(result);
        }

    }

    public abstract void process(String phone);

    private String generPhone(int index) {

        String phoneMiddle = fillChar(index);

        int random = (int) (Math.random() * 10000);
        String phoneSuffix = fillChar(random);


        return phoneZone + phoneMiddle + phoneSuffix;
    }

    private String fillChar(int n) {
        StringBuffer buffer = new StringBuffer();

        String str = String.valueOf(n);
        if (str.length() >= 4) {
            return str.substring(0, 4);
        }

        for (int i = (4 - str.length()); i>0; i--) {
            buffer.append("0");
        }
        buffer.append(str);

        return buffer.toString();
    }

    private void writeResult(Set<String> result){
        String filename = Constant.OUTPUT_FILE_PATH +"/"+ phoneZone +"_"+ System.currentTimeMillis() +".bcp";
        File file = new File(filename);

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try {
            FileUtils.writeLines(file, result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
