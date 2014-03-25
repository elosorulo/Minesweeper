/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.despegar.highflight;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.despegar.highflight.utils.Matrix2DCellPosition;

public class MinesweeperMatrix implements Matrix<MinesweeperCell>{
	private MinesweeperCell[][] matrixArray;
    public MinesweeperMatrix(Integer width, Integer height){
        matrixArray = new MinesweeperCell[width][height];
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                matrixArray[i][j]=new MinesweeperCell();
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
		Random rand = new Random();
		int row = rand.nextInt(this.getWidth());
		int column = rand.nextInt(this.getHeight())-1;
	}
	public ArrayList<MinesweeperCell> getAdjacentCells(){
		int[]rowIndex = new int[]{-1,0,1,-1,1,-1,0,1};
		int[]columnIndex = new int[]{-1,-1,-1,0,0,1,1,1};
		List<MinesweeperCell> adjacentCells = new ArrayList();
		return null;
		
	}
}
