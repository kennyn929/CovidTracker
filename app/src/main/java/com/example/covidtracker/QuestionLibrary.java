package com.example.covidtracker;

public class QuestionLibrary {
    private String mQuestions [] = {
            "In the past 14 days, have you: ",
            "Do you have any of these symptoms? How many do you share? \n" +
                    "- Fever or Chills \n" +
                    "- Cough \n" +
                    "- Shortness of breath of difficulty breathing \n" +
                    "- Fatigue \n" +
                    "- Muscle or body aches \n" +
                    "- Headache \n" +
                    "- New loss of taste or smell \n" +
                    "- Sore throat \n" +
                    "- Congestion or runny nose \n" +
                    "- Nausea, vomiting or diarrhea"
    };

    private String mChoices [][] = {
            {       "Tested positive for COVID-19",
                    "Been tested and now await results of a COVID-19 test",
                    "Been identified as, or knowingly been in close contact with someone who has tested positive",
                    "None of the above"},

            {       "1", "2", "3 or more", "None of these symptoms"}
    };

    private String mCorrectAnswers [] = {"None of the above", "None of these symptoms"};

    public int getNumberOfQuestions() {
        return mQuestions.length;
    }

    public String getQuestion(int a) {
        String question = mQuestions[a];
        return question;
    }

    public String getCorrectAnswers(int a) {
        String answer = mCorrectAnswers[a];
        return answer;
    }

    public String getChoice1(int a) {
        String choice0 = mChoices[a][0];
        return choice0;
    }

    public String getChoice2(int a) {
        String choice1 = mChoices[a][1];
        return choice1;
    }

    public String getChoice3(int a) {
        String choice2 = mChoices[a][2];
        return choice2;
    }

    public String getChoice4(int a) {
        String choice3 = mChoices[a][3];
        return choice3;
    }
}
