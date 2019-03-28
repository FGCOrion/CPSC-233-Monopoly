import java.util.Random;


class ComputerAI extends Player{

	/**
	* Basic setup variables for the computer's position, avatar, money and number
	**/
	private int position;
	private char avatar;
	private int money;
	private int playerNumber = 2;
	private boolean isPlayer = false;


	/**
	* Default Constructor
	**/
	public ComputerAI() {
		position = 1;
		avatar = 'C';
		money = 1500;
	}

	/**
	* Constructor
	* @param playerNumber
	* @param isPlayer		To make sure the computer controls this player's actions
	**/
	public ComputerAI(int playerNumber, boolean isPlayer){
		position = 1;
		avatar = 'C';
		money = 1500;
		setPlayerNumber(playerNumber);
		setIsPlayer(isPlayer);
	}

	/**
	* Returns which player it is as an integer
	* @return playerNumber
	**/
	public int getPlayerNumber(){
		return playerNumber;
	}

	/**
	* Sets the players number so they can be called later
	* @param playerNumber
	**/
	public void setPlayerNumber(int playerNumber){
		this.playerNumber = playerNumber;
	}

	/**
	* Basic turn for the computer, automatically rolls the dice and moves the computer player to make decisions
	* @param board
	* @param player
	**/
	public void takeTurn(Board board, Player player) {
		if(isPlayer == false){
			print("\nIt is the computer's turn");
			int x = rollDie();
			print("AI rolled a " + String.valueOf(x));
			int oldPosition = this.getPosition();
			this.setPosition(oldPosition + x);
			
			//If the player goes all the way around the board it resets their position and they get $200
			if (this.getPosition() > board.getLength()) {
				print("AI passed GO and collected $200");
				this.setPosition(this.getPosition() - board.getLength());
				this.setMoney(this.getMoney() + 200);
			}

			Space newSpace = board.getSpace(this.getPosition() - 1);
			print("AI landed on " + newSpace.getName());

			/**
			 *When an AI comes to an unowned place, it will have the opinion to determine purchase the place or not.
			 *If it has enough money it will purchase it, otherwise it will not, and the later code blocks deal with the
			 * other cases
			 **/
			if (newSpace.getOwner() == 0) {
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
				print("The AI already owns " + newSpace.getName());
			}

			//If the AI lands on a space owned by the player
			else if (newSpace.getOwner() == 1) {
				print("You own " + newSpace.getName() + " The AI owes you $" + String.valueOf(newSpace.getValue()));
				this.setMoney(this.getMoney() - newSpace.getValue());
				player.setMoney(player.getMoney() + newSpace.getValue());
			}
		}
		else{
			// If the player is a human player instead of a computer player than it calles the human takeTurn method
			super.takeTurn(board, player);
		}
	}
}
