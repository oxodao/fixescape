# FixEscape

Since Minecraft 1.19, Mojang has broken the way the game handles keybind, and it doesn't look like they are going to fix it.

Instead of using the emitted key, they use the physical key pressed. People that rebind keys are left alone on this.

For most keybind it's ok, you can remap them in-game. But you can't remap escape.

This mod adds a second escape key that is re-bindable on any key you want. So you can finally play the game again even if you're using `caps:swapescape`.

## Versions

NOTE: Starting 1.21 this mod targets NeoForge. I'm dropping Forge support as most mod seems to do the same.

This branch produces a jar compatible with Minecraft 1.21.1.

## Development compatibility setup

The development client can be started with AE2, Refined Storage, and EMI to
manually test the alternate escape key. After cloning the repository, download
the runtime-only mods once:

```sh
make setup-mods
```

This downloads AE2 19.2.17, its required GuideME dependency, and Refined
Storage 2.0.9 into `run/mods`. The directory is ignored by Git, so none of
these JARs are packaged or published with FixEscape. EMI 1.1.24+1.21.1 is
resolved by Gradle as a `localRuntime` dependency and is also not published.

Then launch the client with:

```sh
./gradlew runClient
```


## Credits

This mod is inspired by [TheEnderCore's EscapeScreen](https://github.com/theendercore/EscapeScreen) for Fabric, but since these days I'm playing with Forge, I needed a forge version.


## License

Copyright © 2023 Oxodao
This work is free. You can redistribute it and/or modify it under the
terms of the Do What The Fuck You Want To Public License, Version 2,
as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
