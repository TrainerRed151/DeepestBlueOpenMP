//Brian Pomerantz

import java.util.*;

public class Queen extends Piece {
	public Queen(boolean si, byte r, byte f) {
		super(si, 'Q', (byte) 9, r, f);
	}
	
	//Updates legal move list
	public void update(Board bd) {
		moves.clear();
		attack.clear();
		//defend.clear();
		
		Piece[][] b = bd.getBoard();
		//int pieceValue, a = 0, d = 0, m;
		byte r = getRank();
		byte c = getFile();
					
		for (byte i = 1; bd.isValid(r + i, c); i++) {
			if (b[r + i][c] instanceof Null) {
				moves.add(b[r + i][c]);
			}
			
			else if (b[r + i][c].getSide() != getSide()) {
				moves.add(b[r + i][c]);
				attack.add(b[r + i][c]);
				break;
			}
			
			else {
				//defend.add(b[r + i][c]);
				break;
			}
		}
		
		for (byte i = -1; bd.isValid(r + i, c); i--) {
			if (b[r + i][c] instanceof Null) {
				moves.add(b[r + i][c]);
			}
			
			else if (b[r + i][c].getSide() != getSide()) {
				moves.add(b[r + i][c]);
				attack.add(b[r + i][c]);
				break;
			}
			
			else {
				//defend.add(b[r + i][c]);
				break;
			}
		}
		
		for (byte i = 1; bd.isValid(r, c + i); i++) {
			if (b[r][c + i] instanceof Null) {
				moves.add(b[r][c + i]);
			}
			
			else if (b[r][c + i].getSide() != getSide()) {
				moves.add(b[r][c + i]);
				attack.add(b[r][c + i]);
				break;
			}
			
			else {
				//defend.add(b[r][c + i]);
				break;
			}
		}
		
		for (byte i = -1; bd.isValid(r, c + i); i--) {
			if (b[r][c + i] instanceof Null) {
				moves.add(b[r][c + i]);
			}
			
			else if (b[r][c + i].getSide() != getSide()) {
				moves.add(b[r][c + i]);
				attack.add(b[r][c + i]);
				break;
			}
			
			else {
				//defend.add(b[r][c + i]);
				break;
			}
		}
		
		for (byte i = 1; bd.isValid(r + i, c + i); i++) {
			if (b[r + i][c + i] instanceof Null) {
				moves.add(b[r + i][c + i]);
			}
			
			else if (b[r + i][c + i].getSide() != getSide()) {
				moves.add(b[r + i][c + i]);
				attack.add(b[r + i][c + i]);
				break;
			}
			
			else {
				//defend.add(b[r + i][c + i]);
				break;
			}
		}
		
		for (byte i = -1; bd.isValid(r + i, c + i); i--) {
			if (b[r + i][c + i] instanceof Null) {
				moves.add(b[r + i][c + i]);
			}
			
			else if (b[r + i][c + i].getSide() != getSide()) {
				moves.add(b[r + i][c + i]);
				attack.add(b[r + i][c + i]);
				break;
			}
			
			else {
				//defend.add(b[r + i][c + i]);
				break;
			}
		}
		
		for (byte i = 1; bd.isValid(r + i, c - i); i++) {
			if (b[r + i][c - i] instanceof Null) {
				moves.add(b[r + i][c - i]);
			}
			
			else if (b[r + i][c - i].getSide() != getSide()) {
				moves.add(b[r + i][c - i]);
				attack.add(b[r + i][c - i]);
				break;
			}
			
			else {
				//defend.add(b[r + i][c - i]);
				break;
			}
		}
		
		for (byte i = -1; bd.isValid(r + i, c - i); i--) {
			if (b[r + i][c - i] instanceof Null) {
				moves.add(b[r + i][c - i]);
			}
			
			else if (b[r + i][c - i].getSide() != getSide()) {
				moves.add(b[r + i][c - i]);
				attack.add(b[r + i][c - i]);
				break;
			}
			
			else {
				//defend.add(b[r + i][c - i]);
				break;
			}
		}
		
//		for (int i = 0; i < attack.size(); i++) {
//			a += attack.get(i).getValue();
//			
//			if (attack.get(i) instanceof King) {
//				a += 10;
//			}
//		}
//		
//		for (int i = 0; i < defend.size(); i++) {
//			d += defend.get(i).getValue();
//		}
		
		//m = moves.size();
		//pieceValue = (45/getValue()) * (getValue() + a + d + m);
		
		//pieceValue = getValue();
		
		//return pieceValue;
	}
	
	public ArrayList<Piece> getMoves() {
		return moves;
	}
	
	public ArrayList<Piece> getAttack() {
		return attack;
	}
//	
//	public ArrayList<Piece> getDefend() {
//		return defend;
//	}
}