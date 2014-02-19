package apriori;

import java.util.*;

public class FKMinus1F1Apriori<V> extends BaseApriori<V> {

	public FKMinus1F1Apriori(List<ItemSet<V>> transactions) {
		super(transactions);
	}

	@Override
    public List<ItemSet<V>> aprioriGen(List<ItemSet<V>> frequentCandidatesKMinus1) {

        Collections.sort(frequentCandidatesKMinus1);
        int allGeneratedCandidatesCounter = 0;
        Set<ItemSet<V>> frequentCandidateSet = new HashSet<ItemSet<V>>();
        // we iterate both itemsets and generate new candidates base on their
        // combinations

        for (int i = 0; i < super.frequent1Itemsets.size(); i++) {
            ItemSet<V> itemSet1 = super.frequent1Itemsets.get(i);

            for (int j = 0; j < frequentCandidatesKMinus1.size(); j++) {
                ItemSet<V> itemSet2 = frequentCandidatesKMinus1.get(j);
                ItemSet<V> union = itemSet1.union(itemSet2);
                if(union.size()>itemSet2.size()){
                    allGeneratedCandidatesCounter++;
                    getAndCacheSupportForItemset(union);
                    frequentCandidateSet.add(union);
                }
            }
        }

        System.out.println(allGeneratedCandidatesCounter
                + " total, unique itemsets: " + frequentCandidateSet.size());
        return new LinkedList<ItemSet<V>>(frequentCandidateSet);
    }
}
