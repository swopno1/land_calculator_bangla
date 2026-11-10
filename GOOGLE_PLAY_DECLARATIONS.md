# Google Play Console Declarations & Approval Master Reference

**App Name:** জমির হিসাব – Land Calculator  
**Package Name:** `com.vivescriptsolutions.jomirhisab`  
**Current Release:** Version Code `5`, Version Name `5.0`  
**Developer:** ViveScript Solutions  
**Support Email:** support@vivescriptsolutions.com  
**Console Contact Email:** amirhossain.limon@gmail.com  
**Privacy Policy URL:** https://www.vivescriptsolutions.com/en/apps/jomirhisab/privacy  
**Terms of Service URL:** https://www.vivescriptsolutions.com/en/apps/jomirhisab/terms

---

## Quick Navigation for Play Console Forms
1. [App Access / Login Credentials](#1-app-access)
2. [Ads Declaration](#2-ads-declaration)
3. [Advertising ID](#3-advertising-id)
4. [Content Rating Questionnaire (IARC)](#4-content-rating-questionnaire)
5. [Target Audience & Content](#5-target-audience--content)
6. [News / COVID-19 / Financial / Government Apps](#6-special-category-declarations)
7. [Data Safety Form Questionnaire](#7-data-safety-form)
8. [Permissions Declarations](#8-permissions-declarations)
9. [Reviewer Testing & Verification Guidance](#9-reviewer-testing-guidance)
10. [Copy-Paste Rejection Appeal Templates](#10-copy-paste-appeal-templates)

---

## 1. App Access
**Question:** Is access to any part of your app restricted?  
**Selection:**  
`All functionality is available without special access`  
**Reviewer Notes (if requested):**  
> *"জমির হিসাব is a single-screen offline calculator. It requires no login credentials, username, password, subscription, or account. Reviewers can access 100% of the app immediately upon launch."*

---

## 2. Ads Declaration
**Question:** Does your app contain advertising?  
**Selection:**  
`Yes, my app contains ads`  
**Explanation for reviewers:** *"Google AdMob banner pinned to the bottom of the screen plus one interstitial after the user taps Share (45-second cooldown). Ads are gated by Google UMP consent — users who decline see no ads. Debug builds use Google demo units; release builds use production units ca-app-pub-5222053984568989/8436765853 (banner) and ca-app-pub-5222053984568989/8246948185 (interstitial)."*

---

## 3. Advertising ID
**Question:** Does your app use advertising ID?  
**Selection:** `Yes`  
**Purpose:** `Advertising or marketing` (Google AdMob SDK).

---

## 4. Content Rating Questionnaire (IARC)
- **Category:** Utility, Productivity, Communication, or Other
- **Does the app share user-provided personal info with third parties?** `No` (first-party; AdMob declared separately under Data Safety)
- **Does the app share physical location with other users?** `No`
- **Does the app allow users to purchase digital goods?** `No`
- **Resulting Rating:** `Everyone` / `PEGI 3` / `USK 0`

---

## 5. Target Audience & Content
- **Target Age Group:** `18 and older` (or `13 and older`)
- **Could the store listing unintentionally appeal to children?** `No`

---

## 6. Special Category Declarations
- **COVID-19 Contact Tracing or Status App:** `No`
- **Government App:** `No` (independent utility; in-app disclaimer states it is not a government/ministry app)
- **Financial Services / Crypto / Loans:** `No` (geometric area converter only)
- **News App:** `No`
- **Health / Medical / Health Connect App:** `No`

---

## 7. Data Safety Form

### Overview
- **Data Collection:** `Yes` (Google Mobile Ads SDK: Device IDs + Diagnostics for advertising).
- **Data Sharing:** `Yes` (with Google for ad serving/reporting; no first-party sharing).
- **Data Encryption in Transit:** `Yes` (AdMob SDK uses HTTPS).
- **Data Deletion:** `No` (no accounts or first-party stored data; Ad ID is user-resettable in Android Settings).

### Data Types Declaration
| Data Category | Data Type | Collected | Shared | Ephemeral | Purpose |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Device or other IDs** | Device or other IDs (Ad ID) | Yes (AdMob SDK) | Yes (Google) | Yes | Advertising or marketing |
| **App info and performance** | Diagnostics / Crash logs | Yes (AdMob SDK) | Yes (Google) | No | Advertising / Analytics (SDK-internal) |
| **Location** | Precise or Approximate Location | **No** | No | N/A | None (no location permission) |
| **Personal Info** | Name / Email / Phone | **No** | No | N/A | None (no registration) |
| **Photos & Videos / Audio** | Media | **No** | No | N/A | None (no camera/mic/storage) |

---

## 8. Permissions Declarations

### 8.1 Internet (`INTERNET`)
- **Declaration Statement:**
```text
Used solely to load Google AdMob banner and interstitial ads. The land conversion engine runs 100% on-device and works in airplane mode; no calculation data is ever transmitted.
```

### 8.2 Network State (`ACCESS_NETWORK_STATE`)
- **Declaration Statement:**
```text
Used by the Google Mobile Ads SDK to check connectivity before requesting ads.
```

### 8.3 Advertising ID (`com.google.android.gms.permission.AD_ID`)
- **Declaration Statement:**
```text
Used by the Google Mobile Ads SDK for ad serving and reporting. Users can reset or delete their advertising ID in Android Settings.
```

---

## 9. Reviewer Testing Guidance

```text
STEP-BY-STEP REVIEWER TESTING INSTRUCTIONS:

1. Launch জমির হিসাব (Land Calculator BD) on any test device or emulator.
2. A UMP consent form may appear; accept or dismiss it (dismissing disables ads).
3. The input field defaults to "1" Decimal with live conversions visible.
4. Type any number (e.g. 250) and tap unit chips to change the input unit; results update instantly.
5. Toggle the top-bar language switch (বাং / EN) to verify both UI languages.
6. Tap any result copy icon, "Copy All", and the Share icon (interstitial may show once first).
7. Tap the Info icon for the measurement standard reference and legal disclaimer.
8. Enable airplane mode to verify calculations work fully offline (ads will not load).
```

---

## 10. Copy-Paste Appeal Templates

### Appeal for Data Safety / Privacy Inquiry:
> **Subject:** Clarification on Data Safety Declaration - `com.vivescriptsolutions.jomirhisab`  
> **Dear Google Play Review Team,**  
> জমির হিসাব is a single-screen offline land unit converter. It collects no first-party data: no accounts, no analytics, no crash SDK, no servers — conversions run in on-device memory. The only collection comes from the embedded Google Mobile Ads SDK (Advertising ID + diagnostics), declared as Device IDs + Diagnostics, encrypted in transit, and gated by Google UMP consent so declined users never load ads.

### Appeal for Ads / Interstitial Inquiry:
> **Subject:** Clarification on Interstitial Ads - `com.vivescriptsolutions.jomirhisab`  
> **Dear Google Play Review Team,**  
> The single interstitial in জমির হিসাব appears only after an explicit user tap on Share, shows at most once per 45 seconds, and the pending share action always completes after dismissal. The persistent banner sits in a fixed bottom bar and never overlaps interactive content. Both formats are gated by UMP consent.

---

## 11. EU Digital Services Act (DSA) & Trader Status Declaration

Google Play requires all developers distributing in the European Economic Area (EEA) to declare their trader status under **Policy and programs > Trader status**:

- **Recommended Selection:** **"Yes, I am a trader"** (for ViveScript Solutions)  
  - Legal Name: `ViveScript Solutions`
  - Email: `support@vivescriptsolutions.com`
  - Website: `https://www.vivescriptsolutions.com/en/apps/jomirhisab`
  - Address: Registered corporate address of ViveScript Solutions.
- See full legal analysis and GDPR user deletion audit in `EU_TRADE_POLICY_COMPLIANCE.md`.
