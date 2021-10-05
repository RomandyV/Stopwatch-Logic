package stopwatch;

import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

/*****************************************************************
 * A class that makes a stopwatch objects where the functions
 * are like a stopwatch. Max time it can hold is 2147483647 minutes,
 * 59 seconds and 999 milliseconds.
 *
 * 
 *
 * @author Romandy Vu
 * @version 1/20/2021
 ******************************************************************/

public class StopWatch  {

	
	//Instance variables
	/** the minutes of the stopwatch */
	private int minutes;

	/** the seconds of the stopwatch */
	private int seconds;

	/** the milliseconds of the stopwatch */
	private int milliseconds;

	/** The instance variable if the stopwatch is suspended */
	private static boolean suspend = false;

    /*****************************************************************
	 * The default constructor which sets minutes, seconds 
	 * and milliseconds to 0.
    ******************************************************************/
	public StopWatch() {
		//Sets the minute instance variable to 0.
		minutes = 0;
		
		//Sets the seconds instance variable to 0.
		seconds = 0;

		//Sets the milliseconds instance variable to 0.
		milliseconds = 0;

	}

	/******************************************************************
	 *
	 *  A constructor that accepts a string as a parameter with the
	 *  following format: "1:21:300" where 1 indicates minutes, 21
	 *  indicates seconds,  and 300 indicates milliseconds.  OR
	 *  the format "15:200" where the 15 indicates seconds, and
	 *  200 indicates milliseconds, OR the format “300” where
	 *  300 indicates milliseconds
	 *
	 * @param startTime is the input string the represents
	 * the starting time
	 * @throws IllegalArgumentException when the input string
	 * does not match the proper format (see description above) or there
	 * is no string or the value for min, sec, millisec is invalid.
	 *********************************************************************/

 	public StopWatch(String startTime) {
		///Checks Validity of String input.

		///Checks to see String input is empty.
		if (startTime == null){
			throw new IllegalArgumentException();
		}
		
		//Checks to see if the characters are valid and counts the number of colons
		else{
			int colonCount = 0;
			//Throws an error if character is not a digit or a colon.
			for(int i = 0; i < startTime.length(); i++){
				if(!(Character.isDigit(startTime.charAt(i))|| startTime.charAt(i)== ':')){
					throw new IllegalArgumentException("String has invalid char");
				}

				/*Count number of colons and throws error if more than 2 colons are detected
				or if the string starts or end with a colon or there are consecutive colons*/
				if (startTime.charAt(i) ==':'){
					colonCount++;
					if(colonCount > 2 || startTime.charAt(0)==':'||
					 	startTime.charAt(startTime.length()-1)==':'|| startTime.charAt(i-1)==':'){
						throw new IllegalArgumentException("Error in colon placement");
					}
				}
			}
			///Checks to see the colon to determine if the value of minutes, seconds, or milliseconds.
			
			//If colon count is 0, then the input is in milliseconds.
			if (colonCount == 0){
				
				//Converts string into an int and checks if valid.
				int stringMillisecs = Integer.parseInt(startTime);
				if (stringMillisecs < 0){
					throw new IllegalArgumentException("Input of millisecond is not valid");
					
				}
				//Converts milliseconds to the time format (min:sec:millisec) if applicable.
				convertToStopWatch(stringMillisecs);

			}
			//If the colon count is 1, then the inputs is in seconds, and milliseconds.
			else if (colonCount == 1){
				
				/*Finds the index of the colon and splits the value that is left of the 
				colon as the seconds, and the value to the right of the colon as the
				milliseconds*/
				int colon = startTime.indexOf(":");
				int stringSecs =Integer.parseInt(startTime.substring(0,colon));
				int stringMillisecs = Integer.parseInt(startTime.substring(colon + 1,startTime.length()));
				
				//Checks the validity of the seconds and milliseconds.
				if (stringMillisecs < 0 && stringSecs < 0){
					throw new IllegalArgumentException("Either milliseconds or seconds is invalid");
				}
				//Sets the input to the proper format.
				//Converts seconds and milliseconds to the closest and lowest min.
				int tempMillisec = (stringMillisecs / 60000);
				int tempSec = (stringSecs / 60);
				this.minutes = tempSec + tempMillisec;
				this.milliseconds = stringMillisecs % 60000;
				this.seconds = stringSecs % 60;
			}

			/*Since an error would already appear if the colon count is greater than 2, the else
			statement will check if the colon count is 2. Therefore assuming that it is in the
			min:sec:millisec format*/
			else{
				/*Finds the index value of the first and last colon to determine
				 and convert the min, sec, and millisec into int, 
				 where min is the left of the first colon, sec is between the 2 colons, 
				 *and millisec is right side of the last colon.*/
				int firstColon = startTime.indexOf(":");
				int lastColon = startTime.lastIndexOf(":");
				int stringMins =Integer.parseInt(startTime.substring(0,firstColon));
				int stringSecs = Integer.parseInt(startTime.substring(firstColon + 1,lastColon));
				int stringMillisecs = Integer.parseInt(startTime.substring(lastColon + 1, startTime.length()));

				//Checks the validity min, sec, and millisec.
				if (stringMins < 0 || (stringSecs < 0 || stringSecs > 59) 
					|| (stringMillisecs > 999 || stringMillisecs < 0)){
					throw new IllegalArgumentException("Either milliseconds or seconds or minutes is invalid");
			}

			///Sets the value to the proper instance variable.
			milliseconds = stringMillisecs;
			seconds = stringSecs;
			minutes = stringMins;
		}
	}
}

