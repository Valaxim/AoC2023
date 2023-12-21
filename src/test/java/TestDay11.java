import d11.CosmicExpansion;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import utils.ParseUtil;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TestDay11 {
	
	@ParameterizedTest
	@CsvSource({
			"2, 374",
			"10, 1030",
			"100, 8410"
	})
	void testCalculate(long inputParameter, long expectedOutput) throws IOException {
		char[][] input = ParseUtil.read2DArray("testInputDay11.txt");
		
		long output = CosmicExpansion.calculate(input, inputParameter);
		
		assertEquals(expectedOutput, output);
	}
}

