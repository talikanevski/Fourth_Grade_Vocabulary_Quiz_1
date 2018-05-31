package com.example.com.fourth_grade_vocabulary_quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText whatIsYourName;//ask the user to enter his/her name
    private Button letsGo;//button to submit user's name
    private Button start;

    // @parm score will help to define how good is user's vocabulary
    int score = 0;
    private View line1;
    private View line2;
    private View line3;
    private View line4;
    private View line5;
    private View line6;
    private View line7;
    private View line8;

    // those @parms  will help to reach the right string to tne next question, possible answers and results
    private int i = 0;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private int questionID;
    private TextView question_1;
    private TextView comment;
    public String result;
    private int resultID;
    private TextView result1;
    private String answer1CheckBox;
    private String answer2CheckBox;
    private String answer3CheckBox;
    private String answer4CheckBox;

    //will show my reaction to user's answer
    private TextView reaction;
    private RelativeLayout relative_layout_score;
    private TextView your_score;
    private TextView display_score;
    private RelativeLayout relative_layout_questions_left;
    private TextView question_left;
    private TextView display_questions_left;

    //first 3 question are radioButtons
    private RadioGroup rd;
    private RadioButton answer1_1;
    private RadioButton answer1_2;
    private RadioButton answer1_3;
    private RadioButton answer1_4;
    private Button submit_radio_button;

    //second 3 questions are CheckBoxes
    private CheckBox answer4_1;
    private CheckBox answer4_2;
    private CheckBox answer4_3;
    private CheckBox answer4_4;
    private Button submit_check_box;
    private CheckBox shareCheckBox;

    //third 3 questions are EditTexts
    private EditText answer_7;
    private Button submit_edit_text;//button to submit user's answer to EditText's question

    private Button next;
    private Button reset;
    private Button solution;
    private ScrollView hscrollViewMain;
    private TextView NameOfTheTest;
    private TextView introduction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        whatIsYourName = findViewById(R.id.name); //EditText
        whatIsYourName.hasOnClickListeners();
        NameOfTheTest = findViewById(R.id.NameOfTheTest);
        introduction = findViewById(R.id.introduction);
        //design lines
        line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);
        line4 = findViewById(R.id.line4);
        line5 = findViewById(R.id.line5);
        line6 = findViewById(R.id.line6);
        line7 = findViewById(R.id.line7);
        line8 = findViewById(R.id.line8);

        //buttons
        letsGo = findViewById(R.id.letsGo);
        start = findViewById(R.id.start);

        //first 3 questions - RadioButtons
        reaction = findViewById(R.id.reaction);
        rd = findViewById(R.id.rd);
        answer1_1 = findViewById(R.id.answer1_1);
        answer1_2 = findViewById(R.id.answer1_2);
        answer1_3 = findViewById(R.id.answer1_3);
        answer1_4 = findViewById(R.id.answer1_4);
        answer_7 = findViewById(R.id.answer_7);
        //buttons
        submit_radio_button = findViewById(R.id.submit_radio_button);
        next = findViewById(R.id.next);
        reset = findViewById(R.id.reset);
        submit_edit_text = findViewById(R.id.submit_edit_text);
        solution = findViewById(R.id.solution);
        //second 3 questions - CheckBoxes
        answer4_1 = findViewById(R.id.answer4_1);
//        answer4_1.hasOnClickListeners();
        answer4_2 = findViewById(R.id.answer4_2);
//        answer4_2.hasOnClickListeners();
        answer4_3 = findViewById(R.id.answer4_3);
//        answer4_3.hasOnClickListeners();
        answer4_4 = findViewById(R.id.answer4_4);
