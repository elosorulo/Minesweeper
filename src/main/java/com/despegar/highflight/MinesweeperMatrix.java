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
    public MinesweeperMatrix(Integer width, Integer height){
        matrixArray = new MinesweeperCell[width][height];
        this.generateMines();
        for(int i=0;i<this.getWidth();i++){
        	for(int j=0;j<this.getHeight();j++){
        		matrixArray[i][j]= new MinesweeperCell();
        		if(matrixArray[i][j].getValue().equals('*')){
        			Matrix2DCellPosition position = new Matrix2DCellPosition(i,j);

        			int numberOfMines= this.adjacentMines(getAdjacentCells(position));
        			matrixArray[i][j].setValue(Character.toChars(numberOfMines)[0]);
        		}
        		else matrixArray[i][j].setValue('0');
        	}
        }
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
		int mines=0;
		MinesweeperCell cell = new MinesweeperCell('*');
		while(mines<(this.getArea()*0.15)){
			Random rand = new Random();
			int row = rand.nextInt(this.getWidth());
			int column = rand.nextInt(this.getHeight())-1;
			Matrix2DCellPosition position = new Matrix2DCellPosition(row,column);
			if((this.adjacentMines(this.getAdjacentCells(position))<3)||!this.getCell(position).getValue().equals('*')){
				if(this.isValidPosition(position))this.setCell(position,cell);
				mines++;
			}
		}
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
		catch(NullPointerException error){System.out.println("");}
		return false;
	}
	public ArrayList<MinesweeperCell> getAdjacentCells(Matrix2DCellPosition position){
		int[]rowIndex = new int[]{-1,0,1,-1,1,-1,0,1};
		int[]columnIndex = new int[]{-1,-1,-1,0,0,1,1,1};
		ArrayList<MinesweeperCell> adjacentCells = new ArrayList<MinesweeperCell>();
		for(int i=0;i<8;i++){
			Matrix2DCellPosition j = new Matrix2DCellPosition(rowIndex[i],columnIndex[i]);
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