# Contributing to bugsnag-kotlin-multiplatform

All contributors are welcome! This guide covers everything you need to develop, test, and release `bugsnag-kmp`.

## Prerequisites

Please complete the [environment setup](ENVIRONMENT_SETUP.md) before starting development. In summary you will need:

- Java 17 JDK (installable via [sdkman](https://sdkman.io/))
- [Android Studio](https://developer.android.com/studio) (latest stable) with `$ANDROID_SDK_ROOT` set
- Xcode (for iOS targets)
- A working [git](https://git-scm.com/) installation
- Ruby (required only for E2E tests)

Clone the repository **with submodules**:

```shell
git clone --recurse-submodules https://github.com/bugsnag/bugsnag-kotlin-multiplatform.git
```

If you've already cloned without submodules:

```shell
git submodule update --init --recursive
```

## Project structure

| Path | Description |
|---|---|
| `bugsnag-kmp/` | The main KMP library module |
| `bugsnag-kmp/src/commonMain/` | Shared Kotlin API (expect declarations) |
| `bugsnag-kmp/src/androidMain/` | Android-specific implementations (wraps `bugsnag-android`) |
| `bugsnag-kmp/src/appleMain/` | Apple/iOS-specific implementations (wraps `bugsnag-cocoa` via cinterop) |
| `bugsnag-kmp/src/jsMain/` | Browser JS implementations (wraps `@bugsnag/browser`) |
| `vendor/bugsnag-cocoa/` | Vendored `bugsnag-cocoa` SDK (git submodule) |
| `example/BugsnagExample/` | Compose Multiplatform example app |
| `features/` | Maze Runner E2E test features and fixtures |

## Building the library

Compile a release build:

```shell
./gradlew assembleRelease
```

## Running checks

Run unit tests and all static analysis (detekt, ktlint, lint) in one step:

```shell
make check
```

Or individually:

```shell
# Unit tests only
./gradlew test

# Static analysis
./gradlew detekt ktlintCheck lint
```

## Running E2E tests

E2E tests use [Maze Runner](https://github.com/bugsnag/maze-runner) and are located in `features/`. Targets include Android, iOS, and browser.

### Setup

```shell
bundle install
```

### Build the test fixtures

```shell
make test-fixture
```

This builds all platform fixtures (Android APK, iOS IPA, and browser JS bundle) into the `build/` directory.

## Example app

The Compose Multiplatform example app lives in `example/BugsnagExample/`. Open it as a separate project in Android Studio or build it from the command line.

## Code style

This project enforces code style with:

- **[ktlint](https://github.com/JLLeitschuh/ktlint-gradle)** — Kotlin linting and formatting
- **[detekt](https://detekt.dev/)** — Static analysis for Kotlin

Run `./gradlew ktlintFormat` to auto-fix formatting issues.

## Making a pull request

1. Fork the repository and create a branch from `main`.
2. Make your changes, ensuring new functionality has appropriate test coverage.
3. Run `make check` and verify everything passes.
4. Update `CHANGELOG.md` — add your changes under the `## Unreleased` section.
5. Open a pull request against `main` with a clear description of the change.

## Releasing

Releases are published to [Maven Central](https://central.sonatype.com/) via CI (Buildkite).

### Pre-release checklist

- [ ] Full test suite passes on CI
- [ ] E2E tests pass for all platforms (Android, iOS, browser)
- [ ] All documentation PRs are ready
- [ ] New functionality has been manually tested on a release build
- [ ] The example app sends handled and unhandled errors correctly

### Making the release

1. Create a release branch from `main`: `release/vX.Y.Z`
2. Bump the version:
   ```shell
   make bump
   ```
   This updates `VERSION_NAME` in `gradle.properties` and the `CHANGELOG.md` header.
3. Inspect the updated `CHANGELOG.md` and `gradle.properties` to ensure they are correct.
4. Open a pull request from the release branch to `main`.
5. Once merged, tag the release on [GitHub Releases](https://github.com/bugsnag/bugsnag-kotlin-multiplatform/releases).
6. Trigger the publish step on CI. The `kmp-publisher` Docker service handles signing and upload.
7. Verify the artefacts on [Sonatype Central](https://central.sonatype.com/publishing/deployments) and click **Publish**.

### Post-release checklist

- [ ] Artefacts are available on Maven Central
- [ ] A freshly created app can pull the released version and send an error
- [ ] All documentation PRs have been merged
- [ ] Downstream libraries have been notified/updated if appropriate

### Manual publishing

Manual publishing is possible but discouraged:

```shell
./gradlew assembleRelease publish
```

This requires GPG signing credentials and Sonatype access configured in `~/.gradle/gradle.properties`:

```ini
NEXUS_USERNAME=your-nexus-username
NEXUS_PASSWORD=your-nexus-password
signing.keyId=<8-char-hex-key-id>
signing.password=your-gpg-passphrase
signing.secretKeyRingFile=/Users/<username>/.gnupg/secring.gpg
```

See the [Sonatype GPG guide](https://central.sonatype.org/publish/requirements/gpg/) for key setup instructions.

## Generating documentation

API docs are generated with [Dokka](https://github.com/Kotlin/dokka):

```shell
./gradlew dokkaGeneratePublicationHtml
```

Output is written to `bugsnag-kmp/build/dokka/`.

## Reporting issues

- [Search existing issues](https://github.com/bugsnag/bugsnag-kotlin-multiplatform/issues)
- [Report a bug or request a feature](https://github.com/bugsnag/bugsnag-kotlin-multiplatform/issues/new)
- Security vulnerabilities should be reported to [support@bugsnag.com](mailto:support@bugsnag.com) (see [SECURITY.md](../SECURITY.md))

## License

This project is released under the [MIT License](../LICENSE).
