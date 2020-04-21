package bowling.dto;

import bowling.Score;
import bowling.frame.BowlingFrame;

public class BowlingFrameConsoleResult {

    private static final String UN_CALCULATE_TOTAL_SCORE = "";

    private final FrameState frameState;
    private final String totalScore;

    private BowlingFrameConsoleResult(final FrameState frameState, final String totalScore) {
        this.frameState = frameState;
        this.totalScore = totalScore;
    }

    public static BowlingFrameConsoleResult newInstance(final BowlingFrame bowlingFrame, final Score totalScore) {
        return new BowlingFrameConsoleResult(bowlingFrame.makeFrameState(), makeTotalScore(bowlingFrame, totalScore));
    }

    private static String makeTotalScore(final BowlingFrame bowlingFrame, final Score totalScore) {
        if (bowlingFrame.canCalculateScore()) {
            return String.valueOf(totalScore.getScore());
        }

        return UN_CALCULATE_TOTAL_SCORE;
    }

    public FrameState getFrameState() {
        return frameState;
    }

    public String getTotalScore() {
        return totalScore;
    }
}