package com.example.trivia;

import java.util.ArrayList;
import java.util.Collections;

public class Collection2 {
    private ArrayList<Question> arr;
    private int index; //מספר השאלה הבאה בתור|

    public Collection2()
    {
        Question q1 = new Question("1+10","7","11","3","100",2);
        Question q2 = new Question("1+2","8","2","3","100",3);
        Question q3 = new Question("1+0","1","2","3","100",1);
        Question q4 = new Question("1+3","6","2","4","100",3);
        Question q5 = new Question("1+4","5","2","3","100",1);
        Question q6= new Question("14+3","1","2","17","100",3);
        Question q7 = new Question("11+0","11","2","3","100",1);



        arr = new ArrayList<>();
        arr.add(q1);
        arr.add(q2);
        arr.add(q3);
        arr.add(q4);
        arr.add(q5);
        arr.add(q6);
        arr.add(q7);



    }
    public void initQuestions()
    {
        index = 0;
        Collections.shuffle(arr);
    }

    public Question getNextQuestions()
    {
        Question q = arr.get(index);
        index++;
        return q;
    }

    public boolean isNotLastQuestion()
    {
        return (index< arr.size());
    }

    public int getIndex()
    {
        return index;
    }
}
