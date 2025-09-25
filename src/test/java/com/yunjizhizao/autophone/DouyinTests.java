package com.yunjizhizao.autophone;

import com.volcengine.model.acep.DetailPodQuery;
import com.volcengine.model.acep.DetailPodRes;
import com.volcengine.service.acep.ACEPService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DouyinTests {

    @Autowired
    private ACEPService service;

    @Test
    public void getPodDetail() {
        DetailPodQuery query = new DetailPodQuery();

        // 实例所归属的业务 ID，可在**云手机控制台 > 业务管理 > 业务详情**中获取。
        query.setProductId("1965298718945054720");
        // 实例 ID，可通过调用 [ListPod](https://www.volcengine.com/docs/6394/1221468) 接口获取。
        query.setPodId("7547992861502544679");

        try {
            DetailPodRes resp = service.detailPod(query);
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
