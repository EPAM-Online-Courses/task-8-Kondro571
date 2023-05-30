package efs.task.unittests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
class FitCalculatorTest {

    @Test
    void shouldReturnTrue_whenDietRecommended() {
        //given
        double weight = 89.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }
    @Test
    void shouldReturnFalse_whenDietNotRecommended() {
        //given
        double weight = 69.5;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }
    @Test
    void shouldThrowIllegalArgumentException_whenHeightIsZero() {

        double weight = 70.0;
        double height = 0.0;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            FitCalculator.isBMICorrect(weight, height);
        });
    }

    @ParameterizedTest(name = "weight: {0}")
    @ValueSource(doubles = {90.0, 100.0, 99.0})
    void  shouldReturnTrue_whenDietIsRecommended(double weight) {
        //given
        double height = 1.80;

        //when
        boolean result = FitCalculator.isBMICorrect(weight, height);

        //then
        Assertions.assertTrue(result);
    }
    @ParameterizedTest(name = "weight: {0}, height{1}")
    @CsvSource({
            "160.0, 60.0",
            "165.0, 65.0",
            "170.0, 70.0"
    })
    void shouldReturnFalse_whenDietIsNotRecommended(double height, double weight) {
        //given
        //brak potrzeby, dostarczane jako parametry

        //when
        boolean result = FitCalculator.isBMICorrect(weight, height);

        //then
        Assertions.assertFalse(result);
    }

    @ParameterizedTest(name = "height={0}, weight={1}")
    @CsvFileSource(resources = "/data.csv", numLinesToSkip  = 1)
    void shouldReturnFalse_whenDietIsRecommendedFromFile(double height, double weight) {
        //given
        //brak potrzeby, dostarczane z pliku CSV

        //ghen
        boolean result = FitCalculator.isBMICorrect(weight, height);

        //then
        Assertions.assertFalse(result);
    }

    @Test
    void shouldReturnUserWithTheWorstBMI() {
        //given
        List<User> testUsersList = TestConstants.TEST_USERS_LIST;

        //when
        User result = FitCalculator.findUserWithTheWorstBMI(testUsersList);

        //then
        Assertions.assertEquals(97.3, result.getWeight());
        Assertions.assertEquals(1.79, result.getHeight());
    }

    @Test
    void shouldReturnNull_whenListEmpty() {
        //given
        List<User> emptyList = new ArrayList<>();

        //when
        User result = FitCalculator.findUserWithTheWorstBMI(emptyList);

        //then
        Assertions.assertNull(result);
    }

    @Test
    void shouldCalculateBMIScoreEqualToExpected() {
        //given
        List<User> userList = TestConstants.TEST_USERS_LIST;
        double[] expectedScores = TestConstants.TEST_USERS_BMI_SCORE;

        //when
        double[] result = FitCalculator.calculateBMIScore(userList);

        //then
        Assertions.assertArrayEquals(expectedScores, result);
    }
}
