# Bot Varush
A Discord bot to display Battlerite player data and other cool things. Everyone is welcome to integrate it on their servers, or if you don't want to host it just ask me for the discord integration link!

# Features
- [x] `!br welp` to show all the commands
- [x] solo league stats
- [x] 2v2 and 3v3 teams stats
- [ ] Dynamic "Streaming" role for who's currently streaming Battlerite
- [ ] 3 best champions on (on `!br stats playername`)
- [ ] player's all champions data
- [ ] randomizer for inhouses - select 2/4/6 random champions, with random rites, and 1 random skill
- [ ] get live match data

To suggest a feature contact me on Discord (@Curlicue#0002) or create an Issue here on Github.

# Run
1. Install java (version 8 specifically).
2. Pull the repository.
3. `gradle run`

##### Setup your private keys
1. Create file src/main/java/app/utils/Auth.java with `BOT_TOKEN`, `BATTLERITE_TOKEN` and `TWITCH_TOKEN`, being your discord's app token, your battlerite dev account token and your twitch app token respectively.
2. For Streaming role - Change Helper.STREAMER_ROLE_NAME to the name of your "Streaming" role on your server


# Contribute
This is the followed method: https://goo.gl/images/Su7k5k
1. Create branch from `develop`
2. If you do weird stuff make sure to comment it
3. PR to `develop`

###### Contributors and worth mentioning
- @Bohnenkrautsaft#4768 - Help with API integration and deployment.
