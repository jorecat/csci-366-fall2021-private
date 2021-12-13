package edu.montana.csci.csci366.archivecat.archiver.runners;

import edu.montana.csci.csci366.archivecat.archiver.jobs.AbstractDownloadJob;
import edu.montana.csci.csci366.archivecat.archiver.jobs.DownloadJob;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolJobRunner implements DownloadJobRunner {
    public void executeJobs(List<? extends DownloadJob> downloadJobs) {
        // TODO implement - use a ThreadPoolExecutor with 10 threads to execute the jobs
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(downloadJobs.size());
        CountDownLatch latch = new CountDownLatch(downloadJobs.size());
        for (DownloadJob downloadJob : downloadJobs){
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    downloadJob.run();
                    latch.countDown();
                }
            });
        }
        try {
            latch.await(); //original thread.
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        pool.shutdown();
    }
}
