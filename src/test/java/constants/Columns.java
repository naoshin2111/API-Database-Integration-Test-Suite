package constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Columns {

    public static final String ID = "id";
    public static final String NAME = "name";

    public static class Author {
        public static final String LOG_IN = "login";
        public static final String EMAIL = "email";
    }

    public static class Test {
        public static final String STATUS_ID = "status_id";
        public static final String METHOD_NAME = "method_name";
        public static final String PROJECT_ID = "project_id";
        public static final String SESSION_ID = "session_id";
        public static final String END_TIME = "end_time";
        public static final String ENV = "env";
        public static final String BROWSER = "browser";
        public static final String AUTHOR_ID = "author_id";
    }
}
