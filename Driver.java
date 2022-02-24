import java.util.Scanner;

public class Driver{
	private static final int WIDTH = 80;
 	private static final int HEIGHT = 30;
  	private static final int BORDER_COLOR = Text.BLACK + Text.BRIGHT;
  	private static final int BORDER_BACKGROUND = Text.BLACK + Text.BACKGROUND + Text.BRIGHT;

	public static void main(String[] args){
		String[] playlist = Audio.generatePlaylist();
		scramble(playlist);
		Text.clear();
	    drawScreen();
	    playAudio(playlist);
		
	}

	public static void playAudio(String[] playlist){//make list
		try{
			for (int i = 0; i < playlist.length; i++){
	            Audio audio = new Audio("songs/" + playlist[i] + ".wav");//change
	            
	            String s = "";
	            Scanner sc = new Scanner(System.in);

	            audio.play();
	            
	            while (true){
	            	drawScreen();
	            	Text.go(2, 4);
	            	System.out.print("Song: " + strip(playlist[i]) + " (" +  audio.getLength() + ")");

		            Text.go(4, 4);
		            System.out.print("Play/Pause (p)");

		            Text.go(5, 4);
		            System.out.print("Skip current track (s)");

		            Text.go(6, 4);
		            System.out.print("Close program (x)");

		            Text.go(9, 3);
		            String temp = "";
		            for (int klong = 0; klong < WIDTH - 15; klong++){
		            	temp += " ";
		            }
		            System.out.print(Text.colorize(" Type here:" + temp, 
		            	Text.WHITE + Text.BACKGROUND));

		            Text.go(12, 4);
		            System.out.print("Queue:");

		            for (int j = 0; j < 10; j++){
		            	if (i + j >= playlist.length){
		            		break;
		            	}
		            	Text.go(13 + j, 6);
		            	System.out.print(strip(playlist[i + j]));
		            }

		            System.out.print("\u001b[" + (Text.WHITE + Text.BACKGROUND) + "m");
		            Text.go(9, 16);
		            if (sc.hasNext()) 
		            	s = sc.next();
		            Text.reset();

		            Text.clear();
		            drawScreen();

		            switch (s){
		            	case "p":
		            		if (audio.status.equals("play")){
		            			audio.pause();
		            			Text.go(11,7);
		            			System.out.print("[PAUSED]");
		            		}
		            		else audio.resumeAudio();
		            		break;
		            	case "s":
		            		audio.stop();
		            		break;
		            	case "x":
		            		audio.stop();
		            		Text.clear();
		            		return;
		            	default:
		            		Text.go(11,7);
		            		System.out.print("Invalid input, try again");
		            		break;
		            }
		            
		            if (s.equals("s")) {
		            	Text.clear();
		            	break;
		            }	
		        }
		        sc = null;
		    }
		}     
        catch (Exception ex){
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
	}

	public static void drawScreen(){
	    String a = Text.colorize(" ", BORDER_COLOR, BORDER_BACKGROUND);
	    String b = Text.colorize(" ", BORDER_COLOR, Text.WHITE + Text.BACKGROUND);

	    for (int i = 0; i < WIDTH + 1; i++){
		    Text.go(1,i);
		    System.out.print(a);
	    	Text.go(HEIGHT,i);
	    	System.out.print(a);
	    	if (i > 1 && i < WIDTH - 1){
	    		Text.go(8,i);
	    		System.out.print(b);
	    		Text.go(10,i);
	    		System.out.print(b);
	    	}
	    }

	    for (int i = 2; i < HEIGHT + 1; i++){
	    	Text.go(i, 1);
	    	System.out.print(a + a);
	    	Text.go(i, WIDTH - 1);
	    	System.out.print(a + a);
	    }

	    Text.go(0, 3);
	    System.out.print(Text.colorize("dumb terminal music thing", Text.WHITE + Text.BRIGHT, BORDER_BACKGROUND));

	    Text.go(0, WIDTH - 13);
	    System.out.print(Text.colorize("calybri (jo)", Text.WHITE + Text.BRIGHT, BORDER_BACKGROUND));
	}

	public static String strip(String str){
		String ans = "";
		for (int i = 0; i < str.length(); i++){
			if (str.charAt(i) == '_'){
				ans += " ";
			}
			else{
				ans += str.charAt(i);
			}
		}
		return ans;
	}

	public static void scramble(String[] arr){
		for (int i = arr.length - 1; i >= 0; i--){
			String temp = arr[i];
			int rand = (int) (Math.random() * (i + 1));
			arr[i] = arr[rand];
			arr[rand] = temp;
		}
	}
}