package nl.ordina.brewery.recipe.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import nl.ordina.brewery.entity.Kettle;
import nl.ordina.brewery.entity.KettleAction;

import static java.util.Collections.emptyList;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Ignore;
import static org.mockito.Mockito.*;

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

  @Ignore
  @Test
  public void isDone_noActions() {
//    when(recipe.getSteps()).thenReturn(Arrays.asList(step));
    when(step.getActions()).thenReturn(emptyList());

    RecipeExecutor executor = new RecipeExecutor(recipe);

    assertTrue(executor.isDone());
  }

  @Ignore
  @Test
  public void isDone_oneAction() {
//    when(recipe.getSteps()).thenReturn(Arrays.asList(step));
    when(step.getActions()).thenReturn(Arrays.asList(action));

    RecipeExecutor executor = new RecipeExecutor(recipe);

    assertFalse(executor.isDone());
  }

  @Ignore
  @Test
  public void nextAction() {
//    when(recipe.getSteps()).thenReturn(Arrays.asList(step, step, step));
    when(step.getActions()).thenReturn(Arrays.asList(action, action));

    RecipeExecutor executor = new RecipeExecutor(recipe);

//    while(!executor.isDone()) executor.nextAction(kettle);

    assertTrue(executor.isDone());
    verify(step, times(3)).getActions();
    verify(action, times(6)).executeFor(kettle);
  }

}
