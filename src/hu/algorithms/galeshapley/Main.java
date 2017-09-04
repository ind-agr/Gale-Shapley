package hu.algorithms.galeshapley;

import java.util.*;

public class Main {

	private static ArrayList<Man> manList = new ArrayList<Man>();
	private static ArrayList<Woman> womanList = new ArrayList<Woman>();

	public static void main(String[] args) {
			
		try {
	
			Man m1 = new Man(1, "Adam");
			Man m2 = new Man(2, "Bob");
			Man m3 = new Man(3, "Charlie");
			Man m4 = new Man(4, "Dan");
			
			Woman w1 = new Woman(5, "Amy");
			Woman w2 = new Woman(6, "Betty");
			Woman w3 = new Woman(7, "Chloe");
			Woman w4 = new Woman(8, "Diane");
			
			m1.setPreferenceList(new LinkedList<Person>(Arrays.asList(w1, w2, w3, w4)));
			m2.setPreferenceList(new LinkedList<Person>(Arrays.asList(w1, w4, w3, w2)));
			m3.setPreferenceList(new LinkedList<Person>(Arrays.asList(w2, w1, w3, w4)));
			m4.setPreferenceList(new LinkedList<Person>(Arrays.asList(w4, w3, w2, w1)));
			w1.setPreferenceList(new LinkedList<Person>(Arrays.asList(m4, m3, m1, m2)));
			w2.setPreferenceList(new LinkedList<Person>(Arrays.asList(m2, m4, m1, m3)));
			w3.setPreferenceList(new LinkedList<Person>(Arrays.asList(m4, m1, m2, m3)));
			w4.setPreferenceList(new LinkedList<Person>(Arrays.asList(m3, m2, m1, m4)));
			
			manList.add(m1);
			manList.add(m2);
			manList.add(m3);
			manList.add(m4);
			
			womanList.add(w1);
			womanList.add(w2);
			womanList.add(w3);	
			womanList.add(w4);
			
			//Gale-Shapley algorithm starts here
			int cycleIndex = 1;
			while (hasSingleWoman()) {
				for (Man man : manList) {
					if (man.isSingle())
						man.proposeNextWoman();
				}

				System.out.println("\nCycle #"+cycleIndex);

				for (Woman woman : womanList) {
					System.out.println(woman.getName()+" proporsals = " + woman.getProposals());
					
					if (woman.getProposals().size() >= 1)
						woman.rejectAllButBest();
				}
				
				for (Woman woman : womanList) {
					if (!woman.isSingle()) {
						System.out.println(woman.getName() +"'s pair = " + woman.getPair().getName());
					}
					else {
						System.out.println(woman.getName() +" is single yet" );
					}
				}
				
				//Log result
				for (Man man : manList) {
					if (!man.isSingle()) {
						System.out.println(man.getName() +"'s pair = " + man.getPair().getName());
					}
					else {
						System.out.println(man.getName() +" is single yet" );
					}
				}				
				++cycleIndex;
			}
			
			System.out.println("\nThe final stable marriege set: ");
			//Log result
			for (Woman woman : womanList) {
				if (!woman.isSingle()) {
					System.out.println(woman.getName() +"'s pair = " + woman.getPair().getName());
				}
				else {
					System.out.println(woman.getName() +" is single yet" );
				}
			}
			
			//Log result
			for (Man man : manList) {
				if (!man.isSingle()) {
					System.out.println(man.getName() +"'s pair = " + man.getPair().getName());
				}
				else {
					System.out.println(man.getName() +" is single yet" );
				}
			}			

		} catch (GenderMismatchException ex) {
			ex.printStackTrace();
		}
		
	}
	
	public static boolean hasSingleWoman()
	{
		for (Woman woman : womanList) {
			if (woman.isSingle())
				return true;
		}
		return false;
	}
}
