package filedependencies.tools;

public class SplitterAndJoiner {

    private final String workLine;

    public SplitterAndJoiner(String lineToSeparate) {
        workLine = lineToSeparate;
    }

    public String splitAndJoin(String separatorForSplit, String separatorForJoin) {
        String[] splitParts = workLine.split(separatorForSplit);
        return String.join(separatorForJoin, splitParts);
    }

}
