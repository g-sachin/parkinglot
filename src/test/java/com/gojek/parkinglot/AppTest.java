package com.gojek.parkinglot;
import java.io.ByteArrayOutputStream;

import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;


/**
 * Unit test for simple App.
 */
public class AppTest
{
	private final ByteArrayOutputStream	outContent	= new ByteArrayOutputStream();
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@After
	public void cleanUp()
	{
		System.setOut(null);
	}
	
}