	/**********************************************************************
 	* 
 	* A constructor that initializes the instance variables with the 
 	* provided values.
 	* @param minutes input to set the minutes of the stopwatch.
 	* @param seconds input to set the seconds of the stopwatch.
 	* @param milliseconds input to set the milliseconds of the stopwatch.
	 * @throws IllegalArgumentException when the input for minutes, seconds,
 	* or milliseconds are not valid.
 	**********************************************************************/
	public StopWatch(int minutes, int seconds, int milliseconds) {
		///Checks the Validity of parameters.
		if (minutes < 0){ 
			throw new IllegalArgumentException("constuctor with 3 params");
		}

		if ((seconds < 0) || (seconds > 59) ){
			throw new IllegalArgumentException("Not a valid value");
		}

		if ((milliseconds < 0) || (milliseconds > 999)){
			throw new IllegalArgumentException("Not a valid value");
		} 

		//Sets input parameters to instance variables.
		this.minutes = minutes;
		this.seconds = seconds;
		this.milliseconds = milliseconds;

		
	}

	/***********************************************************************
 	* //Sets the value of the an stopWatch object to "this" stopWatch object.
 	* @param stopWatch
 	***********************************************************************/
	public StopWatch(StopWatch stopWatch) {
		///Checks the validity of the object.
		if (stopWatch == null){
			throw new IllegalArgumentException("Not a valid StopWatch");
		}

		//Assigns the values of the other stopWatch object to "this" object.
		this.minutes = stopWatch.minutes;
		this.seconds = stopWatch.seconds;
		this.milliseconds = stopWatch.milliseconds;
	}

	/***********************************************************************
 	* 
 	* A constructor that initializes the instance variables 
 	* with the provided values
 	* @param seconds input to set the seconds of the stopwatch.
 	* @param milliseconds input to set the milliseconds of the stopwatch.
 	* @throws IllegalArgumentException when the input for seconds or
 	* milliseconds are invalid.
 	***********************************************************************/
	public StopWatch(int seconds, int milliseconds) {
		//Checks the validity of the parameters.
		if (seconds < 0){
			throw new IllegalArgumentException("Input for seconds must be valid");
		}
		if (milliseconds < 0){
			throw new IllegalArgumentException("Input for milliseconds must be valid");
		}

		/*Converts the seconds to milliseconds and adds to the local variable
		to distribute the overall millisecond to the proper time format*/
		milliseconds += (seconds * 1000);

		//Assigns the instance variable values from the input parameters.
		minutes = milliseconds / 60000;
		this.seconds = (milliseconds % 60000) / 1000;
		this.milliseconds = milliseconds % 1000;
	}

	/***********************************************************************
 	* 
 	* A constructor that initializes the instance variables 
 	* with the provided value
 	* @param milliseconds input to set the time of the stopwatch.
 	* @throws IllegalArgumentException when the input for milliseconds is
 	* not valid from the convertToStopWatch(int milliseconds) method. 
 	***********************************************************************/
	public StopWatch(int milliseconds) {
		//Calls the convertToStopWatch() method.
		convertToStopWatch(milliseconds);
	}

