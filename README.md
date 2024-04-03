# Space Invaders - Demonstrating Object Oriented Principles

[cover](/cover.png)

This uses a simulated game printed out to the terminal. To compile, change the directory to the folder that contains src and run:

`javac -d . src/main/java/*.java src/main/java/controller/*.java src/main/java/model/*.java src/main/java/utility/*.java src/main/java/factory/*.java`

To run use:

`java main.java.Main`

## Game Design

I have demonstrated the use of the following OO techniques & patterns
in the following way:

1. Factory Pattern:
   Implemented the GameObjectFactory class to create various game objects such as Invader, Player, and Bullet. This pattern allows for flexible and centralized object creation.

2. Chain of Responsibility Pattern:
   Utilized the CollisionHandler class hierarchy to implement collision handling. Each CollisionHandler subclass in the chain decides whether to handle a collision or pass it to the next handler. This pattern promotes decoupling and extensibility in collision resolution.

3. Singleton Pattern:
   Employed the Singleton pattern for two critical components:
   GameController: Ensured that there is only one instance of the GameController class responsible for managing the game's flow and components.
   SoundManager: Ensured that there is only one instance of the SoundManager class responsible for managing game sounds.

4. Open-Closed Principle:
   Strived to adhere to the Open-Closed Principle by designing classes to be open for extension but closed for modification. For instance, new collision handlers can be added without altering existing code in the CollisionHandler hierarchy, promoting code maintainability.

5. Single Responsibility Principle:
   Aimed to uphold the Single Responsibility Principle by ensuring that each class in the project has a clear and specific responsibility. For example, the Player class primarily handles player-related functionality, the BulletInvaderCollisionHandler class focuses on handling collisions between bullets and invaders, and the SoundManager class exclusively manages game sounds.

## Classes - Responsibilities and Data

**Main.java**

Responsibilities:

- Launch the Space Invaders game by initializing the game controller and starting the game.
- Provide the entry point for the game's execution.

Data:

- None (The Main class primarily serves as an entry point and does not contain any data).

**controller/GameController.java (singleton)**

Responsibilities:

- Manage the overall flow of the game.
- Initialize game components, including players, invaders, and bullets.
- Simulate player and invader actions during the game.
- Handle game over conditions.
- Control game level progression.
- Handle collisions between game objects.
- Keep track of the player's score.
- Play game-related sounds.

Data:

- Game state (running, paused, game over).
- List of invaders.
- List of bullets.
- Player information, including position and score.
- GameObjectFactory instance for creating game objects.
- CollisionHandler instance for handling collisions.
- SoundManager instance for managing game sounds.

**factory/GameObjectFactory.java (factory)**

Responsibilities:

- Create different types of game objects based on the specified type (invaders, player, bullets).
- Initialize and configure the created game objects with appropriate initial positions and attributes.

Data:

- None (The GameObjectFactory class primarily serves as a factory for creating game objects and does not contain specific data).

**model/Bullet.java**

Responsibilities:

- Represent a bullet in the game.
- Implement bullet movement logic.

Data:

- Inherits properties (position, size, health) from the parent class GameObject.

**model/GameObject.java**

Responsibilities:

- Represent a generic game object in the game.

Data:

- Position (x, y) to determine the object's location on the game canvas.
- Size (width, height) to define the object's dimensions.
- Health (if applicable) to track the object's state and potential damage.

**model/Invader.java**

Responsibilities:

- Represent an invader character within the game.
- Handle the movement of the invader.

Data:

- Inherits properties (position, size, health) from the parent class GameObject.
- The specific type of the invader, which may influence its behaviour (though not explicitly implemented in the provided code).

**model/Player.java**

Responsibilities:

- Represent the player's spaceship within the game.
- Handle player input, including movement and shooting.

Data:

- Inherits properties (position, size, health) from the parent class GameObject.
- Player's score, which tracks the player's in-game achievements.

**utility/BulletInvaderCollisionHandler.java**

Responsibilities:

- Handle collisions specifically between bullets and invaders within the game.

Data:

- Inherits the core collision handling structure from the CollisionHandler class, providing extensibility for handling various types of collisions.

**utility/CollisionHandler.java (Chain of responsibility)**

Responsibilities:

- Handle collisions between different game objects.
- Implement the Chain of Responsibility pattern, where a request is passed through a chain of handlers, each deciding whether to process the request or pass it to the next handler. This design pattern decouples the sender of a request from its receiver.

Data:

- Reference to the next collision handler in the chain, allowing for sequential processing of collision handling.

**utility/PlayerInvaderCollisionHandler.java**

Responsibilities:

- Handle collisions specifically between the player and an invader within the game.
- Implement collision resolution logic for the interaction between the player and an invader.
  Optionally, perform additional collision handling logic as needed for this specific collision type.

Data:

- Inherits the core collision handling structure from the CollisionHandler class, providing extensibility for handling various types of collisions.
- Utilizes the object1 and object2 parameters to identify and interact with the player and invader involved in the collision.
- Can access specific methods and properties of the player and invader objects for more advanced collision handling, although this is optional and depends on the specific game requirements.

**utility/SoundManager.java**

Responsibilities:

- Manage game sounds, including playing sound files.
- Implement the Singleton pattern to ensure a single instance of the SoundManager class.

Data:

- None (the SoundManager class is a stateless utility class and does not maintain specific data).

## Rules of Simulation

To help demonstrate the object oriented principles, I have
"simulated" the game with random inputs (using random numbers) and
using a time delay on the printing to the terminal.
Here are the rules I have come up with for simplicity and demonstration purposes:

- The playing canvas is 20 x 20 coordinates.
- There are only 5 levels.
- The player is spawned at the bottom middle of the canvas.
- The invaders are spawned at a rate of 1 x Level Number.
- The invaders are spawned randomly along the x axis to the top of the canvas.
- The player, invader and bullet are all 1 coordinate in area.
- The invaders only move downward and when they exit the canvas they are removed.
- The player can only move left or right along the x axis.
- Bullets can only move upward and maintain the same y axis as the position when they were fired (the coordinates of the player).
- Every time a bullet hits an invader, it is destroyed and the player gets 1 point.
- A player does not have to kill every invader to win the game, instead all he has to do is dodge them.
