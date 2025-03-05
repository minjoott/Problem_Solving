package main.java.Programmers;

import java.util.*;

class 디스크컨트롤러 {
    public int solution(int[][] oldJobs) {
        int jobNum = oldJobs.length;
        Job[] jobs = new Job[jobNum];
        for (int i = 0; i < jobNum; i++) {
            jobs[i] = new Job(oldJobs[i][0], oldJobs[i][1], i);
        }
        Arrays.sort(jobs, (j1, j2) -> j1.requestTime - j2.requestTime);

        int currentTime = 0;
        int jobIndex = 0;
        int completedJobs = 0;
        int totalResponseTime = 0;

        Queue<Job> pq = new PriorityQueue<>();

        while (completedJobs < jobNum) {
            while (jobIndex < jobNum && jobs[jobIndex].requestTime <= currentTime) {
                pq.add(jobs[jobIndex]);
                jobIndex++;
            }

            if (!pq.isEmpty()) {
                Job job = pq.remove();
                currentTime += job.duration;
                totalResponseTime += (currentTime - job.requestTime);
                completedJobs++;
            }
            else {
                currentTime = jobs[jobIndex].requestTime;
            }
        }

        return totalResponseTime / jobNum;
    }

    class Job implements Comparable<Job> {
        int requestTime;
        int duration;
        int no;

        Job(int requestTime, int duration, int no) {
            this.requestTime = requestTime;
            this.duration = duration;
            this.no = no;
        }

        @Override
        public int compareTo(Job j) {
            if (this.duration != j.duration) {
                return this.duration - j.duration;
            }
            return this.no - j.no;
        }
    }
}
