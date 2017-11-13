import java.util.*;

public class Teleport {

	
	HashMap <String, HashSet <String>> routes;

	public Teleport() {
		routes =  new HashMap <String, HashSet <String>> ();
	}


	public void AddRoute(String S, String D){
		HashSet<String> Cit;
		if (routes.containsKey(S)){
			Cit= routes.get(S);
			Cit.add(D);
			routes.put(S, Cit);
		} else {
			Cit= new HashSet <String>();
			Cit.add(D);
			routes.put(S, Cit);
		}

		if (routes.containsKey(D)){
			Cit= routes.get(D);
			Cit.add(S);
			routes.put(D, Cit);
		} else {
			Cit= new HashSet <String>();
			Cit.add(S);
			routes.put(D, Cit);
		}
	}

	public HashSet<String> citiesInJumps(String s, int jumps) {
		HashSet<String> hs = routes.get(s);
		ArrayList<String> hs2 = new ArrayList<String>();
		if (s != null) {
			hs2.addAll(hs);
			
			while (jumps > 1) {
				int l = hs2.size();
				for (int i = 0; i < l; i++)
					hs2.addAll(routes.get(hs2.get(i)));
				jumps --;
			}
		}
		HashSet<String> val = new HashSet<String>();
		val.addAll(hs2);
		val.remove(s);
		return val;
	}

	public void canTeleport(String source, String dest) {
		int l = routes.keySet().size();
		System.out.print("can I teleport from " + source + " to " + dest + ": ");
		HashSet<String> hs = citiesInJumps(source, l);
		if (hs.contains(dest))
			System.out.print("Yes");
		else 
			System.out.print("No");
			
	}
	
	public void loopPossible(String city) {
		System.out.print("loop possible from " + city + ": ");
		HashSet<String> hs = routes.get(city);
		int i = 0;
		int jumps = routes.keySet().size();
		boolean flag = false;
		int temp = jumps - 1;
		ArrayList<String> hs2 = new ArrayList<String>();
		HashSet<String> hs3 = new HashSet<String>();
		if (city != null) {
			hs2.addAll(hs);
//			System.out.println(hs2);
			while (jumps > 1) {
				int l = hs2.size();
				for (i = 0; i < l; i++) {
					for (String str: routes.get(hs2.get(i))) {
						if (routes.get(str).contains(city)) {
							flag = true;
							break;
						}
					}
					if (flag)
						break;
					else {
						hs3.clear();
						for (String str: hs)
							hs3.addAll(routes.get(str));
					}
				}

				System.out.println(hs3);
				jumps--;
			}
		}
		HashSet<String> val = new HashSet<String>();
		val.addAll(hs2);
		if (val.contains(city))
			System.out.print("Yes");
		else 
			System.out.print("No");
		System.out.println();
	}

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		Teleport t1 = new Teleport();
		t1.AddRoute("Washington" , "Baltimore");
		t1.AddRoute("Washington" , "Atlanta");
		t1.AddRoute("Philadelphia" , "NewYork");
		t1.AddRoute("Baltimore", "Philadelphia");
		t1.AddRoute("LosAngeles" , "SanFrancisco");
		t1.AddRoute("SanFrancisco" , "Oakland");
		t1.AddRoute("LosAngeles" , "Oakland");
		t1.AddRoute("Seattle", "NewYork");
		t1.AddRoute("Seattle", "Baltimore");
		
		System.out.println("1. What cities can I reach from city X with a maximum of N jumps?");
		System.out.println("2. Can someone get from city X to city Y");
		System.out.println("3. Starting in city X, is it possible to travel in a loop (leave the city on one route and return on another, without traveling along the same route twice)?");
		System.out.println("Enter your selection: ");
		int selection = scan.nextInt();
		
		switch(selection) {
		case 1:
			String X = scan.next();
			int N = scan.nextInt();
			System.out.println(t1.citiesInJumps(X, N));
			break;
			
		case 2: 
			String Z = scan.next();
			String Y= scan.next();
			t1.canTeleport(Z, Y);
			break;
			
		case 3:
			
			break;
			
		default:
			System.out.println("Invalid input. Try again.");
		}
		
	}

}
