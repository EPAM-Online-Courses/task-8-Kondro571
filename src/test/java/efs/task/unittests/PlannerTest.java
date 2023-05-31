package efs.task.unittests;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PlannerTest {

    private Planner planner;

    @BeforeEach
    void setup() {
        planner = new Planner();
    }
    @ParameterizedTest
    @EnumSource(ActivityLevel.class)
    void shouldCalculateCorrectDailyCaloriesDemand(ActivityLevel level) {
        //given
        User user = TestConstants.TEST_USER;
        int expectedCalories = TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(level);

        //when
        int result = planner.calculateDailyCaloriesDemand(user, level);

        //then
        Assertions.assertEquals(expectedCalories, result);

    }

    @Test
    void shouldCalculateCorrectDailyIntake() {
        //given
        User user = TestConstants.TEST_USER;
        DailyIntake dailyIntake = TestConstants.TEST_USER_DAILY_INTAKE;

        //when
        DailyIntake result = planner.calculateDailyIntake(user);

        //then
        Assertions.assertEquals(dailyIntake.getCalories(), result.getCalories());
        Assertions.assertEquals(dailyIntake.getProtein(), result.getProtein());
        Assertions.assertEquals(dailyIntake.getFat(), result.getFat());
        Assertions.assertEquals(dailyIntake.getCarbohydrate(), result.getCarbohydrate());
    }
}
