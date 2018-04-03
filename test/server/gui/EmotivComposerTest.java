package server.gui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class EmotivComposerTest {
  EmotivComposer emotivComposer = EmotivComposer.getInstance();

  @Test
  public void testStartServer() {
    assertTrue(EmotivComposer.isStarted);

  }
}
