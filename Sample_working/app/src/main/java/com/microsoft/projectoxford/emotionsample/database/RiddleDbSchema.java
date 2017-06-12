package com.microsoft.projectoxford.emotionsample.database;

/**
 * Created by Viji on 5/10/2017.
 */

public class RiddleDbSchema {

    public static final class RiddleTable {
        public static final String NAME = "riddles";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
           public static final String FIELD = "field";
            public static final String SOLVED = "solved";
        }
    }
}
