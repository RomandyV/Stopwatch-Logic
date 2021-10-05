package stopwatch;

import org.junit.Test;

import static org.junit.Assert.*;


public class TestStopWatch {

	/*Default constructor test and getter test. */
	@Test
	public void testDefaultConstructor() {
		StopWatch s = new StopWatch();
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 0);
		assertTrue(s.getMilliseconds() == 0);

		StopWatch s64 = new StopWatch();
		assertTrue(s64.getMinutes() == 0);
		assertTrue(s64.getSeconds() == 0);
		assertTrue(s64.getMilliseconds() == 0);

		StopWatch s65 = new StopWatch();
		assertTrue(s65.getMinutes() == 0);
		assertTrue(s65.getSeconds() == 0);
		assertTrue(s65.getMilliseconds() == 0);
	}

	//Setters and Getters test with valid parameters.
	@Test
	public void testSetters(){
		StopWatch s100 = new StopWatch();
		assertTrue(s100.getMinutes() == 0);
		assertTrue(s100.getSeconds() == 0);
		assertTrue(s100.getMilliseconds() == 0);
		assertTrue(StopWatch.isSuspended()==false);
		s100.setMilliseconds(100);
		s100.setSeconds(50);
		s100.setMinutes(30);
		StopWatch.setSuspend(true);
		assertTrue(s100.getMinutes() == 30);
		assertTrue(s100.getSeconds() == 50);
		assertTrue(s100.getMilliseconds() == 100);
		assertTrue(StopWatch.isSuspended() == true);
		s100.setMilliseconds(999);
		s100.setSeconds(59);
		s100.setMinutes(2147483647);
		StopWatch.setSuspend(false);
		assertTrue(s100.getMinutes() == 2147483647);
		assertTrue(s100.getSeconds() == 59);
		assertTrue(s100.getMilliseconds() == 999);
		assertTrue(StopWatch.isSuspended()==false);
		s100.setMilliseconds(0);
		s100.setSeconds(0);
		s100.setMinutes(0);
		assertTrue(s100.getMinutes() == 0);
		assertTrue(s100.getSeconds() == 0);
		assertTrue(s100.getMilliseconds() == 0);
	}
	//Test setter methods with invalid parameters.
	@Test (expected = IllegalArgumentException.class)
	public void invalidSetMinParameter(){
		StopWatch s101 = new StopWatch();
		s101.setMinutes(-40);
	}

	@Test (expected = IllegalArgumentException.class)
	public void invalidSetSecParameter(){
		StopWatch s102 = new StopWatch();
		s102.setSeconds(-5);
	}

	@Test (expected = IllegalArgumentException.class)
	public void invalidSetSecParameterCase2(){
		StopWatch s103 = new StopWatch();
		s103.setSeconds(877);
	}

	@Test (expected = IllegalArgumentException.class)
	public void invalidSetMillisecParameter(){
		StopWatch s104 = new StopWatch();
		s104.setMilliseconds(3443425);
	}

	@Test (expected = IllegalArgumentException.class)
	public void invalidSetMillisecParameterCase2(){
		StopWatch s105 = new StopWatch();
		s105.setMilliseconds(-543);
	}
	//Test constructor where the parameter(valid) is another StopWatch.
	@Test
	public void testConstructorStopWatchParam(){
		StopWatch s154 = new StopWatch(23,43,546);
		StopWatch s155 = new StopWatch(s154);
		assertTrue(s154.equals(s155));

		StopWatch s156 = new StopWatch(3434234,59,999);
		StopWatch s157 = new StopWatch(s156);
		assertTrue(s156.equals(s157));
		assertFalse(s155.equals(s157));

		s157 = new StopWatch(4343,59,434);
		assertFalse(s156.equals(s157));
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorStopWatchParamInvalid(){
		StopWatch s158 = null;
		StopWatch s159 = new StopWatch(s158);

	}

	// valid input constructor test of StopWatch(int minutes, int seconds, int
	// milliseconds)
	@Test
	public void testConstructor3Parameters() {
		StopWatch s1 = new StopWatch(2, 3, 4);
		assertTrue(s1.getMinutes() == 2);
		assertTrue(s1.getSeconds() == 3);
		assertTrue(s1.getMilliseconds() == 4);

		StopWatch s2 = new StopWatch(20, 30, 40);
		assertTrue(s2.getMinutes() == 20);
		assertTrue(s2.getSeconds() == 30);
		assertTrue(s2.getMilliseconds() == 40);

		StopWatch s3 = new StopWatch(0, 0, 0);
		assertTrue(s3.getMinutes() == 0);
		assertTrue(s3.getSeconds() == 0);
		assertTrue(s3.getMilliseconds() == 0);

		StopWatch s4 = new StopWatch(2147483647, 59, 999);
		assertTrue(s4.getMinutes() == 2147483647);
		assertTrue(s4.getSeconds() == 59);
		assertTrue(s4.getMilliseconds() == 999);
	}

	// Constructor test errors of StopWatch(int minutes, int seconds, int
	// milliseconds)
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor3ParametersX() {
		StopWatch s5 = new StopWatch(2, -3, 4);
		StopWatch s6 = new StopWatch(-2, 3, 4);
		StopWatch s7 = new StopWatch(2, 3, -4);
		StopWatch s8 = new StopWatch(2147483647 + 1, 59, 999);
		StopWatch s9 = new StopWatch(2147483647, 60, 999);
		StopWatch s10 = new StopWatch(2147483647, 59, 1000);

	}

	// Test the contructor where the input is in milliseconds and valid.
	@Test
	public void testConstructorMillisec() {
		StopWatch s11 = new StopWatch(10);
		assertTrue(s11.getMilliseconds() == 10);
		StopWatch s12 = new StopWatch(100);
		assertTrue(s12.getMilliseconds() == 100);
		StopWatch s13 = new StopWatch(1000);
		assertTrue(s13.getMilliseconds() == 0);
		assertTrue(s13.getSeconds() == 1);
		StopWatch s14 = new StopWatch(60000);
		assertTrue(s14.getMilliseconds() == 0);
		assertTrue(s14.getSeconds() == 0);
		assertTrue(s14.getMinutes() == 1);
		StopWatch s15 = new StopWatch(60001);
		assertTrue(s15.getMilliseconds() == 1);
		assertTrue(s15.getSeconds() == 0);
		assertTrue(s15.getMinutes() == 1);
		StopWatch s16 = new StopWatch(61000);
		assertTrue(s16.getMilliseconds() == 0);
		assertTrue(s16.getSeconds() == 1);
		assertTrue(s16.getMinutes() == 1);
		StopWatch s17 = new StopWatch(61001);
		assertTrue(s17.getMilliseconds() == 1);
		assertTrue(s17.getSeconds() == 1);
		assertTrue(s17.getMinutes() == 1);
		StopWatch s18 = new StopWatch(59000);
		assertTrue(s18.getMilliseconds() == 0);
		assertTrue(s18.getSeconds() == 59);
		assertTrue(s18.getMinutes() == 0);
		StopWatch s19 = new StopWatch(59999);
		assertTrue(s19.getMilliseconds() == 999);
		assertTrue(s19.getSeconds() == 59);
		assertTrue(s19.getMinutes() == 0);
		StopWatch s20 = new StopWatch(60000 * 2);
		assertTrue(s20.getMilliseconds() == 0);
		assertTrue(s20.getSeconds() == 0);
		assertTrue(s20.getMinutes() == 2);
	}

	// Tests the constructor with the millisecond parameter(invalid)
	@Test(expected = IllegalArgumentException.class)

	public void testContructorMillisecFail() {
		StopWatch s23 = new StopWatch(-500);
	}

	/*
	 * Tests the constructor with sec, millisecond parameter (valid) works and the
	 * conversion
	 */
	@Test
	public void testConstructorValildSecMillisec() {
		StopWatch s51 = new StopWatch(0, 0);
		assertTrue(s51.getMinutes() == 0);
		assertTrue(s51.getSeconds() == 0);
		assertTrue(s51.getMilliseconds() == 0);

		StopWatch s52 = new StopWatch(0, 1000);
		assertTrue(s51.getMinutes() == 0);
		assertTrue(s52.getSeconds() == 1);
		assertTrue(s52.getMilliseconds() == 0);

		StopWatch s53 = new StopWatch(0, 2050);
		assertTrue(s53.getMinutes() == 0);
		assertTrue(s53.getSeconds() == 2);
		assertTrue(s53.getMilliseconds() == 50);

		StopWatch s54 = new StopWatch(59, 0);
		assertTrue(s54.getMinutes() == 0);
		assertTrue(s54.getSeconds() == 59);
		assertTrue(s54.getMilliseconds() == 0);

		StopWatch s55 = new StopWatch(30, 500);
		assertTrue(s55.getMinutes() == 0);
		assertTrue(s55.getSeconds() == 30);
		assertTrue(s55.getMilliseconds() == 500);

		StopWatch s56 = new StopWatch(60, 0);
		assertTrue(s56.getMinutes() == 1);
		assertTrue(s56.getSeconds() == 0);
		assertTrue(s56.getMilliseconds() == 0);

		StopWatch s57 = new StopWatch(62, 50);
		assertTrue(s57.getMinutes() == 1);
		assertTrue(s57.getSeconds() == 2);
		assertTrue(s57.getMilliseconds() == 50);

		StopWatch s58 = new StopWatch(59, 60000);
		assertTrue(s58.getMinutes() == 1);
		assertTrue(s58.getSeconds() == 59);
		assertTrue(s58.getMilliseconds() == 0);

		StopWatch s59 = new StopWatch(62, 60050);
		assertTrue(s59.getMinutes() == 2);
		assertTrue(s59.getSeconds() == 2);
		assertTrue(s59.getMilliseconds() == 50);

		StopWatch s60 = new StopWatch(121, 1050);
		assertTrue(s60.getMinutes() == 2);
		assertTrue(s60.getSeconds() == 2);
		assertTrue(s60.getMilliseconds() == 50);
	}

	// Test the contructor that takes in a string parameter (valid).
	@Test
	public void testConstructorStringParameter() {
		StopWatch s66 = new StopWatch("35:23:590");
		assertTrue(s66.getMinutes() == 35);
		assertTrue(s66.getSeconds() == 23);
		assertTrue(s66.getMilliseconds() == 590);

		StopWatch s67 = new StopWatch("4434:59:999");
		assertTrue(s67.getMinutes() == 4434);
		assertTrue(s67.getSeconds() == 59);
		assertTrue(s67.getMilliseconds() == 999);

		StopWatch s68 = new StopWatch("26:32:999");
		assertTrue(s68.getMinutes() == 26);
		assertTrue(s68.getSeconds() == 32);
		assertTrue(s68.getMilliseconds() == 999);

		StopWatch s69 = new StopWatch("0:23:590");
		assertTrue(s69.getMinutes() == 0);
		assertTrue(s69.getSeconds() == 23);
		assertTrue(s69.getMilliseconds() == 590);

		StopWatch s70 = new StopWatch("29:50");
		assertTrue(s70.getMinutes() == 0);
		assertTrue(s70.getSeconds() == 29);
		assertTrue(s70.getMilliseconds() == 50);
		
		StopWatch s71 = new StopWatch("63:59");
		assertTrue(s71.getMinutes() == 1);
		assertTrue(s71.getSeconds() == 3);
		assertTrue(s71.getMilliseconds() == 59);

		StopWatch s72 = new StopWatch("59:60001");
		assertTrue(s72.getMinutes() == 1);
		assertTrue(s72.getSeconds() == 59);
		assertTrue(s72.getMilliseconds() == 1);

		StopWatch s73 = new StopWatch("69:60891");
		assertTrue(s73.getMinutes() == 2);
		assertTrue(s73.getSeconds() == 9);
		assertTrue(s73.getMilliseconds() == 891);

		StopWatch s74 = new StopWatch("953");
		assertTrue(s74.getMinutes() == 0);
		assertTrue(s74.getSeconds() == 0);
		assertTrue(s74.getMilliseconds() == 953);

		StopWatch s75 = new StopWatch("2312");
		assertTrue(s75.getMinutes() == 0);
		assertTrue(s75.getSeconds() == 2);
		assertTrue(s75.getMilliseconds() == 312);

		StopWatch s76 = new StopWatch("62561");
		assertTrue(s76.getMinutes() == 1);
		assertTrue(s76.getSeconds() == 2);
		assertTrue(s76.getMilliseconds() == 561);


	}

	// Test the constructor with parameter(invalid) sec, millisec
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorSecMillisecInvalidCase1() {
		StopWatch s61 = new StopWatch(-5, 600);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorSecMillisecInvalidCase2() {
		StopWatch s62 = new StopWatch(60, -5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorSecMillisecInvalidCase3() {
		StopWatch s63 = new StopWatch(-5, -720);
	}

	// Test the constructor with parameter (invalid) min,sec,millisec.
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor3e2Parameters() {
		StopWatch s21 = new StopWatch(12, 67, 14);
	}

	// Test the toString method with a valid string input.
	@Test
	public void testConstructor() {
		StopWatch s22 = new StopWatch(5, 10, 300);
		assertEquals(s22.toString(), "5:10:300");

		s22 = new StopWatch("20:10:8");
		assertEquals(s22.toString(), "20:10:008");

		s22 = new StopWatch("20:8");
		assertEquals(s22.toString(), "0:20:008");

		s22 = new StopWatch("8");
		assertEquals(s22.toString(), "0:00:008");

	}

	// There can only be one test here
	// no more lines of code after "new StopWatch("-2");"
	// Test the constructor with parameter millisec (invalid).
	@Test(expected = IllegalArgumentException.class)
	public void testNegSingleInput2() {
		StopWatch s40 = new StopWatch(-2);

	}

	// Test the constructor with a string parameter (invalid).
	@Test(expected = IllegalArgumentException.class)
	public void testNegDouble1Input() {
		StopWatch s41 = new StopWatch("-59:-23");

	}

	@Test(expected = IllegalArgumentException.class)
	public void testNegDouble2aInput() {
		StopWatch s42 = new StopWatch("2:-2");

	}

	@Test(expected = IllegalArgumentException.class)
	public void testAlphaInput() {
		StopWatch s43 = new StopWatch("a");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidChar() {
		StopWatch s44 = new StopWatch("1:a53:35");
	}

	@Test(expected = IllegalArgumentException.class)
	public void extraColon() {
		StopWatch s45 = new StopWatch("1:53:35:0");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNegTripleInput() {
		StopWatch s46 = new StopWatch("-3:-30:-50");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidValConstructorStringParameter() {
		StopWatch s47 = new StopWatch("500:60:000");

	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidValConstructorStringParameterCase2() {
		StopWatch s48 = new StopWatch("500:30:1000");

	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidValConstructorStringParameterCase3() {
		StopWatch s49 = new StopWatch("500:62:2065");

	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidValConstructorStringParameterCase4() {
		StopWatch s50 = new StopWatch("-1:49:25");
	}

	@Test (expected = IllegalArgumentException.class)
	public void invalidValConstructorStringParameterCase5(){
		StopWatch s77 = new StopWatch(":50:56");
	}

	@Test (expected = IllegalArgumentException.class)
	public void invalidValConstructorStringParameterCase6(){
		StopWatch s78 = new StopWatch("::5056");
	}

	@Test (expected = IllegalArgumentException.class)
	public void invalidValConstructorStringParameterCase7(){
		StopWatch s79 = new StopWatch("542356::34");
	}

	@Test (expected = IllegalArgumentException.class)
	public void invalidValConstructorStringParameterCase8(){
		StopWatch s80 = new StopWatch("50:");
	}
		
    
	/*Test the add() method where the parameter (valid) is an int (millisec)
	and the inc() method */
	@Test
	public void testAddMethod() {
		StopWatch s24 = new StopWatch(5, 59, 300);
		s24.add(2000);
		assertEquals(s24.toString(), "6:01:300");

		s24 = new StopWatch(5, 59, 300);
		StopWatch s25 = new StopWatch(2, 2, 300);
		s24.add(s25);
		System.out.println(s24);
		assertEquals(s24.toString(), "8:01:600");

		for (int i = 0; i < 15000; i++)
			s24.inc();
		System.out.println(s24);
		assertEquals(s24.toString(), "8:16:600");

		StopWatch s161 = new StopWatch(2147483647,59,999);
		s161.add(2147483647);
		assertTrue(s161.getMinutes()==2147483647);
		assertTrue(s161.getMilliseconds()==999);
		assertTrue(s161.getSeconds()==59);

	}

	//Test the static equals method.
	@Test
	public void testStaticEqual(){
		StopWatch s106 = new StopWatch(43,34,65);
		StopWatch s107 = new StopWatch(43,34,65);
		StopWatch s108 = new StopWatch(43,54,43);
		StopWatch s109 = new StopWatch(34,42,544);

		assertTrue(StopWatch.equals(s106,s107));
		assertTrue(StopWatch.equals(s107,s106));
		assertFalse(StopWatch.equals(s106,s108));
		assertFalse(StopWatch.equals(s106,s109));
		assertFalse(StopWatch.equals(s107,s108));
		assertFalse(StopWatch.equals(s108,s109));
		assertFalse(StopWatch.equals(s109,s108));
	}
	//Tests the static equals methods with invalid where parameter objects are null.
	@Test (expected = IllegalArgumentException.class)
	public void testStaticEqualsInvalidCase1(){
		StopWatch s110 = new StopWatch(43,56,343);
		StopWatch s111 = null;
		StopWatch.equals(s110,s111);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testStaticEqualsInvalidCase2(){
		StopWatch s112 = null;
		StopWatch s113 = null;
		StopWatch.equals(s112,s113);
	}

	
	//Tests the equals() method that takes in an object as a parameter.
	@Test
	public void testEqual() {
		StopWatch s26 = new StopWatch(5, 59, 300);
		StopWatch s27 = new StopWatch(6, 01, 200);
		StopWatch s28 = new StopWatch(5, 50, 200);
		StopWatch s29 = new StopWatch(5, 59, 300);

		assertFalse(s26.equals(s27));
		assertTrue(s26.equals(s29));

		assertTrue(s27.compareTo(s26) > 0);
		assertTrue(s28.compareTo(s26) < 0);
		assertTrue(s26.compareTo(s29) == 0);

		StopWatch s81 = new StopWatch(10,10,10);
		StopWatch s82 = new StopWatch(10,10,10);
		StopWatch s83 = new StopWatch(10,9,10);
		StopWatch s84 = new StopWatch(10,10,9);
		StopWatch s85 = new StopWatch(10,9,9);

		assertTrue(s81.equals(s82));
		assertFalse(s81.equals(s83));
		assertFalse(s81.equals(s84));
		assertFalse(s81.equals(s85));
		assertFalse(s82.equals(s83));
		assertFalse(s82.equals(s84));
		assertFalse(s82.equals(s85));
		assertFalse(s83.equals(s84));
		assertFalse(s83.equals(s85));
		assertFalse(s84.equals(s85));

	}
	//Test the equals method where the parameter is invalid.
	@Test (expected = IllegalArgumentException.class)
	public void invalidEqualsParameterCase1(){
		StopWatch s89 = new StopWatch(123,34,577);
		StopWatch s90 = null;
		s89.equals(s90);
	}

	@Test (expected = IllegalArgumentException.class)
	public void invalidEqualsParameterCase2(){
		StopWatch s89 = new StopWatch(123,34,577);
		int integer = 5;
		s89.equals(integer);
	}
	

	//Tests the compareTo() method that takes in an object as a parameter.
	@Test
	public void testCompareTo() {
		StopWatch s30 = new StopWatch(5, 59, 300);
		StopWatch s31 = new StopWatch(6, 01, 200);
		StopWatch s32 = new StopWatch(5, 50, 200);
		StopWatch s33 = new StopWatch(5, 59, 300);

		assertFalse(s30.equals(s31));
		assertTrue(s30.equals(s33));

		assertTrue(s31.compareTo(s30) > 0);
		assertTrue(s32.compareTo(s30) < 0);
		assertTrue(s30.compareTo(s33) == 0);

		StopWatch s85 = new StopWatch(10,10,10);
		StopWatch s86 = new StopWatch(10,10,10);
		StopWatch s87 = new StopWatch(10,9,10);
		StopWatch s88 = new StopWatch(10,10,9);
		StopWatch s89 = new StopWatch(10,9,9);

		assertTrue(s85.compareTo(s86)==0 && s86.compareTo(s85)== 0);
		assertTrue(s85.compareTo(s87)==1 && s87.compareTo(s85)== -1);
		assertTrue(s85.compareTo(s88 )==1 && s88.compareTo(s85)== -1);
		assertTrue(s85.compareTo(s89)==1 && s89.compareTo(s85)== -1);
		assertTrue(s86.compareTo(s87)==1 && s87.compareTo(s86)== -1);
		assertTrue(s86.compareTo(s88)==1 && s88.compareTo(s86)== -1);
		assertTrue(s86.compareTo(s89)==1 && s89.compareTo(s86)== -1);
		assertTrue(s87.compareTo(s88)==-1 && s88.compareTo(s87)== 1);
		assertTrue(s87.compareTo(s89)==1 && s89.compareTo(s87)== -1);
		assertTrue(s88.compareTo(s89)==1 && s89.compareTo(s88)== -1);
	}
	//Tests the compareTo method that when parameters are invalid.
	@Test (expected = IllegalArgumentException.class)
	public void testCompareToInvalidParameterCase1(){
		StopWatch s114 = new StopWatch(43,45,546);
		StopWatch s115 = null;
		s114.compareTo(s115);
	}
	//Tests the save and load method.
	@Test
	public void testLoadSave() {
		StopWatch s34 = new StopWatch(5, 59, 300);
		StopWatch s35 = new StopWatch(5, 59, 300);

		s34.save("file1");
		s34 = new StopWatch(); // resets to zero


		s34.load("file1");
		assertTrue(s34.equals(s35));

		StopWatch s116 = new StopWatch(942432,59,321);
		StopWatch s117 = new StopWatch(942432,59,321);
		s116.save("file2");
		s116 = new StopWatch(); //resets to 0.
		assertTrue(s116.getMilliseconds()==0);
		assertTrue(s116.getSeconds()==0);
		assertTrue(s116.getMinutes()==0);

		s116.load("file2");
		assertTrue(s116.equals(s117));

		StopWatch s118 = new StopWatch(27213123,59,999);
		StopWatch s119 = new StopWatch(27213123,59,999);
		s118.save("file3");
		s118 = new StopWatch();
		assertTrue(s118.getMilliseconds()==0);
		assertTrue(s118.getSeconds()==0);
		assertTrue(s118.getMinutes()==0);
		s118.load("file3");
		assertTrue(s118.equals(s119));
	}

	//Test load method when parameter is invalid
	@Test (expected = IllegalArgumentException.class)
	public void invalidLoadParameter(){
		StopWatch s120 = new StopWatch();
		s120.load("");
	}

	//Test save method when parameter is invalid
	@Test (expected = IllegalArgumentException.class)

	public void invalidSaveParameter(){
		StopWatch s121 = new StopWatch();
		s121.save("");
	}

	//Tests the add method when suspend is active.
	@Test
	public void testMutate() {
		StopWatch s36 = new StopWatch(5, 59, 300);
		StopWatch s37 = new StopWatch(5, 59, 300);

		StopWatch.setSuspend(true);
		s36.add(1000);
		assertTrue(s36.equals(s37));
		StopWatch.setSuspend(false);

		StopWatch s122 = new StopWatch(34,35,647);
		StopWatch s123 = new StopWatch(34,36,647);
		StopWatch.setSuspend(true);
		s122.add(1000);
		assertFalse(s123.equals(s122));
		StopWatch.setSuspend(false);

		StopWatch s124 = new StopWatch(2344332,59,999);
		StopWatch s125 = new StopWatch(2344332,59,999);
		StopWatch.setSuspend(true);
		s124.add(1234);
		assertTrue(s124.equals(s125));
		StopWatch.setSuspend(false);

		StopWatch s126 = new StopWatch();
		StopWatch s127 = new StopWatch(0,0,1);
		s126.add(1);
		assertTrue(s126.equals(s127));

		StopWatch s128 = new StopWatch();
		StopWatch s129 = new StopWatch(0,1,0);
		s128.add(1000);
		assertTrue(s128.equals(s129));

		StopWatch s130 = new StopWatch();
		StopWatch s131 = new StopWatch(0,1,1);
		s130.add(1001);
		assertTrue(s131.equals(s130));

		StopWatch s132 = new StopWatch();
		StopWatch s133 = new StopWatch(1,1,1);
		s132.add(61001);
		assertTrue(s132.equals(s133));

		StopWatch s163 = new StopWatch(2147483647,0,0);
		s163.add(2147483647);
		assertTrue(s163.getMinutes()==2147483647);
		assertTrue(s163.getSeconds()==59);
		assertTrue(s163.getMilliseconds()==999);

		StopWatch s164 = new StopWatch();
		s164.add(2147483647);
		assertTrue(s164.getMinutes()==35791);
		assertTrue(s164.getSeconds()==23);
		assertTrue(s164.getMilliseconds()==647);


	}
	//Test add(int milliseconds) method where parameters are invalid.
	@Test (expected = IllegalArgumentException.class)
	public void testAddMilisecParmInvalid(){
		StopWatch s134 = new StopWatch();
		s134.add(-4354643);
	}
	//Test the add(StopWatch stopWatch) method where parameters are valid.
	@Test
	public void testAddStopWatch(){
		StopWatch s135 = new StopWatch();
		StopWatch s136 = new StopWatch(43,33,342);
		s135.add(s136);
		assertTrue(s135.equals(s136));

		StopWatch s137 = new StopWatch(7,27,8);
		s135.add(s137);
		assertTrue(s135.getMinutes()==51);
		assertTrue(s135.getSeconds()==0);
		assertTrue(s135.getMilliseconds()==350);

		StopWatch s138 = new StopWatch(49000,0,650);
		s135.add(s138);
		assertTrue(s135.getMinutes()==49051);
		assertTrue(s135.getSeconds()==1);
		assertTrue(s135.getMilliseconds()==0);
		StopWatch s147 = new StopWatch(2147483647,0,0);
		s135.add(s147);
		assertTrue(s135.getMinutes()==2147483647);
		assertTrue(s135.getSeconds()==59);
		assertTrue(s135.getMilliseconds()==999);
		s135.add(s135);
		assertTrue(s135.getMinutes()==2147483647);
		assertTrue(s135.getSeconds()==59);
		assertTrue(s135.getMilliseconds()==999);
	}

	//Test the add(StopWatch stopWatch) method where parameters are invalid.
	@Test (expected = IllegalArgumentException.class)
	public void testAddStopWatchInvalidParm(){
		StopWatch s140 = new StopWatch();
		StopWatch s141 = null;
		s140.add(s141);
	}
	//Tests the sub(int milliseconds) with valid parameters.
	@Test
	public void testSubMilliseconds(){
		StopWatch s142 = new StopWatch(32,43,578);
		s142.sub(578);
		assertTrue(s142.getMinutes()==32);
		assertTrue(s142.getSeconds()==43);
		assertTrue(s142.getMilliseconds()==0);
		s142.sub(1000);
		assertTrue(s142.getMinutes()==32);
		assertTrue(s142.getSeconds()==42);
		assertTrue(s142.getMilliseconds()==0);
		s142.sub(60000);
		assertTrue(s142.getMinutes()==31);
		assertTrue(s142.getSeconds()==42);
		assertTrue(s142.getMilliseconds()==0);
		StopWatch s143 = new StopWatch(2,0,0);
		s143.sub(1);
		assertTrue(s143.getMinutes()==1);
		assertTrue(s143.getSeconds()==59);
		assertTrue(s143.getMilliseconds()==999);
		s143 = new StopWatch();
		s143.sub(100000);
		assertTrue(s143.getMinutes()==0);
		assertTrue(s143.getSeconds()==0);
		assertTrue(s143.getMilliseconds()==0);
	}

	//Tests the sub(int milliseconds) with invalid parameters.
	@Test (expected = IllegalArgumentException.class)
	public void testSubMillisecondsInvalidParam(){
		StopWatch s144 = new StopWatch();
		s144.sub(-1);
	}
	//Test the sub(StopWatch stopWatch) with valid parameters.
	@Test
	public void testSubStopWatch(){
		StopWatch s145 = new StopWatch();
		StopWatch s146 = new StopWatch(32,43,435);
		s145.sub(s146);
		assertTrue(s145.getMinutes()==0);
		assertTrue(s145.getSeconds()==0);
		assertTrue(s145.getMilliseconds()==0);
		
		StopWatch s147 = new StopWatch(0,0,436);
		s146.sub(s147);
		assertTrue(s146.getMinutes()==32);
		assertTrue(s146.getSeconds()==42);
		assertTrue(s146.getMilliseconds()==999);
		
		StopWatch s148 = new StopWatch(0,43,0);
		s146.sub(s148);
		assertTrue(s146.getMinutes()==31);
		assertTrue(s146.getSeconds()==59);
		assertTrue(s146.getMilliseconds()==999);
		
		StopWatch s149 = new StopWatch(32,0,0);
		s146.sub(s149);
		assertTrue(s146.getMinutes()==0);
		assertTrue(s146.getSeconds()==0);
		assertTrue(s146.getMilliseconds()==0);
		
		StopWatch s150 = new StopWatch(2147483647,59,999);
		s146.sub(s150);
		assertTrue(s146.getMinutes()==0);
		assertTrue(s146.getSeconds()==0);
		assertTrue(s146.getMilliseconds()==0);

		StopWatch s151 = new StopWatch(43252,59,999);
		StopWatch s152 = new StopWatch(41000,57,999);
		s151.sub(s152);
		assertTrue(s151.getMinutes()==2252);
		assertTrue(s151.getSeconds()==2);
		assertTrue(s151.getMilliseconds()==0);
	}
	//Tests sub(StopWatch stopWatch) with invalid parameter.
	@Test (expected = IllegalArgumentException.class)
	public void testSubStopWatchInvalidParam(){
		StopWatch s152 = new StopWatch();
		StopWatch s153 = null;
		s152.sub(s153);
	}
	//Tests the inc() method
	@Test
	public void testInc(){
		StopWatch s158 = new StopWatch();
		s158.inc();
		assertTrue(s158.getMilliseconds()==1);
		s158 = new StopWatch(0,0,999);
		s158.inc();
		assertTrue(s158.getMilliseconds()==0);
		assertTrue(s158.getSeconds()==1);
		s158 = new StopWatch(0,59,999);
		s158.inc();
		assertTrue(s158.getMinutes()==1);
		s158.inc();
		assertTrue(s158.getMilliseconds()==1);
		s158 = new StopWatch(1,0,999);
		s158.inc();
		assertTrue(s158.getMilliseconds()==0);
		assertTrue(s158.getSeconds()==1);
		s158 = new StopWatch(2147483647,59,999);
		s158.inc();
		assertTrue(s158.getMinutes()==2147483647);
		assertTrue(s158.getMilliseconds()==999);
		assertTrue(s158.getSeconds()==59);
		
	}

	//Test the dec() method
	@Test
	public void testDec(){
		StopWatch s159 = new StopWatch(999,59,999);
		s159.dec();
		assertTrue(s159.getMinutes()==999);
		assertTrue(s159.getSeconds()==59);
		assertTrue(s159.getMilliseconds()==998);

		s159 = new StopWatch(999,1,0);
		s159.dec();
		assertTrue(s159.getMinutes()==999);
		assertTrue(s159.getSeconds()==0);
		assertTrue(s159.getMilliseconds()==999);

		s159 = new StopWatch(999,0,0);
		s159.dec();
		assertTrue(s159.getMinutes()==998);
		assertTrue(s159.getSeconds()==59);
		assertTrue(s159.getMilliseconds()==999);

		s159 = new StopWatch();
		s159.dec();
		assertTrue(s159.getMinutes()==0);
		assertTrue(s159.getSeconds()==0);
		assertTrue(s159.getMilliseconds()==0);
	}
	@Test
	public void testConvertMillisec(){
		StopWatch s160 = new StopWatch(1,0,0);
		
	}


	
	@Test
	public void equalsTest() {
		StopWatch s38 = new StopWatch(5, 59, 300);
		StopWatch s39 = new StopWatch(5, 59, 300);

		assertEquals(s38, s39);
	}
}
