# Elden Flasks (RPG Additions)
![banner image](https://cdn.modrinth.com/data/cached_images/8cea34f55811950426aca14586922297fa79de7c.png)
# Installation
**Requires**
- [oωo (owo-lib)](https://modrinth.com/mod/owo-lib)
- [Fabric API](https://modrinth.com/mod/fabric-api)

**Forge via [Sinytra Connector](https://modrinth.com/mod/connector)**
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

![image of lask with tooltip](https://cdn.modrinth.com/data/cached_images/19a05811e3d23df3261ea4345cac8f4587aa49d1.png)
### Enhancer Items
These items can be applied in the Flask Mixer to the Healing Flask. These can be found in various loot chests.

- Goblet of Sacred Tears (Charges Enhancer)
    - Enhances the maximum charges the flask has
    - Dropped by bosses and found in end-game loot chests
- Ancient Rune (Healing Enhancer)
    - Increases the amount of healing done
- Pearl of a Thousand Souls (Drink Time Enhancer)
    - Lowers the amount of time required to drink the flask


![Image of all the enhancer items](https://cdn.modrinth.com/data/cached_images/076820551a6197b4d05e8440e81dff3fab813114.png)
### Flask Mixer
The flask mixer is used to enhance your Healing Flask.
![Replace this with a description](https://cdn.modrinth.com/data/cached_images/af30e0027a5ab7ebd96d61c6ab9b8cbbc98b085d.png)

# Configuration ⚙️
Nearly everything is configurable. Custom loot table injections can be added for all enhancer items and the Healing Flask is fully configurable, from base stats to maximum stats. The enhancer items' strength is also configurable.

# Roadmap 🏗️
- 1.21.1 Port
- Mixing Flask
- General improvements, this is my first mod so i'm still learning things.

# Technical Info 🔧
Custom enhancer items can be created by extending ```ChargeEnhancerItem```, ```HealingEnhancerItem``` or ```DrinkEnhancerItem```. The modifier amount can also be passed.