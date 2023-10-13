//https://school.programmers.co.kr/learn/courses/30/lessons/12923

class NumberBlock {

    public int[] solution(long begin, long end) {
        int size = (int) (end - begin + 1);
        int[] answer = new int[size];

        for (int i = 0; i < answer.length; i++) {
            long num = i + begin;

            int maxDivisor = 1;
            for (int div = 2; div <= Math.sqrt(num); div++) {
                if (num % div != 0) continue;
                if (num / div > 10000000) {
                    maxDivisor = div;
                    continue;
                }
                maxDivisor = (int) num / div;
                break;
            }

            answer[i] = maxDivisor;
        }

        if(begin == 1) {
            answer[0] = 0;
        }

        return answer;
    }

}