package org.xu;

import org.xu.handler.Handler;
import org.xu.handler.imp.IP138Handler;
import org.xu.handler.imp.TaoBaoHandler;

/**
 * 添加配置
 * 添加日志
 * 改为反射入口
 */
public class PhoneLocation {

    public static void main(String[] args) {

        new IP138Handler(183).start();

        new TaoBaoHandler(184).start();

//        for (int i = 130; i < 200; i ++){
//            new IP138Handler(i).start();
//        }

    }

}
