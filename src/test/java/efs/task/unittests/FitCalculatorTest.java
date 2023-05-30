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
    void shouldReturnFalse_whenDietRecommended() {
        //given
        double weight = 69.5;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }
    @Test
    void testIsBMICorrect_ThrowsIllegalArgumentException() {
        double weight = 70.0; // dowolna waga
        double height = 0.0; // wzrost równy 0.0
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            FitCalculator.isBMICorrect(weight, height);
        });
    }

    @ParameterizedTest(name = "weight: {0}")
    @ValueSource(doubles = {90.0, 100.0, 99.0})
    void testIsBMICorrect_ReturnsTrue(double weight) {
        double height = 1.80; // wybrany wzrost
        boolean result = FitCalculator.isBMICorrect(weight, height);
        Assertions.assertTrue(result);
    }
    @ParameterizedTest(name = "weight: {0}, height{1}")
    @CsvSource({
            "160.0, 60.0",
            "165.0, 65.0",
            "170.0, 70.0"
    })
    void testIsBMICorrect_ReturnsFalse(double height, double weight) {
        boolean result = FitCalculator.isBMICorrect(weight, height);
        Assertions.assertFalse(result);
    }

    @ParameterizedTest(name = "height={0}, weight={1}")
    @CsvFileSource(resources = "/data.csv", numLinesToSkip  = 1)
    void shouldReturnFalse_whenDietIsRecommendedFromFile(double height, double weight) {
        boolean result = FitCalculator.isBMICorrect(weight, height);
        Assertions.assertFalse(result);
    }

    @Test
    void testFindUserWithTheWorstBMI_ReturnsUserWithWorstBMI() {
        User result = FitCalculator.findUserWithTheWorstBMI(TestConstants.TEST_USERS_LIST);
        Assertions.assertEquals(97.3, result.getWeight());
        Assertions.assertEquals(1.79, result.getHeight());
    }

    @Test
    void testFindUserWithTheWorstBMI_EmptyList_ReturnsNull() {
        List<User> emptyList = new ArrayList<>();
        User result = FitCalculator.findUserWithTheWorstBMI(emptyList);
        Assertions.assertNull(result);
    }

    @Test
    void testCalculateBMIScore_ReturnsExpectedScores() {
        List<User> userList = TestConstants.TEST_USERS_LIST;
        double[] expectedScores = TestConstants.TEST_USERS_BMI_SCORE;

        double[] result = FitCalculator.calculateBMIScore(userList);

        Assertions.assertArrayEquals(expectedScores, result);
    }
}
