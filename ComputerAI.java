import java.util.Random;
import java.util.ArrayList;


class ComputerAI extends Player{
	private int position;
	private char avatar;
	private int money;
	private Board size=new Board();
	private int newMoney;
	private int newProperty=0;
	private String information1;
	private String information2;
	private String information3;
	private String information4;
	private String information0;
	


	public int getNewMoney(){
		return newMoney;
	}
	
	public int getNewProperty(){
		return newProperty;
	}
	public ComputerAI() {
		position = 1;
        avatar = 'C';
        money = 1500;
	}
	
	public String getInformation0(){
		return information0;
	}
	public String getInformation1(){
		return information1;
	}
	public String getInformation2(){
		return information2;
	}
	public String getInformation3(){
		return information3;
	}
	public String getInformation4(){
		return information4;
	}
	

	public void takeTurn(Board board, Player player) {
		wait(250);
		//print("\nIt is the computer's turn");
		int x = rollDie();
		wait(250);
		//print("AI rolled a " + String.valueOf(x));
		information1="AI rolled a " + String.valueOf(x);
		int oldPosition = this.getPosition();
		this.setPosition(oldPosition + x);
		if (this.getPosition() > board.getLength()) {
			wait(250);
			
			information0="AI passed GO and collected $200";
			this.setPosition(this.getPosition() - board.getLength());
			this.setMoney(this.getMoney() + 200);
			wait(250);
		}
		else
		{	
			
			information0="";
		}
		
		Space newSpace = board.getSpace(this.getPosition() - 1);
		wait(250);
		//print("AI landed on " + newSpace.getName());
		information2="AI landed on " + newSpace.getName();
		

		
		/**
		*when a player comes to an unowned place, player will have the opinion to determine purchase this very place or not. 
		*if player decide to own the place and have enough money, player will lose the amount of money and own this place.
		**/
		if (newSpace.getOwner() == 0) {
			wait(250);
			//print(newSpace.getName() + " is unowned and costs $" + String.valueOf(newSpace.getCost()) + ". (Value of $" + String.valueOf(newSpace.getValue()) + ")");
			information3=newSpace.getName() + " is unowned and costs $" + String.valueOf(newSpace.getCost()) + ". (Value of $" + String.valueOf(newSpace.getValue()) + ")";
			//If the AI can afford the space, they buy it
			if (this.getMoney() >= newSpace.getCost()) {
				//print("AI bought " + newSpace.getName());
				
				information4="AI bought " + newSpace.getName();
				newSpace.setOwner(2);
				newProperty+=1;
				this.setMoney(this.getMoney() - newSpace.getCost());
				
				board.setSpace(this.getPosition() - 1, newSpace);
			}
			else 
			{
				//print("They can't afford it");
				
				information4="They can't afford it";
			}
		}
		//If the player lands on a space they already own
		else if (newSpace.getOwner() == 2) {
			wait(250);
			//print("The AI already owns " + newSpace.getName());
			
			information3="The AI already owns " + newSpace.getName();
			information4="";
		}
		
		//If the AI lands on a space owned by the player
		else if (newSpace.getOwner() == 1) {
			wait(250);
			//print("You own " + newSpace.getName() + " The AI owes you $" + String.valueOf(newSpace.getValue()));
		
			information3="You own " + newSpace.getName() + " The AI owes you $" + String.valueOf(newSpace.getValue());
			information4="";
			this.setMoney(this.getMoney() - newSpace.getValue());
			player.setMoney(player.getMoney() + newSpace.getValue());
		}
		
	
			
	}
		
}
