package com.hlsofttech.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ProjectName: zyytMixing
 * @Package: com.yt.fanyi.control
 * @ClassName: RequestControl
 * @Description: java类作用描述
 * @Author: mxp
 * @CreateDate: 2018/11/15 18:40
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/11/15 18:40
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RequestControl implements Runnable {
    private static BlockingQueue<Long> queue = new LinkedBlockingQueue<Long>();
    private static Long j = 0L;
    private static Integer maxNum = 4;//每5秒30次请求

    static {
        for (int i = maxNum/2; i > 0; i--) {
            queue.add(j++);
        }
    }
    //阻塞取
    public static void take() {
        try {
            queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            if (queue.size() < maxNum / 2) {
                //控制每秒请求数
//                while (queue.size() > (maxNum / 4)) {
//                    try {
//                        queue.poll(1, TimeUnit.SECONDS);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
                for (int i = maxNum; i > 0; i--) {
                    queue.add(j++);
                }
            }
            try {
                Thread.sleep(960);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
