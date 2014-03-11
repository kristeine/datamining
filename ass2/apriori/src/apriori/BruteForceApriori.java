package apriori;

import java.util.List;

public class BruteForceApriori<V> extends BaseApriori<V> {

	public BruteForceApriori(List<ItemSet<V>> transactions) {
		super(transactions);
	}

	@Override
	public void apriori(Double minSupport) {
		// get frequent 1-itemsets
		List<ItemSet<V>> candidatesLevel1 = getAllItemsetsOfSizeOne();
		System.out.println("Level 1:");
		System.out.println("\tGenerated " + candidatesLevel1.size()
				+ " candidates.");
		System.out.println("\t\t" + candidatesLevel1);

		// prune 1-itemsets
		List<ItemSet<V>> frequentCandidatesLevel = candidatesLevel1;
		System.out.println("\tKept " + frequentCandidatesLevel.size()
				+ " frequent itemsets");
		System.out.println("\t\t" + frequentCandidatesLevel);
		// storing frequent 1-itemsets for a later time
		frequent1Itemsets = frequentCandidatesLevel;

		// store in our list of frequent itemsets
		frequentItemSets.put(1, frequentCandidatesLevel);

		// create the higher levels as long as we still produce frequent
		// itemsets
		for (int i = 2;; i++) {
			// generate candidates for level i
			System.out.println("Level " + i);

			// generate candidates
			List<ItemSet<V>> generateCandidateSizeK = aprioriGen(frequentCandidatesLevel);
			System.out.println("\tGenerated " + generateCandidateSizeK.size()
					+ " candidates.");
			System.out.println("\t\t" + generateCandidateSizeK);

			frequentCandidatesLevel = generateCandidateSizeK;

			System.out.println("\tKept " + frequentCandidatesLevel.size()
					+ " frequent itemsets");
			if (frequentCandidatesLevel.size() == 0) {
				System.out
						.println("We're done here, there's no more frequent itemsets");
				break;
			}
			// store in our list of frequent itemsets
			frequentItemSets.put(i, frequentCandidatesLevel);
			System.out.println("\t\t" + frequentCandidatesLevel);
		}
	}

}
