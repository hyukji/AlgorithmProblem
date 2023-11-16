import java.util.HashMap;

class MBTI {
    public String solution(String[] survey, int[] choices) {
        Character[][] types = {{'R', 'T'}, {'C', 'F'}, {'J', 'M'}, {'A', 'N'}};
        HashMap<Character, Integer> scores = new HashMap<Character, Integer>();
        int[] choiceScore = {3, 2, 1, 0, 1, 2, 3};

        for (Character[] type : types) {
            scores.put(type[0], 0);
            scores.put(type[1], 0);
        }
        for (int idx = 0; idx < choices.length; idx++) {
            String type = survey[idx];
            int choice = choices[idx] - 1;

            char letter = (choice > 3) ? type.charAt(0) : type.charAt(1);
            int score = choiceScore[choice];

            scores.put(letter, scores.get(letter) + score);
        }

        String answer = "";
        for (Character[] type : types) {
            answer += (scores.get(type[0]) <= scores.get(type[1])) ? type[0] : type[1];
        }

        return answer;
    }
}