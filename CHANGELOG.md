# Changelog — জমির হিসাব

All notable changes to the জমির হিসাব project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## [Unreleased]

### Added
- Play compliance doc set: `DESIGN_ASSETS.md`, `PLAY_STORE_METADATA.md`, `PLAY_STORE_DATA_SAFETY.md`, `GOOGLE_PLAY_DECLARATIONS.md`, `EU_TRADE_POLICY_COMPLIANCE.md`, `PRIVACY_POLICY.md`, `TERMS_OF_SERVICE.md`, `CHANGELOG.md`.

---

## [5.0.0] - 2026-11-09

### Added
- **One-Tap Unit Chips:** Fast `FilterChip` selection for input units replaces the dropdown.
- **UMP Consent Gating:** Google User Messaging Platform consent flow gates all ads; declining users never initialize the Mobile Ads SDK.
- **Gradle Wrapper:** Committed `gradlew` + `gradle-wrapper.jar` (Gradle 9.9.0); debug keystore generated for local builds.
- **Strict Lint Gate:** `checkReleaseBuilds = true`, `abortOnError = true`.

### Changed
- **Dependency Upgrades:** AGP 9.5.1, Kotlin 2.4.21, Compose BOM 2026.09.00, play-services-ads 25.5.0, UMP 4.0.0, activity-compose 1.13.0, coroutines 1.11.0, compileSdk 37.
- **Dependency Pruning:** Removed unused Room, Coil, Fragment, Firebase BOM, KSP, google-services, secrets plugin, Robolectric, and instrumented-test libraries.
- **Per-Variant Ad Units:** Debug builds use Google demo units; release builds use production units (`.../8436765853` banner, `.../8246948185` interstitial).
- **Release Signing:** Keystore passwords via environment or gitignored `local.properties` only; fail-fast errors on release builds.
- **Info Sheet Accuracy:** Removed self-contradictory Kora/Kranti square-foot claims; traditional chain documented to stop at Ganda.
- **Katha Precision:** Displayed as ≈1.653 decimals everywhere (was rounded 1.65).
- **Language Toggle:** 48dp touch target with proper button semantics.
- **Footer Version:** Now reads from `BuildConfig.VERSION_NAME` instead of a hardcoded string.
- **Docs Honesty Pass:** README, privacy/data-safety, store listings (EN/BN), and ASO research corrected — offline calculation engine, free with AdMob ads (never "ad-free" / "zero internet").

### Fixed
- **Launcher Icons:** Dropped JPG monochrome tag (rendered as solid blob); moved art to `drawable-nodpi`; renamed timestamped asset to `ic_land_calc_art.jpg`.
- **Dead Resources:** Removed unused strings (stale "Version 1.0.0" / "100% Offline" labels) and template colors.
- **Manifest:** Removed redundant activity label.

### Security
- **Secrets Hygiene:** Untracked `my-upload-key.jks` and `builds/` (APKs, AAB, 22 MB `mapping.txt`) from git; gitignored going forward. Note: the keystore and its historic password remain in git history — treat the key as semi-public and rotate for future apps.

---

## [5.0] - 2026-11-09 (pre-production builds)

### Added
- Google AdMob banner integration (bottom bar) with production ad units.
- Google AdMob interstitial shown after Share tap, 45-second cooldown.
- R8 minification + resource shrinking (release APK ~4 MB).
- Fragments force-resolved to 1.8.6 (later removed as unused).

### Changed
- Bumped `versionCode` to `5` and `versionName` to `5.0`.
