# CPSC-233-Monopoly
We will be creating a modified version of the popular board game Monopoly for our group
project this semester. Our application will have a modified game board with fewer
spaces/properties than the original board game, and an odd number (5) of property sets to
purchase.

The rules of our application will be similar to the standard rules of monopoly, limited to 2
players (player vs player, or player vs computer) where each player chooses a token to
represent them on the game board, rolls a die and move clockwise around the board according
to the number on the die. There are a variety of spaces a player can land on; vacant property
that they can choose to purchase, purchased property for which they will pay rent to the
owner, a “go to jail” space that sends the player to the “jail” space until they can either roll a 6,
or pay a fee to be set free, and finally “pick a card” spaces, where the player picks up a card
that they either keep in their hand for later use, or use immediately (directions for uses of cards
will be available when the card is picked up).

There are a few ways our version of Monopoly can end; a player goes bankrupt (they have no
money and no properties) and their opponent wins by default, a player owns the majority of
property sets on the game board (if there are 5 property sets, a player would need to own 3
complete sets to win), or the players reach a predetermined round count which can be set at
the start of gameplay, and the player with the most money (cash + property values) wins. In the
case that no player owns a majority of property sets in the game and there are no properties
left for sale, the game will end and the player with the most combined money (cash + property
value) will be declared the winner.

Each player will start with $1500, which will decrease by purchasing property, by paying rent
when landing on an opponent’s property spaces, by paying to be released from jail, and when
cards dictate that they pay taxes to the bank or to their opponent. Players can earn money by
selling a property to the bank (usually to avoid bankruptcy), by collecting rent from their
properties, by passing the “Go” space on the game board, and through cards when they’re
picked up.

We will include a dialogue box/section to the GUI to indicate whose turn it is and details about
each player on their turn, such as the amount of money they have, the value of their properties,
the number of properties they own and the cards in their hand. This is also where the player
will be prompted to the roll a die on their turn, buy property when they land on a vacant space
or sell property they already own.
