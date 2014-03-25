package com.despegar.highflight;

public class MinesweeperCell implements Cell <Character>{
	Character value;
	boolean flagged;
	public MinesweeperCell(){
		this.value='0';
		this.flagged=false;
	}
	public MinesweeperCell(Character value){
		this.value=value;
		this.flagged=false;
	}
	public boolean isFlagged() {
		// TODO Auto-generated method stub
		return this.flagged;
	}

	public Character getValue() {
		// TODO Auto-generated method stub
		return this.value;
	}

	public void setValue(Character value) {
		this.value=value;
	}

	public void unFlag() {
		this.flagged=false;
	}

	public void flag() {
		this.flagged=true;
	}
}