	/************************************************************************
 	* 
 	* A static method that returns true 
 	* if StopWatch object stopwatch1 is exactly the same 
 	* as StopWatch object stopWatch2.
 	* @param stopWatch1 the object being compared.
	* @param stopWatch2 the object that is being compared to.
	 * @return True if they are the same, else returns False.
	 @throws IllegalArgumentException when any of the stopWatch object is null.
 	************************************************************************/
	public static boolean equals(StopWatch stopWatch1, StopWatch stopWatch2) {
		//Checks to see if the objects are empty.
		if(stopWatch1 == null || stopWatch2 == null ){
			throw new IllegalArgumentException();
		}
		//Returns a boolean value from the result of the comparison.
		return (stopWatch1.minutes == stopWatch2.minutes)
		 && (stopWatch1.seconds == stopWatch2.seconds) 
		 && (stopWatch1.milliseconds == stopWatch2.milliseconds);
	}
	

	/**************************************************************************
 	*
 	*  A method that returns true if “this”
 	* StopWatch object is exactly the same (minutes, seconds, milliseconds) 
 	* as the other StopWatch object "other"
 	* @param other input of the object that is being compared to.
 	* @return True if minutes, seconds, and milliseconds are the same, else
	 * returns false.
	 @throws IllegalArgumentException When the object is not the StopWatch object
 	**************************************************************************/
	public boolean equals(Object other) {
		//Checks to see if the object is the same class.
		if (!(other instanceof StopWatch)){
			throw new IllegalArgumentException();
		}

		//Creates object and compares the objects.
		StopWatch tmp = (StopWatch) other;
		return StopWatch.equals(this,tmp);

		
	}

