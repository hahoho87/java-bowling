package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import bowling.domain.score.Score;

public class FramesTest {
    @Test
    void 생성_테스트() {
        // given
        Frames frames = Frames.init();
        List<Frame> frameList = frames.frames();
        // when & then
        assertThat(frameList.size()).isEqualTo(1);
        assertThat(frameList.get(0)).isInstanceOf(NormalFrame.class);
    }

    @Test
    void 현재_프레임_종료_다음_프레임_생성_테스트() {
        // given
        Frames frames = Frames.init();
        // when
        frames.bowl(3);
        frames.bowl(3);
        // then
        assertThat(frames.frames().size()).isEqualTo(2);
    }

    @Test
    void 현재_프레임_미종료_다음_프레임_미생성_테스트() {
        // given
        Frames frames = Frames.init();
        // when
        frames.bowl(3);
        // then
        assertThat(frames.frames().size()).isEqualTo(1);
    }

    @Test
    void 전체_게임_종료_테스트() {
        // given
        Frames frames = Frames.init();
        // when
        IntStream.range(0, 9).forEach(i -> {
            frames.bowl(10);
        });
        frames.bowl(3);
        frames.bowl(3);
        // then
        assertThat(frames.isDone()).isTrue();
    }


    @Test
    void 보너스_게임_종료_테스트_1() {
        // given
        Frames frames = Frames.init();
        // when
        IntStream.range(0, 9).forEach(i -> {
            frames.bowl(10);
        });
        frames.bowl(3);
        frames.bowl(7);
        frames.bowl(7);
        // then
        assertThat(frames.isDone()).isTrue();
    }

    @Test
    void 보너스_게임_종료_테스트_2() {
        // given
        Frames frames = Frames.init();
        // when
        IntStream.range(0, 9).forEach(i -> {
            frames.bowl(10);
        });
        frames.bowl(10);
        frames.bowl(7);
        frames.bowl(3);
        // then
        assertThat(frames.isDone()).isTrue();
    }

    @Test
    void 보너스_게임_종료_테스트_3() {
        // given
        Frames frames = Frames.init();
        // when
        IntStream.range(0, 9).forEach(i -> {
            frames.bowl(10);
        });
        frames.bowl(10);
        frames.bowl(10);
        frames.bowl(10);
        // then
        assertThat(frames.isDone()).isTrue();
    }

    @Test
    void 게임_점수_테스트_1() {
        // given
        Frames frames = Frames.init();
        // when
        IntStream.range(0, 9).forEach(i -> {
            frames.bowl(10);
        });
        frames.bowl(10);
        frames.bowl(10);
        frames.bowl(10);
        // then
        List<Score> scores = frames.scores();
        assertThat(scores.get(0).score()).isEqualTo(30);
        assertThat(scores.get(9).score()).isEqualTo(300);
    }

    @Test
    void 게임_점수_테스트_2() {
        // given
        Frames frames = Frames.init();
        // when
        IntStream.range(0, 9).forEach(i -> {
            frames.bowl(10);
        });
        frames.bowl(5);
        frames.bowl(2);
        // then
        List<Score> scores = frames.scores();
        assertThat(scores.get(0).score()).isEqualTo(30);
        assertThat(scores.get(6).score()).isEqualTo(210);
        assertThat(scores.get(7).score()).isEqualTo(235);
        assertThat(scores.get(8).score()).isEqualTo(252);
        assertThat(scores.get(9).score()).isEqualTo(259);
    }

    @Test
    void 게임_점수_테스트_3() {
        // given
        Frames frames = Frames.init();
        // when
        IntStream.range(0, 10).forEach(i -> {
            frames.bowl(5);
            frames.bowl(3);
        });
        // then
        List<Score> scores = frames.scores();
        assertThat(scores.get(0).score()).isEqualTo(8);
        assertThat(scores.get(9).score()).isEqualTo(80);
    }
}