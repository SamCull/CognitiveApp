package com.vogella.android.cognitiveapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import com.vogella.android.cognitiveapp.QuizContract.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CognitiveAppDatabase.db";
    private static final int DATABASE_VERSION = 1;


    private static QuizDbHelper instance;
    private TextView textViewHighscore;

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //textViewHighscore = textViewHighscore.findViewById(R.id.text_view_highscore);
        this.db = db;

        final String SQL_CREATE_SCORE_TABLE = "CREATE TABLE " +
                QuizContract.ScoreTable.TABLE_NAME + "( " +
                QuizContract.ScoreTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.ScoreTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.ScoreTable.COLUMN_ANSWER_NR + " TEXT, " +
                QuizContract.ScoreTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuizContract.ScoreTable.COLUMN_CATEGORY_ID + " TEXT, " +
                QuizContract.ScoreTable.COLUMN_SCORE + " TEXT " +
                ")";


        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                QuizContract.CategoriesTable.TABLE_NAME + "( " +
                QuizContract.CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuizContract.QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                QuizContract.QuestionsTable.COLUMN_SCORE.toString() + " INTEGER, " +
                "FOREIGN KEY(" + QuizContract.QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                QuizContract.CategoriesTable.TABLE_NAME + "(" + QuizContract.CategoriesTable._ID + ")" + "ON DELETE CASCADE" +

                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        //db.execSQL(SQL_CREATE_SCORE_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
        //db.execSQL("DROP TABLE IF EXISTS " + QuizContract.ScoreTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        Category c1 = new Category("History");
        addCategory(c1);
        Category c2 = new Category("Geography");
        addCategory(c2);
        Category c3 = new Category("Math");
        addCategory(c3);
    }
    private void fillScoresTable(){

    }

    private void addCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(QuizContract.CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {
        // HISTORY | EASY //
        Question q1 = new Question("Where was the titanic headed to before sinking?",
                "Japan", "Ireland", "USA", 3,
                Question.DIFFICULTY_EASY, Category.History);
        addQuestion(q1);
        Question q2 = new Question("Who was the leader of Russia during the World War II?",
                "Josef Stalin", "Adolf Hitler", "Michael Collins", 1,
                Question.DIFFICULTY_EASY, Category.History);
        addQuestion(q2);
        Question q3 = new Question("What day is Christmas day in Ireland?",
                "25th October", "25th December", "25th November", 2,
                Question.DIFFICULTY_EASY, Category.History);
        addQuestion(q3);
        Question q4 = new Question("Who was president of the USA during the Cuban Missile Crisis?",
                "Barack Obama", "John F. Kennedy", "Donald Trump", 2,
                Question.DIFFICULTY_EASY, Category.History);
        addQuestion(q4);
        Question q5 = new Question("Which ancient people built the pyramids?",
                "The Irish", "The Cavemen", "The Egyptians", 3,
                Question.DIFFICULTY_EASY, Category.History);
        addQuestion(q5);
        Question q6 = new Question("Which town was Jesus born?",
                "Medina", "Bethelehem", "Vatican City", 2,
                Question.DIFFICULTY_EASY, Category.History);
        addQuestion(q6);
        Question q7 = new Question("Who first stepped on the mood?",
                "Neil Armstrong", "Christopher Columbus", "Buzz Lightyear", 1,
                Question.DIFFICULTY_EASY, Category.History);
        addQuestion(q7);
        Question q8 = new Question("Which of the following wars came first?",
                "World War II", "The Vietnam War", "World War I", 3,
                Question.DIFFICULTY_EASY, Category.History);
        addQuestion(q8);
        Question q9 = new Question("Who invented the lightbulb?",
                "Thomas Edison", "Alexander Graham Bell", "John Peirce", 1,
                Question.DIFFICULTY_EASY, Category.History);
        addQuestion(q9);
        Question q10 = new Question("Who was the leader of the Nazi Party?",
                "Winston Churchill", "Adolf Hitler", "Vladimir Putin", 2,
                Question.DIFFICULTY_EASY, Category.History);
        addQuestion(q10);
        // HISTORY | MEDIUM //

        Question q11 = new Question("What sank the SS Titanic?",
                "A storm", "An iceberg", "A lightning strike", 2,
                Question.DIFFICULTY_MEDIUM, Category.History);
        addQuestion(q11);
        Question q12 = new Question("Which country gifted the Statue of Liberty?",
                "Spain", "Italy", "France", 3,
                Question.DIFFICULTY_MEDIUM, Category.History);
        addQuestion(q12);
        Question q13 = new Question("Which country first used paper money?",
                "China", "Russia", "Spain", 1,
                Question.DIFFICULTY_MEDIUM, Category.History);
        addQuestion(q13);
        Question q14 = new Question("Which city was destroyed by Mt. Vesuvies?",
                "Alexandria", "Rome", "Pompeii", 2,
                Question.DIFFICULTY_MEDIUM, Category.History);
        addQuestion(q14);
        Question q15 = new Question("Who said 'I have a dream' ?",
                "Martin Luther King", "Ghandhi", "Joe Biden", 1,
                Question.DIFFICULTY_MEDIUM, Category.History);
        addQuestion(q15);

        // HISTORY | HARD //
        Question q16 = new Question("What color was the Statue of Liberty originally?",
                "Yellow", "Green", "Copper", 3,
                Question.DIFFICULTY_HARD, Category.History);
        addQuestion(q16);
        Question q17 = new Question("Who did Henry VIII first marry?",
                "Meghan Markle", "Catherine of Aragon", "Kate Middleton", 2,
                Question.DIFFICULTY_HARD, Category.History);
        addQuestion(q17);
        Question q18 = new Question("Who painted the Mona Lisa?",
                "Leonardo da Vinci", "Michelangelo", "Vincent van Gogh", 1,
                Question.DIFFICULTY_HARD, Category.History);
        addQuestion(q18);
        Question q19 = new Question("What came after the Bronze Age?",
                "The Silver Age", "The Stone Age", "The Iron Age", 3,
                Question.DIFFICULTY_HARD, Category.History);
        addQuestion(q19);
        Question q20 = new Question(" According to popular myth, who chased all the snakes out of Ireland?",
                "St Dunne", "St Patrick", "St Peters", 2,
                Question.DIFFICULTY_HARD, Category.History);
        addQuestion(q20);

        // GEOGRAPHY | EASY //
        Question q21 = new Question("What is the largest ocean in the world?",
                "Atlantic Ocean", "Artic Ocean", "The Pacific Ocean", 3,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        addQuestion(q21);
        Question q22 = new Question("What country has the largest population in the world?",
                "The USA", "Russia", "China", 3,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        addQuestion(q22);
        Question q23 = new Question("How many continents are in the world",
                "Six", "Seven", "Eight", 2,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        addQuestion(q23);
        Question q24 = new Question("What is the name of the largest country in the world?",
                "Russia", "Spain", "China", 1,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        addQuestion(q24);
        Question q25 = new Question("What is the name of the largest mountain in the world?",
                "Mt Leinster", "Mt Everest", "Mt Vesuvius", 2,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        addQuestion(q25);

        // GEOGRAPHY | MEDIUM //
        Question q26 = new Question("What country has the largest population in the world?",
                "The USA", "Russia", "China", 3,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        addQuestion(q26);
        Question q27 = new Question("Where is the Eiffel Tower located?",
                "France", "Italy", "England", 1,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        addQuestion(q27);
        Question q28 = new Question("What is the coldest place on Earth?",
                "Russia", "Antartica", "India", 2,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        addQuestion(q28);
        Question q29 = new Question("What country lies above Mexico?",
                "Brazil", "The USA", "Canada", 2,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        addQuestion(q29);
        Question q30 = new Question("What planet is closest to Earth?",
                "Mercury", "Saturn", "Venus", 3,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        addQuestion(q30);


        // GEOGRAPHY | HARD //
        Question q31 = new Question("How many countries are there in the UK?",
                "Five", "Three", "Four", 3,
                Question.DIFFICULTY_HARD, Category.GEOGRAPHY);
        addQuestion(q31);
        Question q32 = new Question("What is the capital of Nova Scotia",
                "Nova Scotia", "Quebec City", "Ottowa", 1,
                Question.DIFFICULTY_HARD, Category.GEOGRAPHY);
        addQuestion(q32);
        Question q33 = new Question("What season does Australia experience in December?",
                "Winter", "Summer", "Spring", 2,
                Question.DIFFICULTY_HARD, Category.GEOGRAPHY);
        addQuestion(q33);
        Question q34 = new Question("What is the driest continent on Earth?",
                "Europe", "Antartica", "Asia", 2,
                Question.DIFFICULTY_HARD, Category.GEOGRAPHY);
        addQuestion(q34);
        Question q35 = new Question("Mt Fuji is the highest point located in which Asian country?",
                "China", "India", "Japan", 3,
                Question.DIFFICULTY_HARD, Category.GEOGRAPHY);
        addQuestion(q35);


        // MATH | EASY //
        Question q36 = new Question("What is 10 + 15?",
                "30", "25", "20", 2,
                Question.DIFFICULTY_EASY, Category.MATH);
        addQuestion(q36);
        Question q37 = new Question("What is 13+17?",
                "30", "29", "31", 1,
                Question.DIFFICULTY_EASY, Category.MATH);
        addQuestion(q37);
        Question q38 = new Question("What is 20-13?",
                "9", "6", "7", 3,
                Question.DIFFICULTY_EASY, Category.MATH);
        addQuestion(q38);
        Question q39 = new Question("What is 5+12-7?",
                "10", "12", "11", 1,
                Question.DIFFICULTY_EASY, Category.MATH);
        addQuestion(q39);
        Question q40 = new Question("What is 30-15?",
                "10", "20", "15", 3,
                Question.DIFFICULTY_EASY, Category.MATH);
        addQuestion(q40);

        // MATH | MEDIUM //
        Question q41 = new Question("How many lives are cats said to have?",
                "8", "9", "10", 2,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        addQuestion(q41);
        Question q42 = new Question("What is the only even prime number?",
                "0", "1", "2", 3,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        addQuestion(q42);
        Question q43 = new Question("How many cupcakes are in a baker's dozen? ",
                "13", "12", "10", 1,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        addQuestion(q43);
        Question q44 = new Question("What is 5 x 12?",
                "55", "65", "75", 2,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        addQuestion(q44);
        Question q45 = new Question("What is 81 / 9?",
                "5", "11", "9", 3,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        addQuestion(q45);

        // MATH | HARD //
        Question q46 = new Question("What letter does every odd number have in it?",
                "A", "E", "O", 2,
                Question.DIFFICULTY_HARD, Category.MATH);
        addQuestion(q46);
        Question q47 = new Question("What is the name of an angle of less than 90 degrees?",
                "Right Angle", "Obtuse", "Acute angle", 3,
                Question.DIFFICULTY_HARD, Category.MATH);
        addQuestion(q47);
        Question q48 = new Question("What is the next prime number after 7?",
                "11", "13", "9", 1,
                Question.DIFFICULTY_HARD, Category.MATH);
        addQuestion(q48);
        Question q49 = new Question("What is (5 x 10) / 2?",
                "100", "25", "50", 2,
                Question.DIFFICULTY_HARD, Category.MATH);
        addQuestion(q49);
        Question q50 = new Question("What is 25% of 200?",
                "75", "125", "50", 3,
                Question.DIFFICULTY_HARD, Category.MATH);
        addQuestion(q50);


    }



    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        cv.put(QuestionsTable.COLUMN_SCORE, question.getCategoryID());
        db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, cv);
        //db.update(QuestionsTable._ID, cv, "_id = ?", new String[]{});
    }

    public ArrayList<ScoreTable> getScore(){
        ArrayList<ScoreTable> scoreList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + ScoreTable.TABLE_NAME, null);

        if(c.moveToFirst()) {
            do {
                ScoreTable scoretable = new ScoreTable();
                scoretable.getClass();
                scoreList.add(scoretable);
            } while (c.moveToNext());
        }
        c.close();
        return scoreList;
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.CategoriesTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }

        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                question.setScore(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_SCORE)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuizContract.QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuizContract.QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c = db.query(
                QuizContract.QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        //if (c.getCount() == 1) {
           // String mytext = c.getString(c.getColumnIndex("score"));
            //textViewHighscore.setText(textViewHighscore.getText());
        //}
        //c.close();

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_CATEGORY_ID)));
                question.setScore(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_SCORE)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

        //              Retrieve Data
    /*public ArrayList<String> getUsers(){
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(get)
    }*/



}