import java.util.Random;


class ComputerAI extends Player{
	private int position;
	private char avatar;
	private int money;
	private int playerNumber = 2;
	private boolean isPlayer = false;
	
	public ComputerAI() {
		position = 1;
        avatar = 'C';
        money = 1500;
	}

	public ComputerAI(int playerNumber, boolean isPlayer){
		position = 1;
		avatar = 'C';
		money = 1500;
		setPlayerNumber(playerNumber);
		setIsPlayer(isPlayer);
	}

	public int getPlayerNumber(){
		return playerNumber;
	}

	public void setIsPlayer(boolean newVal){
		this.isPlayer = newVal;
	}

	public void setPlayerNumber(int playerNumber){
		this.playerNumber = playerNumber;
	}

	public void takeTurn(Board board, Player player) {
		if(isPlayer == false){
			wait(250);
			print("\nIt is the computer's turn");
			int x = rollDie();
			wait(250);
			print("AI rolled a " + String.valueOf(x));
			int oldPosition = this.getPosition();
			this.setPosition(oldPosition + x);
			if (this.getPosition() > board.getLength()) {
				wait(250);
				print("AI passed GO and collected $200");
				this.setPosition(this.getPosition() - board.getLength());
				this.setMoney(this.getMoney() + 200);
				wait(250);
			}

			Space newSpace = board.getSpace(this.getPosition() - 1);
			wait(250);
			print("AI landed on " + newSpace.getName());

			/**
			 *when a player comes to an unowned place, player will have the opinion to determine purchase this very place or not.
			 *if player decide to own the place and have enough money, player will lose the amount of money and own this place.
			 **/
			if (newSpace.getOwner() == 0) {
				wait(250);
				print(newSpace.getName() + " is unowned and costs $" + String.valueOf(newSpace.getCost()) + ". (Value of $" + String.valueOf(newSpace.getValue()) + ")");

				//If the AI can afford the space, they buy it
				if (this.getMoney() >= newSpace.getCost()) {
					print("AI bought " + newSpace.getName());
					newSpace.setOwner(2);
					this.setMoney(this.getMoney() - newSpace.getCost());
					board.setSpace(this.getPosition() - 1, newSpace);
				}
				else
					print("They can't afford it");
			}
			//If the player lands on a space they already own
			else if (newSpace.getOwner() == 2) {
				wait(250);
				print("The AI already owns " + newSpace.getName());
			}

			//If the AI lands on a space owned by the player
			else if (newSpace.getOwner() == 1) {
				wait(250);
				print("You own " + newSpace.getName() + " The AI owes you $" + String.valueOf(newSpace.getValue()));
				this.setMoney(this.getMoney() - newSpace.getValue());
				player.setMoney(player.getMoney() + newSpace.getValue());
			}

		}
		else{
			super.takeTurn(board, player);
		}
	}
		
}
