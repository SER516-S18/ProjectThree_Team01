package server.gui;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

class EmotivComposerTest {
  EmotivComposer emotivComposer = EmotivComposer.getInstance();

  @Test
  public void testStartServer() {
    assertTrue(EmotivComposer.isStarted);
  }
}
