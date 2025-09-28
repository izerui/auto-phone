package com.yunjizhizao.autophone.controller;

import com.volcengine.model.acep.RunSyncCommandRes;
import com.yunjizhizao.autophone.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class DouyinController {

    @Autowired
    private PhoneService phoneService;

    /**
     * 测试版本：只执行打开抖音和基本点击测试
     */
    @GetMapping("/douyin/test-step")
    public String testStep(@RequestParam(defaultValue = "1965298718945054720") String productId, @RequestParam(defaultValue = "7547992861502544679") String podId, @RequestParam(defaultValue = "1") int step) {
        try {
            List<String> podIds = Arrays.asList(podId);
            
            switch(step) {
                case 1:
                    System.out.println("=== 测试步骤1：打开抖音应用 ===");
                    RunSyncCommandRes result = phoneService.runSyncCommand(productId, podIds, "am start -n com.ss.android.ugc.aweme/.splash.SplashActivity");
                    return "步骤1完成 - 打开抖音: " + result;
                    
                case 2:
                    System.out.println("=== 测试步骤2：关闭未登录 ===");
                    RunSyncCommandRes result2 = phoneService.runSyncCommand(productId, podIds, "input tap 62 95");
                    return "步骤2完成 - 点击屏幕中央: " + result2;
                    
                case 3:
                    System.out.println("=== 测试步骤3：尝试点击搜索按钮 ===");
                    RunSyncCommandRes result3 = phoneService.runSyncCommand(productId, podIds, "input tap 960 120");
                    return "步骤3完成 - 点击搜索按钮: " + result3;
                    
                case 4:
                    System.out.println("=== 测试步骤4：输入搜索内容 ===");
                    RunSyncCommandRes result4 = phoneService.runSyncCommand(productId, podIds, "input text \"美梅超甜\"");
                    return "步骤4完成 - 输入搜索内容: " + result4;
                    
                case 5:
                    System.out.println("=== 测试步骤5：执行搜索 ===");
                    RunSyncCommandRes result5 = phoneService.runSyncCommand(productId, podIds, "input keyevent KEYCODE_ENTER");
                    return "步骤5完成 - 执行搜索: " + result5;
                    
                default:
                    return "无效的步骤号，请使用1-5";
            }
            
        } catch (Exception e) {
            return "测试步骤" + step + "执行失败: " + e.getMessage();
        }
    }

    @GetMapping("/douyin/auto-ctl01")
    public String autoCtl01(@RequestParam(defaultValue = "1965298718945054720") String productId, @RequestParam(defaultValue = "7547992861502544679") String podId) {
        try {
            List<String> podIds = Arrays.asList(podId);
            
            // 1. 打开抖音
            System.out.println("=== 步骤1：打开抖音应用... ===");
            RunSyncCommandRes result1 = phoneService.runSyncCommand(productId, podIds, "am start -n com.ss.android.ugc.aweme/.splash.SplashActivity");
            System.out.println("打开抖音结果: " + result1);
            Thread.sleep(8000); // 增加等待时间，让抖音完全启动
            
            // 2. 处理可能的弹窗和权限请求
            System.out.println("=== 步骤2：处理弹窗和权限... ===");
            
            // 处理权限弹窗 - 使用UI Automator查找"允许"按钮
            System.out.println("2.1 处理权限弹窗...");
            RunSyncCommandRes result2a = phoneService.runSyncCommand(productId, podIds, 
                "uiautomator runtest /system/framework/uiautomator.jar -c android.widget.Button | grep -i '允许\\|allow' | head -1 | xargs -I {} input tap {}");
            System.out.println("点击允许按钮结果: " + result2a);
            Thread.sleep(2000);
            
            // 处理登录弹窗 - 查找"跳过"或"取消"按钮
            System.out.println("2.2 处理登录弹窗...");
            RunSyncCommandRes result2b = phoneService.runSyncCommand(productId, podIds, 
                "uiautomator dump && grep -o 'text=\"[^\"]*跳过[^\"]*\"\\|text=\"[^\"]*取消[^\"]*\"' /sdcard/window_dump.xml | head -1");
            System.out.println("查找跳过按钮结果: " + result2b);
            Thread.sleep(2000);
            
            // 3. 搜索用户 "美梅超甜"
            System.out.println("=== 步骤3：搜索用户 '美梅超甜'... ===");
            
            // 使用UI Automator查找搜索按钮
            System.out.println("3.1 查找并点击搜索按钮...");
            RunSyncCommandRes result3a = phoneService.runSyncCommand(productId, podIds, 
                "uiautomator dump && grep -o 'resource-id=\"[^\"]*search[^\"]*\"' /sdcard/window_dump.xml");
            System.out.println("查找搜索按钮结果: " + result3a);
            Thread.sleep(2000);
            
            // 尝试点击常见的搜索图标
            RunSyncCommandRes result3b = phoneService.runSyncCommand(productId, podIds, 
                "uiautomator runtest /system/framework/uiautomator.jar -c android.widget.ImageView | grep -i 'search\\|搜索' | head -1");
            System.out.println("点击搜索图标结果: " + result3b);
            Thread.sleep(3000);
            
            // 查找搜索输入框并输入内容
            System.out.println("3.2 输入搜索内容...");
            RunSyncCommandRes result3c = phoneService.runSyncCommand(productId, podIds, 
                "uiautomator dump && grep -o 'class=\"android.widget.EditText\"' /sdcard/window_dump.xml");
            System.out.println("查找输入框结果: " + result3c);
            
            // 直接输入搜索内容
            RunSyncCommandRes result3d = phoneService.runSyncCommand(productId, podIds, "input text \"美梅超甜\"");
            System.out.println("输入搜索内容结果: " + result3d);
            Thread.sleep(2000);
            
            // 点击搜索或按回车
            System.out.println("3.3 执行搜索...");
            RunSyncCommandRes result3e = phoneService.runSyncCommand(productId, podIds, "input keyevent KEYCODE_ENTER");
            System.out.println("执行搜索结果: " + result3e);
            Thread.sleep(5000);
            
            // 查找并点击用户条目
            System.out.println("3.4 点击搜索结果中的用户...");
            RunSyncCommandRes result3f = phoneService.runSyncCommand(productId, podIds, 
                "uiautomator dump && grep -o 'text=\"[^\"]*美梅超甜[^\"]*\"' /sdcard/window_dump.xml");
            System.out.println("查找用户条目结果: " + result3f);
            Thread.sleep(2000);
            
            // 如果找不到精确匹配，点击第一个搜索结果
            RunSyncCommandRes result3g = phoneService.runSyncCommand(productId, podIds, 
                "uiautomator dump && grep -o 'class=\"android.widget.LinearLayout\".*bounds=\"\\[0,[0-9]*\\]\\[[0-9]*,[0-9]*\\]\"' /sdcard/window_dump.xml | head -1");
            System.out.println("点击第一个搜索结果: " + result3g);
            Thread.sleep(5000);
            
            // 4. 打开作品列表
            System.out.println("=== 步骤4：进入作品列表... ===");
            
            // 查找作品tab
            System.out.println("4.1 查找作品tab...");
            RunSyncCommandRes result4a = phoneService.runSyncCommand(productId, podIds, 
                "uiautomator dump && grep -o 'text=\"[^\"]*作品[^\"]*\"\\|text=\"[^\"]*视频[^\"]*\"' /sdcard/window_dump.xml");
            System.out.println("查找作品tab结果: " + result4a);
            Thread.sleep(3000);
            
            // 查找并点击第一个视频缩略图
            System.out.println("4.2 查找第一个视频...");
            RunSyncCommandRes result4b = phoneService.runSyncCommand(productId, podIds, 
                "uiautomator dump && grep -o 'class=\"android.widget.ImageView\".*bounds=\"\\[[0-9]*,[0-9]*\\]\\[[0-9]*,[0-9]*\\]\"' /sdcard/window_dump.xml | head -1");
            System.out.println("点击第一个视频结果: " + result4b);
            Thread.sleep(5000);
            
            // 5. 开始滑动观看作品
            System.out.println("=== 步骤5：开始向上滑动观看所有作品... ===");
            
            for (int i = 0; i < 30; i++) {
                System.out.println("观看第 " + (i + 1) + " 个作品...");
                
                // 等待视频播放
                Thread.sleep(5000);
                
                // 向上滑动到下一个视频 - 使用相对安全的滑动区域
                RunSyncCommandRes swipeResult = phoneService.runSyncCommand(productId, podIds, "input swipe 540 1400 540 300 800");
                System.out.println("滑动到第 " + (i + 2) + " 个视频结果: " + swipeResult);
                Thread.sleep(2000);
            }
            
            System.out.println("=== 抖音自动化流程执行完成！ ===");
            return "SUCCESS: 抖音自动化流程执行完成，已观看30个作品";
            
        } catch (Exception e) {
            System.err.println("执行抖音自动化流程时出错: " + e.getMessage());
            e.printStackTrace();
            return "ERROR: " + e.getMessage();
        }
    }

    // 新增：使用UI Automator的方式进行自动化操作
    @GetMapping("/douyin/auto-ui-automator")
    public String autoWithUIAutomator(@RequestParam(defaultValue = "1965298718945054720") String productId, @RequestParam(defaultValue = "7547992861502544679") String podId) {
        try {
            List<String> podIds = Arrays.asList(podId);
            
            // 1. 打开抖音
            System.out.println("=== 步骤1：打开抖音应用... ===");
            RunSyncCommandRes result1 = phoneService.runSyncCommand(productId, podIds, "am start -n com.ss.android.ugc.aweme/.splash.SplashActivity");
            System.out.println("打开抖音结果: " + result1);
            Thread.sleep(8000);
            
            // 2. 获取当前UI结构
            System.out.println("=== 步骤2：分析UI结构... ===");
            RunSyncCommandRes dumpResult = phoneService.runSyncCommand(productId, podIds, "uiautomator dump /sdcard/ui_dump.xml");
            System.out.println("UI dump结果: " + dumpResult);
            Thread.sleep(1000);
            
            // 查看UI结构
            RunSyncCommandRes catResult = phoneService.runSyncCommand(productId, podIds, "cat /sdcard/ui_dump.xml");
            System.out.println("UI结构: " + catResult);
            
            // 3. 使用UI Automator查找搜索按钮
            System.out.println("=== 步骤3：查找搜索功能... ===");
            
            // 查找包含"search"或"搜索"的元素
            RunSyncCommandRes searchResult = phoneService.runSyncCommand(productId, podIds, 
                "grep -i 'search\\|搜索' /sdcard/ui_dump.xml | head -5");
            System.out.println("搜索相关元素: " + searchResult);
            
            // 使用uiautomator2方式点击搜索
            RunSyncCommandRes clickSearchResult = phoneService.runSyncCommand(productId, podIds, 
                "uiautomator runtest /system/framework/uiautomator.jar -c com.github.uiautomator.stub.Stub -e debug false -e class 'com.github.uiautomator.stub.Stub#pressSearch'");
            System.out.println("点击搜索结果: " + clickSearchResult);
            Thread.sleep(3000);
            
            // 4. 输入搜索内容
            System.out.println("=== 步骤4：输入搜索内容... ===");
            
            // 查找输入框
            RunSyncCommandRes findEditText = phoneService.runSyncCommand(productId, podIds, 
                "uiautomator dump /sdcard/ui_dump2.xml && grep 'EditText' /sdcard/ui_dump2.xml");
            System.out.println("查找输入框: " + findEditText);
            
            // 直接输入文本
            RunSyncCommandRes inputResult = phoneService.runSyncCommand(productId, podIds, "input text \"美梅超甜\"");
            System.out.println("输入搜索内容: " + inputResult);
            Thread.sleep(2000);
            
            // 按回车搜索
            RunSyncCommandRes enterResult = phoneService.runSyncCommand(productId, podIds, "input keyevent KEYCODE_ENTER");
            System.out.println("执行搜索: " + enterResult);
            Thread.sleep(5000);
            
            // 5. 查找并点击用户
            System.out.println("=== 步骤5：查找用户... ===");
            
            // 重新dump UI获取搜索结果
            RunSyncCommandRes dump3Result = phoneService.runSyncCommand(productId, podIds, "uiautomator dump /sdcard/ui_dump3.xml");
            System.out.println("搜索结果UI dump: " + dump3Result);
            
            // 查找包含用户名的元素
            RunSyncCommandRes findUserResult = phoneService.runSyncCommand(productId, podIds, 
                "grep -i '美梅超甜' /sdcard/ui_dump3.xml");
            System.out.println("查找用户元素: " + findUserResult);
            
            // 6. 使用通用方法点击第一个可点击的用户条目
            System.out.println("=== 步骤6：点击用户条目... ===");
            
            // 提取第一个可点击元素的bounds并点击
            RunSyncCommandRes extractBoundsResult = phoneService.runSyncCommand(productId, podIds, 
                "grep -o 'clickable=\"true\".*bounds=\"\\[[0-9,]*\\]\"' /sdcard/ui_dump3.xml | head -1 | grep -o 'bounds=\"\\[[0-9,]*\\]\"' | sed 's/bounds=\"\\[\\([0-9]*\\),\\([0-9]*\\)\\]\\[\\([0-9]*\\),\\([0-9]*\\)\\]\"/\\1 \\2 \\3 \\4/' | awk '{x=($1+$3)/2; y=($2+$4)/2; print \"input tap \" int(x) \" \" int(y)}'");
            System.out.println("提取坐标命令: " + extractBoundsResult);
            
            // 如果提取成功，执行点击
            if (extractBoundsResult != null && extractBoundsResult.getResponseMetadata() != null) {
                String tapCommand = extractBoundsResult.getResponseMetadata().toString();
                if (tapCommand.contains("input tap")) {
                    RunSyncCommandRes tapResult = phoneService.runSyncCommand(productId, podIds, tapCommand);
                    System.out.println("点击用户条目: " + tapResult);
                }
            }
            Thread.sleep(5000);
            
            // 7. 进入作品列表
            System.out.println("=== 步骤7：进入作品列表... ===");
            
            // dump当前UI
            RunSyncCommandRes dump4Result = phoneService.runSyncCommand(productId, podIds, "uiautomator dump /sdcard/ui_dump4.xml");
            System.out.println("用户页面UI dump: " + dump4Result);
            
            // 查找作品相关的tab
            RunSyncCommandRes findWorksResult = phoneService.runSyncCommand(productId, podIds, 
                "grep -i '作品\\|视频\\|works' /sdcard/ui_dump4.xml");
            System.out.println("查找作品tab: " + findWorksResult);
            
            // 8. 开始滑动浏览视频
            System.out.println("=== 步骤8：开始浏览视频... ===");
            
            for (int i = 0; i < 10; i++) {
                System.out.println("观看第 " + (i + 1) + " 个视频...");
                
                // 等待视频播放
                Thread.sleep(3000);
                
                // 向上滑动
                RunSyncCommandRes swipeResult = phoneService.runSyncCommand(productId, podIds, 
                    "input swipe 540 1200 540 400 800");
                System.out.println("滑动结果: " + swipeResult);
                Thread.sleep(1000);
            }
            
            System.out.println("=== UI Automator自动化流程执行完成！ ===");
            return "SUCCESS: UI Automator自动化流程执行完成";
            
        } catch (Exception e) {
            System.err.println("执行UI Automator自动化流程时出错: " + e.getMessage());
            e.printStackTrace();
            return "ERROR: " + e.getMessage();
        }
    }

    // 辅助方法：使用UI Automator查找元素
    @GetMapping("/douyin/dump-ui")
    public String dumpUI(@RequestParam(defaultValue = "1965298718945054720") String productId, @RequestParam(defaultValue = "7547992861502544679") String podId) {
        try {
            List<String> podIds = Arrays.asList(podId);
            
            // dump UI结构
            RunSyncCommandRes dumpResult = phoneService.runSyncCommand(productId, podIds, "uiautomator dump /sdcard/ui_dump_current.xml");
            System.out.println("UI dump结果: " + dumpResult);
            
            // 读取UI结构
            RunSyncCommandRes catResult = phoneService.runSyncCommand(productId, podIds, "cat /sdcard/ui_dump_current.xml");
            System.out.println("当前UI结构:");
            if (catResult != null && catResult.getResponseMetadata() != null) {
                return catResult.getResponseMetadata().toString();
            }
            
            return "UI dump完成，请查看日志";
            
        } catch (Exception e) {
            System.err.println("dump UI时出错: " + e.getMessage());
            e.printStackTrace();
            return "ERROR: " + e.getMessage();
        }
    }

    // 智能点击方法：通过文本或属性查找元素并点击
    @GetMapping("/douyin/smart-click")
    public String smartClick(@RequestParam(defaultValue = "1965298718945054720") String productId, 
                            @RequestParam(defaultValue = "7547992861502544679") String podId,
                            @RequestParam String searchText) {
        try {
            List<String> podIds = Arrays.asList(podId);
            
            // 1. 获取当前UI结构
            RunSyncCommandRes dumpResult = phoneService.runSyncCommand(productId, podIds, "uiautomator dump /sdcard/ui_current.xml");
            System.out.println("UI dump结果: " + dumpResult);
            Thread.sleep(1000);
            
            // 2. 查找包含指定文本的可点击元素
            String findCommand = String.format(
                "grep -i '%s' /sdcard/ui_current.xml | grep 'clickable=\"true\"' | head -1", 
                searchText
            );
            RunSyncCommandRes findResult = phoneService.runSyncCommand(productId, podIds, findCommand);
            System.out.println("查找元素结果: " + findResult);
            
            // 3. 如果没找到可点击的，查找所有包含文本的元素
            if (findResult == null || findResult.getResponseMetadata() == null || 
                findResult.getResponseMetadata().toString().trim().isEmpty()) {
                
                findCommand = String.format("grep -i '%s' /sdcard/ui_current.xml | head -1", searchText);
                findResult = phoneService.runSyncCommand(productId, podIds, findCommand);
                System.out.println("查找所有相关元素结果: " + findResult);
            }
            
            // 4. 提取坐标并点击
            if (findResult != null && findResult.getResponseMetadata() != null) {
                String element = findResult.getResponseMetadata().toString();
                
                // 使用正则表达式提取bounds坐标
                String extractCommand = String.format(
                    "echo '%s' | grep -o 'bounds=\"\\[[0-9,]*\\]\"' | sed 's/bounds=\"\\[\\([0-9]*\\),\\([0-9]*\\)\\]\\[\\([0-9]*\\),\\([0-9]*\\)\\]\"/\\1 \\2 \\3 \\4/' | awk '{x=($1+$3)/2; y=($2+$4)/2; print \"input tap \" int(x) \" \" int(y)}'",
                    element.replace("'", "'\\''")
                );
                
                RunSyncCommandRes coordsResult = phoneService.runSyncCommand(productId, podIds, extractCommand);
                System.out.println("坐标提取结果: " + coordsResult);
                
                if (coordsResult != null && coordsResult.getResponseMetadata() != null) {
                    String tapCommand = coordsResult.getResponseMetadata().toString().trim();
                    if (tapCommand.startsWith("input tap")) {
                        RunSyncCommandRes tapResult = phoneService.runSyncCommand(productId, podIds, tapCommand);
                        System.out.println("点击结果: " + tapResult);
                        return "SUCCESS: 成功点击了包含 '" + searchText + "' 的元素，坐标: " + tapCommand;
                    }
                }
            }
            
            return "FAILED: 未找到包含 '" + searchText + "' 的可点击元素";
            
        } catch (Exception e) {
            System.err.println("智能点击时出错: " + e.getMessage());
            e.printStackTrace();
            return "ERROR: " + e.getMessage();
        }
    }

    // 改进的抖音自动化流程 - 使用智能点击
    @GetMapping("/douyin/auto-smart")
    public String autoSmart(@RequestParam(defaultValue = "1965298718945054720") String productId, 
                           @RequestParam(defaultValue = "7547992861502544679") String podId,
                           @RequestParam(defaultValue = "美梅超甜") String username) {
        try {
            List<String> podIds = Arrays.asList(podId);
            
            // 1. 打开抖音
            System.out.println("=== 步骤1：打开抖音应用... ===");
            RunSyncCommandRes result1 = phoneService.runSyncCommand(productId, podIds, "am start -n com.ss.android.ugc.aweme/.splash.SplashActivity");
            System.out.println("打开抖音结果: " + result1);
            Thread.sleep(8000);
            
            // 2. 处理可能的弹窗
            System.out.println("=== 步骤2：处理弹窗... ===");
            
            // 尝试点击"允许"按钮
            String allowResult = smartClick(productId, podId, "允许");
            System.out.println("处理允许弹窗: " + allowResult);
            Thread.sleep(2000);
            
            // 尝试点击"跳过"按钮
            String skipResult = smartClick(productId, podId, "跳过");
            System.out.println("处理跳过弹窗: " + skipResult);
            Thread.sleep(2000);
            
            // 3. 查找并点击搜索
            System.out.println("=== 步骤3：点击搜索... ===");
            String searchResult = smartClick(productId, podId, "搜索");
            if (!searchResult.contains("SUCCESS")) {
                // 如果中文搜索失败，尝试英文
                searchResult = smartClick(productId, podId, "search");
            }
            System.out.println("点击搜索结果: " + searchResult);
            Thread.sleep(3000);
            
            // 4. 输入搜索内容
            System.out.println("=== 步骤4：输入搜索内容... ===");
            RunSyncCommandRes inputResult = phoneService.runSyncCommand(productId, podIds, "input text \"" + username + "\"");
            System.out.println("输入用户名: " + inputResult);
            Thread.sleep(2000);
            
            // 5. 执行搜索
            RunSyncCommandRes enterResult = phoneService.runSyncCommand(productId, podIds, "input keyevent KEYCODE_ENTER");
            System.out.println("执行搜索: " + enterResult);
            Thread.sleep(5000);
            
            // 6. 点击用户
            System.out.println("=== 步骤5：点击用户... ===");
            String userResult = smartClick(productId, podId, username);
            System.out.println("点击用户结果: " + userResult);
            Thread.sleep(5000);
            
            // 7. 点击作品tab
            System.out.println("=== 步骤6：进入作品列表... ===");
            String worksResult = smartClick(productId, podId, "作品");
            if (!worksResult.contains("SUCCESS")) {
                worksResult = smartClick(productId, podId, "视频");
            }
            System.out.println("点击作品tab: " + worksResult);
            Thread.sleep(3000);
            
            // 8. 开始浏览视频
            System.out.println("=== 步骤7：开始浏览视频... ===");
            
            // 点击第一个视频（使用屏幕中央区域）
            RunSyncCommandRes firstVideoResult = phoneService.runSyncCommand(productId, podIds, "input tap 540 800");
            System.out.println("点击第一个视频: " + firstVideoResult);
            Thread.sleep(5000);
            
            // 开始滑动浏览
            for (int i = 0; i < 15; i++) {
                System.out.println("观看第 " + (i + 1) + " 个视频...");
                Thread.sleep(4000); // 观看视频
                
                // 向上滑动到下一个视频
                RunSyncCommandRes swipeResult = phoneService.runSyncCommand(productId, podIds, 
                    "input swipe 540 1200 540 400 800");
                System.out.println("滑动到下一个视频: " + swipeResult);
                Thread.sleep(1000);
            }
            
            System.out.println("=== 智能自动化流程执行完成！ ===");
            return "SUCCESS: 智能自动化流程执行完成，已浏览用户 " + username + " 的作品";
            
        } catch (Exception e) {
            System.err.println("执行智能自动化流程时出错: " + e.getMessage());
            e.printStackTrace();
            return "ERROR: " + e.getMessage();
        }
    }
}