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
	public void uncover(int row, int col) {
		// TODO Auto-generated method stub
		
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
		if(matrix.getCell(position).equals('*'))return true;
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
		// TODO Auto-generated method stub
		
	}
}