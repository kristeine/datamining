package apriori;

import java.util.*;

public class FkMinus1FKMinus1<V> extends BaseApriori<V> {

	public FkMinus1FKMinus1(List<ItemSet<V>> transactions) {
		super(transactions);
	}

	@Override
    public List<ItemSet<V>> aprioriGen(List<ItemSet<V>> frequentCandidatesKMinus1) {

        Collections.sort(frequentCandidatesKMinus1);
        int allGeneratedCandidatesCounter = 0;
        Set<ItemSet<V>> frequentCandidateSet = new HashSet<ItemSet<V>>();
        // we iterate both itemsets and generate new candidates base on their
        // combinations

        for (ItemSet<V> itemSet1 : frequentCandidatesKMinus1) {

            inner : for (ItemSet<V> itemSet2: frequentCandidatesKMinus1) {

                Iterator<V> iterator1 = itemSet1.getItems().iterator();
                Iterator<V> iterator2 = itemSet1.getItems().iterator();

                while (iterator1.hasNext()){
                    if (iterator1.next()!= iterator2.next() && !iterator1.hasNext()) {
                        break inner;
                    }
                }
                ItemSet<V> union = itemSet1.union(itemSet2);
                if(union.size() <= itemSet1.size()) {break;}

                allGeneratedCandidatesCounter++;
                getAndCacheSupportForItemset(union);
                frequentCandidateSet.add(union);
            }
        }

        System.out.println(allGeneratedCandidatesCounter
                + " total, unique itemsets: " + frequentCandidateSet.size());
        return new LinkedList<ItemSet<V>>(frequentCandidateSet);
    }

}
