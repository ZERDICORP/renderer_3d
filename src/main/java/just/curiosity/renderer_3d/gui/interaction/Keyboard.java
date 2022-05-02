package just.curiosity.renderer_3d.gui.interaction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class Keyboard implements KeyListener {
  private static int keyCode;

  public int getCurrentKeyCode() {
    return keyCode;
  }

  @Override
  public void keyPressed(KeyEvent keyEvent) {
    keyCode = keyEvent.getKeyCode();
  }

  @Override
  public void keyReleased(KeyEvent keyEvent) {
    keyCode = -1;
  }

  @Override
  public void keyTyped(KeyEvent keyEvent) {

  }
}
