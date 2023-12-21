package d19;

import java.util.List;
import java.util.Optional;

public record Workflow(String name, List<Rule> ruleList) {
	
	public String apply(Part part) {
		for (Rule rule : ruleList) {
			Optional<String> result = rule.apply(part);
			if (result.isPresent()) {
				return result.get();
			}
		}
		throw new IllegalArgumentException("For part " + part + ", this workflow does not work " + this);
	}
}

