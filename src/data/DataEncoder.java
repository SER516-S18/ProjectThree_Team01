package data;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class DataEncoder implements Encoder.Text<EmotivData> {

  @Override
  public void destroy() {
    // TODO Auto-generated method stub

  }

  @Override
  public void init(EndpointConfig arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public String encode(EmotivData arg0) throws EncodeException {
    // TODO Auto-generated method stub
    return null;
  }

}
