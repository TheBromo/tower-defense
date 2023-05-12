# Team05-java.lang.NullPointerException-projekt2-tower-defense
## 🏰 🥷 🏰 Short Introduction to the Game Tower Defense
Tower Defense is a captivating game that challenges players to strategically place towers to defend their base from waves of relentless enemies. This game is still an MVP. However, with further development, and with a variety of towers, enemies, and maps, players will find themselves immersed in a world of strategic decision-making and excitement. 

## 📋 Table of contents
- [Getting Started](#-getting-started)
- [Key Features](#-key-features)
- [Technical Details](#-technical-details)
  - [Technologies used in the Project](#-technologies-used-in-the-project)
  - [Class-Diagram](#-class-diagram)
  - [Architecture & MVC](#-architecture--mvc)
  - [Branching Modell](#-branching-modell)
  - [Pull Requests](#-pull-requests)
- [Features to come (Post-MVP)](#-features-to-come-post-mvp)
- [Credits & Contact](#-credits--contact)

## 🚀 Getting started
1. Clone the repository
2. Open the project in your desired IDE (IntteliJ, Eclipse or else)
3. run `gradle build`
4. run `gradle run`
4. click start button in separate window
5. play and have fun

## 🔑 Key Features
1. User-friendly Interface: Navigate the game with ease through an intuitive and visually appealing interface.
2. Purchase Towers: Buy towers and place them on strategically optimal spots. 
3. Upgrade Tower: Upgrade existing towers to more powerful ones.
4. Buy Health: Buy health function to invest your earned money into your health recovery.
5. Pause & Attack Phases: Pause phase without enemy attacking, and different attack phases with increasing level of difficulties.

## 🔎 Technical Details 

### 🧪 Technologies used in the Project

- Java SDK 17
- Gradle Version 8.0.1
- Clean Code Convention

### 🧮 Class-Diagram
![exported_from_idea drawio-6](https://github.zhaw.ch/storage/user/4894/files/381468e5-5e27-4f28-82dd-e6f731f5b3ee)

### 🏗 Architecture & MVC

#### MVC Game 

```
 UI Thread                                                       GameThread


┌────────────────────┐
│                    │
│ GameView.fxml      │
│                    │
└────────────────────┘
          ▲
          │ Displays
          │
┌─────────┴──────────┐          ┌─────────────────────┐         ┌─────────────────────┐
│                    │          │                     │         │                     │
│ GameViewController ├────┬────►│ GameState           │◄───┬────┤   Game              │
│                    │    │     │                     │    │    │                     │
└────────────────────┘    │     └─────────────────────┘    │    └─────────────────────┘
                          │                                │
                      Observes                          Updates
```
The MVC model communicates with the Observer Pattern. 

- Game State hold all the properties that are needed to display in the UI
- The Game class is the Game Thread it simulates the game rules and physics and updates the Gamestate accordingly
- GameViewController displays the given Information of the GameState accordingly.

> When Rendering on the canvas the UI Thread is awaited because otherwise to many requests would clog up the UI Thread with the `Platform.runLater(...)` function.

#### Game Thread

The Game Thread is structured like the following:

```java
double previous = getCurrentTime();
double lag = 0.0;
while (true)
{
  double current = getCurrentTime();
  double elapsed = current - previous;
  previous = current;
  lag += elapsed;

  processInput();

  while (lag >= MS_PER_UPDATE)
  {
    update();
    lag -= MS_PER_UPDATE;
  }

  render();
}

```

1. The `update()` method is only called  when the given Time per Update (100ms) is elapsed.
2. The `render()` method is always called and renders as many frames as possible

> In our case the `render()` method sets the GameState to trigger a render and awaits for the render to finish.

This was implemented in the following PR [#48](https://github.zhaw.ch/PM2-IT22tbWIN-scmy-bles-krea/Team05-java.lang.NullPointerException-projekt2-tower-defense/pull/48)

#### Sprite System

> **Sprite** means: In computer graphics, a sprite is a two-dimensional bitmap that is part of a larger scene (e.g., a 2D video game). Sprites can be static images or 
> animated graphics. [source](https://www.educative.io/answers/definition-sprite) 

The Sprite System loads images of for the given `SpritePath` enum. Here the number of variant of the same image and amount of images (more than 2 is animated). 

It has its own timer which changes the current image to display in a given interval.

> Through this system animations are enabled

This is how the code works:
```java
public class Sprite {
    private List<Image> sprites;
    private int intervalMs = 300;
    private int index = 0;
    private long lastUpdate = 0;

    public Sprite(SpritePath sprite) {
    ...
    intervalMs = randomUtil.getRandomInRange(sprite.minAnimationTimer, sprite.maxAnimationTimer); // not all animations play at the same speed
    lastUpdate = randomUtil.getRandomInRange(sprite.minAnimationTimer, sprite.maxAnimationTimer); // they dont start synchronized
    ...
    }
    
    public Image getSprite() {
        if (System.currentTimeMillis() - lastUpdate >= intervalMs) {
            continueIndex();
            lastUpdate = System.currentTimeMillis();
        }
        return sprites.get(index);
    }
    ...
```

`getSprite()` returns the Image which should be displayed of the whole set an is incremented if the interval was passed.

**Example sprite of enemy walking**

![Sprite](https://github.zhaw.ch/storage/user/5886/files/c626a93f-6a99-4e08-bf5e-6ddf08d4db29)

#### Rendering System

The rendering is called each `game.loop()`. As the Rendering has to be done in the UI Thread the GameThread( `game.loop()`) is waiting. This is so the rendering does not spam the UI Thread until it dies and the calling of the render function doesn't have to be locked to a fixed number. 

```
                                                                                          ┌──────────────────┐
                                                                                          │                  │
                                                                                          │    Game.loop     │
                                              ┌────────────────────┐                      │                  │
                          Render in UI Thread │                    │                      │  update          │
┌────────────────────┐                        │  Game State        │    Set Val and Waits │                  │
│                    │                        │                    │                      │                  │
│    UI Thread       ├────────────────────────┤ RenderNeeded:Bool  │ ◄────────────────────┼──setRender(True) │
│                    │                        │                    │                      │                  │
│                    │                        └────────────────────┘                      │                  │
│                    │                                                                    │                  │
│                    ├────────────────────────────────────────────────────────────────────┼───► continues..  │
└────────────────────┘                         On finish                                  │                  │
                                                                                          │                  │
                                                                                          │                  │
                                                                                          └──────────────────┘
```


The following classes implement the `Rendreable` interface: 

![exported_from_idea drawio](https://github.zhaw.ch/storage/user/5886/files/a93f6ef8-8e4d-4ba5-9b05-fad69868de7e)

The `Rendreable` interface enforces the `render(Canvas canvas)` method on it's implementers. Here an Example:

```java
    @Override
    public void render(Canvas canvas) {
        var g2d = canvas.getGraphicsContext2D();
        g2d.drawImage(sprite.getSprite(), position.getX() - width / 2, position.getY() - height / 2,
                width, height);
        g2d.setFill(Color.LIGHTGREEN);
        g2d.fillRect(position.getX() - width / 2, position.getY() - height / 2-10,
                (((double) width / 100) * health), 2);
    }
```

Here the sprite is drawn with the position of the enemy being in the middle of the Image (hence the  `- width / 2` and `- height / 2`). 
The health of the enemy is also represented by a green Rectangle drawn with it's width being relative to the enemies health. 

The top element of the render chain is the Game class which starts the rendering. Here is the order and hierarchy of the rendering process. 

![exported_from_idea drawio (1)](https://github.zhaw.ch/storage/user/5886/files/59ae9acc-cdc8-4faa-803d-75f7e0fa848e)



#### Update System

#### Enemy Spawning

#### Enemy Crowd Behaviour

#### Phase System


#### Attack Patterns

### 🌳 Branching Modell 

A feature branch was generated for each task. If the feature was completed, a pull request was created.   This had to be reviewed and approved by another team member so that the code from the feature branch could be merged into the main branch.

![Branching](https://github.zhaw.ch/storage/user/4894/files/4e1b24bb-0a45-4792-999c-f864144bfa3f)

### 👨‍🔧 Pull Requests
Whenever there was a change, it was consequently merged via a pull request. Suggestions for improvements were either commented within the pull request or discussed in person. 

Example: Pull-Request 

- [#25](https://github.zhaw.ch/PM2-IT22tbWIN-scmy-bles-krea/Team05-java.lang.NullPointerException-projekt2-tower-defense/pull/25)
- [#30](https://github.zhaw.ch/PM2-IT22tbWIN-scmy-bles-krea/Team05-java.lang.NullPointerException-projekt2-tower-defense/pull/30)
- [#47](https://github.zhaw.ch/PM2-IT22tbWIN-scmy-bles-krea/Team05-java.lang.NullPointerException-projekt2-tower-defense/pull/47)
- [#28](https://github.zhaw.ch/PM2-IT22tbWIN-scmy-bles-krea/Team05-java.lang.NullPointerException-projekt2-tower-defense/pull/28)
- [#48](https://github.zhaw.ch/PM2-IT22tbWIN-scmy-bles-krea/Team05-java.lang.NullPointerException-projekt2-tower-defense/pull/48)

Not every pull-requests have discussion/improvement points in it, as if the reviewer agreed with the proposed changes, he immediately approved it. 

## 🔮 Features to come (Post-MVP)
1. Challenging Gameplay: Engage in an immersive tower defense with increasingly challenging levels, pushing your strategic thinking to the limit.
2. Multiple Tower Types: Choose from a wide variety of towers, each with unique abilities, upgrades, and attributes.
3. Diverse Enemy Types: Face off against an array of different enemies with unique abilities and strategies, keeping you on your toes.
4. Customizable Maps: Play on various maps with distinct terrain, obstacles, and paths, providing new challenges and opportunities.
5. Achievements and Leaderboards: Unlock achievements as you progress and compare your scores with players from around the world. 

## 🙌📫 Credits & Contact

Tower Defense is developed by Team 5 java.lang.NullPointerException. 

If you have any questions, suggestions or concerns, please feel free to contact us:
- Pascal Küng (kuengpas@students.zhaw.ch)
- Manuel Strenge (strenman@students.zhaw.ch)
- Micha Mettler (mettlmi1@students.zhaw.ch)
- Dominik Hartmann (hartmdo1@students.zhaw.ch)
