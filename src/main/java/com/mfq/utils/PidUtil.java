package com.mfq.utils;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * Created by xingyongshan on 8/2/15.
 */
public class PidUtil {

    private static final int pid = getPid0();

    /**
     * 获取Java进程的PID（仅仅在SUN VM上有效）
     *
     * @return Java进程PID，如果获取不到则返回-1
     */
    public static int getPid() {
        return pid;
    }

    private static int getPid0() {
        try {
            RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
            String name = runtime.getName(); //"pid@hostname"
            return Integer.parseInt(name.substring(0, name.indexOf('@')));
        } catch (Exception e) {
            return -1;
        }
    }
}