	/*************************************************************************
 	*
 	*  A method that returns 1 if “this” StopWatch object is greater than 
 	* the other StopWatch object; returns -1 if the “this” StopWatch object 
 	* is less than the other StopWatch; returns 0 if the “this” StopWatch object
 	* is equal to the other StopWatch object
 	* @param other the input object that is being compared to.
 	* @return 1 if “this” StopWatch object is greater than the other StopWatch 
 	* object; returns -1 if the “this” StopWatch object is less than the 
 	* other StopWatch; returns 0 if the “this” StopWatch object
	 * is equal to the other StopWatch object
	 @throws IllegalArgumentException When stopwatch object is null.
 	************************************************************************/
	public int compareTo(StopWatch other) {
		/*First checks to if the StopWatch input is valid ("not null"), then 
		compares the minutes value first*/
		if (other == null){
			throw new IllegalArgumentException();
		}
		else if(this.minutes > other.minutes){
			return 1;
		}
		else if(this.minutes < other.minutes){
			return -1;
		}
		/*Compares the seconds since the minutes are equal from the else if
		being false */
		else if(this.seconds > other.seconds){
			return 1;
		}
		else if(this.seconds < other.seconds){
			return -1;
		}
		/*Compares the milliseconds since the seconds are equal 
		from the else if being false */
		else if(this.milliseconds > other.milliseconds){
			return 1;
		}
		else if (this.milliseconds < other.milliseconds){
			return -1;
		}
		//Since all the else if statements are false, the objects are equal.
		else{
			return 0;
		}
	}

/**************************************************************************
 * 
 * Takes the values of a stopWatch object and converts the time into 
 * milliseconds.
 * @param stopWatch the object whose value is being used to get the 
 * milliseconds
 * @return the milliseconds of the stopWatch object.
 * @throws IllegalArgumentException When stopWatch object is null or the
 * return is a negative number.
 **************************************************************************/
	private static int convertToMilli (StopWatch stopWatch) {
		//Checks to see if the stopwatch object is valid.
		if (stopWatch == null){
			throw new IllegalArgumentException();
		}
		//Checks to see if the minute conversion to millisec is too large to store.
		if(stopWatch.minutes > 35791 || stopWatch.minutes < 0){
			throw new IllegalArgumentException("Minute is too high");
		}

		/*Converts the minutes and seconds of the stopWatch object into
		milliseconds and takes the sum of the milliseconds*/
		int result = (stopWatch.minutes *60000) + (stopWatch.seconds * 1000) +
			stopWatch.milliseconds;
		//Checks if result is a negative
		if(result < 0){
			throw new IllegalArgumentException("Millisecond value too large to store, or is a negative");
		}

		//Returns the overall milliseconds of the stopWatch object.
		return result;
	}

/*************************************************************************
 * 
 * Converts the input value of milliseconds to the proper minute, second, 
 * and millisecond
 * @param tempMilliseconds the input value of the milliseconds used to
 * determine the proper minute, second, and millisecond.
 * @return has no return value due to a void return type.
 * @throws IllegalArgumentException when input is not valid (<0)
 *************************************************************************/
	private void convertToStopWatch (int tempMilliseconds) {		
		//Checks to see if input is valid.
		if (tempMilliseconds < 0){
			throw new IllegalArgumentException("input of milliseconds is invalid");
		}
		
		//Determines the overall whole minutes from the milliseconds input.
		minutes = tempMilliseconds / 60000;
		
		/*Finds the remainder milliseconds left where it is the difference
		of the original milliseconds input and the overall whole minutes.*/
		tempMilliseconds %= 60000;

		//Determines the overall whole seconds from remainder milliseconds left.
		seconds = tempMilliseconds / 1000;
		
		/*Finds the remainder milliseconds left where it is the difference
		of the original milliseconds input and the overall whole seconds.*/
		tempMilliseconds %= 1000;

		/*The leftover milliseconds after determining the whole numbers
		from the minutes and seconds*/
		milliseconds = tempMilliseconds;
	}

/*************************************************************************
 * 
 *  A method that adds the number of milliseconds to “this” StopWatch object.
 * When the stopWatch is suspend and input is non negative. 
 * @param milliseconds input to add additional miliseconds to the
 * overall milliseconds instance variable.
 * @return has no return value due to a void return type.
 * @throws IllegalArgumentException when input is not valid (<0 or >2147483647) 
 * or if suspend instance variable is true.
 ************************************************************************/
	public void add(int milliseconds) {
		/*Checks to see if the object is not suspended and the input is
		non negative*/
		if (!(suspend) && (milliseconds >= 0)){
			//Increments the time by said input (in milliseconds).
			for(int i = 0; i < milliseconds; i++){
				inc();
			}
		}
		else if (milliseconds < 0){
			throw new IllegalArgumentException("input is invalid");
		}
		
		}

/************************************************************************
 *
 * Subtracts milliseconds from the milliseconds instance variable.
 *  @param milliseconds input to subtract the overall milliseconds instance
 * variable by.
 * @return has no return value due to a void return type.
 * @throws IllegalArgumentException when input is not valid (<0 or >2147483647)
 *  or if suspend instance variable is true.
 *************************************************************************/
	public void sub(int milliseconds) {
		/*Checks to see if the object is not suspended and the input is
		non negative*/
		if(!(suspend) && milliseconds >= 0){
			//Deincrement the time by said input (in milliseconds).
			for(int i = 0; i < milliseconds; i++){
				dec();
		}
	}
		else if (milliseconds < 0){
			throw new IllegalArgumentException("input is invalid");
		}
	}


/************************************************************************
 *
 *  A method that adds StopWatch other to the “this” StopWatch. 
 * @param stopWatch object that adds "this" object time by.
 * @return has no return value due to a void return type.
 * @throws IllegalArgumentException When StopWatch object is null.
 ************************************************************************/
	public void add(StopWatch stopWatch) {
		//Checks if the Stopwatch is valid.
		if (stopWatch == null){
			throw new IllegalArgumentException();
		}

		/*First checks to see if the sum of the mins will be greater than
		max minutes int value (2147483647) if so, it will be negative, setting
		the StopWatch to the max time.*/
		if((this.minutes + stopWatch.minutes) < 0){
			this.minutes = 2147483647;
			this.seconds = 59;
			this.milliseconds = 999;
		}
		/* Else Adds the minutes to "this" object as the conversion to milliseconds
		might exceed int limit; temporarily sets the parameter object min to 0, 
		and converts the stopWatch values into milliseconds using convertToMilli 
		method and adds to "this" stopWatch sets back the parameter
		object minutes to it's original value.*/
		else{
				this.minutes += stopWatch.minutes;
				int temp = stopWatch.minutes;
				stopWatch.minutes = 0;
				add(convertToMilli(stopWatch));
				stopWatch.minutes = temp;
	}
}

/************************************************************************
 *
 *  A method that subtracts the number of milliseconds from “this” StopWatch
 * object.
 * @param stopWatch object that subtracts "this" object time by.
 * @return has no return value due to a void return type.
 * @throws IllegalArgumentException when StopWatch object is null.
 ************************************************************************/
	public void sub(StopWatch stopWatch) {
		//Checks if the Stopwatch is valid.
		if (stopWatch == null){
			throw new IllegalArgumentException();
		}
		/*First checks to see if the difference of the mins will be less than
		0 setting the StopWatch to 0.*/
		if((this.minutes - stopWatch.minutes) < 0){
			this.minutes = 0;
			this.seconds = 0;
			this.milliseconds = 0;
		}
		/* Else subtracts the minutes to "this" object as the conversion to milliseconds
		might exceed int limit; temporarily sets the parameter object min to 0, 
		and converts the stopWatch values into milliseconds using convertToMilli 
		method and adds to "this" stopWatch sets back the parameter
		object minutes to it's original value.*/
		else{
				this.minutes -= stopWatch.minutes;
				int temp = stopWatch.minutes;
				stopWatch.minutes = 0;
				sub(convertToMilli(stopWatch));
				stopWatch.minutes = temp;
	}
}

/*************************************************************************
 * 
 * A method that increments the “this” StopWatch by 1 millisecond.
 *  @param N/A has no parameter since it is not needed or used.
 *  @return has no return value due to a void return type.
 *************************************************************************/
	public void inc() {
		//Max time, won't increment any further.
		if(minutes == 2147483647 && seconds == 59 && milliseconds == 999){
		}
		/*Condition that increases the minute and resets the seconds and
		milliseconds*/
		else if(seconds == 59 && milliseconds == 999){
			milliseconds = 0;
			seconds = 0;
			minutes++;
		}
		/*Condition that increases the seconds and resets the
		milliseconds.*/
		else if(milliseconds == 999){
			seconds++;
			milliseconds = 0;
		}
		//Condition that increases the milliseconds.
		else{
			milliseconds++;
		}
	}

/**************************************************************************
 * 
 * A method that decrements the “this” StopWatch by 1 millisecond.
 *  @param N/A has no parameter since it is not needed or used.
 *  @return has no return value due to a void return type.
 **************************************************************************/
	public void dec() {
		//Condition that does nothing since there is no more time left.
		if (minutes <= 0 && seconds <= 0 && milliseconds <= 0){
		}

		/*Condition that decreases the minute and resets the seconds and
		milliseconds*/
		else if(seconds == 0 && milliseconds == 0){
			minutes--;
			seconds = 59;
			milliseconds = 999;
		}
		/* Condition that decreases the seconds and resets the
		milliseconds*/
		else if(milliseconds == 0){
			seconds--;
			milliseconds = 999;
		}

		//Decreases the milliseconds.
		else{
			milliseconds--;
		}
	}

/*************************************************************************
 * 
 * A method that converts the StopWatch object to the format 
 * "minutes:seconds:milliseconds"
 * @param N/A has no parameter since it is not needed or used.
 * @return a string in the format of min:seconds:milliseconds that displays 
 * 2 digits of seconds and 3 digits of milliseconds.
 *************************************************************************/
	public String toString() {
		//Creates the format for seconds and milliseconds
		DecimalFormat secondsFormat = new DecimalFormat("00");
		DecimalFormat millisecondsFormat = new DecimalFormat("000");
		
		//Puts the instance variables into the proper format.
		String formattedSeconds = secondsFormat.format(seconds);
		String formattedMilliseconds = millisecondsFormat.format(milliseconds);
		
		/*Return the string in the format of min:seconds:milliseconds that displays 
		2 digits of seconds and 3 digits of milliseconds. */
		return minutes + ":" + formattedSeconds + ":" + formattedMilliseconds;

	}

/**************************************************************************
 * 
 * A method that saves the “this” StopWatch to a file.
 * @param filename the name of the file that is being saved.
 * @return has no return value due to a void return type.
 * @throws IllegalArgumentException When filename is null.
 **************************************************************************/
	public void save(String filename) {
		//Checks to see if the filename is not empty.
		if (filename == null){
			throw new IllegalArgumentException();
		}
		//Declares the writer and sets to null.
		PrintWriter out = null;
		try {
			//Creates a the writer.
			out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));

