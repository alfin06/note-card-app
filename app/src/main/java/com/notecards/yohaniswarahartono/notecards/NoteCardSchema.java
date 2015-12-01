package com.notecards.yohaniswarahartono.notecards;

/**
 * Created by Alfin Rahardja on 11/27/2015.
 */
public class NoteCardSchema {
    public static final class NoteCardTable {
        public static final String NAME = "Subject";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
        }
    }
}
