- Fix trees not converting to normal sub-levels when built on
- Fix crash with blocks in the `treephysics:falls_from_trees` tag that can have no mass
- Remove the `treephysics:extra_leaves` tag
- Change rootless tree detection to also allow rooted dirt to work if found
- Add `treephysics:leaves` and `treephysics:logs` for defining blocks that should be treated as leaves/logs by the mod
- Add a system for defining types of leaves that should be considered the same. This should (mostly) solve a problem with trees that have multiple kinds of leaves 
### Leaf Grouping
In a data pack, create a file: `data/<namespace>/treephysics/leaf_grouping.json`
```json5
{
  "groups": {
    // A group can be defined with a name and a list of blocks it contains
    // The name can be anything, but generally should contain a namespace to avoid unwanted overlap
    "example:glungus": [
      "example:glungus_leaves",
      "example:flowering_glungus_leaves"
    ],
    
    // Blocks can be added to existing groups by using the same name
    "minecraft:oak": [
      "example:apple_oak_leaves"
    ]
  }
}
```
**Groups already exist for these mods:**
- No Man's Land
- Biomes O' Plenty
- Oh The Biomes We've Gone
- Environmental
- Atmospheric
- Bountiful Fares
- Burnt