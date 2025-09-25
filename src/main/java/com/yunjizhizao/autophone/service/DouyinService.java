package com.yunjizhizao.autophone.service;

import com.volcengine.model.acep.DetailPodQuery;
import com.volcengine.model.acep.DetailPodRes;
import com.volcengine.service.acep.ACEPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DouyinService {

    @Autowired
    private ACEPService acepService;


    public DetailPodRes getDetail(String productId, String podId) throws Exception {
        DetailPodQuery query = new DetailPodQuery();
        // 实例所归属的业务 ID，可在**云手机控制台 > 业务管理 > 业务详情**中获取。
        query.setProductId(productId); // 1965298718945054720
        // 实例 ID，可通过调用 [ListPod](https://www.volcengine.com/docs/6394/1221468) 接口获取。
        query.setPodId(podId); // 7547992861502544679

        DetailPodRes resp = acepService.detailPod(query);
        return resp;
    }

}
