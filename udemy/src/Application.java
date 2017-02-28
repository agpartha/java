
public class Application {

	public static void main(String[] args) {
	    
	    boolean doOnce = true;
	    while (true) {
	        if (doOnce) {
	            System.out.println("This should happen only once in the loop");
	            // Important now to turn of this doOnce flag :-)
	            doOnce = false;
	        }
	        System.out.println("This keeps happening multiple times");
	    }
	}

}
