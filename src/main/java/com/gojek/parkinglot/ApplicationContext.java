package com.gojek.parkinglot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.gojek.parkinglot.controller.ParkingController;

public class ApplicationContext {
	
	private ParkingController controller;
	
	public ApplicationContext(){
		this.controller = new ParkingController();
	}
	public static void main(String[] args) {
		System.out.println("Hello World!");
		
		String file = "D:\\eclipse-workspace\\gojek\\parkinglot\\src\\main\\resources\\file_inputs.txt";
		// Mode: File 
		try {
			//new ApplicationContext().readInputFile(args[0]);
			new ApplicationContext().readInputFile(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Mode: Interactive 
		//TODO
	}
	
	private void readInputFile(String fileName) throws Exception{
		if(fileName == null)
			throw new Exception("File not found");
		
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			
			String line = br.readLine();
			while (line != null){
				//1. validate input
				if(controller.validate(line))
					controller.executeCommand(line);
				else
					System.out.println("Command is not valid; operation: "+line);
				line = br.readLine();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			if (fr != null)
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Exception in closing file");
				}
		}
	}
}
