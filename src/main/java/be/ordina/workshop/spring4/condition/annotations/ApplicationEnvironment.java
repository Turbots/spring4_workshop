package be.ordina.workshop.spring4.condition.annotations;

public @interface ApplicationEnvironment {

    public static enum Name {
        PRODUCTION("PRD"), TEST("");
        private final String name;

        private Name(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    Name value();
}
