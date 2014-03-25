/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.despegar.highflight;

import java.util.Set;

import com.despegar.highflight.utils.Matrix2DCellPosition;

public class MinesweeperImp implements Minesweeper{
    MinesweeperMatrix matrix;
    Set<Matrix2DCellPosition> uncoveredCells;
    Matrix2DCellPosition lastUncoveredCell;
    public MinesweeperImp(int width,int height){
    	matrix = new MinesweeperMatrix(width,height);
    }
	public void uncover(int row, int col) {
		
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
        if(this.thereIsMine(this.lastUncoveredCell))return true;
        return isWinningGame();
	}

    public Integer getNumberOfMines(){
        return (int)(matrix.getArea()*0.15);
    }
	public boolean isWinningGame() {
		// TODO Auto-generated method stub
		if(uncoveredCells.size()==(matrix.getArea()-this.getNumberOfMines()))return true;
		return false;
	}
	public void display() {
		// TODO Auto-generated method stub
		
	}
	public void displayInternal() {
		
	}
	public void displayRaw() {
		for(int i=0;i<matrix.getWidth();i++){
			String output="";
			for(int j=0;j<matrix.getHeight();j++){
				if(this.thereIsMine(new Matrix2DCellPosition(i,j)))output+=1;
				else output+=0;
			}
			System.out.println(output);
		}
	}
	public static void main(String[] args){
		MinesweeperImp game = new MinesweeperImp(20,20);
		game.displayRaw();
	}
}