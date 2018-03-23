package Command;

import static org.junit.Assert.*;

import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExitTest {


	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testExit() {
		Exit e = new Exit();
		
		if (e.equals(0) == true) {
			System.exit(0);
		} else if(e.equals(1) == true){
			System.out.println("No button clicked");
		} else if (e.equals(2) == true){
			System.out.println("Cancel button clicked");
		}
			
	}
		

}
