package examples.hardware;

import com.ev34j.core.common.Platform;
import com.ev34j.core.common.PlatformType;

//gradle clean build
//java -cp ./build/libs/ev3-lang-java-0.3.1-SNAPSHOT.jar PlatformTest
public class PlatformTest {

  public static void main(String[] args) {
    Platform platform = new Platform();
    PlatformType value = Platform.getPlatform();
    System.out.println(value);
    System.exit(0);
  }

}
