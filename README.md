# FixEscape

Since Minecraft 1.19, Mojang has broken the way the game handles keybind, and it doesn't look like they are going to fix it.

Instead of using the emitted key, they use the physical key pressed. People that rebind keys are left alone on this.

For most keybind it's ok, you can remap them in-game. But you can't remap escape.

This mod adds a second escape key that is re-bindable on any key you want. So you can finally play the game again even if you're using `caps:swapescape`.

## Versions

FixEscape targets NeoForge.

This branch targets Minecraft 26.2 and remains compatible with Minecraft 26.1.2.

## Development compatibility setup

Install the optional integration mods in the development instance with:

```
make setup-mods
```

This clears `run/mods` first, then installs every available integration mod.
At present, only EMI has a NeoForge build for 26.2; it is supplied by the
[link-fgfgui fork](https://github.com/link-fgfgui/emi). AE2, GuideME and
Refined Storage will be restored here when they publish compatible builds.

## Credits

This mod is inspired by [TheEnderCore's EscapeScreen](https://github.com/theendercore/EscapeScreen) for Fabric, but since these days I'm playing with Forge, I needed a forge version.


## License

Copyright © 2023 Oxodao
This work is free. You can redistribute it and/or modify it under the
terms of the Do What The Fuck You Want To Public License, Version 2,
as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