			//Writes down the minutes, seconds, and milliseconds.
			out.println(minutes + " " + seconds + " " + milliseconds);

			//Ends the writing.
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
		
	}

/************************************************************************
 * 
 * A method that loads the “this” StopWatch from a file.
 * @param filename the file that is being loaded.
 * @return has no return value due to a void return type.
 * @throws IllegalArgumentException When filename  can't be found.
 ************************************************************************/
	public void load(String filename)  {
		//Checks to see if the filename is not empty.
		if (filename == null){
			throw new IllegalArgumentException();
		}
		//Declares a scanner and set to null.
		Scanner scanner = null;
		try {
			//Creates a scanner and opens file name if applicable.
			scanner = new Scanner(new File(filename));

			//Read fields into instance variables
			minutes = scanner.nextInt();
			seconds = scanner.nextInt();
			milliseconds = scanner.nextInt();

		//Throws error if file is not found.	
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File not found.");
		}

	}

/*************************************************************************
 * This method turns on/off all StopWatch objects from mutating.
 * @param suspend boolean value to determine if stopWatch object
 * should mutate.
 * @return has no return value due to a void return type.
 ************************************************************************/
	public static void setSuspend(boolean suspend) {
		if(!(suspend == true) && !(suspend == false)){
			throw new IllegalArgumentException("input has to be a boolean");
		}
		StopWatch.suspend = suspend;
	}

