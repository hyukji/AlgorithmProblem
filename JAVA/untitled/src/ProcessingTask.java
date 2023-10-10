// https://school.programmers.co.kr/learn/courses/30/lessons/176962#

import java.util.*;

class ProcessingTask {

    class Task {
        private String name;
        private int time;
        private int duration;

        public Task(String name, int time, int duration) {
            this.name = name;
            this.time = time;
            this.duration = duration;
        }

        public Task(String name, int duration) {
            this.name = name;
            this.duration = duration;
        }

    }

    public String[] solution(String[][] plans) {
        ArrayList<String> answer = new ArrayList<>();
        ArrayList<Task> subjects = new ArrayList<>();
        Stack<Task> notEnding = new Stack<>();

        for(String[] plan : plans) {
            String name = plan[0];
            String[] strTime = plan[1].split(":");
            int time = Integer.parseInt(strTime[0]) * 60 + Integer.parseInt(strTime[1]);
            int duration = Integer.parseInt(plan[2]);

            subjects.add(new Task(name, time, duration));
        }

        subjects.sort((Comparator.comparingInt(o -> o.time)));

        for(int i = 0; i < plans.length-1; i++) {
            Task subject = subjects.get(i);
            Task nextSubject = subjects.get(i+1);

            int endTime = subject.time + subject.duration;
            if(nextSubject.time < endTime) {
                notEnding.push(new Task(subject.name, endTime - nextSubject.time));
                continue;
            }

            answer.add(subject.name);

            if (nextSubject.time > endTime) {
                int remain = nextSubject.time - endTime;
                while(!notEnding.isEmpty()) {
                    Task notEndingSubject = notEnding.pop();
                    int duration = notEndingSubject.duration;

                    if(remain < duration) {
                        notEndingSubject.duration = duration - remain;
                        notEnding.push(notEndingSubject);
                        break;
                    }
                    remain -= duration;
                    answer.add(notEndingSubject.name);
                }
            }
        }

        Task lastSubject = subjects.get(plans.length - 1);
        answer.add(lastSubject.name);

        while(!notEnding.isEmpty()) {
            Task notEndingSubject = notEnding.pop();
            answer.add(notEndingSubject.name);
        }

        return answer.toArray(new String[0]);
    }
}