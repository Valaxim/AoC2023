package d01;

import utils.ParseUtil;

import java.io.IOException;
import java.util.List;

public class Day1 {
    
    public static void main(String[] args) throws IOException {
        List<String> input = ParseUtil.readInputLineByLine("inputDay1.txt");
        
        Solution solution = new Solution();
        List<Integer> dataFromInput = solution.createDataFromInput(input);
        
        int outputA = solution.getInteger(dataFromInput);
        int outputB = solution.getVarTop3(dataFromInput);
        
        System.out.println("Answer Day1 partA: " + outputA);
        System.out.println("Answer Day1 partB: " + outputB);
    }
}

