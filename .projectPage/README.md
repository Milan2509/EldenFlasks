# Elden Flasks (RPG Additions)
![banner image](https://raw.githubusercontent.com/Milan2509/EldenFlasks/1.20.1/.projectPage/images/banner.png)
[![discord_link_badge](https://img.shields.io/badge/Join_the_Discord-%235865F2?style=for-the-badge&logo=discord&logoColor=white&link=https%3A%2F%2Fdiscord.gg%2FX6TAsTz8NQ)](https://discord.gg/X6TAsTz8NQ)
[![Static Badge](https://img.shields.io/badge/Forge_Via_Sinytra_Connector-orange?style=for-the-badge&logo=modrinth&logoColor=white)](https://modrinth.com/mod/connector)

[![Static Badge](https://img.shields.io/badge/Report_Issues-red?style=for-the-badge&logo=github&logoColor=%23181717)](https://github.com/Milan2509/EldenFlasks/issues)
[![Static Badge](https://img.shields.io/badge/Source_Code-%23181717?style=for-the-badge&logo=github&logoColor=white&link=https%3A%2F%2Fgithub.com%2FMilan2509%2FEldenFlasks)](https://github.com/Milan2509/EldenFlasks)
# Installation
**Requires**
- [oωo (owo-lib)](https://modrinth.com/mod/owo-lib)
- [Fabric API](https://modrinth.com/mod/fabric-api)
# Features ✨
### Healing Flask
The healing flask is used to quickly heal yourself in the midst of battle. It has various enhanceable stats.

**Charges**
- The amount of uses the flask has before it needs to be recharged
- Can be recharged at campfires

**Healing**
- The amount of healing done when the flask is used

**Drink Time**
- How long it takes for the flask to be consumed

![image of lask with tooltip](https://raw.githubusercontent.com/Milan2509/EldenFlasks/1.20.1/.projectPage/images/healthFlaskTooltip.png)
### Enhancer Items
These items can be applied in the Flask Mixer to the Healing Flask. These can be found in various loot chests.

- Goblet of Sacred Tears (Charges Enhancer)
    - Enhances the maximum charges the flask has
    - Dropped by bosses and found in end-game loot chests
- Ancient Rune (Healing Enhancer)
    - Increases the amount of healing done
- Pearl of a Thousand Souls (Drink Time Enhancer)
    - Lowers the amount of time required to drink the flask


![Image of all the enhancer items](https://raw.githubusercontent.com/Milan2509/EldenFlasks/1.20.1/.projectPage/images/enhancerItemsAllTooltips.png)
### Flask Mixer
The flask mixer is used to enhance your Healing Flask.
![Replace this with a description](https://raw.githubusercontent.com/Milan2509/EldenFlasks/1.20.1/.projectPage/images/mixer.png)

# Configuration ⚙️
Nearly everything is configurable. Custom loot table injections can be added for all enhancer items and the Healing Flask is fully configurable, from base stats to maximum stats. The enhancer items' strength is also configurable.

# Roadmap 🏗️
- Mixing Flask (Coming Soon!)
- General improvements, this is my first mod so i'm still learning things.

# Technical Info 🔧
Custom enhancer items can be created by extending ```ChargeEnhancerItem```, ```HealingEnhancerItem``` or ```DrinkEnhancerItem```. The modifier amount can also be passed.