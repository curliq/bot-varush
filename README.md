# Bot Varush

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/db01059ea82a4c7d92d4ae7c25438fef)](https://app.codacy.com/app/joaosardinha9898/bot-varush?utm_source=github.com&utm_medium=referral&utm_content=joaosardinha/bot-varush&utm_campaign=badger)

A Discord bot to display Battlerite player data and other cool things. Everyone is welcome to integrate it on their servers, or if you don't want to host it just ask me for the discord integration link!

Stats embbed message:

![stats message](https://i.imgur.com/5J9Y0JR.png)

Dynamic Streaming role:

![Streaming role](https://i.imgur.com/fSWJ34x.png)


# Features
- [x] `!br welp` to show all the commands
- [x] solo league stats
- [x] 2v2 and 3v3 teams stats
- [x] Dynamic "Streaming" role for who's currently streaming Battlerite
- [ ] 3 best champions on (on `!br stats playername`)
- [ ] player's all champions data
- [ ] randomizer for inhouses - select 2/4/6 random champions, with random rites, and 1 random skill
- [ ] get live match data
- [ ] player's match history

To suggest a feature contact me on Discord (@Curlicue#0002) or create an Issue here on Github.

# Setup

### Your private keys
Create file src/main/java/app/utils/Secrets.java, here's a template:
```
package app.utils;

public class Secrets {

    public final static String BOT_TOKEN = "your discord app's secret token";
    public final static String BATTLERITE_TOKEN = "your battlerite api key";

    // Database
    public final static String DATABASE_HOST = "your database's host";
    public final static String DATABASE_PORT = "db's port";
    public final static String DATABASE_NAME = "db's name";
    public final static String DATABASE_USER = "db's user";
    public final static String DATABASE_PASSWORD = "db's user password";

}
```
### Streaming role (skip if you don't want the dynamic streaming role feature)
1. Create a role in your discord server and drag it below the bot's role so that the bot can control it
2. Change GenericUtils.STREAMER_ROLE_NAME to the name of your "Streaming" role on your server

### Database (skip if you don't want need to show the points difference from last game on !br stats playername)
1. Create a postgres database
2. Fill the fields in `Secrets.java` accordingly

# Run
1. Install java (version 8 specifically).
2. Pull the repository.
3. Add the bot to your server with the OAuth2 URL Generator in discordapp.com/developers

###### Run locally
1. `gradle run`

###### Build a `.jar` to deploy your bot somewhere
1. `gradle jar -Dorg.gradle.java.home='/Library/Java/JavaVirtualMachines/jdk1.8.0_162.jdk/Contents/Home/'` path being your java home directory
2. Find your `.jar` at `bot-varush/build/libs/bot-varush.jar`
	

# Contribute
This is the followed git workflow: https://goo.gl/images/Su7k5k
1. Create branch from `develop`
2. If you do weird stuff make sure to comment it
3. PR to `develop`

###### Contributors
--

###### Mention worthy
- @Bohnenkrautsaft#4768 - Help with Battlerite's API integration and deployment.
- @Moxian#8121 - Idea for dynamic Streaming role.
