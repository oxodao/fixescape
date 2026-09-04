.PHONY: setup-mods

setup-mods:
	rm -f run/mods/*.jar
	mkdir -p run/mods
	# AE2, GuideME and Refined Storage have no NeoForge build for Minecraft 26.2 yet.
	# EMI comes from the maintained link-fgfgui fork.
	curl -fL https://github.com/link-fgfgui/emi/releases/download/59/emi-1.1.24-b60b8f8%2B26.2%2Bneoforge.jar -o run/mods/emi.jar
