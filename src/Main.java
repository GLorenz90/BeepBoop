public class Main {
	public static void main(String[] args) {
		BeepBoop beepBoop = new BeepBoop();

		try {
			beepBoop.processStates();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
