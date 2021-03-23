package com.byshu.redisson;


import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        // 1. Create config object
        Config config = new Config();
        config.useClusterServers()
                // use "rediss://" for SSL connection
                .addNodeAddress("redis://127.0.0.1:7181");

        // 从文件生成config
        // config = Config.fromYAML(new File("config-file.yaml"));

        // 2. Create Redisson instance
        // Sync and Async API
        RedissonClient redisson = Redisson.create(config);
        RLock lock = redisson.getLock("myLock");

        // traditional lock method
        lock.lock();

        // or acquire lock and automatically unlock it after 10 seconds
        lock.lock(10, TimeUnit.SECONDS);

        // or wait for lock aquisition up to 100 seconds
        // and automatically unlock it after 10 seconds
        boolean res = lock.tryLock(100, 10, TimeUnit.SECONDS);
        if (res) {
            try {

            } finally {
                lock.unlock();
            }
        }
    }

}
