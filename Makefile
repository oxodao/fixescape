MODS_DIR := run/mods

AE2_URL := https://cdn.modrinth.com/data/XxWD5pD3/versions/kfyIqgJ6/appliedenergistics2-19.2.17.jar
GUIDEME_URL := https://cdn.modrinth.com/data/Ck4E7v7R/versions/rduAfwb7/guideme-21.1.17.jar
REFINED_STORAGE_URL := https://cdn.modrinth.com/data/KDvYkUg3/versions/lHHiI26k/refinedstorage-neoforge-2.0.9.jar

.PHONY: setup-mods

## Download the optional mods used by the 1.21.1 development client.
setup-mods:
	mkdir -p $(MODS_DIR)
	rm -f $(MODS_DIR)/*.jar
	curl --fail --location --output $(MODS_DIR)/appliedenergistics2.jar $(AE2_URL)
	curl --fail --location --output $(MODS_DIR)/guideme.jar $(GUIDEME_URL)
	curl --fail --location --output $(MODS_DIR)/refinedstorage.jar $(REFINED_STORAGE_URL)