//        answer4_4.hasOnClickListeners();
        submit_check_box = findViewById(R.id.submit_check_box);
        //question
        question_1 = findViewById(R.id.question_1);
        comment = findViewById(R.id.comment);
        //reaction to users answer
        your_score = findViewById(R.id.your_score);
        display_score = findViewById(R.id.display_score);
        relative_layout_score = findViewById(R.id.relative_layout_score);
        question_left = findViewById(R.id.question_left);
        display_questions_left = findViewById(R.id.display_questions_left);
        relative_layout_questions_left = findViewById(R.id.relative_layout_questions_left);

        shareCheckBox = findViewById(R.id.share);
        hscrollViewMain = (ScrollView) findViewById(R.id.scrollViewMain);
        result1 = findViewById(R.id.yourResult);

    }

    public void letsGo(View view) {
        if (whatIsYourName.getText().toString().equals("")) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
        } else {
            //makes button "Start a test" visible
            start.setVisibility(View.VISIBLE);
            letsGo.setVisibility(View.GONE);
            whatIsYourName.setVisibility(View.GONE);
        }
    }

    public void startTest(View view) {
        // ad a name of the user to the first question
        question_1.setText(whatIsYourName.getText().toString() + ", " + question_1.getText().toString());

        //make the question visible
        question_1.setVisibility(View.VISIBLE);

        // make 4 possible answers (RadioGroup) for the first question visible to user
        rd.setVisibility(View.VISIBLE);
        line3.setVisibility(View.VISIBLE);
        line4.setVisibility(View.VISIBLE);

        // make button "NEXT QUESTION" visible too
        submit_radio_button.setVisibility(View.VISIBLE);

        //make button "TRY AGAIN" visible too
        reset.setVisibility(View.VISIBLE);

        //Makes those views disappear from the screen when user answering the first question
        NameOfTheTest.setVisibility(View.GONE);
        introduction.setVisibility(View.GONE);
        start.setVisibility(View.GONE);
        line1.setVisibility(View.GONE);
        line2.setVisibility(View.GONE);

        i++;
    }

    public void click(View view) {
        //show my reaction to user's answer
        // after he/she chose one of 4 answers on first 3 questions (all of them RadioButtons)

        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked as answer
        switch (view.getId()) {

            case R.id.answer1_1:

                if (checked) { //it makes my reaction on user's answer to show up
                    // make the radioGroup invisible to prevent from user to change his mind.
                    // (If he want to change his answer, he can hit the "try again" button
                    rd.setVisibility(View.GONE);
                    question_1.setVisibility(View.GONE);
                    submit_radio_button.setVisibility(View.GONE);
                    next.setVisibility(View.VISIBLE);

                    if (i == 1 | i == 2) {//first answer in question number 1 and 2
                        reaction.setText(getString(R.string.wrong_answer_reaction) + answer1_2.getText().toString());
                    }
                    if (i == 3) {
                        reaction.setText(getString(R.string.wrong_answer_reaction) + answer1_4.getText().toString());
                    }
                }
                break;

            case R.id.answer1_2:
                if (checked) {
                    // make the radioGroup invisible to prevent from user to change his mind.
                    // (If he want to change his answer, he can hit the "try again" button
                    rd.setVisibility(View.GONE);
                    question_1.setVisibility(View.GONE);
                    submit_radio_button.setVisibility(View.GONE);
                    next.setVisibility(View.VISIBLE);

                    //uncheck other radioButtons in case they were checked before
                    answer1_1.setChecked(false);
                    answer1_3.setChecked(false);
                    answer1_4.setChecked(false);
                    line3.setVisibility(View.GONE);
                    line4.setVisibility(View.GONE);

                    if (i == 1) { //second answer for question number 1
                        score = score + 2;
                    }
                    if (i == 2) {
                        reaction.setText(R.string.congrats_reaction);
                        score = score + 2;
                    }
                    if (i == 3) {
                        reaction.setText(getString(R.string.wrong_answer_reaction) + answer1_4.getText().toString());
                    }
                }
                break;

            case R.id.answer1_3:

                if (checked) {
                    // make the radioGroup invisible to prevent from user to change his mind.
                    // (If he want to change his answer, he can hit the "try again" button
                    rd.setVisibility(View.GONE);
                    question_1.setVisibility(View.GONE);
                    submit_radio_button.setVisibility(View.GONE);
                    next.setVisibility(View.VISIBLE);

                    //uncheck other radioButtons in case they were checked before
                    answer1_1.setChecked(false);
                    answer1_2.setChecked(false);
                    answer1_4.setChecked(false);
                    if (i == 1 | i == 2) {//third answer in question number 1 and 2
                        reaction.setText(getString(R.string.wrong_answer_reaction) + answer1_2.getText().toString());
                    }
                    if (i == 3) {
                        reaction.setText(getString(R.string.wrong_answer_reaction) + answer1_4.getText().toString());
                    }
                }
                break;

            case R.id.answer1_4:

                if (checked) {
                    // make the radioGroup invisible to prevent from user to change his mind.
                    // (If he want to change his answer, he can hit the "try again" button
                    rd.setVisibility(View.GONE);
                    question_1.setVisibility(View.GONE);
                    submit_radio_button.setVisibility(View.GONE);

                    //uncheck other radioButtons in case they were checked before
                    answer1_1.setChecked(false);
                    answer1_2.setChecked(false);
                    answer1_3.setChecked(false);
                    next.setVisibility(View.VISIBLE);

                    if (i == 1 | i == 2) { //forth answer for question number 1 and 2
                        reaction.setText(getString(R.string.wrong_answer_reaction) + answer1_2.getText().toString());
                    }
                    if (i == 3) {
                        score = score + 2;
                        reaction.setText(R.string.good_job_reaction);
                    }
                }
                break;
        }

        reaction.setVisibility(View.VISIBLE);
        line5.setVisibility(View.VISIBLE);
        relative_layout_score.setVisibility(View.VISIBLE);
        display_score.setText("  " + score);
        line7.setVisibility(View.VISIBLE);
        relative_layout_questions_left.setVisibility(View.VISIBLE);
        display_questions_left.setText(" " + (9 - i));
        line8.setVisibility(View.VISIBLE);
    }

    public void submit_radio_button(View view) {

        //I use this method and the button only to prevent from the user skip to next question without answering

        if (!answer1_1.isChecked() && !answer1_2.isChecked() && !answer1_3.isChecked() && !answer1_1.isChecked()) {
            Toast.makeText(this, "Please make any choice", Toast.LENGTH_SHORT).show();
        } else {
            submit_radio_button.setVisibility(View.GONE);
            next.setVisibility(View.VISIBLE);
        }
    }

    public void submit_check_box(View view) {
        //button to submit user's answer to CheckBoxes

        // this method  "listen" what the user have decided to submit as an answer to the CheckBox question

        //  Check which CheckBox was clicked as answer
        if (!answer4_1.isChecked() && !answer4_2.isChecked() && !answer4_3.isChecked() && !answer4_4.isChecked()) {
            Toast.makeText(this, "Please make any choice", Toast.LENGTH_SHORT).show();
        } else {

            if (answer4_1.isChecked()) {
                reaction.setText(getString(R.string.wrong_answer_reaction) + "'" + answer4_2.getText().toString() + "' and '" + answer4_3.getText().toString() + "'");
                score--;
            }

            if (answer4_2.isChecked()) {
                score++;
                reaction.setText(getString(R.string.wrong_answer_reaction) + "'" + answer4_2.getText().toString() + "' and '" + answer4_3.getText().toString() + "'");
            }

            if (answer4_3.isChecked()) {
                score++;
                reaction.setText(getString(R.string.wrong_answer_reaction) + "'" + answer4_2.getText().toString() + "' and '" + answer4_3.getText().toString() + "'");
            }

            if (answer4_4.isChecked()) {
                reaction.setText(getString(R.string.wrong_answer_reaction) + "'" + answer4_2.getText().toString() + "' and '" + answer4_3.getText().toString() + "'");
                score--;
            }

            //the only right answer:
            if (answer4_2.isChecked() && answer4_3.isChecked() && !answer4_1.isChecked() && !answer4_4.isChecked()) {
                reaction.setText(R.string.right_answer_reaction);
            }

            //it makes my reaction on user's answer to show up
            // make the ChexBoxes invisible
            // (If the user want to change his answer, he can hit the "try again" button
            answer4_1.setVisibility(View.GONE);
            answer4_2.setVisibility(View.GONE);
            answer4_3.setVisibility(View.GONE);
            answer4_4.setVisibility(View.GONE);

            question_1.setVisibility(View.GONE);
            comment.setVisibility(View.GONE);
            submit_check_box.setVisibility(View.GONE);

            reaction.setVisibility(View.VISIBLE);
            line5.setVisibility(View.VISIBLE);
            relative_layout_score.setVisibility(View.VISIBLE);
            display_score.setText("  " + score);
            line7.setVisibility(View.VISIBLE);
            relative_layout_questions_left.setVisibility(View.VISIBLE);
            display_questions_left.setText(" " + (9 - i));
            line8.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
        }
    }

    public void submit_edit_text(View view) {
        //button to submit user's answer to EditTexts
        // I need this button only for EditText questions -
        // to "listen" what the user have decided to submit as an answer to the EditText question

        if (answer_7.getText().toString().equals("")) {//if the user doesn't answer  "correctly" or incorrectly"
            Toast.makeText(this, "Please make any choice", Toast.LENGTH_SHORT).show();
        } else {


            if (answer_7.getText().toString().equals("C") || answer_7.getText().toString().equals("c")) {
                if (i == 7) {
                    score = score + 2;
                    reaction.setText(getString(R.string.yes) + " " + whatIsYourName.getText().toString() + "!");
                } else if (i == 8 | i == 9) {
                    reaction.setText(R.string.the_sentence_wasnt_correct);
                }
                question_1.setVisibility(View.GONE);
                answer_7.setVisibility(View.GONE);
                submit_edit_text.setVisibility(View.GONE);
                reaction.setVisibility(View.VISIBLE);
                line5.setVisibility(View.VISIBLE);
                relative_layout_score.setVisibility(View.VISIBLE);
                display_score.setText("  " + score);
                line7.setVisibility(View.VISIBLE);
                relative_layout_questions_left.setVisibility(View.VISIBLE);
                display_questions_left.setText(" " + (9 - i));
                line8.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);

            } else {
                if (answer_7.getText().toString().equals("I") || answer_7.getText().toString().equals("i")) {
                    if (i == 7) {
                        reaction.setText(R.string.it_was_correct);
                    } else if (i == 8 | i == 9) {
                        score = score + 2;
                        reaction.setText(getString(R.string.congrats_reaction));
                    }
                    question_1.setVisibility(View.GONE);
                    answer_7.setVisibility(View.GONE);
                    submit_edit_text.setVisibility(View.GONE);
                    reaction.setVisibility(View.VISIBLE);
                    line5.setVisibility(View.VISIBLE);
                    relative_layout_score.setVisibility(View.VISIBLE);
                    display_score.setText("  " + score);
                    line7.setVisibility(View.VISIBLE);
                    relative_layout_questions_left.setVisibility(View.VISIBLE);
                    display_questions_left.setText(" " + (9 - i));
                    line8.setVisibility(View.VISIBLE);
                    next.setVisibility(View.VISIBLE);

                } else {
                    Toast.makeText(this, "Please write C or I", Toast.LENGTH_SHORT).show();
                }
            }
        }
        if (i == 9) {//when the user submit his answer to the last question, I need the "NEXT" button disappear
            next.setVisibility(View.GONE);
        }
    }

    public void updateQuestion(View view) {
        //update the question and the possible answers
        // after the user press "Next question" button

        //update the question
        question = "question_" + (i + 1);
        questionID = getResources().getIdentifier(question, "string", getPackageName());
        question = getResources().getString(questionID);
        question_1.setText(whatIsYourName.getText().toString() + ", " + question);
        question_1.setVisibility(View.VISIBLE);

        if (i < 3) {//update the answers for first 3 questions - radioButtons
            rd.setVisibility(View.VISIBLE);//make the radioGroup visible

            answer1 = "answer" + (i + 1) + "_1";
            answer2 = "answer" + (i + 1) + "_2";
            answer3 = "answer" + (i + 1) + "_3";
            answer4 = "answer" + (i + 1) + "_4";

            int answer1ID = getResources().getIdentifier(answer1, "string", getPackageName());
            answer1 = getResources().getString(answer1ID);
            answer1_1.setText(answer1);

            int answer2ID = getResources().getIdentifier(answer2, "string", getPackageName());
            answer2 = getResources().getString(answer2ID);
            answer1_2.setText(answer2);

            int answer3ID = getResources().getIdentifier(answer3, "string", getPackageName());
            answer3 = getResources().getString(answer3ID);
            answer1_3.setText(answer3);

            int answer4ID = getResources().getIdentifier(answer4, "string", getPackageName());
            answer4 = getResources().getString(answer4ID);
            answer1_4.setText(answer4);

            next.setVisibility(View.GONE);
            submit_radio_button.setVisibility(View.VISIBLE);
            line3.setVisibility(View.VISIBLE);
            line4.setVisibility(View.VISIBLE);
        }

        if (i >= 3 && i <= 5) { //questions 3-6 - CheckBoxes
            rd.setVisibility(View.GONE); //make RagioGroup gone
            line4.setVisibility(View.GONE);
            line5.setVisibility(View.VISIBLE);
            line6.setVisibility(View.VISIBLE);
            comment.setVisibility(View.VISIBLE);//
            answer4_1.setVisibility(View.VISIBLE);//make CheckBoxes visible
            answer4_2.setVisibility(View.VISIBLE);
            answer4_3.setVisibility(View.VISIBLE);
            answer4_4.setVisibility(View.VISIBLE);
            next.setVisibility(View.GONE);
            submit_check_box.setVisibility(View.VISIBLE);

            answer1CheckBox = "answer" + (i + 1) + "_1";
            answer2CheckBox = "answer" + (i + 1) + "_2";
            answer3CheckBox = "answer" + (i + 1) + "_3";
            answer4CheckBox = "answer" + (i + 1) + "_4";

            int answer1ID = getResources().getIdentifier(answer1CheckBox, "string", getPackageName());
            answer1 = getResources().getString(answer1ID);
            answer4_1.setText(answer1);

            int answer2ID = getResources().getIdentifier(answer2CheckBox, "string", getPackageName());
            answer2 = getResources().getString(answer2ID);
            answer4_2.setText(answer2);

            int answer3ID = getResources().getIdentifier(answer3CheckBox, "string", getPackageName());
            answer3 = getResources().getString(answer3ID);
            answer4_3.setText(answer3);

            int answer4ID = getResources().getIdentifier(answer4CheckBox, "string", getPackageName());
            answer4 = getResources().getString(answer4ID);
            answer4_4.setText(answer4);

            Toast.makeText(this, "Scroll down to continue", Toast.LENGTH_SHORT).show();

        }

        if (i >= 6 && i <= 8) { //questions 7-9 - EditTexts
            //make unnecessary views gone
            answer4_1.setVisibility(View.GONE);
            answer4_2.setVisibility(View.GONE);
            answer4_3.setVisibility(View.GONE);
            answer4_4.setVisibility(View.GONE);
            rd.setVisibility(View.GONE);
            line6.setVisibility(View.GONE);
            next.setVisibility(View.GONE);
            comment.setVisibility(View.GONE);
            answer_7.setVisibility(View.VISIBLE);//make an EditText visible
            submit_edit_text.setVisibility(View.VISIBLE);//make a "submit my answer" button visible

            Toast.makeText(this, "Scroll down to continue", Toast.LENGTH_SHORT).show();

        }

        if (i == 8) { //it's a last question. makes "NEXT" button gone and "GET RESULT" button visible

            next.setVisibility(View.GONE);
            solution.setVisibility(View.VISIBLE);//make a "GET RESULT" button visible
        }
        // clear the radioGroup, checkboxes and editText from previous answers
        rd.clearCheck();
        answer4_1.setChecked(false);
        answer4_2.setChecked(false);
        answer4_3.setChecked(false);
        answer4_4.setChecked(false);
        answer_7.setText("");
        // hide my previous reaction to users answer
        reaction.setVisibility(View.GONE);
        line5.setVisibility(View.GONE);
        relative_layout_score.setVisibility(View.GONE);
        line7.setVisibility(View.GONE);
        relative_layout_questions_left.setVisibility(View.GONE);
        line8.setVisibility(View.GONE);

        i++;

        // scroll back to the question
        question_1.getParent().requestChildFocus(question_1, question_1);
    }

    public void solution(View view) {

        NameOfTheTest.setVisibility(View.VISIBLE);
        line5.setVisibility(View.GONE);
        line6.setVisibility(View.GONE);

        line8.setVisibility(View.GONE);
        relative_layout_score.setVisibility(View.VISIBLE);
        display_score.setText("  " + score * 100 / 18 + "%");
        shareCheckBox.setVisibility(View.VISIBLE);
        reaction.setVisibility(View.GONE);
        relative_layout_questions_left.setVisibility(View.GONE);
        display_questions_left.setVisibility(View.GONE);
        solution.setVisibility(View.GONE);
        Toast.makeText(this, "Your score is " + score * 100 / 18 + "%", Toast.LENGTH_SHORT).show();
    }

    public void share(View view) {/* Figure out if the user wants to share the result*/
        boolean share = shareCheckBox.isChecked();
        switch (view.getId()) {
            case R.id.share:
                if (share) {/*user can share the Quiz result with friends (ONLY A TEXT,NOT A PICTURE)*/
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.name_of_the_test) + ". Your score, " + whatIsYourName.getText().toString() + ", is:  " + score * 100 / 18 + "%");
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                }
        }
    }

    public void reset(View view) {
        // clear the radioGroup from last answer.Hide the reactions. Hide the result.
        rd.clearCheck();
        reaction.setVisibility(View.GONE);
        solution.setVisibility(View.GONE);
        shareCheckBox.setVisibility(View.GONE);
        NameOfTheTest.setVisibility(View.GONE);
        answer4_1.setVisibility(View.GONE);
        answer4_2.setVisibility(View.GONE);
        answer4_3.setVisibility(View.GONE);
        answer4_4.setVisibility(View.GONE);
        answer_7.setVisibility(View.GONE);
        comment.setVisibility(View.GONE);
        submit_check_box.setVisibility(View.GONE);
        submit_edit_text.setVisibility(View.GONE);
        relative_layout_score.setVisibility(View.GONE);
        relative_layout_questions_left.setVisibility(View.GONE);
        display_questions_left.setVisibility(View.VISIBLE);
        next.setVisibility(View.VISIBLE);
        line3.setVisibility(View.VISIBLE);
        line4.setVisibility(View.VISIBLE);

        line1.setVisibility(View.GONE);
        line2.setVisibility(View.GONE);
        line5.setVisibility(View.GONE);
        line6.setVisibility(View.GONE);
        line7.setVisibility(View.GONE);
        line8.setVisibility(View.GONE);


        //update the score
        score = 0;
        i = 0;

        //update the question back to the first question with 4 possible answers

        question_1.setText(whatIsYourName.getText().toString() + ", " + getString(R.string.question_1));
        question_1.setVisibility(View.VISIBLE);

        answer1_1.setText(getString(R.string.answer1_1));

        answer1_2.setText(getString(R.string.answer1_2));

        answer1_3.setText(getString(R.string.answer1_3));

        answer1_4.setText(getString(R.string.answer1_4));

        rd.setVisibility(View.VISIBLE);

        hscrollViewMain.scrollTo(0, 0); /* scroll to application top*/

        i++;
    }

}

