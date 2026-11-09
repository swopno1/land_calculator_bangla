# Design Assets & Visual Identity Specification — জমির হিসাব

**Application:** জমির হিসাব (Land Calculator BD) (`com.vivescriptsolutions.jomirhisab`)  
**Developer:** ViveScript Solutions  
**Copyright:** © 2026 ViveScript Solutions  
**Tagline:** হিসাব চলে অফলাইনেও।  
**Concept:** Bangladesh earth and lush crop green — a surveyor's globe with a measuring ruler, communicating trust in land measurement.

---

## 1. Official Visual Assets

| Asset Name | Dimensions | Format | File Path |
| :--- | :--- | :--- | :--- |
| **Play Store App Icon** | 512 x 512 | PNG32 (with alpha) | `store-assets/icon/play_store_icon_512.png` |
| **Play Store Feature Graphic** | 1024 x 500 | PNG (no alpha) | `store-assets/feature-graphic/feature_graphic_1024x500.png` |
| **Phone Screenshots** | 1080 x 1920 | PNG | `store-assets/screenshots/screenshot_1..6_*.png` |
| **7-inch Tablet Screenshots** | tablet | PNG | `store-assets/screenshots-tablet-7inch/tablet_7in_*.png` |
| **10-inch Tablet Screenshots** | tablet | PNG | `store-assets/screenshots-tablet-10inch/tablet_10in_*.png` |
| **App Icon Foreground Source** | 1:1 (1024px) | High-Quality JPEG | `app/src/main/res/drawable-nodpi/ic_land_calc_art.jpg` |

> **Note:** The phone/tablet screenshots in `store-assets/` are pre-release mockups
> and do not match the shipped v5 UI. They must be re-captured from the real app
> (including the AdMob banner) before publishing. See `docs/PLAY-STORE-PUBLISHING.md`.

---

## 2. Color Palette

- **Primary Green (Brand / App Bar):** `#1B5E20` (Deep Forest Green)
- **Primary Green Light:** `#4C8C4A`
- **Primary Green Dark:** `#003300`
- **Secondary Green (Emerald):** `#2E7D32`
- **Tertiary Clay (Earthy Accent):** `#795548`
- **Light Background:** `#F8FAF7`
- **Light Surface:** `#FFFFFF`
- **Light Surface Variant:** `#E8EFE5`
- **Light Primary Container:** `#DCF0DC`
- **Dark Background:** `#111411`
- **Dark Surface:** `#191D19`
- **Dark Primary:** `#81C784`
- **Gold Badge (Highlights):** bg `#FFF8E1` / text `#E65100`

---

## 3. Adaptive Launcher Icons

- **Foreground Layer:** `app/src/main/res/drawable/ic_launcher_foreground.xml` (Centered 66dp artwork within 108dp canvas)
- **Background Layer:** `app/src/main/res/drawable/ic_launcher_background.xml` (Solid Deep Green `#1B5E20`)
- **Adaptive Wrapper:** `app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml` and `ic_launcher_round.xml`
- **Monochrome:** Intentionally omitted — the foreground is a full-color photo without an alpha channel, which would render as a solid blob as a themed icon. Themed-icon users fall back to the color icon.
- **Legacy Raster Mipmaps:** Pre-rendered at mdpi (48px), hdpi (72px), xhdpi (96px), xxhdpi (144px), and xxxhdpi (192px) for API 24–25 devices.
