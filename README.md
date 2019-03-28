Table of Contents 

Introduction
Features
Installation
Game Instructions


Introduction
Welcome to Mono-Poly!
This is a condensed version of the popular board game, with a slightly smaller board with less spaces.
You can play PvP against another human player only.


Installation

Compile all java files in the zip file using javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar *.java 
Run program by running only java Gui
Run text based bersion but running java GameMain


Game Instructions

Player 1 will always roll first.
Press the "Roll Die" button in the middle of the screen to begin play.
If you land on an unowned property you will be prompted to purchase it.
If you would like to purchase press “Yes", if you would not like to purchase press “No”.
After your decision is made and you have made your selection, please hit the Next Turn button to change play to the next
player.
Play will continue until all spaces on the board have been purchased, or until all but one player has been eliminated.
Players are eliminated when they run out of both money and properties. When a player runs out of money, if they own 
property they will be prompted to sell enough properties to bring their balance to at least $0.

There are more than just property spaces available, there are also chance spaces, where you randomly pay money to the bank
or are paid money from the bank, aswell as tax spaces, which require you to pay $100 to the bank, and the "go to jail" 
space. When you land on these spaces, or a space that is owned by your opponent, no action is required by the user. Money will automatically be deposited or withdrawn from your total, and your avatar will automatically move for you.
