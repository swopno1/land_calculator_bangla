# App Store Optimization (ASO) Strategy & Metadata for Bangladesh

**App Name:** জমির হিসাব (Land Calculator BD)  
**Package Name:** `com.vivescriptsolutions.jomirhisab`  
**Publisher:** ViveScript Solutions (https://www.vivescriptsolutions.com/)  
**Primary Market:** Bangladesh (Bengali-first)

---

## ১. Google Play Title Evaluation

Google Play allows up to **30 characters** for the App Title. Titles must avoid keyword stuffing, emojis, ALL CAPS, or promotional buzzwords ("Free", "Best", "#1").

### Candidate Titles:

1. **জমির হিসাব – Land Calculator BD**  
   - Character count: 32 characters (❌ Exceeds 30 char limit!)
   - Analysis: While informative, it violates Google Play's 30-character limit.

2. **জমির হিসাব (Land Calculator)**  
   - Character count: 27 characters (✅ Compliant, clear, natural)
   - Analysis: Clean, easily readable, establishes Bengali identity while allowing English search discovery.

3. **জমির হিসাব – Land Calculator**  
   - Character count: 29 characters (✅ Compliant, professional)
   - Analysis: Uses standard en-dash, highly relevant, strikes optimal balance between local and English search terms.

### 🏆 Recommended App Title:
`জমির হিসাব – Land Calculator` (29 characters)  
*Alternative for home launcher label:* `জমির হিসাব` (১০ characters)

---

## ২. Short Description (Google Play)

Maximum limit: **80 characters**. Must communicate core purpose, key units, and offline capability without keyword stuffing.

### Bengali (Default):
`শতাংশ, কাঠা, বিঘা ও একরের সহজ হিসাব। ফ্রি, অফলাইনে চলে।`  
- Character count: **৫৫ অক্ষর** (Well within 80 characters limit)
- Source of truth: `store-assets/play-store-metadata/short-description-bengali.txt`

### English:
`Land calculator for BD: Decimal, Katha, Bigha & Acre. Free, works offline.`  
- Character count: **74 characters** (Compliant)
- Source of truth: `store-assets/play-store-metadata/short-description-english.txt`

---

## ৩. Full Description (Google Play)

Canonical texts live in the metadata files (single source of truth — do not
duplicate them here):
- Bengali: `store-assets/play-store-metadata/full-description-bengali.txt`
- English: `store-assets/play-store-metadata/full-description-english.txt`

Listing honesty rules (enforced in review):
- Never claim "ad-free" — the app shows AdMob banner + interstitial ads.
- Never claim "zero internet requirement" — only the calculation engine is
  offline; ads require connectivity.
- Conversion figures must match `docs/LAND-MEASUREMENT-STANDARD.md`.

---

## ৪. Keyword Research & Search Intent (Hypotheses)

### Primary Bengali Keywords:
- জমির হিসাব
- জমির মাপ
- জমি ক্যালকুলেটর
- জমির হিসাব ক্যালকুলেটর
- জমির মাপের হিসাব

### Secondary Bengali Units:
- শতাংশ
- শতক
- কাঠা
- বিঘা
- একর
- ডেসিমেল

### English Search Intent in BD:
- land calculator bd
- bangladesh land measurement
- land calculator bangladesh
- katha to decimal bd
- bigha to acre converter
- jomir hisab
