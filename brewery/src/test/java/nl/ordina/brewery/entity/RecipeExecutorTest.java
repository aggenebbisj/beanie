package nl.ordina.brewery.entity;

import nl.ordina.brewery.recipe.entity.Recipe;
import nl.ordina.brewery.recipe.entity.RecipeExecutor;
import nl.ordina.brewery.recipe.entity.Step;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static java.util.Collections.emptyList;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecipeExecutorTest {

  @Mock
  Recipe recipe;
  @Mock
  Step step;
  @Mock
  KettleAction action;
  @Mock
  Kettle kettle;

  @Test
  public void isDone_noSteps() {
    when(recipe.getSteps()).thenReturn(emptyList());

    RecipeExecutor executor = new RecipeExecutor(recipe);

    assertTrue(executor.isDone());
  }

  @Test
  public void isDone_noActions() {
    when(recipe.getSteps()).thenReturn(Arrays.asList(step));
    when(step.getActions()).thenReturn(emptyList());

    RecipeExecutor executor = new RecipeExecutor(recipe);

    assertTrue(executor.isDone());
  }

  @Test
  public void isDone_oneAction() {
    when(recipe.getSteps()).thenReturn(Arrays.asList(step));
    when(step.getActions()).thenReturn(Arrays.asList(action));

    RecipeExecutor executor = new RecipeExecutor(recipe);

    assertFalse(executor.isDone());
  }

  @Test
  public void nextAction() {
    when(recipe.getSteps()).thenReturn(Arrays.asList(step, step, step));
    when(step.getActions()).thenReturn(Arrays.asList(action, action));

    RecipeExecutor executor = new RecipeExecutor(recipe);

    while(!executor.isDone()) executor.nextAction(kettle);

    assertTrue(executor.isDone());
    verify(step, times(3)).getActions();
    verify(action, times(6)).executeFor(kettle);
  }

}
