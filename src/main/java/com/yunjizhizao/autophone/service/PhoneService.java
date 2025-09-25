package com.yunjizhizao.autophone.service;

import com.volcengine.model.acep.*;
import com.volcengine.service.acep.ACEPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {

    @Autowired
    private ACEPService acepService;


    public DetailPodRes getDetail(String productId, String podId) throws Exception {
        DetailPodQuery query = new DetailPodQuery();
        query.setProductId(productId);
        query.setPodId(podId);
        DetailPodRes resp = acepService.detailPod(query);
        return resp;
    }

    public ScreenShotRes getScreenShot(String productId, String podId) throws Exception {
        ScreenShotBody body = new ScreenShotBody();
        // 实例 ID，可通过调用 ListPod 接口获取。
        body.setPodId(podId);
        // 实例所属业务 ID，可在「云手机控制台-业务管理-业务详情」中获取。
        body.setProductId(productId);
        // 截图请求的唯一标识，防止由于网络等原因造成重复请求，同一 RoundId 在 5 分钟内只可使用一次。
        body.setRoundId("01");
        // 是否保存截图文件在云手机实例：
        // <li> true：上传截图文件到火山引擎对象存储，并保存截图文件在云手机实例中。 </li>
        // <li> false：默认值，上传截图文件到火山引擎对象存储，上传完成后，删除保存在云手机实例中的文件。 </li>
        //
        // 截图文件保存和清理逻辑如下：
        // <li> 当保存截图文件在云手机实例时，截图超过 1000 张时会清理之前保存的截图文件； </li>
        // <li> 当上传截图文件到火山引擎对象存储时，截图上传到对象存储成功后再清理云手机实例中的截图文件； </li>
        // <li> 当云手机存储空间小于 600MB 时： </li>
        // <li> 若设置该参数为 true，则不可执行截图操作； </li>
        // <li> 若设置该参数为 false，可执行截图操作。 </li>
        body.setIsSavedOnPod(true);
        // 截图画面横竖屏旋转：
        // <li> 0：默认值，截图方向不做处理； </li>
        // <li> 1：截图画面旋转为竖屏： </li>
        // <li> 手机竖屏的截图，不做处理； </li>
        // <li> 手机横屏的截图，截图顺时针旋转 90 度。 </li>
        body.setRotation(0);
        // 截图事件是否广播：
        // <li> true：默认值，广播； </li>
        // <li> false：不广播。 </li>
        body.setIsBroadcasted(true);
        // 设置截图地址回调触发的频次：
        // <li> 0/1/置空：当前行为/默认行为，表示每次调用本接口发起截图，都会刷新 URL，触发新的截图地址回调； </li>
        // <li> -1：URL 固定不刷新，直到 7 天刷新一次 URL（由于 TOS 的 URL 链接有效期为 7 天）； </li>
        // <li> 其他数值：截图累计 xx 次后，再刷新 URL，刷新 URL 再触发回调。URL 不变，则不触发新的回调。 </li>
        body.setInterval(0);

        ScreenShotRes resp = acepService.screenShot(body);
        return resp;
    }

    public RunSyncCommandRes runSyncCommand(String productId, List<String> podIds, String command) throws Exception {
        RunSyncCommandBody body = new RunSyncCommandBody();

        // 实例所归属的业务 ID，可在「云手机控制台-业务管理-业务详情」中获取。
        body.setProductId(productId);

        // 实例 ID 列表，对多个实例批量执行命令操作时，支持的最大实例数量为 100。
        body.setPodIdList(podIds);
        // 待执行的命令，支持的最大长度为 1024 字节。
        body.setCommand(command);
        // 权限类型。
        body.setPermissionType("root");
        RunSyncCommandRes resp = acepService.runSyncCommand(body);
        return resp;
    }

}
