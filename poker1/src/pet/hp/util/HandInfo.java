package pet.hp.util;

import java.util.Arrays;

import pet.eq.Poker;
import pet.hp.*;

public class HandInfo {
	public final Hand hand;
	public Hole hole;
	
	public Seat winner;
	public int wonOn;
	
	// "Winner", "WonOn", "Value"
	// position, stacks, spr, n-flop/draw,
	public HandInfo(Hand hand) {
		this.hand = hand;
		this.hole = new Hole(hand.myseat.hole);
	}
	
	public int mypos() {
		// FIXME just look at first street actions?
		for (int n = 0; n < hand.seats.length; n++) {
			if (hand.seats[n].num == hand.button) {
				for (int p = 0; p < hand.max; p++) {
					if (hand.seats[(n + p) % hand.seats.length] == hand.myseat) {
						return p;
					}
				}
				return -1;
			}
		}
		return -2;
	}
	
	public int myvalue() {
		return hand.myseat.won - hand.myseat.pip + hand.myseat.uncalled;
	}
	
	public String getDescription() {
		StringBuilder sb= new StringBuilder();
		sb.append("button " + hand.button + "\n");
		sb.append("currency " + hand.currency + "\n");
		sb.append("date " + hand.date + "\n");
		sb.append("discarded " + Arrays.toString(hand.mydiscard) + "\n");
		sb.append("game " + hand.gamename + "\n");
		sb.append("gametype " + hand.gametype + "\n");
		sb.append("id " + hand.id + "\n");
		sb.append("max " + hand.max + "\n");
		sb.append("pot " + hand.pot + "\n");
		sb.append("rake " + hand.rake + "\n");
		sb.append("showdown " + hand.showdown + "\n");
		sb.append("table " + hand.tablename + "\n");
		
		for (Seat seat : hand.seats) {
			sb.append("  seat " + seat + "\n");
		}
		
		for (int s = 0; s < hand.streets.length; s++) {
			sb.append("street " + s + " board " + HandUtil.getStreetBoard(hand.board, s) + "\n");
			for (Action a : hand.streets[s]) {
				sb.append("  act " + a + "\n");
			}
		}
		return sb.toString();
	}
}