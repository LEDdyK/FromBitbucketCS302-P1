package PacmanRip;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Timer {
	private Text countdownText;
	private Text showLimit;
	private int countdownValue;
	private int frameCount;
	private int secCount;
	private int timeLimit;
	private int seconds;
	private int minutes;
	private boolean transitioned;

	public Timer() {
		countdownText = new Text();
		countdownText.setFont(Font.font("Verdana", 100));
		countdownText.setFill(Color.RED);
		countdownText.setStroke(Color.GREEN);
		countdownText.setStrokeWidth(3);
		countdownText.setX(Environment.getScreenWidth()/2);
		countdownText.setY(Environment.getScreenHeight()/2);
		
		showLimit = new Text();
		showLimit.setX(Environment.getScreenWidth()/2);
		showLimit.setY(50);
	}
	
	public int getSecCount() {
		return secCount;
	}
	public boolean getTrans() {
		return transitioned;
	}
	public int getCountdownVal() {
		return countdownValue;
	}
	public Text getCountdownText() {
		return countdownText;
	}
	public Text getShowLimit() {
		return showLimit;
	}
	
	public void setTrans(boolean t) {
		transitioned = t;
	}
	public void setTimeLimit(int T) {
		timeLimit = T;
	}
	
	public void resetCountdown() {
		countdownValue = 4;
	}
	public void endCountdown() {
		secCount = 125;
	}
	
	public void doCountdown() {
		if (countdownValue == 4) {
			countdownText.setText("Ready?");;
		}
		else if (countdownValue == 0) {
			countdownText.setText("GO!");;
		}
		else {
			countdownText.setText(Integer.toString(countdownValue));
		}
		--countdownValue;
		transitioned = false;
	}
	
	public void doGameTime() {
		String limitString = new String();
		minutes = timeLimit/60;
		seconds = timeLimit%60;
		limitString = Integer.toString(minutes);
		limitString = limitString.concat(":");
		if (seconds < 10) {
			limitString = limitString.concat("0");
		}
		limitString = limitString.concat(Integer.toString(seconds));
		showLimit.setText(limitString);
		--timeLimit;
		transitioned = false;
	}
	
	public void countFrames(int limit) {
		if (frameCount == limit) {
			frameCount = 0;
			++secCount;
			transitioned = true;
		}
		else {
			++frameCount;
		}
	}
	
}
