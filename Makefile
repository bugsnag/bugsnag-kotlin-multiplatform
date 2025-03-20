.PHONY: install test-fixture check

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
