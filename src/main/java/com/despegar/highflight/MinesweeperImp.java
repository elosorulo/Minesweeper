/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.despegar.highflight;


import java.util.Set;

import javax.swing.JOptionPane;

import com.despegar.highflight.utils.Matrix2DCellPosition;
import com.despegar.highflight.utils.MatrixUtils;

public class MinesweeperImp implements Minesweeper{
    MinesweeperMatrix matrix;
    int uncoveredCells;
    boolean lastCellIsMine;
    public MinesweeperImp(int width,int height){
    	matrix = new MinesweeperMatrix(width,height);
    	lastCellIsMine=false;
    }
	public void uncover(int row, int col) {
		Set <Matrix2DCellPosition> positions = MatrixUtils.cascade(matrix.matrixToInt(),row,col);
		for(Matrix2DCellPosition position:positions){
			matrix.getCell(position).unCover();
			if(!matrix.getCell(position).isCovered())uncoveredCells++;
		}
		if (positions.isEmpty())lastCellIsMine=true;
	}
	public void flagAsMine(int row, int col) {
		Matrix2DCellPosition position =new Matrix2DCellPosition(row, col);
		this.matrix.getCell(position).flag();
	}
	public void clearFlag(int row, int col) {
		Matrix2DCellPosition position =new Matrix2DCellPosition(row, col);
		this.matrix.getCell(position).unFlag();
	}
	public boolean thereIsMine(Matrix2DCellPosition position){
		if(matrix.thereIsMine(matrix.getCell(position)))return true;
		return false;
	}
	public boolean isGameOver() {
        if(lastCellIsMine)return true;
        return isWinningGame();
	}
	public boolean isWinningGame() {
		// TODO Auto-generated method stub
		if(uncoveredCells==(matrix.getArea()-matrix.getNumberOfMines()))return true;
		return false;
	}
	public void display(int displayType){
		System.out.println(this.uncoveredCells+"/"+(matrix.getArea()-matrix.getNumberOfMines()));
		System.out.println("");
		for(int i=0;i<matrix.getWidth();i++){
			String output="";
			for(int j=0;j<matrix.getWidth();j++){
				output+="| ";
				MinesweeperCell cell = this.matrix.getCell(new Matrix2DCellPosition(i,j));
				switch(displayType){
				case 0:
					output+=cell.display();
					break;
				case 1:
					output+=cell.getValue();
					break;
				case 2:
					if(this.thereIsMine(new Matrix2DCellPosition(i,j)))output+=1;
					else output+=0;
					break;
				case 3:
					if(this.thereIsMine(new Matrix2DCellPosition(i,j)))output+="*";
					else output+=cell.display();
				}
				output+=" | ";
			}
			System.out.println(output);
			System.out.println(" ");
		}
	}
	public void display() {
		// TODO Auto-generated method stub
		this.display(0);
	}
	public void displayInternal() {
		this.display(1);
	}
	public void displayRaw() {

		this.display(2);
	}
	public void displayGameOver(){
		this.display(3);
	}
	public static String textInput(String message){
		String input;
		input = JOptionPane.showInputDialog(message);
		return input;			
	}
	public int rowInput(){
		int value;
		String input;
		boolean isValid;
		do{
			isValid=true;
			input = JOptionPane.showInputDialog(null,"Row:");
			if(isNumeric(input))isValid=false;
			value=Integer.parseInt(input);
			if(value-1<0&& value-1>matrix.getWidth())isValid=false;
		}while(isValid);
		return value;
	}
	public int columnInput(){
		int value;
		boolean isValid;
		String input;
		do{
			isValid=true;
			input = JOptionPane.showInputDialog(null,"Column:");
			if(isNumeric(input))isValid=false;
			value=Integer.parseInt(input);
			if(value-1<0&& value-1>matrix.getHeight())isValid=false;
		}while(isValid);
		return value;
	}
	public static int operationInput(String message){
		boolean valid = true;
		String input;
		int value;
		do{
			input= textInput(message);
			if(!isNumeric(input))valid = false;
			value = Integer.parseInt(input);
			if(isValidOperation(value)) valid = false;
		}while(valid);
		return value;
	}
	public static boolean isNumeric(String text){
		int number;
		try{
			number=Integer.parseInt(text);
		}
		catch(NumberFormatException error){
			return false;
		}
		return true;
	}
	public static boolean isValidOperation(int operation){
		if(operation > 0 && operation < 4)return true;
		return false;
	}
	public static void main(String[] args){
		String input="";
		do{
			int difficulty = operationInput("Difficulty. 1:Easy(4*4), 2:Intermediate(8*8), 3:Expert(12,16).");
			MinesweeperImp game = new MinesweeperImp(4,4);
			switch (difficulty){
			case 1:
				game = new MinesweeperImp(4,4);
				break;
			case 2:
				game = new MinesweeperImp(8,8);
				break;
			case 3:
				game = new MinesweeperImp(12,16);
			}
			do{
				game.display();
				System.out.println("--------------");
				int operation = operationInput("Operation. 1:Flag. 2: Unflag. 3: Uncover.");
				int row = game.rowInput()-1;
				int col = game.columnInput()-1;
				switch(operation){
				case 1:
					game.flagAsMine(row, col);
					break;
				case 2:
					game.clearFlag(row,col);
					break;
				case 3:
					game.uncover(row, col);
				}
			}while(!game.isGameOver());
			if(game.isWinningGame()){
				game.displayInternal();
				System.out.println("You Win!");
			}
			else{
				game.displayGameOver();
				System.out.println("Game Over.");
			}
			input=JOptionPane.showInputDialog(null,"Try Again?");
		}
		while(input.equalsIgnoreCase("y"));
	}
}