import d15.LensLibrary;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Day 15 {@link LensLibrary}
 */
class TestDay15 {
	
	@Test
	void test1() throws IOException {
		List<String> input = List.of("HASH");
		
		long output = LensLibrary.calculate(input);
		
		assertEquals(52, output);
	}
	
	@Test
	void test2() throws IOException {
		List<String> input = List.of("rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7");
		
		long output = LensLibrary.calculate(input);
		
		assertEquals(1320, output);
	}
	
	@Test
	void test3() throws IOException {
		List<String> input = List.of("rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7");
		
		long output = LensLibrary.calculateB(input);
		
		assertEquals(145, output);
	}
	
}
