.PHONY: setup-mods

setup-mods:
	rm -f run/mods/*.jar
	mkdir -p run/mods
	curl -fL https://cdn.modrinth.com/data/XxWD5pD3/versions/AyF0Qu5L/appliedenergistics2-26.1.11-beta.jar -o run/mods/ae2.jar
	curl -fL https://cdn.modrinth.com/data/Ck4E7v7R/versions/wiNQW8T5/guideme-26.1.11-beta.jar -o run/mods/guideme.jar
	curl -fL https://cdn.modrinth.com/data/KDvYkUg3/versions/P12kjgPK/refinedstorage-neoforge-3.2.1.jar -o run/mods/refinedstorage.jar
	# EMI comes from the maintained link-fgfgui fork for Minecraft 26.1.2.
	curl -fL https://github.com/link-fgfgui/emi/releases/download/60/emi-1.1.24-63e8aef%2B26.1.2%2Bneoforge.jar -o run/mods/emi.jar