/***********************************************************************
 * 
 * A method that returns a boolean value to check to see if the stopwatch
 * is suspended.
 * @param N/A has no parameter since it is not needed or used.
 * @return true if stopwatch is suspended, else returns false.
 **********************************************************************/
	public static boolean isSuspended() {
		return suspend;
	}

/************************************************************************
 * 
 * A method that returns the minutes of the stopwatch.
 * @param N/A has no parameter since it is not needed or used.
 * @return an integer value of minutes of the stopwatch.
 ************************************************************************/
	public int getMinutes() {
		return minutes;
	}

/**************************************************************************
 * 
 * A method that sets the minutes of the stopwatch (0-2147483647).
 * @param minutes input to set the minute instance variable.
 * @return has no return value due to a void return type.
 *************************************************************************/
	public void setMinutes(int minutes) {
		//Checks if the parameter is valid.
		if(minutes < 0){
			throw new IllegalArgumentException("Input for minutes is not valid");
		}
		this.minutes = minutes;
	}

/**************************************************************************
 * 
 * A method that returns the seconds of the stopwatch.
 * @param N/A has no parameter since it is not needed or used.
 * @return an integer value of seconds in the stopwatch.
 **************************************************************************/
	public int getSeconds() {
		return seconds;
	}

/*************************************************************************
 * 
 * A method that sets the seconds of the stopwatch if valid (0-59).
 * @param seconds input to set the seconds of the stopwatch.
 * @return an integer value of seconds in the stopwatch. 
 **************************************************************************/
	public void setSeconds(int seconds) {
		//Checks if the parameter is valid.
		if ((seconds < 0) || (seconds > 59)){
			throw new IllegalArgumentException("Input for seconds is not valid");
		}

		this.seconds = seconds;
	}

/*************************************************************************
 * 
 * A method that returns the milliseconds of the stopwatch.
 * @param N/A has no parameter since it is not needed or used.
 * @return an integer value of milliseconds of the stopwatch.
 *************************************************************************/
	public int getMilliseconds() {
		return milliseconds;
	}

/**************************************************************************
 * 
 * A method that sets the milliseconds of the stopwatch if valid (0-999).
 * @param milliseconds input to set the milliseconds of the stopwatch.
 * @return an integer value of milliseconds of the stopwatch.
 **************************************************************************/
	public void setMilliseconds(int milliseconds) {
		//Checks if the parameter is valid.
		if (milliseconds < 0 || milliseconds > 999){
			throw new IllegalArgumentException("Input for milliseconds must" +
			"be valid");

		}
		this.milliseconds = milliseconds;
	}

}
