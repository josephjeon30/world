public class Tester{
	public static void main(String[] args){
		String[] playlist = Audio.generatePlaylist();
		System.out.println(toString(playlist));
	}

	public static String toString(String[] arr){
		String ans = "{";
		for (int i = 0; i < arr.length; i++){
			ans += arr[i];
			if (i != arr.length - 1){
				ans += ", ";
			}
		}
		return ans + "}";
	}
}