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
    void shouldCheckDailyCaloriesDemand(ActivityLevel level) {
        User user = TestConstants.TEST_USER;
        int expectedCalories = TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(level);

        int result = planner.calculateDailyCaloriesDemand(user, level);

        Assertions.assertEquals(expectedCalories, result);

    }

    @Test
    void shouldCheckDailyIntake() {
        User user = TestConstants.TEST_USER;
        DailyIntake dailyIntake = TestConstants.TEST_USER_DAILY_INTAKE;

        DailyIntake calculatedDailyIntake = planner.calculateDailyIntake(user);

        Assertions.assertEquals(dailyIntake.getCalories(), calculatedDailyIntake.getCalories());
        Assertions.assertEquals(dailyIntake.getProtein(), calculatedDailyIntake.getProtein());
        Assertions.assertEquals(dailyIntake.getFat(), calculatedDailyIntake.getFat());
        Assertions.assertEquals(dailyIntake.getCarbohydrate(), calculatedDailyIntake.getCarbohydrate());
    }
}