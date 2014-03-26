/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.despegar.highflight;

import java.util.ArrayList;
import java.util.Random;
import com.despegar.highflight.utils.Matrix2DCellPosition;

public class MinesweeperMatrix implements Matrix<MinesweeperCell>{
	private MinesweeperCell[][] matrixArray;
	int mines;
    public MinesweeperMatrix(Integer width, Integer height){
        matrixArray = new MinesweeperCell[width][height];
        this.generateMines();
        this.generateCells();
    }
    public int getNumberOfMines(){
    	return mines;
    }
	public void setCell(Matrix2DCellPosition position, MinesweeperCell value) {
        this.matrixArray[position.getRow()][position.getColumn()]=value;
	}

	public void clearMatrix() {
		this.matrixArray=null;
		
	}
	public MinesweeperCell getCell(Matrix2DCellPosition position) {
		return matrixArray[position.getRow()][position.getColumn()];
	}

	public Integer getHeight() {
		return matrixArray[0].length;
	}

	public Integer getWidth() {
		// TODO Auto-generated method stub
		return matrixArray.length;
	}

	public Integer getArea() {
		return this.getHeight()*this.getWidth();
	}
	public void generateMines(){
		mines=0;
		while(mines<(this.getArea()*0.15)){
			Random rand = new Random();
			int row = rand.nextInt(this.getWidth());
			int column = rand.nextInt(this.getHeight())-1;
			Matrix2DCellPosition position = new Matrix2DCellPosition(row,column);
			if(this.isValidPosition(position)){
				if((this.adjacentMines(this.getAdjacentCells(position))<3)&&!this.thereIsMine(this.getCell(position))){
					this.setCell(position,new MinesweeperCell('*'));
					mines++;
				}
			}
		}
	}
	public void generateCells(){
		for(int i=0;i<this.getWidth();i++){
			for(int j=0;j<this.getHeight();j++){
				if(!this.thereIsMine(matrixArray[i][j])){
					Matrix2DCellPosition position = new Matrix2DCellPosition(i,j);
					int mines=this.adjacentMines(this.getAdjacentCells(position));
					this.setCell(position,new MinesweeperCell( (char)(mines+48)));
				}
			}
		}
	}
	public int[][] matrixToInt(){
		int[][] matrixInt = new int[this.getWidth()][this.getHeight()];
		for(int i=0;i<this.getWidth();i++){
			for(int j=0;j<this.getHeight();j++){
				MinesweeperCell cell = matrixArray[i][j];
				if(this.thereIsMine(cell))matrixInt[i][j]=1;
				else matrixInt[i][j]=0;
			}
		}
		return matrixInt;
	}
	public int adjacentMines(ArrayList<MinesweeperCell> adjacentCells){
		int mines = 0;
		for(MinesweeperCell cell:adjacentCells){
			if(this.thereIsMine(cell))mines++;
		}
		return mines;
	}
	public boolean thereIsMine(MinesweeperCell cell){
		try{
			if(cell.value.equals('*')) return true;	
		}
		catch(NullPointerException error){
			
		}
		return false;
	}
	public ArrayList<MinesweeperCell> getAdjacentCells(Matrix2DCellPosition position){
		int[]rowIndex = new int[]{-1,0,1,-1,1,-1,0,1};
		int[]columnIndex = new int[]{-1,-1,-1,0,0,1,1,1};
		ArrayList<MinesweeperCell> adjacentCells = new ArrayList<MinesweeperCell>();
		for(int i=0;i<8;i++){
			Matrix2DCellPosition j = new Matrix2DCellPosition(rowIndex[i]+position.getRow(),columnIndex[i]+position.getColumn());
			if(isValidPosition(j)){
				adjacentCells.add(this.getCell(j));
			}
		}
		return adjacentCells;
		
	}
	boolean isValidPosition(Matrix2DCellPosition position){
		MinesweeperCell test = new MinesweeperCell();
		try{
			test =this.getCell(position);
		}
		catch(ArrayIndexOutOfBoundsException error){
			return false;
		}
		return true;
	}
}