package data;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class DataDecoder implements Decoder.Text<EmotivData> {

  @Override
  public void destroy() {
    // TODO Auto-generated method stub

  }

  @Override
  public void init(EndpointConfig arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public EmotivData decode(String arg0) throws DecodeException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean willDecode(String arg0) {
    // TODO Auto-generated method stub
    return false;
  }

}
