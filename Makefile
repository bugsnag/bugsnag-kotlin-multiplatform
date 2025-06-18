.PHONY: install test-fixture check bump

check:
	@./gradlew --continue license detekt lint ktlintCheck test
	@cd features/fixtures/mazeracer && ./gradlew ktlintCheck detekt

install:
	@./gradlew -PVERSION_NAME=9.9.9 clean publishToMavenLocal

test-fixture: install
	@./gradlew -p=features/fixtures/mazerunner assemble assembleRelease -x check
	@cd features/fixtures/mazerunner/app/build/dist/js/productionExecutable/ && zip -r ../../../../../../../../build/web.zip *
	@./features/scripts/export_ios_app.sh Release
	@cp features/fixtures/mazerunner/app/build/outputs/apk/release/app-release.apk build/test-fixture.apk
	@cp features/fixtures/mazerunner/iosApp/output/Mazerunner_Release.ipa build/test-fixture.ipa

bump:
ifneq ($(VERSION),)
	@echo "Bumping version to $(VERSION)"
	@./scripts/bump-version.sh $(VERSION)
else
	@echo "Please provide a version number"
	@./scripts/bump-version.sh
endif
