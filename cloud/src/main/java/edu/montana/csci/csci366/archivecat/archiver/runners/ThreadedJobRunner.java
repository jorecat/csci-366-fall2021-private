package edu.montana.csci.csci366.archivecat.archiver.runners;

import edu.montana.csci.csci366.archivecat.archiver.jobs.AbstractDownloadJob;
import edu.montana.csci.csci366.archivecat.archiver.jobs.DownloadJob;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ThreadedJobRunner implements DownloadJobRunner {
    public void executeJobs(List<? extends DownloadJob> downloadJobs) {
        // TODO - run each job in its own thread.  Use a CountdownLatch
        //        to ensure that all threads complete before exiting this
        //        method
        CountDownLatch latch = new CountDownLatch(downloadJobs.size()); //set to the length of the download jobs list.
        for (DownloadJob downloadJob : downloadJobs){
            var t = new Thread(new Runnable() {
                @Override
                public void run() {
                    downloadJob.run();
                    latch.countDown(); //newly created thread.
                }
            });
            t.start();
        }
        try {
            latch.await(); //original thread.
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
