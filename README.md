# Magic belot Rules

In addition of original [Belot Rules](https://en.wikipedia.org/wiki/Belote) each belotPlayer have two Magic cards.
A belotPlayer could use only one of them per dealing. After a game ends ( one of the teams make 151 or more points )
the magic cards are restored to 2 - they don't stack for another game.

## Magic Card 1 - Blizzard Storm

After your play Blizzard Storm Magic card, nobody can announce any further. You play this magic card **_AFTER_** your own announce.
Your announce could be contract(Clubs ♣ Diamonds ♦ Hearts ♥ Spades ♠ "No trumps" "All trumps") or bid (Pass, Double, Re-double).

## Magic Card 2 - Destiny Creator

After your play Destiny Creator Magic card, you draw 6th card. You play this magic card **_BEFORE_** your own announce.

##For contributors/developers
##Tech Stack
1. Front End
    1. [VanillaJS](http://vanilla-js.com/)  As you can see its faster, less prone to errors, and easier for beginners
    2. [SockJS](https://github.com/sockjs/sockjs-client) for legacy web socket support
    3. [StompJS](https://github.com/stomp-js/stompjs) as websocket messaging protocol
    4. [JQuery](https://jquery.com/) AJAX requests and some selectors
    5. [MDBootstrap](https://mdbootstrap.com/) UI tries to be as much material as it can
2. Back end
    1. **Spring**
        1. Spring boot
        2. Spring MVC
        3. Spring security
        4. Spring data
        5. Spring web sockets
    2. **Template engine** : Thymeleaf  
    3. **Database** : PostgreSQL
    4. **Data modeling** : Hibernate        
For the sake of consistency i use these project-level naming conventions: <br>
## Front end:
   *  **object** -> Any global object, IIFE or ES6 class (*Note:* this refers only to global objects, not one 'living'
   inside functions) <br>
   *  **script** -> a compilation of functions with similar
   logic (cohesion)
   *  **function** -> any function regardless of context and visibility  
## Back end: 
   * Backend's naming are not ambiguous so no need for project-level naming convention
## **Naming conventions** :
**_NOTE_: These are under negotiation from the developers team, we MUST decide this together, i've only made this as an example**
**_NOTE_: I try to use google naming convention (since im google fanboy)** 
* [HTML&CSS](https://google.github.io/styleguide/htmlcssguide.html#ID_and_Class_Name_Delimiters)
* [JavaScript](https://google.github.io/styleguide/jsguide.html)
1. Front end 
    * **objects**: PascalCase 
    * **functions**: camelCase
    * **variables**: camelCase
    * **constants**: JAVA_LIKE_SYNTAX (_NOTE_: not all fields defined as const keyword are constants, if you cant decide about naming a 
 variable THIS_WAY or thisWay, think if it was java, would it be static final or only final)
    * **javascript/html/css files**: all-lowercase-divided-by-hyphen (Google allows underscores too, we should discuss this)
    * **html id**: camelCase (_NOTE_: this is contrary to google's convention but when writing id selectors in javascript,
  makes it a bit more cleaner IMHO) subject to a change (needs discussion)
    * **html class**: all-lowercase-divided-by-hyphen
    * **html data-attribute**: all-lowercase-divided-by-hyphen
2. Back end
    * **Same as in the company**
    <br>
## **Code styling conventions**
1. Front end
    * Braces: [google's convention](https://google.github.io/styleguide/jsguide.html#formatting-braces-all)
        * Always use braces
        * [Egyptian brackets](https://blog.codinghorror.com/new-programming-jargon/)       
2. Back end
    * **Same as in the company for ease**
##**TODO's** : 
Most of the todo's are in the code too
1. Front end
    * **Naming conventions**:
        1. all objects must be named the same (they are currently not)
        2. all file names must be named the same (some are named PascalCase)
 <br>**_NOTE_: <br> in javascript file name does not need to be the same as the class or IIFE <br> 
 so its not a problem if a file named web-socket.js contains class WebSocket**
        3. all html classes and id's should be named the same
    * **Change user profile info page**: for changing/adding user info (profile pic, address, first name etc etc)
    * **Belot UI**: 
        1. Initial belot menu with Search ranked, create private, join private games
        2. Render players in appropriate places and order in the screen
        3. Render cards by given cardValue and cardSuit
        4. Render available announces by given announceList
        5. Etc etc
2. Back end
    * **DTOs**: All entities must have their DTO counterpart which the service to work with
     for now the more needed ones are:
        * **UserDTO** (no need to expose the password, the front end needs only the id and username of the user)
        * **BelotDTO** (or BelotViewModel) will be used for rendering the game state in the browser
    * **UserProfile**: entity holding user profile settings like profile pic, address, first and last names,
     and other stuff the user can change
    * **GameServices**: subclasses of AbstractGameService class, for every client related message type
        1. **AnnounceService**
        2. **CardPlayedService**
        3. **MagicCardPlayedService**
        4. **etc**
    * **Change Spring's in memory message broker with full pledged one (RabbitMQ, ActiveMQ or Kafka)**      
    * **Unit tests and integration tests**
                 
