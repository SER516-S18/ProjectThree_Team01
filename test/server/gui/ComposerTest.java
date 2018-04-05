package server.gui;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ComposerTest {
  EmotivComposer frame = EmotivComposer.getInstance();

  @Test
  public void testStartServer() {
    assertTrue(EmotivComposer.isStarted);
  }
}
