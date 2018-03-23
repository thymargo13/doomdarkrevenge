package Command;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SettingTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSetting() {
		Setting s = new Setting();
		
		if (s.equals(0) == true) {
			System.out.println("Sound muted");
		} else if(s.equals(1) == true){
			System.out.println("Sound unmuted");
		} else if (s.equals(2) == true){
			System.out.println("Cancel button clicked");
		}
			
	}
}
