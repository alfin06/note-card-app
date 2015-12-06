package com.notecards.yohaniswarahartono.notecards;

/**********************************************************************/
/*                            Notecard Schema                         */
/**********************************************************************/
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
