package primerDesign.Test;

import org.biojava.bio.symbol.IllegalSymbolException;
import org.biojava.utils.ChangeVetoException;

public class Test {
	private static char[] sequence = "DCBA".toCharArray();
	private static int[] suftab = new int[]{0,1,2,3};
	//private static int[] suftab = new int[]{10,6,8,2};
	private static int sequenceLength = sequence.length;
	
	public static void main(String[] args) throws IllegalSymbolException, ChangeVetoException{
		System.out.println("Before:");
		for(int i=0; i<suftab.length; i++){
			//System.out.println(suftab[i] + " " + new String(sequence).substring(suftab[i]));
			System.out.println(suftab[i]);
		}
		heapsort(suftab, suftab.length);
		System.out.println("After:");
		for(int i=0; i<suftab.length; i++){
			//System.out.println(suftab[i] + " " + new String(sequence).substring(suftab[i]));
			System.out.println(suftab[i]);
		}
	}
	
	private static int heapsort(int[] data, int n) // zu sortierendes Feld und seine L�nge
	{
		  int val, parent, child;
		  int root= n >> 1;                  // erstes Blatt im Baum
		  int count= 0;                      // Z�hler f�r Anzahl der Vergleiche

		  for ( ; ; )
		  {
		    if ( root > 0 ) {                    // Teil 1: Konstruktion des Heaps
		      parent= --root;
		      val= data[root];               // zu versickernder Wert
		    }
		    else
		    if ( --n > 0 ) {                     // Teil 2: eigentliche Sortierung
		      val= data[n];                  // zu versickernder Wert vom Heap-Ende
		      data[n]= data[0];              // Spitze des Heaps hinter den Heap in
		      parent= 0;                     //   den sortierten Bereich verschieben
		    }
		    else                             // Heap ist leer; Sortierung beendet
		      break;

		    while ( (child= (parent + 1) << 1) < n )  // zweites (!) Kind;
		    {                                         // Abbruch am Ende des Heaps
		      if ( ++count > data[child] && data[child-1] > data[child] )  // gr��eres Kind w�hlen
		        --child;

		      data[parent]= data[child];     // gr��eres Kind nach oben r�cken
		      parent= child;                 // in der Ebene darunter weitersuchen
		    }

		    if ( child == n )                // ein einzelnes Kind am Heap-Ende
		    {                                //   ist �bersprungen worden
		      if ( ++count >= val && data[--child] >= val ) {  // gr��er als der zu versick-
		        data[parent]= data[child];   //   ernde Wert, also noch nach oben
		        data[child]= val;            // versickerten Wert eintragen
		        continue;
		      }

		      child= parent;                 // 1 Ebene nach oben zur�ck
		    }
		    else
		    {
		      if ( ++count >= val && data[parent] >= val ) {  // das Blatt ist gr��er als der
		        data[parent]= val;           //   zu versickernde Wert, der damit
		        continue;                    //   direkt eingetragen werden kann
		      }

		      child= (parent - 1) >> 1;      // 2 Ebenen nach oben zur�ck
		    }

		    while ( child != root )          // maximal zum Ausgangspunkt zur�ck
		    {
		      parent= (child - 1) >> 1;      // den Vergleichswert haben wir bereits
		                                     //   nach oben verschoben
		      if ( ++count >= val && data[parent] >= val )  // gr��er als der zu versickernde
		        break;                             //   Wert, also Position gefunden

		      data[child]= data[parent];     // R�ckverschiebung n�tig
		      child= parent;                 // 1 Ebene nach oben zur�ck
		    }

		    data[child]= val;                // versickerten Wert eintragen
		  }

		  return count;
		}
	
	private static int compareSuffix(int a, int b){
		assert a>=0 && b>=0 && a<sequenceLength && b<sequenceLength;
		int idxA = suftab[a]; //getSufTab(a);
		int idxB = suftab[b]; //getSufTab(b);
		int end = Math.min(sequenceLength-idxA, sequenceLength-idxB);
		for (int i = 0; i < end; i++) {
			int first = sequence[idxA+i];
			int second = sequence[idxB+i];
			if(first == second) continue;
			else if(first > second) return 1;
			else if(first < second) return -1;
		}
		if(sequenceLength-idxA > sequenceLength-idxB) return -1;
		else if(sequenceLength-idxA < sequenceLength-idxB) return 1;
		else return 0;
	}
}
